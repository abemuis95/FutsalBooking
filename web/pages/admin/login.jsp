<%-- 
    Document   : login
    Created on : Nov 23, 2016, 11:22:50 AM
    Author     : USER
--%>

<%@ include file="../taglib.jsp" %>
<c:if test="${!empty sessionScope.userSession}">
    <c:redirect url="ksc-admin/Home" />
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KovanSports Centre</title>
        <link rel='shortcut icon' href='favicon.ico' type='image/x-icon' >
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <!-- Font Awesome -->
        <link rel="stylesheet" href="bootstrap/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="bootstrap/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="distAdminLTE/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="distAdminLTE/css/skins/skin-blue-light.min.css">
        <!-- Clean UI Styles -->
        <link rel="stylesheet" type="text/css" href="CleanUI/common/css/source/main.css">

        <%
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
            httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
            httpResponse.setDateHeader("Expires", 0); // Proxies.
        %>
        
    </head>
    <body>      
        <div class="container">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    <a href="<c:out value='${pageContext.request.contextPath}' /> ">
                        KovanSports
                        <small>Centre</small>
                    </a>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="<c:out value='${pageContext.request.contextPath}' />"><i class="fa fa-dashboard"></i> Home</a></li>
                    <li class="active">AdminLogin</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content" id="loginSection">
                <div class="single-page-block">
                    <div class="single-page-block-inner effect-3d-element" style="background: url(/images/folder/pokok01.jpg) ; border: 1px solid black;">
                        <div class="blur-placeholder"><!-- --></div>
                        <div class="single-page-block-form">
                            <h3 class="text-center">Login Admin</h3>
                            <br />
                            <form role="form" name="loginForm" method="post" action="SignIn" id="loginMainForm">
                                <div class="box-body">
                                    <c:if test="${sessionScope.loginError == true}">
                                        <div id="errormsg" class="alert alert-danger" >
                                            <a class="close" data-dismiss="alert" href="#">&times;</a>
                                            Incorrect Username or Password!
                                        </div>
                                    </c:if>
                                    <c:remove var="loginError" scope="session" />
                                    <div id="errormessage" class="alert alert-danger" style="display: none">
                                        <a class="close" data-dismiss="alert" href="#">&times;</a>
                                        Username and password must not be empty
                                    </div>
                                    <div class="form-group">
                                        <input id="usernameID"
                                               class="form-control"
                                               placeholder="Username"
                                               name="username"
                                               type="text" required>
                                    </div>
                                    <div class="form-group">
                                        <input id="passwordID"
                                               class="form-control password"
                                               name="password"
                                               type="password" 
                                               placeholder="Password" required>
                                    </div>
                                </div><!-- /.box-body -->
                                <div>
                                    <button type="reset" class="btn btn-default" >Cancel</button>&nbsp;
                                    <button type="submit" class="btn btn-info"  >Sign in</button>
                                </div><!-- /.box-footer -->
                            </form>
                        </div>
                    </div>
                </div>


            </section><!-- /.content -->

        </div><!-- /.container -->
        <!-- jQuery 2.2.3 -->
        <script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="distAdminLTE/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="distAdminLTE/js/demo.js"></script>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script language="JavaScript">
            function butOver(name1, src1) {
                document.images[name1].src = src1;
            }
            function butOut(name1, src1) {
                document.images[name1].src = src1;
            }
            function toUpperCase(item)
            {
                str = item.value;
                item.value = str.toUpperCase();
            }
            function toUpperCaseCheck(item)
            {
                str = item.value.replace(/[^a-zA-Z 0-9]+/g, '');
                item.value = str.toUpperCase();
            }
            function replaceTextPublic(item) {
                var str = item.value.replace(/[^a-zA-Z 0-9]+/g, '');
                item.value = str;
            }
            function replaceTextPublicSpecChar(item) {
                var str = item.value.replace(/[^a-zA-Z 0-9.:;!?@#$/-]+/g, '');
                item.value = str;
            }
            function maxLengthPublic(item, maxChars) {
                if (item.value.length > maxChars) {
                    alert("Kemasukan hanya dibenarkan sehingga " + maxChars + " aksara sahaja");
                    item.value = item.value.substring(0, maxChars);
                    return false;
                }
            }
        </script>
        <!-- v1.1.1 -->
        <script src="CleanUI/vendors/gsap/src/minified/TweenMax.min.js"></script>
        <!-- Clean UI Scripts -->
        <script src="CleanUI/common/js/common.js"></script>

        <script src="CleanUI/vendors/html5-form-validation/pure/jquery.validation.min.js"></script>
        <script src="CleanUI/vendors/bootstrap-show-password/bootstrap-show-password.min.js"></script>
        <!-- Own App Controller -->
        <script src="distAdminLTE/js/sampleLogin4Controller.js"></script>
    </body>
</html>
