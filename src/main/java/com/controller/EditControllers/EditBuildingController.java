package com.controller.EditControllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BuildingDao;

/**
 * Servlet implementation class EditBuildingController
 */

@WebServlet("/EditBuildingController")
public class EditBuildingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBuildingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session=request.getSession();
		System.out.println("edit building controller");
		String buildingName=request.getParameter("building_name");
		int buildingFloor=Integer.parseInt(request.getParameter("no_of_floors"));
		int buildingid=Integer.parseInt(request.getParameter("building_id"));
		
		BuildingDao bd=new BuildingDao();
		int i=bd.buldingUpdate(buildingName,buildingFloor,buildingid);
		
		if(i>0)
		{
			session.setAttribute("update-success","true");
			response.sendRedirect("BuildingDetails.jsp");
			
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
