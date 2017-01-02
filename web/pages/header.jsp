<%-- 
    Document   : header
    Created on : Nov 29, 2016, 7:13:20 PM
    Author     : USER
--%>

<header class="main-header">
    <!-- Logo -->
    <a href="Home" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>KSC</b></span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>KSC</b>-system</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">

                <!-- User Account: style can be found in dropdown.less 
                -------------------------------------------------------- -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <!--<img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">-->
                        <span class="hidden-xs"> Welcome : <c:out value="${fn:toUpperCase(sessionScope.userSession.fullname)}"/></span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="../distAdminLTE/img/avatar5.png" 
                                 class="img-circle" 
                                 alt="User Image">

                            <p>
                        <c:out value="${(sessionScope.userSession.name)}"/> - 
                        <c:if test="${(sessionScope.userSession.role) == 1}">
                            Staff KSC
                        </c:if>
                        <c:if test="${(sessionScope.userSession.role) == 2}">
                            Admin KSC
                        </c:if>    
                        <small>Member since <c:out value="${(sessionScope.userSession.dateRegistered)}"/></small>
                        </p>
                </li>
                <!-- Menu Body -->
                <li class="user-body">
                    <div class="row">
                        <div class="col-xs-4 text-center">
                            <a href="#">Followers</a>
                        </div>
                        <div class="col-xs-4 text-center">
                            <a href="#">Sales</a>
                        </div>
                        <div class="col-xs-4 text-center">
                            <a href="#">Friends</a>
                        </div>
                    </div>
                    <!-- /.row -->
                </li>
                <!-- Menu Footer-->
                <li class="user-footer">
                    <div class="pull-left">
                        <a href="#" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                        <a href="../Logout" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                </li>
            </ul>
            </li>
            
            <!-- Control Sidebar Toggle Button -->
            <li>
                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
            </li>
            </ul>
        </div>
    </nav>
</header>
