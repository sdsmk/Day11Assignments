package com.method.spring.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
class XyzComponent{

    @Secured("ROLE_ANONYMOUS")
    public String anonymous() {
        return "Hello World!!!";
    }

    @Secured("ROLE_ADMIN")
    public String hasAdminRole() {
        return "Hello World!!!";
    }
}

@RestController
@RequestMapping("agent")
public class AgentController {
	@Autowired
	XyzComponent xyz;
	
    @GetMapping("anonymous")
    public String anonymous() {
        return xyz.anonymous();
    }

    @GetMapping("has-role")
    public String hasAdminRole() {
        return xyz.hasAdminRole();
    }

}
