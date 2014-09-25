package com.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ticket.service.TicketAssignedToService;
import com.ticket.service.TicketCancelService;
import com.ticket.service.TicketRaisedService;
import com.ticket.service.TicketResolveService;
import com.ticket.service.TicketReturnService;
import com.ticket.ticketEvents.Ticket;

@ComponentScan
@Controller
public class NotificationController {
	@Autowired
	private TicketRaisedService ticketRaisedService;
	@Autowired
	private TicketReturnService ticketReturnService;
	@Autowired
	private TicketCancelService ticketCancelService;
	@Autowired
	private TicketResolveService ticketResolveService;
	
	@RequestMapping(value = "/jsp/ne", method = RequestMethod.GET)
	public ModelAndView news(@RequestParam(value = "txt_empId") int empId,
			@RequestParam(value = "txt_groupId") int groupId,
			@RequestParam(value = "txt_deptName") String category,
			@RequestParam(value = "txt_sc") String subCategory,
			@RequestParam(value = "txt_task") String subject,
			@RequestParam(value = "txt_comm") String query) {
		ticketRaisedService.createRaiseEvent(empId, groupId, category,
				subCategory, subject, query);
		ModelAndView model = new ModelAndView("employee");
		model.addObject("title", "Employee Form");
		model.addObject("message", "Request sent to");
		model.setViewName("employee");
		return model;
	}

	@RequestMapping(value = "/jsp/hrform", method = RequestMethod.POST)
	public ModelAndView hrform(@RequestParam(value = "txt-viewEmp") int viewempId) {
		ModelAndView model = new ModelAndView();
		List<Ticket> al = ticketRaisedService.display(viewempId);
		model.addObject("form", al);
		model.setViewName("hr");

		return model;
	}

	@RequestMapping(value = "/jsp/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam(value = "txt_empId") int empId,
			@RequestParam(value = "txt_category") String category,
			@RequestParam(value = "txt_eventId") int eventId) {
		ticketCancelService.display(empId, category, eventId);
		ModelAndView model = new ModelAndView("employee");
		model.addObject("title", " Form");
		model.addObject("message", "Request sent to");
		if (category.contains("HR")) {
			model.setViewName("hr");
		} else {
			model.setViewName("it");
		}
		return model;
	}

	@RequestMapping(value = "/jsp/return", method = RequestMethod.GET)
	public ModelAndView returnTicket(
			@RequestParam(value = "txt_empId") int empId,
			@RequestParam(value = "txt_category") String category,
			@RequestParam(value = "txt_groupid") int groupId) {
		ticketReturnService.display(empId, category, groupId);
		ModelAndView model = new ModelAndView("employee");
		model.addObject("title", " Form");
		model.addObject("message", "Request sent to");
		model.setViewName("hr");
		if (category.contains("HR")) {
			model.setViewName("hr");
		} else {
			model.setViewName("it");
		}
		return model;
	}

	@Autowired
	private TicketAssignedToService ticketAssignedToService;

	@RequestMapping(value = "/jsp/assign", method = RequestMethod.GET)
	public ModelAndView assign(@RequestParam(value = "txt_empId") int empId,
			@RequestParam(value = "txt_category") String category,
			@RequestParam(value = "txt_assign") int assign,
			@RequestParam(value = "txt_groupid") int groupId) {
		ticketAssignedToService.display(empId, category, assign, groupId);
		ModelAndView model = new ModelAndView("employee");
		model.addObject("title", " Form");
		model.addObject("message", "Request sent to");
		model.setViewName("hr");
		if (category.contains("HR")) {
			model.setViewName("hr");
		} else {
			model.setViewName("it");
		}
		return model;
	}

	@RequestMapping(value = "/jsp/itform", method = RequestMethod.POST)
	public ModelAndView itform(@RequestParam(value = "txt-viewEmp") int viewempId) {
		ModelAndView model = new ModelAndView();
		List<Ticket> al = ticketRaisedService.display(viewempId);
		model.addObject("form", al);
		model.setViewName("it");
		return model;
	}
	@RequestMapping(value = "/jsp/resolve", method = RequestMethod.GET)
	public ModelAndView resolve(@RequestParam(value = "txt_empId") int empId,
			@RequestParam(value = "txt_category") String category,
			@RequestParam(value = "txt_eventId") int eventId) {
		ticketResolveService.display(empId, category, eventId);
		ModelAndView model = new ModelAndView("employee");
		model.addObject("title", " Form");
		model.addObject("message", "Request sent to");
		if (category.contains("HR")) {
			model.setViewName("hr");
		} else {
			model.setViewName("it");
		}
		return model;
	}
}
