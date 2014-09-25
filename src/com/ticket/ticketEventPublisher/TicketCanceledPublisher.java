package com.ticket.ticketEventPublisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.ticket.ticketEvents.TicketCancel;


public class TicketCanceledPublisher implements ApplicationEventPublisherAware{
ApplicationEventPublisher publisher;
	@Override
	public void setApplicationEventPublisher(
			ApplicationEventPublisher applicationEventPublisher) {
		// TODO Auto-generated method stub
		this.publisher = applicationEventPublisher;
	}
	public void publish(TicketCancel ticketCancel) {

		publisher.publishEvent(ticketCancel);
		
	}

}
