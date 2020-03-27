package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.model.Student;
import com.cognizant.service.StudentServiceImpl;

@Controller
public class StudentController {

	@Autowired
	private StudentServiceImpl studentService;

	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public ModelAndView insertPage() {
		ModelAndView mv = new ModelAndView("insert");
		Student student = new Student();
		mv.addObject("student", student);
		return mv;
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public ModelAndView insert(@ModelAttribute("student") Student student) {
		System.out.println("Model Attribute:" + student);
		String res = studentService.insert(student);
		ModelAndView mv = new ModelAndView("insert");
		if (res.equals("SUCCESS")) {
			mv.addObject("msg", "Record Inserted");
		} else {
			mv.addObject("msg", "Record Not Inserted");
		}
		return mv;
	}
	
//	@RequestMapping(value = "insert", method = RequestMethod.GET)
//	public String insert() {
//		return "insert";
//	}
//	
//	@RequestMapping(value = "insert", method = RequestMethod.POST)
//	public String insertPage(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String name = request.getParameter("name");
//		Student st = new Student(id, name);
//		String result = studentService.insert(st);
//		System.out.println(st);
//		if(result.equals("SUCCESS")) {
//			request.setAttribute("msg", "INSERTED DONE!");
//		}else {
//			request.setAttribute("msg", "NOT INSERTED!");
//		}
//		return "insert";
//	}

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public ModelAndView updatePage(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView("update");
		Student student = new Student();
		mv.addObject("id",id);
		mv.addObject("student", student);
		return mv;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("student") Student student) {
		System.out.println("Model Attribute:" + student);
		String res = studentService.update(student);
		ModelAndView mv = new ModelAndView("display");
		if (res.equals("SUCCESS")) {
			mv.addObject("msg", "Record Updated");
		} else {
			mv.addObject("msg", "Record Not Updated");
		}
		return getAll();
	}
	
//	@RequestMapping(value = "update", method = RequestMethod.GET)
//	public String updateReq() {
//		return "update";
//	}
//
//	@RequestMapping(value = "update", method = RequestMethod.POST)
//	public String update(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String name = request.getParameter("name");
//		Student st = new Student(id, name);
//		String result = studentService.update(st);
//		System.out.println(st);
//		if (result.equals("SUCCESS")) {
//			request.setAttribute("msg", "UPDATE DONE!");
//		} else {
//			request.setAttribute("msg", "NOT UPDATED!");
//		}
//		return "update";
//	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public ModelAndView deletePage(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView();
		String res = studentService.delete(id);
		if (res.equals("SUCCESS")) {
			mv.addObject("msg", "Record Deleted");
		} else {
			mv.addObject("msg", "Record Not Deleted");
		}
		mv.setViewName("display");
		return getAll();
	}

//	@RequestMapping(value = "delete", method = RequestMethod.POST)
//	public ModelAndView delete(@ModelAttribute("student") Student student) {
//		System.out.println("Model Attribute:" + student);
//		String res = studentService.delete(student);
//		ModelAndView mv = new ModelAndView("delete");
//		if (res.equals("SUCCESS")) {
//			mv.addObject("msg", "Record Deleted");
//		} else {
//			mv.addObject("msg", "Record Not Deleted");
//		}
//		return mv;
//	}
	
//	@RequestMapping(value = "delete", method = RequestMethod.GET)
//	public String deleteReq() {
//		return "delete";
//	}
//
//	@RequestMapping(value = "delete", method = RequestMethod.POST)
//	public String delete(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		Student st = new Student();
//		st.setId(id);
//		String result = studentService.delete(st);
//		System.out.println(st);
//		if (result.equals("SUCCESS")) {
//			request.setAttribute("msg", "DELETION DONE!");
//		} else {
//			request.setAttribute("msg", "DELETION NOT DONE!");
//		}
//		return "delete";
//	}

	@RequestMapping(value = "getAll")
	public ModelAndView getAll() {
		List<Student> list = studentService.getAll();
		System.out.println(list);
		ModelAndView mv=new ModelAndView("display");
		mv.addObject("list", list);
		return mv;
	}
	
//	@RequestMapping(value = "getAll", method = RequestMethod.GET)
//	public String getAll(HttpServletRequest request) {
//		List<Student> slist = studentService.getAll();
//		request.setAttribute("list", slist);
//		return "display";
//	}

}
