package com.ticket.ticketEvents;

import java.util.Date;

public class Ticket {
    
   
    String category;
    String subject;
    String query;
    int groupid;
    int empid;
    

    public int getGroupid() {
        return groupid;
    }


    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }


    public int getEmpid() {
        return empid;
    }


    public void setEmpid(int empid) {
        this.empid = empid;
    }


 public String getSubCategory() {
		return subCategory;
	}


	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}


String subCategory;

    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getQuery() {
        return query;
    }


    public void setQuery(String query) {
        this.query = query;
    }
    Date date;

    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }
    String priority;

    public String getPriority() {
        return priority;
    }


    public void setPriority(String priority) {
        this.priority = priority;
    }
    int ticketId;

    public int getTicketId() {
        return ticketId;
    }


    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    Date ModifiedDate;

    public Date getModifiedDate() {
        return ModifiedDate;
    }


    public void setModifiedDate(Date modifiedDate) {
        ModifiedDate = modifiedDate;
    }
}
