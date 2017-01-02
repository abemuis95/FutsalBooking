<%-- 
    Document   : bookcourt
    Created on : Dec 20, 2016, 5:27:32 PM
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
                <div class="container" align="center">
                    <h1 class="display-4">Book Online | Court Layout</h1>
                    <img class="img-fluid mx-auto d-block" src="../dist/img/KovanWebLayout.jpeg" >
                    <hr class="my-2">
                    <div class="row">
                        <!-- left column -->
                        <div class="col-md-6">
                            <h3>Legend</h3>
                            <div class="table-responsive">
                                <table class="table table-bordered table-sm table-condensed">
                                    <thead class="thead-inverse">
                                        <tr>
                                            <th>Available</th>
                                            <th>Booked</th>
                                            <th>Booked and Paid</th>
                                            <th>Maintenance</th>                                    
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <div class="btn-group" data-toggle="buttons">
                                                    <label class="btn btn-outline-primary btn-sm" id="lblCheckBox">
                                                        Available
                                                    </label>
                                                </div>
                                            </td>
                                            <td class="bg-warning"></td>
                                            <td class="table-active"></td>
                                            <td class="table-danger"></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-md-6">

                        </div>
                    </div>
                </div>
                <div class="container" >
                    <hr class="my-2">
                    <h3>Slots for date :<small> 12/12/12</small></h3>
                    <form method="POST" action="../bookonline/cust-details">
                        <div class="container-fluid">
                            <div class="table-responsive">
                                <table class="table table-bordered table-sm">
                                    <thead class="thead-inverse">
                                        <tr>
                                            <th>Hour</th>
                                            <th>Court 1</th>
                                            <th>Court 2</th>
                                            <th>Court 3</th>
                                            <th>Court 4</th>
                                            <th>Court 5</th>
                                            <th>Court 6</th>
                                            <th>Court 7</th>
                                            <th>Court 8</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                        <c:forEach begin="1" end="5" varStatus="loop">
                                            <tr align="center">
                                                <c:choose>
                                                    <c:when test="${loop.index == 1}">
                                                        <th scope="row">18:00 - 19:00</th>
                                                        </c:when>
                                                        <c:when test="${loop.index == 2}">
                                                        <th scope="row">19:00 - 20:00</th>
                                                        </c:when>
                                                        <c:when test="${loop.index == 3}">
                                                        <th scope="row">20:00 - 21:00</th>
                                                        </c:when>
                                                        <c:when test="${loop.index == 4}">
                                                        <th scope="row">21:00 - 22:00</th>
                                                        </c:when>
                                                        <c:when test="${loop.index == 5}">
                                                        <th scope="row">22:00 - 22:30</th>
                                                        </c:when>                                
                                                    </c:choose>

                                                <c:forEach begin="1" end="8" varStatus="nestLoop">
                                                    <c:set value="${loop.index += nestLoop.index}" var="cBoxValue" />
                                                    <c:set var="codeListSize" value="${fn:length(requestScope.codeList)-1}" />
                                                    <c:choose>
                                                        <c:when test="${not empty requestScope.codeList}">
                                                            <c:forEach items="${requestScope.codeList}" var="code" varStatus="codeLoop">

                                                                    <c:if test="${(code == cBoxValue)}">
                                                                        <td class="table-active"><c:out value="${codeLoop.index}" /></td>
                                                                    </c:if>
                                                                    <c:otherwise>
                                                                        <c:if var="test" test="${(codeLoop.index == (codeListSize))}">
                                                                            <td> 
                                                                                <c:out value="${codeListSize}" />
                                                                                <c:out value="${codeLoop.index}" />
                                                                                <div class="btn-group" data-toggle="buttons">
                                                                                    <label class="btn btn-outline-primary btn-sm" id="lblCheckBox">
                                                                                        <input type="checkbox" 
                                                                                               autocomplete="off"
                                                                                               class="cCode"
                                                                                               name="courtTimeSlot"
                                                                                               value="<c:out value="${cBoxValue}" />"> Available <c:out value="${cBoxValue}" />
                                                                                    </label>
                                                                                </div>
                                                                            </td>
                                                                        </c:if>                                                                            
                                                                    </c:otherwise>    
                                                                </c:choose>
                                                            </c:forEach>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td> 
                                                                <div class="btn-group" data-toggle="buttons">
                                                                    <label class="btn btn-outline-primary btn-sm" id="lblCheckBox">
                                                                        <input type="checkbox" 
                                                                               autocomplete="off"
                                                                               class="cCode"
                                                                               name="courtTimeSlot"
                                                                               value="<c:out value="${cBoxValue}" />"> Available
                                                                    </label>
                                                                </div>
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </tr>
                                        </c:forEach>                
                                    </tbody>
                                </table>
                            </div>
                            <div class="float-xs-right">
                                <button class="btn btn-info" type="button" onclick="history.back()">
                                    <i class="fa fa-calendar" aria-hidden="true"></i> Choose other date
                                </button>
                                <button class="btn btn-outline-success" type="submit" id="checkBtn">
                                    Proceed <i class="fa fa-angle-double-right" aria-hidden="true"></i>
                                </button>
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
                    <a href="#">Privacy Policy</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">Terms and Conditions</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">FAQ</a> 
                </div>
                <!-- Default to the left -->
                <strong>Copyright &copy; 2016 <a href="http://utm.my">Kovan Sports Centre - @abemuisz</a>.</strong> All rights
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
                $('#checkBtn').click(function() {
                  checked = $("input[type=checkbox]:checked").length;

                  if(!checked) {
                    alert("You must choose at least one time slot");
                    return false;
                  }

                });
            });
            
            
        </script>


    </body>
</html>
