<%--
  Created by IntelliJ IDEA.
  User: MaxMac
  Date: 2019-10-29
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="signUp.css">
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
    <title>Sign Up / Login DOGTrade</title>
</head>
<body>
<h1>Click below to sign up for DOGTrade</h1>
<div class="container-fluid mb-3" style="display:inline-block; margin:auto;">
    <button onclick="document.getElementById('id01').style.display='block'">Sign Up</button>

    <!-- The Modal (contains the Sign Up form) -->
    <div id="id01" class="modal">
        <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">EXIT</span>
        <h1 align="center">Sign up for DOGTrade</h1>
        <form class="modal-content" method="POST">
            <div class="container">
                <h1>Sign Up</h1>
                <p>Please fill in this form to create an account.</p>
                <hr>
                <label><b>Email</b></label>
                <input type="text" placeholder="Enter Email" name="email" required>

                <label><b>First Name</b></label>
                <input type="text" placeholder="Enter First Name" name="firstName" required>

                <label><b>Last Name</b></label>
                <input type="text" placeholder="Enter Last Name" name="lastName" required>

                <label><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="username" required>

                <label><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>

                <label><b>Repeat Password</b></label>
                <input type="password" placeholder="Repeat Password" name="re-password" required>

                <label>
                    <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me
                </label>

                <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

                <div class="row">
                    <div class="col">
                        <button type="submit" class="signup" name="submit">Sign Up</button>
                    </div>
                    <div class="col">
                        <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
                    </div>

                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
