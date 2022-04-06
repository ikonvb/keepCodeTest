package com.konstantinbulygin.onlinestore.controllers.restapi;

import com.konstantinbulygin.onlinestore.model.Role;
import com.konstantinbulygin.onlinestore.model.restmodel.MessageResponse;
import com.konstantinbulygin.onlinestore.service.RoleRepoService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
@ApiModel("Test controller, enable for all")
public class TestController {

    @Autowired
    RoleRepoService roleRepoService;

    @GetMapping("/all")
    @ApiOperation("Test method")
    public String allAccess() {
        return "public API";
    }

    @PostMapping(value = "/add/role", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Method to add role to the data base")
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        Role newRole = new Role(role.getRoleName());
        roleRepoService.save(newRole);
        return ResponseEntity.ok(new MessageResponse(newRole.getRoleName().name() + " added"));
    }
}