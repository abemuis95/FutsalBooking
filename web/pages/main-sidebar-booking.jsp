<%-- 
    Document   : main-sidebar.jsp
    Created on : Dec 11, 2016, 2:16:30 PM
    Author     : USER
--%>

<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="../distAdminLTE/img/avatar5.png" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p><c:out value="${sessionScope.userSession.name}"/></p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- search form -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                    <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                    </button>
                </span>
            </div>
        </form>
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>
            <li class="treeview">
                <a href="Home">
                    <i class="fa fa-dashboard"></i> <span>KSC Dashboard</span>
                </a>
            </li>
            <li class="treeview active">
                <a href="viewbooking">
                    <i class="fa fa-circle-o"></i> <span>Approved Booking</span>
                </a>
            </li>
            <li class="treeview">
                <a href="member">
                    <i class="fa fa-circle-o"></i> <span>Register Member</span>
                </a>
            </li>
            <% //when user is admin show this menu list on dashboard %>
            <c:choose>
                <c:when test="${sessionScope.userSession.role == 2}" >
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-angle-double-right"></i> <span>Admin </span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="staff"><i class="fa fa-circle-o"></i> Register Staff</a></li>
                            <li><a href="coupon"><i class="fa fa-circle-o"></i> Set Coupon Code</a></li>
                            <li><a href="report"><i class="fa fa-circle-o"></i> Report</a></li>
                        </ul>
                    </li>
                </c:when>
            </c:choose>
            <li class="treeview">
                <a href="../Logout">
                    <i class="fa fa-sign-out"></i> <span>Logout</span>
                </a>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>