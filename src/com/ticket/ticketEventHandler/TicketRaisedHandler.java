package com.ticket.ticketEventHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.ticket.ticketDaoImpl.TicketRaisedDaoImpl;
import com.ticket.ticketEvents.TicketRaise;

public class TicketRaisedHandler implements ApplicationListener<TicketRaise> {
	@Autowired
private TicketRaisedDaoImpl ticketRaisedDaoImpl;
	@Override
	public void onApplicationEvent(TicketRaise ticketRaise) {
		// TODO Auto-generated method stub
	
		ticketRaisedDaoImpl.createTicket(ticketRaise);
	}
}
