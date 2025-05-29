package com.springaop.aopdemo;

import com.springaop.aopdemo.dao.AccountDAO;
import com.springaop.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO membershipDAO) {
		return  runner -> {
			demoTheBeforeAdvice(theAccountDAO,membershipDAO);
		};
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
		//call the business methods
		Account account = new Account();
		theAccountDAO.addAccount(account, true);
		theAccountDAO.doWork();

		//call the membership methods
		theMembershipDAO.addMembership();
		theMembershipDAO.goToSleep();
		//do it again!
		System.out.println("\n let's call it again! \n");

	}
}
