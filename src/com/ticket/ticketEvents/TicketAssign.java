package com.ticket.ticketEvents;

import org.springframework.context.ApplicationEvent;

public class TicketAssign extends ApplicationEvent {

	public TicketAssign(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int empId;
	Ticket ticket;
	int assign;
	int groupId;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public int getAssign() {
		return assign;
	}

	public void setAssign(int assign) {
		this.assign = assign;
	}
}
