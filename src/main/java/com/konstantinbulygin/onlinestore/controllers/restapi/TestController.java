package com.konstantinbulygin.onlinestore.controllers.restapi;

import com.konstantinbulygin.onlinestore.model.Role;
import com.konstantinbulygin.onlinestore.model.restmodel.MessageResponse;
import com.konstantinbulygin.onlinestore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {

    @Autowired
    RoleService roleService;

    @GetMapping("/all")
    public String allAccess() {
        return "public API";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        return "user API";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "admin API";
    }

    @PostMapping(value = "/addrole", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        roleService.save(role);
        return ResponseEntity.ok(new MessageResponse(role.getRoleName().name() + " role added"));
    }
}