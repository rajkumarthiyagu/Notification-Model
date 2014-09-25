package com.ticket.ticketEventHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.ticket.ticketDaoImpl.TicketAssigntoDaoImpl;
import com.ticket.ticketEvents.TicketAssign;

public class TicketAssignedToHandler implements
		ApplicationListener<TicketAssign> {
	@Autowired
	private TicketAssigntoDaoImpl ticketAssigntoDaoImpl;

	@Override
	public void onApplicationEvent(TicketAssign ticketAssign) {
		// TODO Auto-generated method stub
		ticketAssigntoDaoImpl.assignTicket(ticketAssign);
	}

}
