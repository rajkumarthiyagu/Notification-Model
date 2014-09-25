package com.ticket.ticketEvents;

import org.springframework.context.ApplicationEvent;

public class TicketRaise extends ApplicationEvent {

	public TicketRaise(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Ticket ticket;
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}


}
