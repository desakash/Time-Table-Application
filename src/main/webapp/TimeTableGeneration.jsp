<%@page import="com.dao.DivisionDao"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/form.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="sweetalert2.all.min.js"></script>
<script src="sweetalert2.min.js"></script>
<link rel="stylesheet" href="sweetalert2.min.css">
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<title>Insert title here</title>
</head>
<body>
<%@ include file="html/sidenav.html" %>  

<%
	if(!session.isNew())
	{
		String successMsg=(String)session.getAttribute("success_msg");
		String errorMsg=(String)session.getAttribute("error_msg");
		
		if(successMsg=="true")
		{
			%>
			
			<script type="text/javascript">
			
			Swal.fire({
				//  position: 'top-end',
						icon: 'success',
						title: 'Division Registered Successfully.',
						showConfirmButton: false,
						timer: 1500,
					})

				</script>
				
				<%
		}
		if(errorMsg=="true")
		{
			%>
			<script type="text/javascript">
			
			Swal.fire({
				//  position: 'top-end',
						icon: 'error',
						title: 'Failed ',
						showConfirmButton: false,
						timer: 1500,
					})

				</script>
				<%
		}
	}
session.removeAttribute("success_msg");
session.removeAttribute("error_msg");

%>


	<script type="text/javascript">
	var day;
	var xmlHttp;
		
		function showOfferedCourses(str)
		{
			console.log('hello in courses'+str);
			if(typeof XMLHttpRequest !=="undefined")
			{
				xmlHttp=new XMLHttpRequest();
			}
			else if (window.ActiveXObject)
			{
			xmlHttp=new ActiveXOjbject("Microsoft.XMLHTTP");
		}
			if(xmlHttp===null)
			{
				alert("Browser does not support XMLHTTP Request.");
				return;
			}
			var url="OfferedCoursesDropdown?division="+str;
			xmlHttp.onreadystatechange=stateChange;
			xmlHttp.open("POST",url,true);
			xmlHttp.send(null);
			
			
		}
		function stateChange()
		{
			console.log('hello in printcourses')
			if(xmlHttp.readyState===4 ||xmlHttp.readyState==='complete')
			{
				console.log('hello in if of courses')
				document.getElementById("offered_courses").innerHTML=xmlHttp.responseText;
			}
		}
		
		function showOfferedSlots(str)
		{
			day=str;
			console.log('hello in slots'+str);
			if(typeof XMLHttpRequest !=="undefined")
			{
				xmlHttp=new XMLHttpRequest();
			}
			else if (window.ActiveXObject)
			{
			xmlHttp=new ActiveXOjbject("Microsoft.XMLHTTP");
		}
			if(xmlHttp===null)
			{
				alert("Browser does not support XMLHTTP Request.");
				return;
			}
			var url="DisplaySlotDropDown?day="+str;
			xmlHttp.onreadystatechange=stateChange1;


			xmlHttp.open("POST",url,true);
			xmlHttp.send(null);
			
			
		}
		 function stateChange1()
		{
			console.log('hello in statechange1')
			if(xmlHttp.readyState===4 ||xmlHttp.readyState==='complete')
			{
				console.log('inside fromtime tag')
				document.getElementById("offeredSlots").innerHTML=xmlHttp.responseText;



			}

		}
		 function showOfferedSlots2()
		 {
			 console.log('hello in slots'+day);
				if(typeof XMLHttpRequest !=="undefined")
				{
					xmlHttp=new XMLHttpRequest();
				}
				else if (window.ActiveXObject)
				{
				xmlHttp=new ActiveXOjbject("Microsoft.XMLHTTP");
			}
				if(xmlHttp===null)
				{
					alert("Browser does not support XMLHTTP Request.");
					return;
				}
				var url="DisplaySlotToTime?day="+day;
				xmlHttp.onreadystatechange=stateChange2;


				xmlHttp.open("POST",url,true);
				xmlHttp.send(null);
				
 
		 }
		 
		 function stateChange2()
			{
				console.log('hello in stateChange2 ')
				if(xmlHttp.readyState===4 ||xmlHttp.readyState==='complete')
				{
					console.log('inside totime tag')
					document.getElementById("offeredSlots2").innerHTML=xmlHttp.responseText;
				}
			}
	</script>



      <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
        <div class="wrapper wrapper--w680">
        <div class="card card-4">
        <div class="card-body">
        <h2 class="title">Generate Timetable</h2>
        <form class="row g-3 needs-validation" action="TimeTableGenerationController" method="post" novalidate>
           

           
             
            <div class="col-md-6">
              <label for="validationCustom04" class="form-label">Division</label>
              <select class="form-select" id="division" name="Division_name" onchange="showOfferedCourses(this.value)"required>
                <option selected disabled value="">Division</option>
                 <%
                 	ResultSet rs=null;
                	DivisionDao ddao=new DivisionDao();
                	rs=ddao.getDivisionDetails();
                	while(rs.next())
                	{
                %>
                	<option style="font-size: 17px"><%=rs.getString(2) %></option>
                <%
                	}
                %>
               
              </select>
              <div class="invalid-feedback">
                Please select a Division.
              </div>
            </div>
           
            <div class="col-md-6">
              <label for="validationCustom04" class="form-label">Courses</label>
              <select class="form-select" id="offered_courses" name="offered_courses" required>
                <option selected disabled value="">Courses</option>
                <!-- <option>Python With Programming [CM5102]</option>
                <option>Android Application Programming</option> -->
              </select>
              <div class="invalid-feedback">
                Please select a Course.
              </div>
            </div>
            <script  type="text/javascript">
                
            </script>
            <div class="col-md-6">
              <label for="validationCustom04" class="form-label">Head</label><br>
              <select class="form-select" id="headval" name="head" required>
                <option selected disabled value="">Head</option>
                <option>Theory</option>
                <option>Practical</option>
                <option>Tutorial</option>
                <option >Break</option>
              </select>
              <div class="invalid-feedback">
                Please select The Option.
              </div>
            </div>

          

            <div class="col-md-6">
                <label for="validationCustom04" class="form-label">Day</label>
                <select class="form-select" id="validationCustom04" name="day" onchange="showOfferedSlots(this.value)" required>
                  <option selected disabled value="">Day</option>
                  <option>Monday</option>
                  <option>Tuesday</option>
                  <option>Wednesday</option>
                  <option>Thrusday</option>
                  <option>Friday</option>
                  <option>Saturday</option>
  
                </select>
                <div class="invalid-feedback">
                  Please select a Day.
                </div>
              </div>
              
              <div class="col-md-6">
                  <label for="validationCustom04" class="form-label">From Time:</label>
                  <select class="form-select" id="offeredSlots"onchange="showOfferedSlots2()" name="fromtime" required>
                    <option selected disabled value="">From Time</option>
                   <!--  <option>08 AM</option>
                    <option>09 AM</option>
                    <option>10 AM</option> -->
                  </select>
                  <div class="invalid-feedback">
                    Please select a From time.
                  </div>
                </div>
             
                <div class="col-md-6">
                  <label for="validationCustom04" class="form-label">To Time:</label>
                  <select class="form-select" id="offeredSlots2" onchange="" name="totime" required>
                    <option selected disabled value="">To Time</option>
                   <!--  <option>08 AM</option>
                    <option>09 AM</option>
                    <option>10 AM</option> -->
                  </select>
                  <div class="invalid-feedback">
                    Please select to Time.
                  </div>
                </div>
            
                <script type="text/javascript">
                  function update(){
                  var value = $('#fromtime :selected').text();
                  var value1 = $('#totime :selected').text();

                  if(value==value1){
                	  Swal.fire({
                		  icon: 'error',
                		  title: 'Oops...',
                		  text: 'From time and to time can not be same',
                		})
                  }

                  var s=document.getElementById("start_dis")
                  var t=document.getElementById("end_dis")

                  s.innerText="Star Time: "+value
                  t.innerText="Star Time: "+value1
                }
                </script>
     
            
         <br><br><br>
            <div class="col-md-6">
              <label for="validationCustom04" class="form-label">Faculty</label>
              <select class="form-select" id="validationCustom04" name="faculty" required>
                <option selected disabled value="">Faculty</option>
                <option>Gauri Garud</option>
                <option>S.P.P Panchakshari</option>
           
              </select>
              <div class="invalid-feedback">
                Please select a Faculty.
              </div>
            </div>  

            <div class="col-md-6">
              <label for="validationCustom04" class="form-label"></label>Classroom</label>
              <select class="form-select" id="validationCustom04" name="classroom" required>
                <option selected disabled value="">Classroom</option>
                <option>CR1</option>
                <option>CR2</option>
                <option>CR3</option>
                
              </select>
              <div class="invalid-feedback">
                Please select a Classroom.
              </div>
            </div>  
            <div class="col-md-12" style="display: inline">
            
                <div class="d-inline-block"><h4 id="start_dis">Start Time  :</h4></div>
                <div class="d-inline-block" style="margin-left: 189px;"><h4 id="end_dis">End Time :</h4></div>
              
              </div>  
            <div class="col-12">
              <button class="btn btn-primary" type="submit">ADD</button>
            </div>
            

          </form>
       
        </form>
        </div>
        </div>
        </div>
        </div>
        
<script src="js/form.js"></script>   
</body>
</html>