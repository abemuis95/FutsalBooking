<%@ include file="taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
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

        <!-- Custom styles for this template -->
        <link href="cover.css" rel="stylesheet">
       
        
        
    </head>

    <body>

        <div class="site-wrapper">

            <div class="site-wrapper-inner">

                <div class="cover-container">

                    <div class="masthead clearfix">
                        <div class="inner">
                            <h3 class="masthead-brand">KovanSports<small> centre</small></h3>
                            <nav class="nav nav-masthead">
                                <a class="nav-link" href="<c:out value='${pageContext.request.contextPath}' />">Home</a>
                                <a class="nav-link" href="bookonline">Book Online</a>
                                <a class="nav-link  active" href="event">Events</a>
                                <a class="nav-link" href="contact-us">Contact</a>
                            </nav>
                        </div>
                    </div>
                        

                    

                    <div class="jumbotron-fluid" style="background-color: black">
                        <hr class="featurette-divider">

                        <div class="row featurette">
                            <div class="col-md-7">
                                <h2 class="featurette-heading">Tournament. <span class="text-muted">It'll blow your mind.</span></h2>
                                <p class="lead">Do join us for excitement and joyful tournament games at our arena.</p>
                            </div>
                            <div class="col-md-5">
                                <img class="featurette-image img-fluid mx-auto" src="dist\img\tournament.jpg" alt="Generic placeholder image">
                            </div>
                        </div>

                        <hr class="featurette-divider">

                        <div class="row featurette">
                            <div class="col-md-7 push-md-5">
                                <h2 class="featurette-heading">Family  Event. <span class="text-muted">See for yourself.</span></h2>
                                <p class="lead">Enjoy spending quality time with your Family at our place and have fun together.</p>
                            </div>
                            <div class="col-md-5 pull-md-7">
                                <img class="featurette-image img-fluid mx-auto" src="dist\img\familyevent.jpg" alt="Generic placeholder image">
                            </div>
                        </div>

                        <hr class="featurette-divider">

                        <div class="row featurette">
                            <div class="col-md-7">
                                <h2 class="featurette-heading">Company Event. <span class="text-muted">Checkmate.</span></h2>
                                <p class="lead"> Train and Build up trust among your workplace friends at our best training ground. </p>
                            </div>
                            <div class="col-md-5">
                                <img class="featurette-image img-fluid mx-auto" src="dist\img\companyevent.jpg" alt="Generic placeholder image">
                            </div>
                        </div>

                        <hr class="featurette-divider">

                    </div>

                    <div class="mastfoot">
                        <div class="inner"> 
                            <div class="float-xs-right">
                                <a href="ksc-admin">Login</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">About</a> 
                            </div>
                            <div>Futsal Booking System for <a href="https://getbootstrap.com">IP Project</a>, by <a href="https://twitter.com/mdo">@abemuisz</a>.</div>

                        </div>
                    </div>

                </div>

            </div>

        </div>

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
    </body>
</html>
