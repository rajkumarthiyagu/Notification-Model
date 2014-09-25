package com.ticket.ticketDao;

import javax.sql.DataSource;


import com.ticket.ticketEvents.TicketRaise;

public interface TicketRaisedDao  {
	public void createTicket(TicketRaise ticketRaise);
	public void returnMail(TicketRaise ticketRaise);
	public void setDataSource(DataSource dataSource);

}
