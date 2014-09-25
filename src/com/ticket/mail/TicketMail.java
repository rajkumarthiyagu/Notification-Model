package com.ticket.mail;

import com.ticket.ticketEvents.MailDetail;
import com.ticket.ticketEvents.Ticket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class TicketMail {

	public void createMail(Ticket ticket, MailDetail mailDetail) {
		final String username = mailDetail.getFrom();
		final String password = mailDetail.getPassword();
		Session session = session(username, password);
		System.out.println(username);
		String temp = "/templates/tempformail.vm";
		mailerMethod(ticket, mailDetail, username, session, temp);
	}

	public void createNewMail(Ticket ticket, 
			MailDetail mailDetail) {
		final String username = mailDetail.getFrom();
		final String password = mailDetail.getPassword();
		Session session = session(username, password);
		String temp = "/templates/tempforcancelassign.vm";
		mailerMethod(ticket, mailDetail, username, session, temp);
	}
	
	public VelocityEngine velocityEngine() {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		ve.init();
		return ve;
	}

	public Session session(final String username, final String password) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "q4email.chennai.qfor.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		return session;
	}

	public Map<String, Object> model(MailDetail mailDetail,Ticket ticket, String name, String query) {
		Map<String, Object> model = new HashMap<>();
		model.put("Id", name);
		model.put("category", ticket.getCategory());
		model.put("query", query);
		model.put("sub", ticket.getSubCategory());
		model.put("subject", ticket.getSubject());
		model.put("query", mailDetail.getMessage());
		model.put("subquery", ticket.getQuery());
		model.put("eventId", ticket.getTicketId());
		return model;
	}

	public void returnMail(MailDetail mailDetail, Ticket ticket) {
		final String username = mailDetail.getFrom();
		final String password = mailDetail.getPassword();
		Session session = session(username, password);
		String temp = "/templates/tempforreturn.vm";
		System.out.println(username);
		try {
				String name = mailDetail.getUsername();
				String query = ticket.getQuery();
				VelocityEngine ve = velocityEngine();
				Map<String, Object> model = model(mailDetail,ticket, name, query);
				MimeMessage mess = new MimeMessage(session);
				MimeMessageHelper message = new MimeMessageHelper(mess);
				message.setFrom(new InternetAddress(username));
				message.setTo(new InternetAddress(mailDetail.getTo()));
				message.setSubject(ticket.getSubCategory());
				String body = VelocityEngineUtils.mergeTemplateIntoString(ve, temp,
						"UTF-8", model);
				mess.setContent(body, "text/html");
				Transport.send(mess);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void mailerMethod(Ticket ticket, MailDetail mailDetail,
			final String username, Session session, String temp) {
		try {
				System.out.println(mailDetail.getUsername());
				String name = mailDetail.getUsername();
				String query = mailDetail.getMessage();
				VelocityEngine ve = velocityEngine();
				Map<String, Object> model = model(mailDetail,ticket, name, query);
				MimeMessage mess = new MimeMessage(session);
				MimeMessageHelper message = new MimeMessageHelper(mess);
				message.setFrom(new InternetAddress(username));
				message.setTo(new InternetAddress(mailDetail.getTo()));
				message.setSubject(mailDetail.getSubject());
				String body = VelocityEngineUtils.mergeTemplateIntoString(ve, temp,
						"UTF-8", model);
				mess.setContent(body, "text/html");
				Transport.send(mess);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
