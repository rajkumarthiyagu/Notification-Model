package com.ticket.ticketDao;

import javax.sql.DataSource;

import com.ticket.ticketEvents.TicketAssign;

public interface TicketAssignDao {

	public void assignTicket(TicketAssign ticketAssign);
	public void setDataSource(DataSource dataSource);

}
