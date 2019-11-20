<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main page</title>
    <style>
        a {
            text-decoration: none;
        }

        .container {
            position: fixed;
            z-index: 9998;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
        }

        .header {
            display: flex;
            background-color: #DDDDDD;
        }

        .header-login {
            font-size: 1.5rem;
        }

        .header-logout {
            display: flex;
            font-size: 1.5rem;
            text-decoration: none;
            position: relative;
            left: 2rem;
        }

        .text-caption {
            text-transform: uppercase;
            font-weight: 500;
            font-size: 1.2rem;
            padding: 1rem 0;
            margin-left: 1rem;
        }

        .container-button {
            width: 6rem;
            padding: 0.1rem 0;
            cursor: pointer;
        }

        .text-thead {
            font-size: 1.1rem;
        }

        .text-tbody {
            margin: 0 0 0 2rem;
            font-size: 1.1rem;
            background-color: #DDDDDD;
        }

        .container-table {
            margin-left: 1.5rem;
            margin-bottom: 1.2rem;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <div class="header-login">User name: ${user.login}</div>
        <div class="header-logout"><a href="logout">logout</a></div>
    </div>

    <div class="container-tasks">
        <form method="POST" id="rowDiv">
            <div class="text-caption">Tasks:
                <input
                        class="container-button"
                        type="button"
                        value="Today"
                        onclick='location.href = "tasks?section=today"'>
                <input
                        class="container-button"
                        type="button"
                        value="Tomorrow"
                        onclick='location.href = "tasks?section=tomorrow"'>
                <input
                        class="container-button"
                        type="button"
                        value="Someday"
                        onclick='location.href = "tasks?section=someday"'>
                <input
                        class="container-button"
                        type="button"
                        value="Fixed"
                        onclick='location.href = "tasks?section=fixed"'>
                <input
                        class="container-button"
                        type="button"
                        value="Recycle Bin"
                        onclick='location.href = "tasks?section=recycle_bin"'>
            </div>
            <div class="tasks">
                <table class="container-table">
                    <thead class="text-thead">
                    <tr>
                        <th>#</th>
                        <th>Date create</th>
                        <th>Header</th>
                        <th>Description</th>
                        <th>Fixed</th>
                        <th>Recycle bin</th>
                    </tr>
                    </thead>
                    <tbody class="text-tbody">
                    <c:forEach var="tasks" items="${tasks}">
                        <tr>
                            <td>
                                <input type="checkbox" name="task-${tasks.idTask}">
                            </td>
                            <td>${tasks.date}</td>
                            <td>${tasks.title}</td>
                            <td>${tasks.body}</td>
                            <td>${tasks.isReady}</td>
                            <td>${tasks.isRecycle}</td>

                            <td>
                                <c:choose>
                                    <c:when test="${tasks.url == 'Empty' }">
                                        <input
                                                type="button"
                                                value="add file"
                                                onclick='location.href = "UploadServlet?id=${tasks.idTask}"'/>
                                    </c:when>

                                    <c:otherwise>
                                        <input
                                                type="button"
                                                value="download file"
                                                onclick='location.href = "DownloadFileServlet?url=${tasks.url}"'/>
                                    </c:otherwise>
                                </c:choose>
                            </td>                          
                        </tr>
                         <input type="hidden" name="idTask" value="${tasks.idTask}"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:if test="${section == 'RECYCLE_BIN'}">
                <c:set var="Display" value="1"/>
            </c:if>
            <c:choose>
                <c:when test="${Display == 1}">
                    <input
                            class="container-button"
                            type="submit"
                            value="Restore"
                            formaction="restore"/>
                    <input
                            class="container-button"
                            type="submit"
                            value="Delete"
                            formaction="delete"/>
                    <input
                            class="container-button"
                            type="submit"
                            value="Delete All"
                            formaction="deleteAll"/>
                </c:when>
                <c:otherwise>
                    <input
                            class="container-button"
                            type="button"
                            value="add task"
                            onclick='location.href = "addTask?section=${section}"'/>
                    <input
                            class="container-button"
                            type="submit"
                            value="done"
                            formaction="done"/>
                    <input
                            class="container-button"
                            type="submit"
                            value="delete"
                            formaction="deleteInRecycle"/>
                </c:otherwise>
            </c:choose>
        </form>
    </div>
</div>
</body>
</html>