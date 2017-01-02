<%-- 
    Document   : bookpayment
    Created on : Dec 22, 2016, 12:41:35 AM
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

        <!-- Custom CSS -->
        <link href="../starter-template.css" rel="stylesheet">
        <style>
            html,
            body {
                height: 100%;
                background: no-repeat center center fixed; 
                background-color: rgb(228, 247, 215);
                background-image: url("assets/img/grass.jpg");
                background-image: linear-gradient(to bottom, rgba(0,0,0,0.7) 0%,rgba(0,0,0,0.9) 100%), url("../assets/img/futsal.jpg");

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
                <div class="container">
                    <h1 class="display-2">Booking detail</h1>
                    <h3>Booking number: <c:out value="${sessionScope.booking.uniqueID}"/></h3>
                    <div class="container">
                        <form>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Name:</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static mb-0"><c:out value="${sessionScope.customer.name}"/></p>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">NRIC: </label>
                                <div class="col-sm-10">
                                    <p class="form-control-static mb-0"><c:out value="${sessionScope.customer.nric}"/></p>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Booking Date:</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static mb-0"><c:out value="${sessionScope.booking.bookingDate}"/></p>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Schedule Date:</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static mb-0"><c:out value="${sessionScope.booking.scheduleDate}"/></p>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Court & Time Slot:</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static mb-0">
                                    <table class="table table-sm table-bordered">
                                        <thead>
                                            <tr>
                                                <th>Time Slot</th>
                                                <th>Court</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${sessionScope.courts}" var="court" >
                                                <tr>
                                                    <td><c:out value="${court.timeSlot}"/></td>
                                                    <td><c:out value="${court.courtNumber}"/></td>
                                                </tr>   
                                            </c:forEach>
                                        </tbody>                                            
                                    </table>
                                    </p>
                                </div>
                            </div> 
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Amount to be paid:</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static mb-0">SGD $<c:out value="${sessionScope.booking.amountAfter}"/></p>
                                </div>
                            </div>
                            <p class="lead">Please save your booking number for future references</p>
                        </form>
                    </div>                        
                </div>
                <hr class="my-2">
                <div class="container">
                    <h1>Upload receipt:</h1>
                    <p class="lead">Please make your payment to the account below as soon as possible:</p>
                    <p class="lead">*Use <strong>BOOKING NUMBER</strong> as transaction reference</p>
                    <p class="lead">Maybanks - Kovan Sports (12345678):</p>
                    <form method="POST"  action="insertbooking" enctype="multipart/form-data">
                        <div class="form-group">
                            <div class="input-group">
                                <label class="input-group-btn">
                                    <span class="btn btn-primary">
                                        Browse&hellip; 
                                        <input type="file"
                                               id="uploadReceipt"
                                               name="receipt"
                                               style="display: none;" required>
                                    </span>
                                </label>
                                <input type="text" id="textReceipt" class="form-control" placeholder="Upload receipt..." disabled>
                                <span class="input-group-btn">
                                    <button type="submit" id="checkBtn" class="btn btn-outline-primary">Proceed</button>
                                </span>
                                
                            </div>
                        </div>
                        
                    </form>
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
                    checked = $('#uploadReceipt')[0].files.length;

                    if (!checked) {
                        alert("You must upload receipt to continue");
                        $('#textReceipt').focus();
                        return false;
                    }

                });
            });
            
            $(function() {

                // We can attach the `fileselect` event to all file inputs on the page
                $(document).on('change', ':file', function() {
                  var input = $(this),
                      numFiles = input.get(0).files ? input.get(0).files.length : 1,
                      label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
                  input.trigger('fileselect', [numFiles, label]);
                });

                // We can watch for our custom `fileselect` event like this
                $(document).ready( function() {
                    $(':file').on('fileselect', function(event, numFiles, label) {

                        var input = $(this).parents('.input-group').find(':text'),
                            log = numFiles > 1 ? numFiles + ' files selected' : label;

                        if( input.length ) {
                            input.val(log);
                        } else {
                            if( log ) alert(log);
                        }

                    });
                });

              });
            
           


        </script>


    </body>
</html>
