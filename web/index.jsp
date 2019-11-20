<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tasks!</title>
    <style>
        .container {
            position: absolute;
            z-index: 9998;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, .8);
            display: table;
            transition: opacity .3s ease;
        }

        .main-menu {
            width: 50%;
            margin: auto;
            padding: 20px 30px;
            border-radius: 2px;
            transition: all .3s ease;
            background-color: #DDDDDD;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="main-menu">
        <a style="font-size: 24px; background-color: gainsboro; " href="login.jsp"> LOGIN<br></a>
        <a style="font-size: 24px; background-color: gainsboro; " href="registrate.jsp"> REGISTRATION<br></a>
        <p>Developed by Pavel Dashkevich 375447255070@ya.ru</p>
    </div>
</div>
</body>
</html>