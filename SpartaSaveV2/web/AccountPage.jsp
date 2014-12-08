<%@page import="java.util.ArrayList"%>
<%@page import="Model.Book"%>
<%@page import="DAO.UserAccountDAO"%>
<%@page import="DAO.UserBookDAO"%>
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
        
<%
    // Redirect to index page if no user is signed in.
    Cookie[] allCookies = request.getCookies();
    String email = "0";
    for(Cookie cookie : allCookies)
    {
        if("useremail".equals(cookie.getName()))
        {
            email = cookie.getValue();
        }
    }
    
    if(email.equals("0"))
        response.sendRedirect("index.jsp");
%>
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
                            <li><a href="LogoutServlet">Sign Out</a></li>
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
                    <div class="col-sm-6 col-md-6">
                        <div class="wecome_text">
                            <h1>Welcome Back, 
                                <%
                                Cookie[] cookies = request.getCookies();

                                String userEmail = null;
                                for(Cookie cookie : cookies)
                                {
                                    if("useremail".equals(cookie.getName()))
                                    {
                                        userEmail = cookie.getValue();
                                    }
                                }
                                %>
                            <%=userEmail%>!</h1>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-6">
                        <div class="user_post_setting">
                            <ul>
                                <li><a href="AccountPage.jsp">Posting</a></li>
                                <li><a href="">|</a></li>
                                <li><a href="AccountSettings.jsp">Settings</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="showing_all_posting" >
                        <div class="panel panel-default">
                          <!-- Default panel contents -->
                          <div class="panel-heading">Showing all postings</div>

                          <!-- Table -->
                          <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                                <thead>
                                  <tr>
                                    <th>#</th>
                                    <th>Manage</th>
                                    <th>Textbook Title</th>
                                    <th>Category</th>
                                    <th>Condition</th>
                                    <th>Price</th>
                                    <th>Post Date</th>
                                  </tr>
                                </thead>
                                <tbody>
                                <%
                                    UserBookDAO userDAO = new UserBookDAO();
                                    UserAccountDAO accDAO = new UserAccountDAO();

                                    ArrayList<Book> listings;
                                    int count = 1;
                                    listings = userDAO.getUserBooks(accDAO.getUserID(email));
                                    for (Book b : listings)
                                    {
                                        String title = b.getTitle();
                                        String category = b.getCategory();
                                        String date = b.getPost_date();
                                        String condition = b.getBook_condition();
                                        int price = b.getPrice();
                                        int id = b.getId();
                                %>
                                  <tr>
                                    <td><%=count%></td>
                                    <td>
                                        <form name="DeleteBookButton" action="DeleteBookServlet" method="post">
                                             <input type="hidden" name="id" value="<%=id%>">
                                             <button type="submit">Delete</button>
                                        </form>
                                    </td>
                                    <td><%=title%></td>
                                    <td><%=category%></td>
                                    <td><%=condition%></td>
                                    <td>$<%=price%></td>
                                    <td><%=date%></td>
                                  </tr>
                                <%
                                    count++;
                                    }
                                %>
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
    <form action="LoginServlet" method="get">
        <input type="text" placeholder="Email Address" name="email">
        <input type="password" placeholder="password" name="password">
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
    <form name="NewUserForm" action="NewUserServlet" method="post">
        <input type="text" placeholder="First Name" name="first">
        <input type="text" placeholder="Last Name" name="last">
        <input type="text" placeholder="Enter an email address" name="email">
        <input type="password" placeholder="Enter a password" name="password">
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
