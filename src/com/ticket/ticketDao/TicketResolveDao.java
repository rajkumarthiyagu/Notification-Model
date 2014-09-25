package com.ticket.ticketDao;

import javax.sql.DataSource;


import com.ticket.ticketEvents.TicketResolve;

public interface TicketResolveDao {

	public void resolveTicket(TicketResolve ticketResolve);
	public void setDataSource(DataSource dataSource);
}
