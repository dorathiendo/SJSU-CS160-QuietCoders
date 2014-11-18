<%-- 
    Document   : index
    Created on : Nov 17, 2014, 1:24:51 PM
    Author     : ckloveleen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="reset.css" />
        <link rel="stylesheet" href="style.css" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <header>
            <div class="container">
            <div class="left">
            	<a href="index.jsp">
                    </a>
                </div>
                <div class="right">
                        <a href="SellTextbookPage.jsp"><input type="submit" value="Sell Your Textbook(s)" class="gray-button yellow"></a>
                    </div>
                </div>
            </div>
        </header>
        <section class="body">
            <div class="container">
                <div class="left">
                    <div class="first">
                        <span class="fullw">Find Your Textbooks Fast and Cheap!</span>
                        <div style="width: 100%"><input type="text" class="input" placeholder="Enter the ISBN, Author or Title..." style="width: 450px;"></div>
                        <a href="search.html"><input type="submit" value="Search" class="gray-button blue" style="margin-right: 65px;"></a>
                    </div>
                    <div class="second">
                        <span class="fullw">Or Enter in Your Course(s):</span>
                        <input type="text" class="input" placeholder="Department">
                        <input type="text" class="input" placeholder="Course#">
                        <input type="text" class="input" placeholder="Section#" style="margin-right: 0;">
                        <input type="text" class="input" placeholder="Department">
                        <input type="text" class="input" placeholder="Course#">
                        <input type="text" class="input" placeholder="Section#" style="margin-right: 0;">
                        <input type="submit" value="Go" class="gray-button green">
                    </div>
                    <div class="third">
                        <a href="SignInPage.jsp">Already have an account? Sign-in!</a>
                    </div>
                </div>
                <div class="right">
                    <div class="content">
                        <p class="center">SJSU Bookstore Hours</p>
                        <div class="white">
                            <ul class="fleft">
                                <li>Monday</li>
                                <li>Tuesday</li>
                                <li>Wednesday</li>				
                                <li>Thursday</li>				
                                <li>Friday</li>				
                                <li>Saturday</li>				
                                <li>Sunday</li>				
                            </ul>
                            <ul class="fright">
                                <li>7.30AM-6.00PM</li>
                                <li>7.30AM-6.00PM</li>
                                <li>7.30AM-6.00PM</li>
                                <li>7.30AM-6.00PM</li>
                                <li>7.30AM-6.00PM</li>
                                <li>Closed</li>
                                <li>Closed</li>
                            </ul>
                        </div>
                        <p class="center">MLK Library Hours</p>
                        <div class="white" style="margin-bottom: 0;">
                            <ul class="fleft">
                                <li>Student Hours</li> 
                                <li>Monday-Thursday</li> 
                                <li>Friday</li> 
                                <li>Saturday</li> 
                                <li>Sunday</li> 
                            </ul>
                            <ul class="fright">
                                <li> &nbsp;</li>
                                <li>8:00AM-1.00AM*</li>
                                <li>8.00AM-6.00AM</li>
                                <li>9.00AM-6.00PM</li>
                                <li>1.00PM-1.00AM*</li>
                            </ul>
                        </div>
                        &nbsp;
                    </div>
                </div>
            </div>
        </section>
        <section class="footer">
            <div class="container">
                Copy-Rights Reserved
            </div>
        </section>
    </body>
</html>
