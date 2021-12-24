package com.method.spring.security.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("anon1")
    @PermitAll
    public String anonymously() {
        return "Hello World!!!";
    }

    @GetMapping("admin-role")
    @RolesAllowed({"ROLE_ADMIN"})
    public String hasRole() {
        return "Hello World!!!";
    }

}
