package com.method.spring.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Component
class AbcComponent{

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public String hasAdmUsrRole() {
        return "Hello World!!!";
    }
    
    @PreAuthorize("permitAll()")
    public String anonymous() {
        return "Hello World!!!";
    }
}

@RestController
@RequestMapping("owner")
public class OwnerController {
	@Autowired
	AbcComponent abc;
	
    @GetMapping("anonymous")
    public String anonymous() {
        return abc.anonymous();
    }

    @GetMapping("has-role")
    public String hasAdmUsrRole() {
        return abc.hasAdmUsrRole();
    }

}
