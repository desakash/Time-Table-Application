package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CourseDao;
import com.model.Course;

/**
 * Servlet implementation class CourseMasterController
 */
@WebServlet("/CourseMasterController")
public class CourseMasterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseMasterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		String course_code=request.getParameter("course_code");
		String course_name=request.getParameter("course_name");
		String course_abbr=request.getParameter("course_abbr");
		String year=request.getParameter("year");
		int yearInt=0;
		if(year.equals("First Year"))
		{
			yearInt=1;
		}
		if(year.equals("Second Year"))
		{
			yearInt=3;
		}
		if(year.equals("Third Year"))
		{
			yearInt=3;
		}
		int no_of_theory=Integer.parseInt(request.getParameter("no_of_theory"));
		int no_of_prac=Integer.parseInt(request.getParameter("no_of_pract"));
		int no_of_tuts=Integer.parseInt(request.getParameter("no_of_tuts"));
		
		Course cou=new Course(course_code, course_name, course_abbr, yearInt, no_of_theory, no_of_prac, no_of_tuts);
		CourseDao cdao=new CourseDao();
		int j=cdao.createCourse(cou);
		if(j>0)
		{
			session.setAttribute("course-success", "true");
			response.sendRedirect("CourseMaster.jsp");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
