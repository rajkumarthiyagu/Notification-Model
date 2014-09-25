package com.ticket.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ticket.ticketEvents.TicketEventMail;

@Repository
public class NewMapper implements RowMapper<TicketEventMail> {

	@Override
	public TicketEventMail mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		TicketEventMail ticketEventMail = new TicketEventMail();
		ticketEventMail.setEmpId(rs.getInt(1));
		ticketEventMail.setUsername(rs.getString(2));
		ticketEventMail.setPassword(rs.getString(4));
		ticketEventMail.setMailId(rs.getString(3));
		return ticketEventMail;

	}

}
