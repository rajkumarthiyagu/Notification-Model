package com.ticket.ticketDaoImpl;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.ticket.mail.TicketMail;
import com.ticket.mapper.ExtractMapper;
import com.ticket.mapper.FewMapper;
import com.ticket.mapper.NewMapper;
import com.ticket.mapper.TableMapper;
import com.ticket.ticketDao.TicketRaisedDao;
import com.ticket.ticketEvents.MailDetail;
import com.ticket.ticketEvents.TicketEventMail;
import com.ticket.ticketEvents.Ticket;
import com.ticket.ticketEvents.TicketRaise;

public class TicketRaisedDaoImpl implements TicketRaisedDao {
	@Autowired
	private TicketMail ticketMail;
	private DataSource jdbcDataSource;
	private JdbcTemplate jdbcTemplate;

	@Override
	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcDataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(jdbcDataSource);
		System.out.println(this.jdbcDataSource);
	}

	public List<Ticket> display(int viewempId) {
		List<Ticket> al = new ArrayList<>();
		List<Ticket> send = new ArrayList<>();
		List<Ticket> newadder = new ArrayList<>();
		String sequel= "select * from r_eventDetails where empid = "+viewempId+"";
		al = jdbcTemplate.query(sequel , new TableMapper());
		for(int i  =0 ;i<al.size();i++)
		{
			Ticket ticket  = al.get(i);
			String sql = "select * from RaiseTicket where category = '"+ticket.getCategory()+"' and sub_Category = '"+ticket.getSubCategory()+"'";
			send = jdbcTemplate.query(sql, new ExtractMapper());
			newadder.addAll(send);
		}
		System.out.println(al.toString());
		return newadder;
	}

	@Override
	public void createTicket(TicketRaise ticketRaise) {
		// TODO Auto-generated method stub
		String checkSql;
		Integer groupId;
		Integer employeeId;
		String newSql;
		String fewSql;
		int channel;
	
		if (ticketRaise.getTicket().getCategory().equalsIgnoreCase("HRGROUP")
				|| ticketRaise.getTicket().getCategory()
						.equalsIgnoreCase("ITGROUP")) {
			checkSql = "select groupId from r_eventDetails where category='"
					+ ticketRaise.getTicket().getCategory()
					+ "'and sub_Category = '"
					+ ticketRaise.getTicket().getSubCategory() + "'";
			groupId = jdbcTemplate.queryForInt(checkSql);
			
			newSql = "select e.empid,e.firstname,e.emailid,u.password from admin_Employee e  join  admin_User u on e.empid = u.empid join admin_DepartmentEmployee d on e.empid = d.empid where d.deptid = "+groupId+""; 
			mailMethod(ticketRaise, newSql);
		//	printMethod(ticketRaise, newSql);
			returnMail(ticketRaise);

		} else {
			checkSql = "select empId from r_eventDetails where category='"
					+ ticketRaise.getTicket().getCategory()
					+ "'and sub_Category = '"
					+ ticketRaise.getTicket().getSubCategory() + "'";
			employeeId = jdbcTemplate.queryForInt(checkSql);
			fewSql = "select channel from r_eventDetails where empId = '"
					+ employeeId + "'and sub_Category = '"
					+ ticketRaise.getTicket().getSubCategory() + "'";
			channel = jdbcTemplate.queryForInt(fewSql);
			System.out.println("employee:"+employeeId + "channel"+channel);
			newSql = "select e.empid,e.firstname,e.emailid,u.password from admin_Employee e join admin_User u on e.empid=u.empid where e.empid= "+employeeId+"";
			if (channel == 1) {
				mailMethod(ticketRaise, newSql);
				returnMail(ticketRaise);
			} else {
				//printMethod(ticketRaise, newSql);
				//mailMethod(ticketRaise, newSql);
			returnMail(ticketRaise);
			}
		}

	}

	

/*	private void printMethod(TicketRaise ticketRaise, String console) {
		List<TicketEventMail> mailList;
		String name;
		Integer empId;
		JSONObject obj = new JSONObject();
		mailList = jdbcTemplate.query(console, new NewMapper());
		for (int j = 0; j < mailList.size(); j++) {
			name = mailList.get(j).getUsername();
			empId = mailList.get(j).getEmpId();
			obj.put("name", name);
			obj.put("empId", empId);
			obj.put("sub", ticketRaise.getTicket().getSubject());
			obj.put("query", ticketRaise.getTicket().getQuery());
			System.out.println(obj);
		}
		if (ticketRaise.getTicket().getGroupId() == 0) {
			returnMail(ticketRaise);
		}
	}*/

	@Override
	public void returnMail(TicketRaise ticketRaise) {
		Ticket ticket = new Ticket();
		String newform = null;
		List<Ticket> sd;
		List<TicketEventMail> mailList;
		List<TicketEventMail> al;
		MailDetail mailDetail = new MailDetail();
		String username;
		String password;
		String sqlFirst = "select * from RaiseTicket where category='"
				+ ticketRaise.getTicket().getCategory()
				+ "'and sub_Category = '"
				+ ticketRaise.getTicket().getSubCategory() + "'";
		sd = jdbcTemplate.query(sqlFirst, new ExtractMapper());
		for (int i = 0; i < sd.size(); i++) {
			ticket = sd.get(i);
		}
		newform = "select empid from RaiseTicket where ticketid ="
				+ ticket.getTicketId() + " ";
		int view = jdbcTemplate.queryForInt(newform);
		String newsql = "select e.empid,e.firstname,e.emailid,u.password from admin_Employee e join admin_User u on e.empid=u.empid where e.empid= "+view+"";
			
		newform = "select empId from r_eventDetails where  category='"
				+ ticketRaise.getTicket().getCategory()
				+ "'and sub_Category = '"
				+ ticketRaise.getTicket().getSubCategory() + "'";
		int emp = jdbcTemplate.queryForInt(newform);
		String sql = "select e.empid,e.firstname,e.emailid,u.password from admin_Employee e join admin_User u on e.empid=u.empid where e.empid= "+emp+"";
		al = jdbcTemplate.query(sql, new FewMapper());
	
		mailList = jdbcTemplate.query(newsql, new NewMapper());
		String to = null;

		
		for (int i = 0; i < al.size(); i++) {
			
			username = al.get(i).getMailId();
			password = al.get(i).getPassword();
			mailDetail.setFrom(username);
			mailDetail.setPassword(password);
			for (int j = 0; j < mailList.size(); j++) {
				to = mailList.get(j).getMailId();
				String newer = mailList.get(j).getUsername();
				System.out.println(to);
			
				mailDetail.setUsername(newer);
			}
			mailDetail.setTo(to);
		}
		ticketMail.returnMail(mailDetail, ticket);
	}

	private void mailMethod(TicketRaise ticketRaise, String newSql) {
		List<TicketEventMail> mailList;
		List<TicketEventMail> al;
		String email;
		String name;
		String username;
		String password;
		MailDetail mailDetail = new MailDetail();
		String sql =  "select e.empid,e.firstname,e.emailid,u.password from admin_Employee e join admin_User u on e.empid = u.empid where e.empid = "+ticketRaise.getTicket().getEmpid()+"";	
		al = jdbcTemplate.query(sql, new FewMapper());
		mailList = jdbcTemplate.query(newSql, new NewMapper());
		for (int i = 0; i < al.size(); i++) {
			username = al.get(i).getMailId();
			password = al.get(i).getPassword();
			
		for (int j = 0; j < mailList.size(); j++) {
			email = mailList.get(j).getMailId();
			name = mailList.get(j).getUsername();
			
				mailDetail.setFrom(username);
				mailDetail.setPassword(password);
				mailDetail.setUsername(name);
				mailDetail.setTo(email);
				/*System.out.println(username);
				System.out.println(password);
				System.out.println(name);
				System.out.println(email);*/
			}
			
			mailDetail.setSubject(ticketRaise.getTicket().getSubCategory());
			mailDetail.setMessage(ticketRaise.getTicket().getQuery());
			ticketMail.createMail(ticketRaise.getTicket(), mailDetail);
		}
		
	}
}
