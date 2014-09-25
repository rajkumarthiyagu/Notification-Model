package com.ticket.ticketEventHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.ticket.ticketDaoImpl.TicketReturnDaoImpl;
import com.ticket.ticketEvents.TicketReturn;

public class TicketReturnedHandler implements ApplicationListener<TicketReturn> {

	@Autowired
	private TicketReturnDaoImpl ticketReturnDaoImpl;

	@Override
	public void onApplicationEvent(TicketReturn ticketReturn) {
		// TODO Auto-generated method stub

		ticketReturnDaoImpl.returnTicket(ticketReturn);
	}

}
