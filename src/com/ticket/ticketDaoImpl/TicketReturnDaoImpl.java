package com.ticket.ticketDaoImpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.mail.TicketMail;
import com.ticket.mapper.ExtractMapper;
import com.ticket.mapper.FewMapper;
import com.ticket.mapper.NewMapper;
import com.ticket.ticketDao.TicketReturnDao;
import com.ticket.ticketEvents.MailDetail;
import com.ticket.ticketEvents.TicketEventMail;
import com.ticket.ticketEvents.Ticket;
import com.ticket.ticketEvents.TicketReturn;

public class TicketReturnDaoImpl implements TicketReturnDao {
	private DataSource jdbcDataSource;
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private TicketMail ticketMail;

	@Override
	public void returnTicket(TicketReturn ticketReturn) {
		// TODO Auto-generated method stub

		String newSql;
		if (ticketReturn.getGroupId() != 0) {
			newSql = "select e.empid,e.firstname,e.emailid,u.password from admin_Employee e  join  admin_User u on e.empid = u.empid join admin_DepartmentEmployee d on e.empid = d.empid where d.deptid = "+ticketReturn.getGroupId()+""; 
			mailMethod(ticketReturn, newSql);
			//printMethod(ticketReturn, newSql);
		} else if (ticketReturn.getEmpId() != 0) {

			newSql = "select e.empid,e.firstname,e.emailid,u.password from admin_Employee e join admin_User u on e.empid= u.empid where e.empid= "+ticketReturn.getEmpId()+"";
			mailMethod(ticketReturn, newSql);
			//printMethod(ticketReturn, newSql);
		} else {
			System.out.println("wrong entry");
		}
	}

	/*private void printMethod(TicketReturn ticketReturn, String console) {

		List<TicketEventMail> mailList;
		String name;
		Integer empId;
		JSONObject obj = new JSONObject();
		mailList = jdbcTemplate.query(console, new NewMapper());
		for (int j = 0; j < mailList.size(); j++) {
			name = mailList.get(j).getUsername();
			empId = mailList.get(j).getEmpId();
			String sub = "Ticket returned";
			String message = "The Ticket you have raised is returned";
			obj.put("name", name);
			obj.put("empId", empId);
			obj.put("sub", sub);
			obj.put("query", message);
			obj.put("Ticket", ticketReturn.getTicket());
			System.out.println(obj);

		}
	}*/

	private void mailMethod(TicketReturn ticketReturn, String newSql) {
		List<TicketEventMail> mailList;
		List<TicketEventMail> al;
		String email;
		MailDetail mailDetail = new MailDetail();
		String username;
		String password;
		String name;
		String sql = "select empId from r_eventDetails where category = '"
				+ ticketReturn.getTicket().getCategory()
				+ "' and sub_Category = '"
				+ ticketReturn.getTicket().getSubCategory() + "'";
		int num = jdbcTemplate.queryForInt(sql);
		String newer = "select e.empid,e.firstname,e.emailid,u.password from admin_Employee e join admin_User u on e.empid=u.empid where e.empid= "+num+"";
		al = jdbcTemplate.query(newer, new FewMapper());
		mailList = jdbcTemplate.query(newSql, new NewMapper());
		for (int j = 0; j < mailList.size(); j++) {
			String sub = "Ticket returned";
			String message = "The Ticket you have raised is returned";
			email = mailList.get(j).getMailId();
			name = mailList.get(j).getUsername();
			for (int i = 0; i < al.size(); i++) {

				username = al.get(i).getMailId();
				password = al.get(i).getPassword();
				mailDetail.setFrom(username);
				mailDetail.setPassword(password);
				mailDetail.setUsername(name);
				
			}
			mailDetail.setTo(email);
			mailDetail.setSubject(sub);
			mailDetail.setMessage(message);
			
			ticketMail.createNewMail(ticketReturn.getTicket(), 
					mailDetail);
		}
	}

	@Override
	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcDataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(jdbcDataSource);
		System.out.println(this.jdbcDataSource);
	}

	public List<Ticket> display(int empid, String category, int groupId) {
		List<Ticket> al;
		String sql;
		if (groupId == 0) {
			sql = "select * from RaiseTicket where empid = " + empid + "";
		} else {
			sql = "select * from RaiseTicket where groupid = " + groupId + "";
		}
		al = jdbcTemplate.query(sql, new ExtractMapper());
		return al;
	}
}
