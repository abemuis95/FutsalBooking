<%-- 
    Document   : bookdetail
    Created on : Dec 21, 2016, 11:35:01 AM
    Author     : USER
--%>
<%@ include file="../taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../favicon.ico">

        <title>KovanSports Centre</title>

        <!-- Bootstrap core CSS -->
        <link href="../dist/css/bootstrap.min.css" rel="stylesheet">
        
        <!-- Font Awesome CSS -->
        <link href="../bootstrap/css/font-awesome.min.css" rel="stylesheet">
        
        <!-- Custom CSS -->
        <link href="../starter-template.css" rel="stylesheet">
        <style>
            html,
            body {
                height: 100%;
                background: no-repeat center center fixed; 
                background-color: rgb(228, 247, 215);
                background-image: url("../assets/img/grass.jpg");
                background-image: linear-gradient(to bottom, rgba(0,0,0,0.7) 0%,rgba(0,0,0,0.9) 100%), url("../assets/img/grass.jpg");

            }

        </style>


    </head>
    <body>
        <nav class="navbar navbar-dark navbar-fixed-top bg-inverse" >
            <div class="container">
                <button class="navbar-toggler hidden-lg-up" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"></button>
                <div class="collapse navbar-toggleable-md" id="navbarResponsive">
                    <h1><a class="navbar-brand mb-0" href="<c:out value='${pageContext.request.contextPath}' />">KovanSports<small> centre</small></a></h1>
                    <ul class="nav navbar-nav float-lg-right">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:out value='${pageContext.request.contextPath}' />">Home </a>
                        </li>
                        <li class="nav-item  active">
                            <a class="nav-link" href="#">Book Online <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Events</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Contacts</a>
                        </li>
                    </ul>
                </div>
            </div>                
        </nav>
        <div class="container">
            <div class="jumbotron  " style="background:white !important">
                <c:if test="${sessionScope.noMemberFound == true}">
                    <div class="alert alert-danger alert-dismissible fade in" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <strong>Oh No!</strong> It seems that you are not one of our member.
                    </div>
                    <c:set value="false" var="noMemberFound" scope="session" />  
                </c:if>
                 
                <div class="row">
                    <div class="col-md-5">
                        <div class="container">
                            <h1>Enter your details below:</h1>
                            <form method="POST" action="../GetCustDetail">
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" name="nric" placeholder="NRIC number*" required>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" name="name" placeholder="Your name*" required>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" name="telno" placeholder="Contact number*" required>
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control form-control-lg" name="email" placeholder="Email address*" required>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" name="coupon" placeholder="Coupon Code" >
                                </div>
                                <button type="submit" class="btn btn-lg btn-success">
                                    Proceed as guest <i class="fa fa-angle-double-right" aria-hidden="true"></i>
                                </button>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-2 ">
                    </div>
                    <div class="col-md-5">
                        <h1>Registered Member?</h1>
                        <p class="lead">Keep it hassle-free!
                            Just enter your NRIC and enjoy the member rate!</p>
                        <form class="form-inline" action="../GetMemberDetail">
                            <div class="form-group">                                
                                <input type="input" name="nric" class="form-control form-control-lg" placeholder="NRIC number" required>
                            </div>
                            <button type="submit" class="btn btn-lg btn-success">
                                Proceed <i class="fa fa-angle-double-right" aria-hidden="true"></i>
                            </button>
                        </form>
                    </div>
                </div>                  
            </div> 
        </div>
        <hr class="my-2">
        <footer class="jumbotron jumbotron-fluid" style="margin-bottom:0;">
            <div class="container">
                <div class="float-xs-right hidden-xs">
                    <a href="ksc-admin">Login</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">Terms and Conditions</a> 
                </div>
                <!-- Default to the left -->
                <strong>Copyright &copy; 2016 <a href="http://utm.my">Kovan Sports Centre - @fsociety</a>.</strong> All rights
                reserved.
            </div>
        </footer>


        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js" integrity="sha384-THPy051/pYDQGanwU6poAc/hOdQxjnOEXzbT+OuUAFqNqFjL+4IGLBgCJC3ZOShY" crossorigin="anonymous"></script>
        <script>
            window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>');
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js" integrity="sha384-Plbmg8JY28KFelvJVai01l8WyZzrYWG825m+cZ0eDDS1f7d/js6ikvy1+X+guPIB" crossorigin="anonymous"></script>
        <script src="../dist/js/bootstrap.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="../assets/js/ie10-viewport-bug-workaround.js"></script>
        <script>
            $(document).ready(function () {
                $('#checkBtn').click(function () {
                    checked = $("input[type=checkbox]:checked").length;

                    if (!checked) {
                        alert("You must check at least one checkbox.");
                        return false;
                    }

                });
            });


        </script>


    </body>
</html>

