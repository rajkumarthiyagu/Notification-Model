package com.ticket.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ticket.ticketEvents.Ticket;

public class TableMapper implements RowMapper<Ticket> {

	@Override
	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Ticket ticket = new Ticket();
		ticket.setCategory(rs.getString(2));
		ticket.setSubCategory(rs.getString(3));
		ticket.setEmpid(Integer.parseInt(rs.getString(4)));
		ticket.setGroupid(Integer.parseInt(rs.getString(5)));
	
		return ticket;

	}
}
