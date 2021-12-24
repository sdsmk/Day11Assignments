package com.method.spring.security.web;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
class BankComponent {
	@RolesAllowed({"MANAGER"})
	public String Loan() {
		// TODO Auto-generated method stub
		//return "LOAN: Successfully granted to you!";
		return LoanService.loanservice();
	}
}
@RestController

public class BankController {
	@Autowired
	BankComponent bank;
	
	@RequestMapping("/bcontrol/loanservice")
    public String loanservice() {
		return bank.Loan();
	}

}