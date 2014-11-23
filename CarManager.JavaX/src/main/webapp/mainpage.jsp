<%--
  Created by IntelliJ IDEA.
  User: wolf
  Date: 2014/11/23
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>车牌管理系统</title>
    <jsp:include page="link.jsp" />
</head>

<body class=" theme-blue">

<script type="text/javascript">
    $(function () {
        var match = document.cookie.match(new RegExp('color=([^;]+)'));
        if (match) var color = match[1];
        if (color) {
            $('body').removeClass(function (index, css) {
                return (css.match(/\btheme-\S+/g) || []).join(' ')
            })
            $('body').addClass('theme-' + color);
        }

        $('[data-popover="true"]').popover({html: true});

    });
</script>
<jsp:include page="top.jsp"/>


<div class="content">
    <iframe src="/import.jsp" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" id="mainframe"
            name="mainframe"></iframe>
</div>


<script src="lib/bootstrap/js/bootstrap.js"></script>

</body>
</html>
