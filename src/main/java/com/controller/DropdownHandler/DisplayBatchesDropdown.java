package com.controller.DropdownHandler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.TimeTableGenerationDao;

/**
 * Servlet implementation class DisplayBatchesDropdown
 */
@WebServlet("/DisplayBatchesDropdown")
public class DisplayBatchesDropdown extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayBatchesDropdown() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Welcome to DisplayBatchesDropdown");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String tag="<select class='form-select' id='validationCustom04' name='batch' required style='font-size: 17px'> <option selected disabled value=''>Choose...</option>";
		
		String division=request.getParameter("division");
		String course=request.getParameter("course");
		String faculty=request.getParameter("faculty");
		
		System.out.println(division+"\t"+course+"\t"+faculty);
		
		TimeTableGenerationDao ttg=new TimeTableGenerationDao();
		ResultSet rs=ttg.displayBatches(division, course, faculty);
		
		String tag1,tag2;
		
		try {
			while(rs.next())
			{
				/*
				 * String batches=rs.getString(9); String batch1=batches.substring(0,7); String
				 * batch2=batch1.substring(7,10);
				 */
				String batches=rs.getString(9);
				if(batches.equals("A Batch, B Batch")||(batches.equals("A Batch, B Batch, C Batch")))
				{
					/*
					 * String A="A Batch"; String B="B Batch";
					 */
					 tag1="<option style='font-size: 17px'>A Batch</option>";
					 tag2="<option style='font-size: 17px'>B Batch</option>";
					 tag=tag1+tag2;

				}
				else
				{
					tag=tag+"<option style='font-size: 17px'>"+rs.getString(9)+"</option>";

				}
				 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tag=tag+"</select>";
		response.getWriter().println(tag);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}