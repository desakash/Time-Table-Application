<%@page import="com.dao.DivisionDao"%>
<%@page import="com.dao.OfferedCourseDao"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.dao.CourseDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Division Course Allocation | GPP</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
    crossorigin="anonymous"></script>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    
        <script src="https://kit.fontawesome.com/a4f00a5269.js" crossorigin="anonymous"></script>
    
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.8.1/css/bootstrap-select.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.8.1/js/bootstrap-select.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
     <script>
   $('select').selectpicker();
    </script>
    
    
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.2.0/css/datepicker.min.css" rel="stylesheet">
      
      
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
      <script src="https://netdna.bootstrapcdn.com/bootstrap/2.3.2/js/bootstrap.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.min.js"></script>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    
<script type="text/javascript">
(function () {
    'use strict'
  
    var forms = document.querySelectorAll('.needs-validation')
  
    Array.prototype.slice.call(forms)
      .forEach(function (form) {
        form.addEventListener('submit', function (event) {
          if (!form.checkValidity()) {
            event.preventDefault()
            event.stopPropagation()
          }
  
          form.classList.add('was-validated')
        }, false)
      })
  })()
  function manage(txt) {
    var bt = document.getElementById('submit');
    var ele = document.getElementsByTagName('input');

    // Loop through each element.
    for (i = 0; i < ele.length; i++) {

      // Check the element type
      if (ele[i].type == 'text' && ele[i].value == '') {
        bt.disabled = true;    // Disable the button.
        return false;
      }
      else {
        bt.disabled = false;   // Enable the button.
      }
    }
  }

function manage(pwd) {
    var bt = document.getElementById('submit');
    var ele = document.getElementsByTagName('input');

    // Loop through each element.
    for (i = 0; i < ele.length; i++) {

      // Check the element type
      if (ele[i].type == 'password' && ele[i].value == '') {
        bt.disabled = true;    // Disable the button.
        return false;
      }
      else {
        bt.disabled = false;   // Enable the button.
      }
    }
  }


  // $(".toggle-password").click(function() {

  //   $(this).toggleClass("fa-eye fa-eye-slash");
  //   var input = $($(this).attr("toggle"));
  //   if (input.attr("type") == "password") {
  //     input.attr("type", "text");
  //   } else {
  //     input.attr("type", "password");
  //   }
  // });

  const togglePassword = document.querySelector('#togglePassword');
  const password = document.querySelector('#desig');

  togglePassword.addEventListener('click', function (e) {
    // toggle the type attribute
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);
    // toggle the eye / eye slash icon
    this.classList.toggle('bi-eye');
});
  </script>
     <meta charset="UTF-8">
     <meta http-equiv="X-UA-Compatible" content="IE=edge">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Building Master | GPP</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>


     <link rel="stylesheet" href="css/form.css">

     <script type="text/javascript">     
      function isNumber(evt) {
              evt = (evt) ? evt : window.event;
              var charCode = (evt.which) ? evt.which : evt.keyCode;
              if ( (charCode > 31 && charCode < 48) || charCode > 57) {
                  return false;
              }
              return true;
          }
      </script>
      <script src="sweetalert2.all.min.js"></script>
<script src="sweetalert2.min.js"></script>
<link rel="stylesheet" href="sweetalert2.min.css">
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
 </head>
 <body>
 		<%@ include file="html/sidenav.html" %>
<!--  		<div id="header"></div>
 --> 		
				
    <%-- <%
    	out.print("in jsp");
    	if(!session.isNew())
    	{
    		session=request.getSession();
        	String str=(String)session.getAttribute("success_msg");
     %>
    					 <div class="alert">
  								<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
  								<strong><%=str %></strong> 
							</div>
     
     <%
     
    	}
    	session.invalidate();
    	
    %>
     --%>
 
  
    <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
        <div class="wrapper wrapper--w680">
        <div class="card card-4">
        <div class="card-body">
        
        <%
    	
    	if(!session.isNew())
    	{
    		session=request.getSession();
        	String  success=(String)session.getAttribute("allotedCourse-Success");
        	if(success=="true")
        	{
     %>
    				<script type="text/javascript">
				
    				Swal.fire({
      				//  position: 'top-end',
       						icon: 'success',
       						title: 'Courses Alloted to Division Successfully.',
       						showConfirmButton: false,
       						timer: 1500,
     					})
 
  					</script>
  <%
		}
	}
	/* session.invalidate(); */
	session.removeAttribute("succes");
  %>
        
        <h2 class="title" style="font-size: 30px">Division Course Allocation</h2>
        <form action="DivisionCourseAllocationController" method="post" class="row g-3 needs-validation" novalidate>
        	<div class="col-md-6">
              <label for="validationCustom04" class="form-label" style="font-size: 17px" >Term</label>
              <select class="form-select" style="font-size: 17px" id="validationCustom04" name="term" required>
                <option selected disabled value="">Choose...</option>
                <option style="font-size: 17px">Even</option>
                <option style="font-size: 17px">Odd</option>
	              </select>
              <div class="invalid-feedback" style="font-size: 17px">
                 Please select a Term.
              </div>
            </div>
            <div class="col-md-6">
              <label for="validationCustom01" style="font-size: 17px" class="form-label">Year</label>
            <input type="text" class="form-control textfield" name="datepicker" id="datepicker" style="font-size: 17px" value="" required>         
            <script>
$(document).ready(function(){
  $("#datepicker").datepicker({
     format: "yyyy",
     viewMode: "years", 
     minViewMode: "years",
     autoclose:true
  });   
})


</script> 
              <div class="valid-feedback" style="font-size: 17px">
                Looks good!
              </div>
              <div class="invalid-feedback" style="font-size: 17px">
                 Please select Year.
              </div>
            </div>
            <div class="col-md-6">
              <label for="validationCustom04" class="form-label" style="font-size: 17px" >Division</label>
              <select class="form-select" style="font-size: 17px" id="validationCustom04" name="division" required>
                <option selected disabled value="">Choose...</option>
                <%
                try{
               	DivisionDao dd=new DivisionDao();
               	ResultSet rs=dd.getDivisionNames();
          		while(rs.next())
          		{
                %>
                <option style="font-size: 17px"><%=rs.getString(1) %></option>
                <%
          		}
                }catch(Exception e){
                	e.printStackTrace();
                }
                %>
	              </select>
              <div class="invalid-feedback" style="font-size: 17px">
                 Please select Division
              </div>
            </div>
            <div class="col-md-6">
              <label for="validationCustom04" class="form-label" style="font-size: 17px" >Courses</label>
              <select class="selectpicker form-label" style="font-size: 17px;" id="validationCustom04" name="courses" multiple data-live-search="true">
                <%
                try{
               	OfferedCourseDao ocd=new OfferedCourseDao();
               	ResultSet rs=ocd.getOfferedCoursesName();
          		while(rs.next())
          		{
                %>
                <option style="font-size: 17px" value="<%=rs.getString(2) %>"><%=rs.getString(1)%>&nbsp&nbsp&nbsp<%=rs.getString(2) %></option>
                <%
          		}
                }catch(Exception e){
                	e.printStackTrace();
                }
                %>
	              </select>
              <div class="invalid-feedback" style="font-size: 17px">
                 Please select a Courses.
              </div>
            </div>
           
           
            <div class="col-12">
              <button class="btn btn-success" type="submit" style="font-size: 17px"; text-align:"center";> <i class="fas fa-plus"></i> Add Building</button>
            </div>
          </form>
       
        </div>
        </div>
        </div>
        </div>
        
<script src="js/form.js"></script>        
 </body>
</html>