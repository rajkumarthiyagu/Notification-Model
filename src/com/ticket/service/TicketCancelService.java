package com.ticket.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.ticket.ticketDaoImpl.TicketCancelDaoImpl;
import com.ticket.ticketEventPublisher.TicketCanceledPublisher;
import com.ticket.ticketEvents.TicketCancel;
import com.ticket.ticketEvents.Ticket;

public class TicketCancelService {
	@Autowired
	private TicketCanceledPublisher ticketCanceledPublisher;
	@Autowired
	private TicketCancelDaoImpl ticketCancelDaoImpl;
	private Ticket ticket;
	public void display(int empId, String category, int eventId) {
		List<Ticket> al;
		al = ticketCancelDaoImpl.display(empId, category,eventId);
		for (int i = 0; i < al.size(); i++) {
			ticket = al.get(i);
		}
		cancelTicket(empId, ticket, eventId);
	}

	public void cancelTicket(int empId, Ticket ticket, int eventId) {
		TicketCancel ticketCancel = new TicketCancel(this);
		ticketCancel.setEmpId(ticket.getEmpid());
		ticketCancel.setTicket(ticket);
		ticketCancel.setEventId(ticket.getTicketId());;
		System.out.println(ticketCancel.getEventId());
		ticketCanceledPublisher.publish(ticketCancel);
	}
}
