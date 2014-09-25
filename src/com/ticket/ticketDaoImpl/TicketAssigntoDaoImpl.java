package com.ticket.ticketDaoImpl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.ticket.mail.TicketMail;
import com.ticket.mapper.ExtractMapper;
import com.ticket.mapper.FewMapper;
import com.ticket.mapper.NewMapper;
import com.ticket.service.TicketRaisedService;
import com.ticket.ticketDao.TicketAssignDao;
import com.ticket.ticketEvents.MailDetail;
import com.ticket.ticketEvents.TicketAssign;
import com.ticket.ticketEvents.TicketEventMail;
import com.ticket.ticketEvents.Ticket;

public class TicketAssigntoDaoImpl implements TicketAssignDao {

	@Autowired
	private TicketMail ticketMail;
	private DataSource jdbcDataSource;
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private TicketRaisedService ticketserRaisedService;
	@Autowired
	private TicketRaisedDaoImpl ticketRaisedDaoImpl;

	@Override
	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcDataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(jdbcDataSource);
	}

	@Override
	public void assignTicket(TicketAssign ticketAssign) {
		// TODO Auto-generated method stub
		String newSql;
		String fewSql;
		int channel;
		if (ticketAssign.getGroupId() !=0) {

			newSql = "select e.empid,e.firstname,e.emailid,u.password from admin_Employee e  join  admin_User u on e.empid = u.empid join admin_DepartmentEmployee d on e.empid = d.empid where d.deptid = "+ticketAssign.getGroupId()+""; 

			mailMethod(ticketAssign, newSql);
			//printMethod(ticketAssign, newSql);
		} else if (ticketAssign.getEmpId() != 0) {
			System.out.println();
			fewSql = "select channel from r_eventDetails where sub_Category = '"
					+ ticketAssign.getTicket().getSubCategory() + "' ";
			channel = jdbcTemplate.queryForInt(fewSql);
			newSql = "select e.empid,e.firstname,e.emailid,u.password from admin_Employee e join admin_User u on e.empid=u.empid where e.empid= "+ticketAssign.getAssign()+"";
			if (channel == 1) {

				mailMethod(ticketAssign, newSql);

			} else {
				mailMethod(ticketAssign, newSql);
				//printMethod(ticketAssign, newSql);
			}
		} else {
			System.out.println("wrong entry");
		}
	}

	/*private void printMethod(TicketAssign ticketAssign, String console) {

		List<TicketEventMail> mailList;
		String name;
		Integer empId;
		JSONObject obj = new JSONObject();
		mailList = jdbcTemplate.query(console, new NewMapper());
		for (int j = 0; j < mailList.size(); j++) {
			name = mailList.get(j).getUsername();
			empId = mailList.get(j).getEmpId();
			String sub = "Ticket Assign to you";
			String message = "A ticket as been assigned to you by:"
					+ ticketAssign.getEmpId() + "";
			obj.put("name", name);
			obj.put("empId", empId);
			obj.put("sub", sub);
			obj.put("query", message);
			obj.put("ticket", ticketAssign.getTicket());
			System.out.println(obj);
		}
	}*/

	private void mailMethod(TicketAssign ticketAssign, String newSql) {
		List<TicketEventMail> mailList;
		List<TicketEventMail> al;
		String email;
		MailDetail mailDetail = new MailDetail();
		String sub = "Ticket Assign to you";
		
		String username;
		String password;
		String name;
		String sql = "select empId from r_eventDetails where category = '"
				+ ticketAssign.getTicket().getCategory()
				+ "' and sub_Category = '"
				+ ticketAssign.getTicket().getSubCategory() + "'";
		int num = jdbcTemplate.queryForInt(sql);
		String message = "A ticket as been assigned to you by:"
				+ num+ "";
		String newer = "select e.empid,e.firstname,e.emailid,u.password from admin_Employee e join admin_User u on e.empid=u.empid where e.empid= "+num+"";
		al = jdbcTemplate.query(newer, new FewMapper());
		mailList = jdbcTemplate.query(newSql, new NewMapper());
		for (int j = 0; j < mailList.size(); j++) {
			email = mailList.get(j).getMailId();
			for (int i = 0; i < al.size(); i++) {
				username = al.get(i).getMailId();
				password = al.get(i).getPassword();
				name= al.get(i).getUsername();
				mailDetail.setFrom(username);
				mailDetail.setPassword(password);
				mailDetail.setUsername(name);
			}
			mailDetail.setTo(email);
			mailDetail.setSubject(sub);
			mailDetail.setMessage(message);
			ticketMail.createMail(ticketAssign.getTicket(), mailDetail);
		}
	}

	public List<Ticket> display(int empid, String category, int groupId) {
		List<Ticket> al;
		String sql;
		if (empid == 0) {
			sql = "select * from RaiseTicket where groupid =" + groupId + "";
		} else {
			sql = "select * from RaiseTicket where empid = " + empid + "";
		}
		al = jdbcTemplate.query(sql, new ExtractMapper());
		return al;
	}
}
