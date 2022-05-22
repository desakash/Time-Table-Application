<%@page import="com.dao.SlotDao"%>
<%@page import="com.dao.DivisionDao"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.dao.BuildingDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Slot Details - GPP</title>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css /">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <script type = "text/javascript" >  
        window.history.forward();
        function noBack() { window.history.forward(); }
    </script> 
    <script src="https://use.fontawesome.com/6867b8e1d6.js"></script>
    <link rel="stylesheet" href="css/table.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
       <script src="sweetalert2.all.min.js"></script>
<script src="sweetalert2.min.js"></script>
<link rel="stylesheet" href="sweetalert2.min.css">
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
       <style type="text/css">
       .swal2-popup {
  font-size: 1.6rem !important;
       </style>
</head>
  <body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
 
 		<%@ include file="html/sidenav.html" %>  
<%
if(!session.isNew())
{
	String error_msg=(String)session.getAttribute("error");
	String success_msg=(String)session.getAttribute("success");

	if(error_msg=="true")
	{
		success_msg="false";
		%>
	<script type="text/javascript">
	Swal.fire({
		  icon: 'error',
		  title: 'Oops...',
		  text: 'Slot is already booked.....You cant edit it!',
		})
	</script>
	<%

	}
	if(success_msg=="true")
	{
		%>
		<script type="text/javascript">
		Swal.fire({
			//  position: 'top-end',
			icon : 'success',
			title : 'slot Updated Successfully',
			showConfirmButton : false,
			timer : 1500,
		})
	</script>
		<%
	}
}
	session.removeAttribute("error");
	session.removeAttribute("success");

%>

    <div class="container" style="margin-top: 150px;margin-left: 200px;">
        <div class="row">
            <div class="col-md-offset-1 col-md-10">
                <div class="panel">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col col-md-6 col-xs-12">
                                <h4 class="title">Slot Details </h4>
                            </div>
                            <div class="col-md-6 col-xs-12 text-right">
                                <div class="btn_group">
                                    <input type="text" id="search" class="form-control" placeholder="Search">
                                    <!-- <button class="btn btn-search " title="Reload"><i class="fa fa-sync-alt"></i></button> -->
                                     <!-- <button class="btn btn-default" title="Pdf"><i class="fa fa-file-pdf"></i></button>
                                    <button class="btn btn-default" title="Excel"><i class="fas fa-file-excel"></i></button> -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body table-responsive">
                        <table class="table" style="width: 2500px">
                            <thead>
                                
                                    <th>Sr.No</th>
                                    <th>Day</th>
                                    <th>From Time</th>
                                    <th>To Time</th>
                                    <th>Action</th>
                                    
                            </thead>
                            <ol id="list">
                            <tbody>
                            <%
                            int flag=0;
                            SlotDao sdao=new SlotDao();
                            ResultSet rs=sdao.getAllSlots();
                            int cnt=1;
                            while(rs.next())
                            {
                            	
                            %>
                                <tr>
                                    <td><%=cnt %></td>
                                    <td><%=rs.getString(1) %></td>
                                    <td><%=rs.getString(2) %></td>
                                    <td><%=rs.getString(3) %></td>
                                    <td>
                                        <ul class="action-list">
                                           <a href="EditModals/EditSlotModal.jsp?slotDay=<%=rs.getString(1)%>"> <button type="button" class="btn btn-success " style="font-size: 15px"><i class="fa fa-edit">  </i> Edit</button>&nbsp &nbsp &nbsp</a>

                                            <!-- <li><a href="#" data-tip="edit"><i class="fa fa-edit"></i></a></li> -->
<%--                                            <a href="DeleteSlotController?slotDay=<%=rs.getString(1)%>"> <button type="button" class="btn btn-danger "  style="font-size: 15px"><i class="fa fa-trash"> </i> Delete</button></a>
 --%>
                                            <!-- <li><a href="#" data-tip="delete"><i class="fa fa-trash"></i></a></li> -->
                                        </ul>
                                    </td>
                                </tr>
                              <%
                              		cnt++;
                            	}
                            
                              %>
                            </tbody>
                        </ol>
                        </table>
                    </div>
                    <div class="panel-footer">
                        <div class="row">
               
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
    <script src="js/table.js"></script>
</body>
</html>