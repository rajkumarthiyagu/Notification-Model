package com.ticket.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.ticket.ticketDaoImpl.TicketRaisedDaoImpl;
import com.ticket.ticketEventPublisher.TicketRaisedPublisher;
import com.ticket.ticketEvents.Ticket;
import com.ticket.ticketEvents.TicketRaise;

public class TicketRaisedService {
	@Autowired
	private TicketRaisedPublisher ticketRaisedPublisher;
	@Autowired
	private TicketRaisedDaoImpl ticketRaisedDaoImpl;

	public void createRaiseEvent(int empId, int groupId, String category,
			String subCategory, String subject, String query) {
		TicketRaise ticket = convertTicketRaiseObject(empId, groupId, category,
			subCategory, subject, query);
		 ticketRaisedPublisher.publish(ticket);
	}

	public TicketRaise convertTicketRaiseObject(int empId, int groupId,
			String category, String subCategory, String subject, String query) {
		Ticket ticket = new Ticket();
		TicketRaise ticketRaise = new TicketRaise(this);
		ticket.setCategory(category);
		ticket.setSubCategory(subCategory);
		ticket.setSubject(subject);
		ticket.setQuery(query);
		ticket.setEmpid(empId);
		ticket.setGroupid(groupId);
		ticketRaise.setTicket(ticket);
		return ticketRaise;
	}

	public List<Ticket> display(int viewempId) {
		List<Ticket> al = new ArrayList<>();
		al = ticketRaisedDaoImpl.display(viewempId);
		return al;
	}


}
