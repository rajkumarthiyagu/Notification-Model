package com.ticket.ticketEvents;

import org.springframework.context.ApplicationEvent;

public class TicketResolve extends ApplicationEvent {

	public TicketResolve(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int empId;
	int eventId;
	Ticket ticket;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
