package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.TimeTableGenerationDao;
import com.model.TimeTableGenerationn;

/**
 * Servlet implementation class TimeTableGenerationController
 */
@WebServlet("/TimeTableGenerationController")
public class TimeTableGenerationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeTableGenerationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String division=request.getParameter("Division_name");
		String offered_courses=request.getParameter("offered_courses");
		String head=request.getParameter("head");
		String day=request.getParameter("day");
		String fromtime=request.getParameter("fromtime");
		String totime=request.getParameter("totime");
		String faculty=request.getParameter("faculty");
		String classroom=request.getParameter("classroom");
		
		TimeTableGenerationn ttg=new TimeTableGenerationn(division, offered_courses, head, day, fromtime, totime, faculty, classroom);
		TimeTableGenerationDao tgd=new TimeTableGenerationDao();
		
		boolean status=tgd.check(ttg);
		HttpSession session=null;
		if(status)
		{
			int i=tgd.insert(ttg);
			if(i>0)
			{
				i=tgd.update(day,fromtime,totime);
				
				session=request.getSession();
				session.setAttribute("success_msg", "true");
				response.sendRedirect("TimeTableGeneration.jsp");
			}
			
		}
		else
		{
				session=request.getSession();
				session.setAttribute("error_msg", "true");
				response.sendRedirect("TimeTableGeneration.jsp");

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
