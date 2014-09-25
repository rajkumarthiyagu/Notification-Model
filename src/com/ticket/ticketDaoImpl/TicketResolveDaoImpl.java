package com.ticket.ticketDaoImpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.mail.TicketMail;
import com.ticket.mapper.ExtractMapper;
import com.ticket.mapper.FewMapper;
import com.ticket.mapper.NewMapper;
import com.ticket.ticketDao.TicketResolveDao;
import com.ticket.ticketEvents.MailDetail;
import com.ticket.ticketEvents.Ticket;
import com.ticket.ticketEvents.TicketEventMail;
import com.ticket.ticketEvents.TicketResolve;

public class TicketResolveDaoImpl  implements TicketResolveDao{
	@Autowired
	private TicketMail ticketMail;
	private DataSource jdbcDataSource;
	private JdbcTemplate jdbcTemplate;
	@Override
	public void resolveTicket(TicketResolve ticketResolve) {
		// TODO Auto-generated method stub
		String sql;
		String newSql;
		int empid;
		int groupid;
		if (ticketResolve.getTicket().getGroupid() == 0) {
			sql = "select empid from RaiseTicket where  ticketid ="
					+ ticketResolve.getTicket().getTicketId() + "";
			empid = jdbcTemplate.queryForInt(sql);
			newSql = "select e.empid,e.firstname,e.emailid,u.password from admin_Employee e join admin_User u on e.empid=u.empid where e.empid= "+empid+"";
		} else {
			sql = "select groupid from RaiseTicket where  ticketid ="
					+ ticketResolve.getTicket().getTicketId() + "";
			groupid = jdbcTemplate.queryForInt(sql);
			newSql = "select e.empid,e.firstname,e.emailid,u.password from admin_Employee e  join  admin_User u on e.empid = u.empid join admin_DepartmentEmployee d on e.empid = d.empid where d.deptid = "+groupid+""; 
		}
		mailMethod(ticketResolve, newSql);
		//printMethod(ticketResolve, newSql);
		System.out.println(ticketResolve.getTicket().getTicketId());
		deleteTicket(ticketResolve);
	}
	
	
	public void deleteTicket(TicketResolve ticketResolve) {

		String SQL = "delete from RaiseTicket where  ticketid ="
				+ ticketResolve.getTicket().getTicketId() + "";
		jdbcTemplate.execute(SQL);

	}
	
	/*private void printMethod(TicketResolve ticketResolve, String console) {

	List<TicketEventMail> mailList;
	String name;
	Integer empId;
	JSONObject obj = new JSONObject();
	mailList = jdbcTemplate.query(console, new NewMapper());
	for (int j = 0; j < mailList.size(); j++) {
		name = mailList.get(j).getUsername();
		empId = mailList.get(j).getEmpId();
		String sub = "Ticket Cancelled";
		String message = "The Ticket you have raised has been Cancelled";
		obj.put("name", name);
		obj.put("empId", empId);
		obj.put("sub", sub);
		obj.put("query", message);
		System.out.println(obj);
	}
}
*/
private void mailMethod(TicketResolve ticketResolve, String newSql) {
	List<TicketEventMail> mailList;
	List<TicketEventMail> al;
	MailDetail mailDetail = new MailDetail();
	String email;
	String username;
	String password;
	String name;
	String sql = "select empId from r_eventDetails where category = '"
			+ ticketResolve.getTicket().getCategory()
			+ "' and sub_Category = '"
			+ ticketResolve.getTicket().getSubCategory() + "'";
	int num = jdbcTemplate.queryForInt(sql);
	String newer ="select e.empid,e.firstname,e.emailid,u.password from admin_Employee e join admin_User u on e.empid=u.empid where e.empid= "+num+"";
	al = jdbcTemplate.query(newer, new FewMapper());
	mailList = jdbcTemplate.query(newSql, new NewMapper());
	for (int j = 0; j < mailList.size(); j++) {
		String sub = "Ticket Resolved";
		String message = "The Ticket you have raised has been resolved";
		email = mailList.get(j).getMailId();
		name = mailList.get(j).getUsername();
		System.out.println(email);
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
		ticketMail.createNewMail(ticketResolve.getTicket(), 
				mailDetail);
	}
}

@Override
@Autowired
public void setDataSource(DataSource ds) {
	this.jdbcDataSource = ds;
	this.jdbcTemplate = new JdbcTemplate(jdbcDataSource);
}

public List<Ticket> display(int empid, String category, int groupId) {
	List<Ticket> al;
	String sql;
	System.out.println(groupId);
	if (empid == 0) {
		sql = "select * from RaiseTicket where  groupid = " + groupId + " ";
	} else {
		sql = "select * from RaiseTicket where  empid = " + empid + " ";
	}
	al = jdbcTemplate.query(sql, new ExtractMapper());
	System.out.println(al);
	return al;
}

}
