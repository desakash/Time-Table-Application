package com.controller.EditControllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.FacultyDao;

/**
 * Servlet implementation class EditFacultyController
 */
@WebServlet("/EditFacultyController")
public class EditFacultyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditFacultyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String faculty_name=request.getParameter("faculty_name");
		String faculty_abbr=request.getParameter("faculty_abbr");
		String faculty_email=request.getParameter("faculty_email");
		String faculty_designation=request.getParameter("faculty_designation");
		String faculty_type=request.getParameter("faculty_type");
		
		int faculty_id=Integer.parseInt(request.getParameter("faculty_id"));
		
		FacultyDao fd=new FacultyDao();
		int i=fd.update_faculty(faculty_name,faculty_abbr,faculty_email,faculty_designation,faculty_type,faculty_id);
		
		if(i>0)
		{
			HttpSession session=request.getSession();
			
			session.setAttribute("update-msg", "true");
			
			response.sendRedirect("FacultyDetails.jsp");
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
