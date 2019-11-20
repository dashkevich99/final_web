<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>LOGIN</title>
    <style>
        .text_caption {
            text-transform: uppercase;
            font-size: 2rem;
            padding: 0.5rem 0;
            margin-left: 1rem;
        }

        .text-note {
            font-size: 1rem;
            text-transform: initial;
            padding: .7rem 0;
        }

        .container-fields {
            flex-wrap: wrap;
            margin: 0.5rem 0 0.5rem;
            justify-content: space-between;
        }

        .container-field {
            font-size: 2rem;
        }

        .container_input {
            font-size: 1rem;
            padding: 0.5rem 0.5rem 0.5rem 0.5rem;
            margin-bottom: 1rem;
            box-sizing: border-box;
            font-family: inherit;
            outline: none;
            width: 35%;
        }

        .container_button {
            display: flex;
            width: 5rem;
            padding: 0.5rem 0.5rem;
            font-size: 1rem;
            box-sizing: border-box;
            cursor: pointer;
        }

        .container {
            position: absolute;
            z-index: 9998;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            display: table;
            transition: opacity .3s ease;
        }

        .main-menu {
            width: 25%;
            padding: 20px 30px;
            border-radius: 2px;
            transition: all .3s ease;
            margin: auto;
        }
    </style>
</head>
<body>


<div class="container">
    <form class=" main-menu" action="start" method="post" name="contact_form">
        <div>
            <div class="text_caption"> Login</div>
            <div class="container-fields">
                <div class="container-field">
                    <input class="container_input" type="text" name="name" placeholder="Login" required/>
                </div>
                <div class="container-field">
                    <input class="container_input" type="password" name="password" placeholder="Password" required/>
                </div>
                <c:if test="${not empty errorMessage}">
                    <c:out value="${errorMessage}"/>
                </c:if>

                <div>
                    <button class="container_button" type="submit"> Log in</button>
                </div>

                <div class="text-note">
                    <%--@declare id="c"--%><label for="c">Don't have an account?</label>
                    <a href="registrate.jsp" class="c"> Registration</a>

                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>