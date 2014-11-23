<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="link.jsp"/>
<head>
    <title>导入</title>
</head>

<body class=" theme-blue">
<jsp:include page="top.jsp"/>

<div class="content">
    <div class="header">
        <h1 class="page-title">数据导入</h1>
        <ul class="breadcrumb">
            <li class="active">1.选择待导入的文件</li>
            <li class="active">2.点击导入</li>
            <li class="active">3.查看导入结果</li>
        </ul>

    </div>

    <div class="main-content">
        <form id="form" action="/upload.do" target="async" enctype="multipart/form-data" method="post">
            <p> 选择待导入的xls文件:
                <input type="file" name="file" id="inputFile" onchange="change(this)" />
            </p>

            <p>&nbsp;</p>

            <p><input type="button" id="inputSubmit" class="btn btn-primary" value="导入" onclick="doSubmit();"/></p>

            <p>&nbsp;</p>

            <p>导入结果：</p>

            <div id="resultDiv"></div>
        </form>
        <IFRAME id="async" name="async" src="about:blank" frameborder='0' width="100%"></IFRAME>
    </div>

    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
<script src="lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
    var fileName = "";
    function change(o) {
        if (o.value.length > 0) {
            if (o.value.indexOf('.xls') > -1) {
                fileName = o.value;
                return;
            }
            else {
                alert("请选择.xls文件。");
                o.value = "";
                o.focus();
            }
        }
        fileName = ""
    }
    function doSubmit() {
        if (fileName != "") {
            var form = document.getElementById("form")
            form.submit();
            onMessage("正在上传文件，请稍等...");
            disableSubmitBtn(true);
        } else {
            alert("先选择文件.");
        }
    }
    function onMessage(msg) {
        var resultDiv = document.getElementById("resultDiv");
        resultDiv.innerHTML = msg;
    }
    function disableSubmitBtn(flag) {
        document.getElementById("inputSubmit").disabled = flag;
    }

    // 这个函数将来会被iframe用到
    function asyncFrameVal(val) {
        onMessage(decodeURIComponent(val));
        disableSubmitBtn(false);

    }

</script>
</html>