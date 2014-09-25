package com.ticket.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.ticket.ticketDaoImpl.TicketReturnDaoImpl;
import com.ticket.ticketEventPublisher.TicketReturnedPublisher;
import com.ticket.ticketEvents.Ticket;
import com.ticket.ticketEvents.TicketReturn;

public class TicketReturnService {
	@Autowired
	private TicketReturnDaoImpl ticketReturnDaoImpl;
	@Autowired
	private TicketReturnedPublisher ticketReturnedPublisher;
	private Ticket ticket;
	public void display(int empId, String category, int groupId) {
		List<Ticket> al;
		al = ticketReturnDaoImpl.display(empId, category,groupId);
		for (int i = 0; i < al.size(); i++) {
			ticket = al.get(i);
		}
		returnTicket(empId, ticket, groupId);
	}

	public void returnTicket(int empId, Ticket ticket, int groupId) {
		TicketReturn ticketReturn = new TicketReturn(this);
		ticketReturn.setEmpId(ticket.getEmpid());
		ticketReturn.setGroupId(ticket.getGroupid());
		ticketReturn.setTicket(ticket);
		ticketReturnedPublisher.publish(ticketReturn);
	}
}
