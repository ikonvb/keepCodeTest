package com.konstantinbulygin.onlinestore.controllers.restapi;

import com.konstantinbulygin.onlinestore.config.jwt.JwtUtils;
import com.konstantinbulygin.onlinestore.model.User;
import com.konstantinbulygin.onlinestore.model.Role;
import com.konstantinbulygin.onlinestore.model.RoleEnum;
import com.konstantinbulygin.onlinestore.model.restmodel.JwtResponse;
import com.konstantinbulygin.onlinestore.model.restmodel.LoginRequest;
import com.konstantinbulygin.onlinestore.model.restmodel.MessageResponse;
import com.konstantinbulygin.onlinestore.model.restmodel.SignUpRequestUser;
import com.konstantinbulygin.onlinestore.service.UserDetailsImpl;
import com.konstantinbulygin.onlinestore.service.UserService;
import com.konstantinbulygin.onlinestore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(value = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                loginRequest.getUserName(),
                                loginRequest.getUserPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getUserId(),
                userDetails.getUsername(),
                roles
        ));

    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequestUser signUpRequestUser) {
        if (userService.existsByUserName(signUpRequestUser.getUserName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: username exists"));
        }

        User user = new User(signUpRequestUser.getUserName(),
                passwordEncoder.encode(signUpRequestUser.getUserPassword()), LocalDateTime.now());

        Set<String> reqRoles = signUpRequestUser.getRoles();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            Role userRole = roleService
                    .findByName(RoleEnum.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                if ("admin".equals(r)) {
                    Role adminRole = roleService
                            .findByName(RoleEnum.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleService
                            .findByName(RoleEnum.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                    roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userService.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}
