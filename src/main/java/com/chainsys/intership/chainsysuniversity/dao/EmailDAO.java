package com.chainsys.intership.chainsysuniversity.dao;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Repository;

@Repository
public class EmailDAO {

	public void sendMail() throws EmailException {

		SimpleEmail email=new SimpleEmail();
		
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("kaviarasan150697@gmail.com", "santhamani"));
		email.setDebug(false);
		email.setHostName("smtp.gmail.com");
		email.setFrom("kaviarasan150697@gmail.com");
		email.setSubject("Course Details");
		email.setMsg("Course completed successfully");
		email.addTo("jehincastic@gmail.com");
		email.setTLS(true);
		email.send();
		System.out.println("Mail sent successfully!");
	}

}
