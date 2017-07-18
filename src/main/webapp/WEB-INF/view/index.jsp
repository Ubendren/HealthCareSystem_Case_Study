<html lang="en">
   <head>
      <meta charset="utf-8"/>
     
      <title>login page</title>
      <meta name="viewport" content="width=device-width, initial-scale=1"/>
      <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"/>
      <link href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet" id="main-css"/>
      <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
      <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>  
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
      <style>
      
       .navbar  {
      		margin-bottom: 0;
      		background-color: #2d2d30;
      		z-index: 9999;
      		border: 0;
      		font-size: 30px !important;
		    line-height: 1.42857143 !important;
		    letter-spacing: 0px;
		    border-radius: 0;
  				}
  	   .navbar-brand {
  	  	color : #fff;
  	  }
  		
 		.jumbotron{
 		        padding: 100px 25px;
 	             min-height : 620px;
 		        }	
 		  #loginbox{
 		    padding-top: 100px;
 		    }	 
      </style>
   </head>
   <body ng-app="postLogin" ng-controller="PostController as postCtrl" >
      <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
    	  <div class="navbar-header">
      		<a class="navbar-brand" href="#myPage">HEALTHCARE BUSINESS PROCESSOR</a>
    	  </div>
  		</div>
	  </nav>
	  <div class="jumbotron clearfix" style="background-image:url('img\abc.jpg');">
        <div id="loginbox" class="mainbox col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3" >
          <div id="message">
		  <%
			String errmsg = (String) request.getAttribute("errmsg");
			try {
				if (errmsg.equals(null)) {
					int a = 2 + 3;
				} else {
		   %>
		     <font size="4" color="#699de8" align="center"><div><%=request.getAttribute("errmsg")%></div></font>
		
		   <%
			    }
			} catch (NullPointerException e) {
			}
		   %>
		  </div>
          <div class="panel panel-default" >
            <div class="panel-heading">
              <div class="panel-title text-center">LOGIN HERE</div>
            </div>
            <div class="panel-body" >
               <form name="login" action="login" class="form-horizontal" method="POST">
                 <div class="input-group">
                   <span class="input-group-addon">Username <i class="glyphicon glyphicon-user"></i></span>
                     <input type="text" id="inputUsername" name="inputUsername" class="form-control" required autofocus ng-model="postCtrl.inputData.username"/>
                 </div>
                 <br>
                 <div class="input-group">
                   <span class="input-group-addon">Password   <i class="glyphicon glyphicon-lock"></i></span>
                      <input type="password" id="inputPassword" name="inputPassword" class="form-control" required ng-model="postCtrl.inputData.password"/>
                 </div>
                 <br>
                <!--  <div class="alert alert-danger" class="text-center" ng-show="errorMsg" aria-hidden="true">
                   <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                     </button>
                    <span class="glyphicon glyphicon-hand-right"></span>&nbsp;&nbsp;{{errorMsg}}
                  </div> -->
                  <div class="form-group">
                    <div class="col-sm-12 controls">
                     <input type="submit" value="Log in"class="btn btn-primary pull-right" ng-disabled="login.$invalid"/>
                      </div>
                   </div>
                  </form>
               </div>
            </div>
         </div>
      </div>
       <nav class="navbar navbar-default navbar-fixed-bottom">
 
</nav>
    <script src="app1.js"></script>
   </body>
</html>