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
                            <li id="sign_in"><a href="#">Sign In</a></li>
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
            <dvi class="sellbook_area">
                <h2>Please fill in the description of your textbook:</h2>
                
                    <form class="form-horizontal" action="AddBookServlet">
                    <div class="row">
                        <div class="col-sm-5 col-md-5">
                            <input type="text" placeholder="Textbook Title" name="title">
                        </div> 

                        <div class="col-sm-3 col-md-3">
                            <p style="display:inline; color:#000;">$</p><input type="text" style="width:87%;  display: inline" placeholder="Price" name="price">
                        </div> 

                        <div class="col-sm-4 col-md-4">
                            <select name="category">
                              <option>Category...</option>
                              <option>Accounting</option>
                              <option>Biology</option>
                              <option>Business</option>
                              <option>Chemistry</option>
                              <option>Computer Science</option>
                              <option>Engineering</option>
                              <option>English</option>
                              <option>Kinesiology</option>
                              <option>Mathematics</option>
                              <option>Social Science</option>
                              <option>Other</option>
                            </select>
                        </div> 
                    </div>

                    <div class="row">
                        <div class="col-sm-5 col-md-5">
                            <input type="text" placeholder="Author(s) Name" name="author">
                        </div> 

                        <div class="col-sm-3 col-md-3">
                            <input type="text" placeholder="ISBN" name="isbn">
                        </div> 

                        <div class="col-sm-4 col-md-4">
                            <select name="condition">
                              <option>Condition...</option>
                              <option>New</option>
                              <option>Like New</option>
                              <option>Used-Good</option>
                              <option>Used-Okay</option>
                              <option>Used-Bad</option>
                            </select>
                        </div> 
                    </div>
                   
                    <div class="row" style="margin-top:10px;">
                    <div class="col-sm-12 col-md-6">
                    <h2 style="color:#000;">Additional Information:</h2>
                    <textarea></textarea>
                    </div>
                    </div>
                    <div class="row">
                    <div class="col-sm-6 col-md-6 sellbook_button">                    
                        <button type="submit" class="submit">SUBMIT</button>
                        <button type="reset" class="reset">RESET</button>
                    </div>
                    </div>
                    </form>
                
            </dvi>
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
