package com.ticket.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.ticket.ticketDaoImpl.TicketAssigntoDaoImpl;
import com.ticket.ticketEventPublisher.TicketAssignedToPublisher;
import com.ticket.ticketEvents.TicketAssign;
import com.ticket.ticketEvents.Ticket;

public class TicketAssignedToService {
	@Autowired
	private TicketAssigntoDaoImpl ticketAssigntoDaoImpl;
	private Ticket ticket;
	public void display(int empId, String category, int assign,
			int groupId) {
		List<Ticket> al;
		al = ticketAssigntoDaoImpl.display(empId, category, groupId);
		for (int i = 0; i < al.size(); i++) {
			ticket = al.get(i);
		}
		assignTicket(empId, groupId, assign, ticket);
		//return ticket;
	}

	@Autowired
	private TicketAssignedToPublisher ticketAssignedToPublisher;

	public void assignTicket(int empId, int groupId, int assign,
			Ticket ticket) {
		TicketAssign ticketAssign = new TicketAssign(this);
		ticketAssign.setEmpId(empId);
		ticketAssign.setGroupId(groupId);
		ticketAssign.setTicket(ticket);
		ticketAssign.setAssign(assign);
		ticketAssignedToPublisher.publish(ticketAssign);
	}
}
