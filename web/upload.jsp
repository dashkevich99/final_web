<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload File</title>
</head>
<body>

    <h1>Upload File</h1>
    <form method="post" action="UploadServlet" enctype="multipart/form-data">
        Select file to upload:
        <input type="file" name="file" size="60"/><br/>
        <input type="submit" value="Upload">
        <input type="hidden" name="idTask" value="${id}">
    </form>
</body>
</html>
