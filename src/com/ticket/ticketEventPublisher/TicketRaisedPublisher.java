package com.ticket.ticketEventPublisher;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import com.ticket.ticketEvents.TicketRaise;


public class TicketRaisedPublisher implements ApplicationEventPublisherAware {
	
	private ApplicationEventPublisher publisher;

	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	public void publish(TicketRaise ticketRaise) {
		
		publisher.publishEvent(ticketRaise);
		
	}
	
}
