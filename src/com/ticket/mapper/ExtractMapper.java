package com.ticket.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ticket.ticketEvents.Ticket;

public class ExtractMapper implements RowMapper<Ticket> {

	@Override
	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Ticket ticket = new Ticket();
		ticket.setCategory(rs.getString("category"));
		ticket.setSubCategory(rs.getString("sub_Category"));
		ticket.setSubject(rs.getString("subject"));
		ticket.setQuery(rs.getString("query"));
		ticket.setEmpid(Integer.parseInt(rs.getString("empid")));
		ticket.setGroupid(Integer.parseInt(rs.getString("groupid")));
		ticket.setTicketId(Integer.parseInt(rs.getString("ticketId")));
		return ticket;

	}
}
