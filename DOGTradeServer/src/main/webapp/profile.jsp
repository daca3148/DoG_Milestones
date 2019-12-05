
<%--
  Created by IntelliJ IDEA.
  User: MaxMac
  Date: 2019-11-03
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
    <script src="profile_page.js"></script>
    <link rel="stylesheet" href="profile_page.css">
    <title>My Profile</title>
</head>
<!---<body style="background-image: linear-gradient(to bottom right, green, yellow);">-->
<body style="background-image: linear-gradient(to bottom, #024166, #34a8eb);">
    <nav class="navbar navbar-dark bg-dark">
        <div class="col-1">
            <a href="http://dogtrader-env.kgd6nfmk8q.us-east-1.elasticbeanstalk.com/home" class="btn btn-secondary" style="width:100%">Home</a>
        </div>
        <div class="col-1">
          <a href="#about" class="btn btn-secondary" style="width:100%; margin-left:-1.6em">About</a>
        </div>
        <div class="col-8">
            <form class="form-inline" style="width:100%" method="post">
                <input class="form-control mr-sm-2 col-10" type="search" placeholder="Search" aria-label="Search" name="search">
                <button class="btn btn-outline-success my-2 my-sm-0 col-1" type="submit">Search</button>
            </form>
        </div>
        <div class="col-1" style="width:100%">
          <a href="#login" class="btn btn-secondary">Login/Signup</a>
        </div>
        <div class="col-1" style="width:100%">
          <a href="#logout" class="btn btn-secondary">Logout</a>
        </div>
    </nav>
<div class="container">
    <br>
    <div class="row">
        <div class="col-4">
            <div class="card " style="background-color: #6495ED">
                <div class="container" id='img_div'>
                  <img class="card-img-top img-responsive fit-image rounded-circle" id="user_img" style="border:0.3em solid #6491DD"
                  src="https://zdnet1.cbsistatic.com/hub/i/r/2016/11/18/a99d0e89-4964-4237-873c-cb7fe6e8ed2c/resize/770xauto/5ddd856909064f39b5fab5794a6b1e45/linux-open-source-money-penguin.jpg"
                  alt="Profile picture">
                </div>
                <div class="card-body mx-auto">
                    <h3>${sessionScope.User.name}</h3>
                    <p id="bio">Bio</p>
                    <div class="container">
                        <button class="btn btn-primary" onclick="editProfile()" type="button" id="edit_profile_button" >Edit Profile</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-8">
            <div class="row">
              <div class="container rounded bg-dark" style="margin-top:-0.2em; margin-bottom:1em; margin-left:1em; margin-right:1em">
                  <h1 style="text-align:center; -webkit-text-stroke: 0.02em skyblue; font-weight:bold; font-family: 'Comic Sans MS'">
                    Time since joining:
                  </h1>
                  <p style="font-size:4em; text-align:center; color:skyblue; font-family: 'Comic Sans MS'">
                  2000 Days
                  </p>
              </div>
            </div>
            <div class="row">
              <div class="container rounded bg-dark" style="margin:1em">
                  <h1 style="text-align:center; -webkit-text-stroke: 0.02em skyblue; font-weight:bold; font-family: 'Comic Sans MS'">
                    Portfolio Value:
                  </h1>
                  <p style="font-size:4em; text-align:center; color:skyblue; font-family: 'Comic Sans MS'">
                  $2000
                  </p>
              </div>
            </div>
            <div class="row">
              <div class="container rounded bg-dark" style="margin:1em">
                  <h1 style="text-align:center; -webkit-text-stroke: 0.02em skyblue; font-weight:bold; font-family: 'Comic Sans MS'">
                    Stocks Owned:
                  </h1>
                  <p style="font-size:4em; text-align:center; color:skyblue; font-family: 'Comic Sans MS'">
                  2000
                  </p>
              </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
