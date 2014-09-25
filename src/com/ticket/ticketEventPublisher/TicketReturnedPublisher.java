package com.ticket.ticketEventPublisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import com.ticket.ticketEvents.TicketReturn;


public class TicketReturnedPublisher implements ApplicationEventPublisherAware{
	private ApplicationEventPublisher publisher;
	@Override
	public void setApplicationEventPublisher(
			ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}
	public void publish(TicketReturn ticketReturn) {
		
		publisher.publishEvent(ticketReturn);
		
	}

}
