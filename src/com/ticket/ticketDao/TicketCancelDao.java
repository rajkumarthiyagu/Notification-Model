package com.ticket.ticketDao;

import javax.sql.DataSource;

import com.ticket.ticketEvents.TicketCancel;

public interface TicketCancelDao {

	public void cancelTicket(TicketCancel ticketCancel);
	public void setDataSource(DataSource dataSource);
}
