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
    <link rel="stylesheet" href="profile_page.css">
    <title>My Profile</title>
</head>
<body style="background-image: linear-gradient(to bottom right, green, yellow);">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="topnav bg-dark">
        <a href="#home">Home</a>
        <a href="#about">About</a>
        <a href="#login">Login/Signup</a>
        <a href="#logout">Logout</a>
        <input type="text" placeholder="Search..">
        <button class="btn search" type="submit" style="float: right">Search</button>
    </div>
</nav>
<div class="container">
    <br>
    <div class="row">
        <div class="col-4">
            <div class="card " style="background-color: #6495ED">
                <img class="card-img-top img-responsive fit-image rounded-circle" id="player_img" src="https://zdnet1.cbsistatic.com/hub/i/r/2016/11/18/a99d0e89-4964-4237-873c-cb7fe6e8ed2c/resize/770xauto/5ddd856909064f39b5fab5794a6b1e45/linux-open-source-money-penguin.jpg" alt="Profile picture">
                <div class="card-body mx-auto">
                    <h3>${sessionScope.User.name}</h3>
                    <p>
                        Bio
                    </p>
                    <div class="dropdown">
                        <button class="btn btn-primary" type="button" id="edit_profile_button" >
                            Edit Profile
                        </button>
                        <div id="player_selector" aria-labelledby="selectPlayerButton">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-8">
            <div class="table table-responsive table-dark table-striped ">
                <table class="table">
                    <tr>
                        <th>Time since joining:</th>
                        <td><span id="membership_duration"></span></td>
                    </tr>
                    <tr>
                        <th>Portfolio Value:</th>
                        <td><span id="portfolio_value"></span></td>
                    </tr>
                    <tr>
                        <th>Stocks Owned:</th>
                        <td><span id="owned_stocks"></span> </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
