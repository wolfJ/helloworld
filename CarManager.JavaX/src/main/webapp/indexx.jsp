<html>
<body>
<h2>Hello World!</h2>

<form method="POST" enctype="multipart/form-data" action="/upload">
    File to upload:
    <input type="file" name="file" id="file"><br/> Name:<br/> <br/>
    <button type="submit" name="submit">Press here to upload the file!</button>
</form>
<%--<input type="text" name="name" id="name">--%>
<br/>
<%--enctype="multipart/form-data"--%>
<form action="/hello" method="post" >
    <input type="text" name="name">
    <button type="submit" name="submit">ClickMe!</button>
</form>
</body>
</html>
