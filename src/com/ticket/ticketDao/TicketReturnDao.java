package com.ticket.ticketDao;

import javax.sql.DataSource;

import com.ticket.ticketEvents.TicketReturn;

public interface TicketReturnDao {
	public void setDataSource(DataSource dataSource);
	public void returnTicket(TicketReturn ticketReturn);
	
}
