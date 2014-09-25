package com.ticket.ticketEventHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.ticket.ticketDaoImpl.TicketCancelDaoImpl;
import com.ticket.ticketEvents.TicketCancel;


public class TicketCanceledHandler implements ApplicationListener<TicketCancel>{

	@Autowired
	private TicketCancelDaoImpl ticketCancelDaoImpl;
	@Override
	public void onApplicationEvent(TicketCancel ticketCancel) {
		// TODO Auto-generated method stub
		ticketCancelDaoImpl.cancelTicket(ticketCancel);
	}

}
