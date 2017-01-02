<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                                <a class="nav-link" href="event">Events</a>
                                <a class="nav-link  active" href="contact-us">Contact</a>
                            </nav>
                        </div>
                    </div>

                    <div class="container">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">                        
                                <h2>Contact</h2>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 ">
                                <form id="contact-form" class="form" action="#" method="POST" role="form">
                                    <div class="form-group">
                                        <label class="form-label" for="name">Your Name</label>
                                        <input type="text" class="form-control" id="name" name="name" placeholder="Your name" tabindex="1" required>
                                    </div>                            
                                    <div class="form-group">
                                        <label class="form-label" for="email">Your Email</label>
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Your Email" tabindex="2" required>
                                    </div>                            
                                    <div class="form-group">
                                        <label class="form-label" for="subject">Subject</label>
                                        <input type="text" class="form-control" id="subject" name="subject" placeholder="Subject" tabindex="3">
                                    </div>                            
                                    <div class="form-group">
                                        <label class="form-label" for="message">Message</label>
                                        <textarea rows="5" cols="50" name="message" class="form-control" id="message" placeholder="Message..." tabindex="4" required></textarea>                                 
                                    </div>
                                    <div class="text-center">
                                        <button type="submit" class="btn btn-start-order">Send Message</button>
                                    </div>
                                </form>
                            </div>
                        </div>
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
