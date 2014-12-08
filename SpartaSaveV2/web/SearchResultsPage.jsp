<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
        <link href='http://fonts.googleapis.com/css?family=Source+Code+Pro:400,300,500,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
        
         <link href="css/animate.css" rel="stylesheet" type="text/css">           
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">                    
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="responsive.css">
    <script src="js/vendor/modernizr-2.6.2.min.js"></script>
    </head>
    <body>
        <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

        <!-- Add your site or application content here -->

<div class="top_area"><!-- start top area -->
    <div class="header">
        <div class="container">
            <div class="row">
                <div class="col-sm-5 col-md-5 col-lg-5">
                    <div class="logo">
                     <a href="index.jsp">
                        <img src="img/logo.png" alt="">
                    </a>
                    </div>
                </div>
                <div class="col-sm-7 col-md-7 col-lg-7">
                    <div class="navigation">
                        <ul class="menu">
                            <li><a href="SellTextbook.jsp">Sell Textbook</a></li>
                            <li><a href="index.jsp">Sign Out</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="search">
        <div class="container">
            <div class="row">
                <div class="col-md-2 col-lg-2"></div>
                <div class="col-md-8 col-lg-8">
                    <form action="SearchServlet" method="get" role="search">
                        <div class="input-group add-on">
                          <input class="form-control" placeholder="Enter title, author, or ISBN..." name="search" id="srch-term" type="text">
                          <div class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                <i class="glyphicon glyphicon-search"></i>
                            </button>
                          </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-2 col-lg-2"></div>
            </div>             
        </div>
    </div>

</div><!-- end top area -->

<div class="content_area"><!-- start content area -->
    <div class="container">
        <p>We search through various sites to find you best price!</p>
    </div>
</div><!-- end content area -->

<div class="footer_area"><!-- start footer area -->
    <div class="widgets">
        <div class="container">
            <div class="user_area">
                <div class="row">

                <div class="row">
                    <div class="showing_all_posting" >
                        <div class="panel panel-default">
                          <!-- Default panel contents -->
                          <div class="panel-heading">Showing all Search Result</div>

                          <!-- Table -->
                          <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                                <thead>
                                  <tr>
                                    <th class="col-xs-4 col-sm-3 col-md-2 col-lg-2" >Image</th>
                                    <th class="col-xs-8 col-sm-9 col-md-10 col-lg-10">Description</th>
                                  </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${titleSearch}" var="book">
                                        <c:if test = "${book.authors == null}">
                                        <tr>
                                            <td><img src="<c:out value="${book.imageURL}"/>"></td>
                                            <td>
                                                Title: <c:out value="${book.title}"/><br />
                                                Author: <c:out value="${book.author}"/><br />
                                                ISBN: <c:out value="${book.isbn}"/><br />
                                                Price: $<c:out value="${book.price}"/><br />
                                                Condition: <c:out value="${book.book_condition}"/><br />
                                                Post Date: <c:out value="${book.post_date}"/><br />
                                            </td>
                                        </c:if>
                                        <c:if test = "${book.authors != null}">
                                        <tr>
                                            <td><img src="<c:out value="${book.imageURL}"/>"></td>
                                            <td>
                                                Title: <a href="<c:out value="${book.detailPageURL}"/>"> <c:out value="${book.title}"/></a><br />
                                                Author: <c:out value="${book.authors}"/><br />
                                                ISBN: <c:out value="${book.isbn}"/><br />
                                                List Price: <c:out value="${book.listPrice}"/><br />
                                                Lowest New Price: <c:out value="${book.lowestNewPrice}"/><br />
                                                Lowest Price: <c:out value="${book.lowestPrice}"/><br />
                                            </td>
                                        </c:if>
                                        </tr>
                                    </c:forEach>

                                  </tr>
                                </tbody>
                              </table>
                              </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div><!-- end footer area -->


<div class="popup_area">
<div class="popup">

<div class="login_area">
<div class="login">
    <h2>Login to SpartaSave</h2>
    <form action="">
        <input type="text" placeholder="Email Address">
        <input type="password" placeholder="password">
        <button class="submin" type="submit">Login</button>
    </form>
</div>

<div class="popup_bottom">
    <p>Not a member yet? <a class="signup_button" href="#">Sign up!</a> </p>
</div>

</div>

<div class="signup_area">
<div class="login signup">
    <h2>Join SpartaSave</h2>
    <p>and start selling your textbooks!</p>
    <form action="">
        <input type="text" placeholder="First Name">
        <input type="text" placeholder="Last Name">
        <input type="text" placeholder="Enter an email address">
        <input type="password" placeholder="Enter an password">
        <button class="submin" type="submit">Join</button>
    </form>
</div>

<div class="popup_bottom">
    <p>Already a member? <a href="#" class="signin_button">Sign in!</a> </p>
</div>

</div>

 <a href="#" class="close_popup"><i class="fa fa-close"></i></a>   
</div>
</div>

    <script src="js/vendor/jquery-1.10.2.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
       <script src="js/main.js"></script>
        

 <script>
jQuery(document).ready(function($) {
    $(".popup_area").hide();
    $(".signup_area").hide();
    $("#sign_in").click(function() {
        $(".popup_area").show();
    });

    $(".close_popup").click(function() {
        $(".popup_area").hide();
    });

    $(".signup_button").click(function() {
            $(".login_area").hide();
            $(".signup_area").show();
    });
    $(".signin_button").click(function() {                
                $(".signup_area").hide();
                $(".login_area").show();
        });





});
 </script>
 



</body>
</html>
