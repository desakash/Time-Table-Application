<%@page import="com.dao.BuildingDao"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.dao.ClassDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css /">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://use.fontawesome.com/6867b8e1d6.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    
       <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script> 

<title>Insert title here</title>
<link rel="stylesheet" href="../css/form.css">
<link rel="stylesheet" href="../css/table.css">
<link rel="stylesheet" href="../css/style.css">


<script>

$(document).ready(function(){
	$("#exampleModal").modal('show');
});
</script>
</head>
<body>

<%@ include file="../ClassDetails.jsp" %> 
 <%
	try{
		int buildingId=Integer.parseInt(request.getParameter("buildingId"));
		System.out.println(buildingId);
		ClassDao cd=new ClassDao();
		ResultSet rs=cd.getClassDetailsById(buildingId);
/* 		ResultSet rs=bdao.getBuildingDetailsById(buildingId);
 */		if(rs.next())
		{
	%>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" style="font-size: 27px" id="exampleModalLabel">Update Building Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
         <h2 class="title" style="font-size: 30px">Class Details</h2>
        <form action="ClassMasterController" method="post" class="row g-3 needs-validation" novalidate>
            <div class="col-md-7">
              <label for="validationCustom01" style="font-size: 17px" class="form-label">Class Name</label>
              <input type="text" class="form-control" name="class_name" id="validationCustom01" value="<%=rs.getString(2)%>" style="font-size: 17px" required >
              <div class="valid-feedback" style="font-size: 17px">
                Looks good!
              </div>
            </div>
            
            <div class="col-md-7">
              <label for="validationCustom04" class="form-label" style="font-size: 17px" >Type of Class</label>
              <select class="form-select" style="font-size: 17px" id="validationCustom04" name="type" value="<%=rs.getString(3) %>" required>
                <option selected disabled value="">Choose...</option>
                <option style="font-size: 17px">Classroom</option>
                <option style="font-size: 17px">Lab</option>
                <option style="font-size: 17px">Tutorial Room</option>
                <option style="font-size: 17px">Drawing Hall</option>
              </select>
              <div class="invalid-feedback" style="font-size: 17px">
                 Please select a Type of Class.
              </div>
            </div>
<%             
}
 %>
             <div class="col-md-6">
              <%
                                    
                                    BuildingDao bdao=new BuildingDao();
                                    ResultSet rs1=bdao.getBuildingNameById(rs.getInt(4));
                                    if(rs1.next())
                                    {
                                   %>
                                    	
              <label for="validationCustom04" class="form-label" style="font-size: 17px" >Building Name</label>
              <select class="form-select" style="font-size: 17px" id="validationCustom04" onchange="showfloor(this.value)" value="<%=rs1.getString(4) %>" name="buildingName" required>
                <option selected disabled value="">Choose...</option>
                 <%
                	BuildingDao bdaoo=new BuildingDao();
            		ResultSet rss=bdao.getBuildingName();
                	while(rs.next())
                	{
                %>
                	<option style="font-size: 17px"><%=rss.getString(1)%></option>
                	<option style="font-size: 17px"><%=rss.getString(2) %></option>
                <option style="font-size: 17px"><%=rss.getString(3) %></option>
                <option style="font-size: 17px"><%=rss.getString(4) %></option>
                <% 
                	}
                	
                %> 
                
              </select>
              <div class="invalid-feedback" style="font-size: 17px">
                 Please select a Building Name.
              </div>
            </div>
           <%--  <% 
            }
                                    %> --%>
            <div class="col-md-6">
              <label for="validationCustom04" class="form-label" style="font-size: 17px">Floor</label>
              <select  class="form-select" style="font-size: 17px" id="floor" value="<%=rs.getInt(5) %>" name="floorNo" required>
                <option selected disabled value="">Choose...</option>
                 <!--<option style="font-size: 17px">1st</option>
                <option style="font-size: 17px">2nd</option>
                <option style="font-size: 17px">3rd</option>
                <option style="font-size: 17px">4th</option> -->
              </select>
              <div class="invalid-feedback" style="font-size: 17px">
                 Please select a Floor.
              </div>
            </div>
            
            
            
            
           <!--  <div class="col-md-10">
              <label for="validationCustom01" style="font-size: 17px" class="form-label">No of floors:</label>
              <input type="text" class="form-control textfield" name="no_of_floors" id="validationCustom01" style="font-size: 17px" value="" id="onlyNumbers" name="onlyNumbers" onkeypress="return isNumber(event)" onpaste="return false;" required>
              <div class="valid-feedback" style="font-size: 17px">
                Looks good!
              </div>
            </div> -->
           
            <!-- <div class="col-md-12">
              <label for="validationCustom01" style="font-size: 17px" class="form-label">Number of classrooms:</label>
              <input type="text" class="form-control textfield" id="validationCustom01" value="" id="onlyNumbers" name="onlyNumbers" onkeypress="return isNumber(event)" onpaste="return false;" style="font-size: 17px" required>
              <div class="valid-feedback" style="font-size: 17px">
                Looks good!
              </div>
            </div>

            <div class="col-md-12">
              <label for="validationCustom01" class="form-label" style="font-size: 17px">No of labs:</label>
              <input type="text" class="form-control textfield" id="validationCustom01" value="" id="onlyNumbers" name="onlyNumbers" onkeypress="return isNumber(event)" onpaste="return false;" style="font-size: 17px" required>
              <div class="valid-feedback" style="font-size: 17px">
                Looks good!
              </div>
            </div>

            <div class="col-md-6">
              <label for="validationCustom04" class="form-label" style="font-size: 17px" >Course</label>
              <select class="form-select" style="font-size: 17px" id="validationCustom04" required>
                <option selected disabled value="">Choose...</option>
                <option style="font-size: 17px">Web Devlopment</option>
                <option style="font-size: 17px">DataBase</option>
                <option style="font-size: 17px">Java</option>
              </select>
              <div class="invalid-feedback" style="font-size: 17px">
                 Please select a Course.
              </div>
            </div>
           
            <div class="col-12">
              <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="invalidCheck" style="font-size: 17px" required>
                <label class="form-check-label" style="font-size: 17px" for="invalidCheck">
                  Agree to terms and conditions
                </label>
                <div class="invalid-feedback" style="font-size: 17px">
                  You must agree before submitting.
                </div>
              </div>
            </div> -->
            <div class="col-12">
              <button class="btn btn-success" type="submit" style="font-size: 17px"; text-align:"center";> <i class="fas fa-plus"></i> Add Class</button>
            </div>
          </form>
      </div>
    </div>
  </div>
</div>
<%
	}
	}catch(Exception e)
		{
			e.printStackTrace();
		}
%>
</body>
</html>