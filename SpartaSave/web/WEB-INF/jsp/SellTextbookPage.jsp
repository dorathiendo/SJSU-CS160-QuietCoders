<%-- 
    Document   : SellTextbookPage
    Created on : Nov 17, 2014, 2:39:22 PM
    Author     : ckloveleen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="reset.css" />
        <link rel="stylesheet" href="style.css" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <header>
            <div class="container">
                <div class="left">
                </div>
                <div class="right">
               <a href="sell.html"><input type="submit" value="Sell Your Textbook(s)" class="gray-button yellow"></a>
                    </div>
                </div>
            </div>
        </header>
        <section class="body">
            <div class="container">
            <form id="form1" name="form1" method="post">
        <p>Please provide the following information in order to sell your textbook: 
</p>
        <p>
          <label for="textfield">Name:</label>
          <input type="text" name="textfield" id="textfield">
        </p>
        <p>
          <label for="email">Email Address:</label>
          <input type="email" name="email" id="email">
        </p>
        <p>
          <label for="email2">Confirm Email Address:</label>
          <input name="email2" type="email" required id="email2">
        </p>
        <p>
          <label for="textfield2">Posting Title:</label>
          <input name="textfield2" type="text" id="textfield2" size="40">
          <label for="textfield3">Textbook Edition:</label>
          <input name="textfield3" type="text" id="textfield3" size="6">
        </p>
        <p>
          <label for="textarea">Please provide additional information:</label><br>
          <textarea name="textarea" cols="60" rows="5" id="textarea"></textarea>
        </p>
        <p>How would you like to be contacted?</p>
        <p>
          <label>
            <input type="radio" name="Contact Form" value="Call" id="ContactForm_0">
          Call</label> 
          <label>
            <input type="radio" name="Contact Form" value="Text" id="ContactForm_1">
            Text</label> 
          <label>
            <input type="radio" name="Contact Form" value="Email " id="ContactForm_2">
            Email</label>
        </p>
        <p>
          <input type="submit" name="submit" id="submit" value="Submit">
           <input type="reset" name="reset" id="reset" value="Reset">
           <br>
        </p>
      </form>
            </div>
        </section>
    </body>