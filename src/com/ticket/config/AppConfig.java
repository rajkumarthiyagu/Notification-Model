package com.ticket.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.ticket.mail.TicketMail;
import com.ticket.service.TicketAssignedToService;
import com.ticket.service.TicketCancelService;
import com.ticket.service.TicketRaisedService;
import com.ticket.service.TicketResolveService;
import com.ticket.service.TicketReturnService;
import com.ticket.ticketDaoImpl.TicketAssigntoDaoImpl;
import com.ticket.ticketDaoImpl.TicketCancelDaoImpl;
import com.ticket.ticketDaoImpl.TicketRaisedDaoImpl;
import com.ticket.ticketDaoImpl.TicketResolveDaoImpl;
import com.ticket.ticketDaoImpl.TicketReturnDaoImpl;
import com.ticket.ticketEventHandler.TicketAssignedToHandler;
import com.ticket.ticketEventHandler.TicketCanceledHandler;
import com.ticket.ticketEventHandler.TicketRaisedHandler;
import com.ticket.ticketEventHandler.TicketResolveHandler;
import com.ticket.ticketEventHandler.TicketReturnedHandler;
import com.ticket.ticketEventPublisher.TicketAssignedToPublisher;
import com.ticket.ticketEventPublisher.TicketCanceledPublisher;
import com.ticket.ticketEventPublisher.TicketRaisedPublisher;
import com.ticket.ticketEventPublisher.TicketResolvePublisher;
import com.ticket.ticketEventPublisher.TicketReturnedPublisher;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.ticket*", "com.ticket.config.*",
		"com.ticket.controller.*", "com.ticketservice.*" })
public class AppConfig extends WebMvcConfigurerAdapter {
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public DataSource setDataSource() throws Exception {

		DriverManagerDataSource jdbcDataSource = new DriverManagerDataSource();
		jdbcDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		jdbcDataSource.setUrl("jdbc:mysql://qhix-mysql:3306/training");
		jdbcDataSource.setUsername("trainee");
		jdbcDataSource.setPassword("trainee");
		System.out.println(jdbcDataSource);
		return jdbcDataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() throws Exception {
		return new JdbcTemplate(setDataSource());
	}

	// -------------------Ticket Raise-----------------
	@Bean
	public TicketRaisedDaoImpl ticketRaisedDaoImpl() {
		return new TicketRaisedDaoImpl();
	}
	
	@Bean
	public TicketRaisedPublisher ticketRaisedPublisher() {
		return new TicketRaisedPublisher();
	}

	@Bean
	public TicketRaisedService ticketRaisedService() {
		return new TicketRaisedService();
	}

	@Bean
	public TicketRaisedHandler ticketRaisedHandler() {
		return new TicketRaisedHandler();
	}

	// -------------------Ticket Return----------------------

	@Bean
	public TicketReturnedPublisher ticketReturnedPublisher() {
		return new TicketReturnedPublisher();
	}

	@Bean
	public TicketReturnedHandler ticketReturnedHandler() {
		return new TicketReturnedHandler();
	}

	@Bean
	public TicketReturnDaoImpl ticketReturnDao() {
		return new TicketReturnDaoImpl();
	}

	@Bean
	public TicketReturnService ticketReturnService() {
		return new TicketReturnService();
	}

	// ----------------------Ticket Cancel----------------

	@Bean
	public TicketCancelDaoImpl ticketCancelDao() {
		return new TicketCancelDaoImpl();

	}

	@Bean
	public TicketCanceledPublisher ticketCanceledPublisher() {
		return new TicketCanceledPublisher();
	}

	@Bean
	public TicketCanceledHandler ticketCanceledHandler() {
		return new TicketCanceledHandler();
	}

	@Bean
	public TicketCancelService ticketCancelService() {
		return new TicketCancelService();
	}

	// ------------------------Ticket Assigned--------------
	@Bean
	public TicketAssigntoDaoImpl ticketAssignDao() {
		return new TicketAssigntoDaoImpl();
	}

	@Bean
	public TicketAssignedToPublisher ticketAssignedToPublisher() {
		return new TicketAssignedToPublisher();
	}

	@Bean
	public TicketAssignedToHandler ticketAssignedToHandler() {
		return new TicketAssignedToHandler();
	}

	@Bean
	public TicketAssignedToService ticketAssignedToService() {
		return new TicketAssignedToService();
	}
	//----------------Ticket Resolve------------------------
	@Bean
	public TicketResolveDaoImpl ticketResolveDaoImpl()
	{
		return new TicketResolveDaoImpl();
	}
	@Bean
	public TicketResolvePublisher ticketResolvePublisher()
	{
		return new TicketResolvePublisher();
	}
	@Bean
	public TicketResolveHandler ticketResolveHandler(){
		return new TicketResolveHandler();
	}
	@Bean
	public TicketResolveService ticketResolveService()
	{
		return new TicketResolveService();
	}
	// ---------------Ticket Mail---------------------------
	@Bean
	public TicketMail ticketMail() {
		return new TicketMail();
	}

}