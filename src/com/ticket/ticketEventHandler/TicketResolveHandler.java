package com.ticket.ticketEventHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.ticket.ticketDaoImpl.TicketResolveDaoImpl;

import com.ticket.ticketEvents.TicketResolve;


public class TicketResolveHandler implements ApplicationListener<TicketResolve>{

	@Autowired
	private TicketResolveDaoImpl ticketResolveDaoImpl;
	@Override
	public void onApplicationEvent(TicketResolve ticketResolve) {
		// TODO Auto-generated method stub
		ticketResolveDaoImpl.resolveTicket(ticketResolve);
	}

}
