<%-- 
    Document   : bookdate
    Created on : Dec 20, 2016, 9:20:15 PM
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
        <link rel="icon" href="favicon.ico">

        <title>KovanSports Centre</title>

        <!-- Bootstrap core CSS -->
        <link href="dist/css/bootstrap.min.css" rel="stylesheet">
        
        <link href="bootstrap/css/font-awesome.min.css" rel="stylesheet">
        <!-- Date Picker -->
        <link rel="stylesheet" href="plugins/datepicker/datepicker3.css">

        <!-- Custom CSS -->
        <link href="starter-template.css" rel="stylesheet">
        <style>
            html,
            body {
                height: 100%;
                background: no-repeat center center fixed; 
                background-color: rgb(228, 247, 215);
                background-image: url("assets/img/grass.jpg");
                background-image: linear-gradient(to bottom, rgba(0,0,0,0.7) 0%,rgba(0,0,0,0.9) 100%), url("assets/img/futsal.jpg");
                
            }
            
            
            
            .navbar-dark .navbar-nav .active>.nav-link, .navbar-dark .navbar-nav .active>.nav-link:focus, .navbar-dark .navbar-nav .active>.nav-link:hover, .navbar-dark .navbar-nav .nav-link.active, .navbar-dark .navbar-nav .nav-link.active:focus, .navbar-dark .navbar-nav .nav-link.active:hover, .navbar-dark .navbar-nav .nav-link.open, .navbar-dark .navbar-nav .nav-link.open:focus, .navbar-dark .navbar-nav .nav-link.open:hover, .navbar-dark .navbar-nav .open>.nav-link, .navbar-dark .navbar-nav .open>.nav-link:focus, .navbar-dark .navbar-nav .open>.nav-link:hover {
                color: #fff;
                border-bottom-color: #fff;
            }
            
            
            .nav-item .nav-link {
                padding: .25rem 0;
                font-weight: bold;
                color: rgba(255,255,255,.5);
                background-color: transparent;
                border-bottom: .25rem solid transparent;
            }

            .nav-item .nav-link:hover,
            .nav-item .nav-link:focus {
                border-bottom-color: rgba(255,255,255,.25);
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
                        <li class="nav-item active">
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
                <div class="container" align="center">
                    <h1 class="display-4">Book Online | Court Layout</h1>
                    <img class="img-fluid mx-auto d-block" src="dist/img/KovanWebLayout.jpeg" >
                </div>
                <hr class="my-2">
                <div class="container">
                    <div class="row">
                        <!-- left column -->
                        <div class="col-md-6" >
                            <p class="lead"><strong>Schedule your match date</strong></p>
                            <form action="bookonline/time-court" class="form-inline">
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar-o" aria-hidden="true"></i>
                                    </div>
                                    <input type="text" 
                                           class="form-control" id="tarikh" 
                                           name="scheduleDate"
                                           style="cursor:pointer; background-color: #FFFFFF"
                                           required readonly>                                    
                                </div>
                                <button class="btn btn-primary" type="submit">Proceed</button>
                            </form>
                        </div>
                        <!-- right column -->
                        <div class="col-md-6">
                            <p class="lead float-xs-right"><strong>or search your booking to check the status</strong></p>
                            <form action="bookonline/search-book" class="form-inline float-xs-right">
                                <input type="text" placeholder="Booking No" 
                                       class="form-control" name="uniqueID" required>
                                <button class="btn btn-secondary" type="submit">
                                    <i class="fa fa-search" aria-hidden="true"></i>Search
                                </button>
                            </form>
                        </div>
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
        <script src="dist/js/bootstrap.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="assets/js/ie10-viewport-bug-workaround.js"></script>
        <script src="moment.min.js"></script>
        <!-- bootstrap datepicker -->
        <script src="plugins/datepicker/bootstrap-datepicker.js"></script>
        
        <script src="bootstrap-datetimepicker.min.js"></script>

        <script>
            var todayDate = new Date();
            //Date picker
            $('#tarikh').datepicker({
                todayHighlight: true,
                startDate: todayDate,
                autoclose: true,
                format: "dd-mm-yyyy"
            });
            
           
            $('#tarikh').val(moment().format('DD-MM-YYYY'));
        </script>
         
                
                
            

    </body>
</html>

