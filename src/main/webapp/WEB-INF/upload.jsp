<html lang="en">
   <head>
      <meta charset="utf-8"/>
    
      <title>file upload  page</title>
      <meta name="viewport" content="width=device-width, initial-scale=1"/>
      <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"/>
      <link href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet" id="main-css"/>
      <link href="css/style.css" rel="stylesheet" id="main-css"/>
      <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
      <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>  
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
      <script type="text/javascript" src="js/bootstrap-filestyle.min.js"> </script>
      <script type="text/javascript" language="javascript"></script>
      <script>
         function checkfile(sender) {
         var validExts = new Array(".xlsx", ".xls");
         var fileExt = sender.value;
         fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
           if (validExts.indexOf(fileExt) < 0) {
              alert("Invalid file selected, valid files are of " +
              validExts.toString() + " types.");
              return false;
              }
           else return true;
          }
       </script>
       <style>
          body {
             font: 400 15px/1.8 Lato, sans-serif;
             color: #777;
             }
          .navbar {
      		  margin-bottom: 0;
      		  background-color: #2d2d30;
      		  z-index: 9999;
      		  border: 0;
      		  font-size: 11px !important;
		      line-height: 1.42857143 !important;
		      letter-spacing: 0px;
		      border-radius: 0;
  			  }
  	  	  .navbar li a, .navbar .navbar-brand {
      		 color: #d5d5d5 !important;
  			  } 
  		   .navbar-nav li a:hover {
      color: #fff !important;
  }
  .navbar-nav li.active a {
      color: #fff !important;
      background-color: #29292c !important;
  }
  		  .navbar-default .navbar-toggle {
			  border-color: transparent;
 			  }
 		  .open .dropdown-toggle {
      color: #fff;
      background-color: grey !important;
  }
         .dropdown-menu li a {
      color: #000 !important;
  }
  .dropdown-menu li a:hover {
      background-color: blue !important;
  }
 		  .jumbotron {
 		      padding: 100px 25px;
 		      }	
 		
     </style>
   </head>
   <body>
   
      <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
    		<div class="navbar-header">
    		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      			<a class="navbar-brand text-center" href="#myPage">HEALTHCARE BUSINESS PROCESSOR</a>
    		</div>
    		 <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#myPage">HOME</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">More  
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="webapp/WEB-INF/src/main/webapp/WEB-INF/upload.jsp">Upload Enrollment File</a></li>
            <li><a href="#">Calculate Premium</a></li> 
            <li><a href="webapp/WEB-INF/src/main/webapp/WEB-INF/uploadPremiumFile.jsp">Premium Processing</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span>  
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">My Profile</a></li>
            <li><a href="index.jsp">Logout</a></li> 
          </ul>
        </li>
        <li><a href="#"><span class="glyphicon glyphicon-search"></span></a></li>
      </ul>
    </div>
  </div>
  		
	</nav>
	 <div class="jumbotron clearfix">
       <div id="loginbox" class="mainbox col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3" >
       <form action="processExcel" method="post"
		enctype="multipart/form-data">
		<div class="input-group">
		<p style="color:#808080">BROWSE ENROLLMENT FILE:</p>
		
		<input type="file" onchange="checkfile(this);" 
		class="filestyle" name="excelfile2007" data-buttonName="btn-primary">
      <br>
      <input type="submit" class="btn btn-danger" value="Upload"/>
      
     
       </div>
      </form>
      <div id="successMsg">
		<%
		String successMsg = (String) request.getAttribute("successMsg");
		try {
			if (successMsg.equals(null)) {
				int a = 2 + 3;
			} else {
		%>
		

			<font size="5" color="#699de8"><div><%=request.getAttribute("successMsg")%></div></font>
		
		<%
			}
			} catch (NullPointerException e) {
			}
		%>
		</div>
      </div>
      </div>
   </body>
   </html>