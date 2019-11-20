<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add task</title>
</head>
<body>
<div class="mainform">
<form class="contact_form" action="addTask" method="post" name="contact_form">
    <ul>
        <li>
            <h1>add task</h1>
        </li>
        <li>
            <%--@declare id="createdate"--%><label for="CreateDate">Date:</label>
            <input type="date" name="CreateDate" value="${dateTask}" required>
        </li>
        <li>
            <%--@declare id="title"--%><label for="title">Title</label>
            <input type="text" name="title" required>
        </li>
        <li>
            <%--@declare id="body"--%><label for="body">Body</label>
            <textarea name="body" rows="15" cols="80" required></textarea>
        </li>
        <li>
            <button class="submit" type="submit">Submit</button>
        </li>
    </ul>
</form>
</div>
</body>
</html>
