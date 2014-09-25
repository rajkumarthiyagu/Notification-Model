package com.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ticket.ticketDaoImpl.TicketResolveDaoImpl;
import com.ticket.ticketEventPublisher.TicketResolvePublisher;
import com.ticket.ticketEvents.Ticket;
import com.ticket.ticketEvents.TicketResolve;



public class TicketResolveService {
	@Autowired
	private TicketResolvePublisher ticketResolvePublisher;
	@Autowired
	private TicketResolveDaoImpl ticketResolveDaoImpl;
	private Ticket ticket;
	public void display(int empId, String category, int eventId) {
		List<Ticket> al;
		al = ticketResolveDaoImpl.display(empId, category,eventId);
		for (int i = 0; i < al.size(); i++) {
			ticket = al.get(i);
		}
		cancelTicket(empId, ticket, eventId);
				
	}

	public void cancelTicket(int empId, Ticket ticket, int eventId) {
		TicketResolve ticketResolve = new TicketResolve(this);
		ticketResolve.setEmpId(ticket.getEmpid());
		ticketResolve.setTicket(ticket);
		ticketResolve.setEventId(ticket.getTicketId());;
		System.out.println(ticketResolve.getEventId());
		ticketResolvePublisher.publish(ticketResolve);
	}
}
