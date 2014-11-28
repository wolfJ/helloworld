<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
<link rel="stylesheet" type="text/css" href="stylesheets/premium.css">
<link rel="stylesheet" type="text/css" href="stylesheets/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="lib/DataTables-1.10.4/media/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="lib/font-awesome/css/font-awesome.css">


<script language="javascript" src="lib/DataTables-1.10.4/media/js/jquery.js"></script>
<script type="text/javascript" src="lib/jQuery-Knob/js/jquery.knob.js"></script>
<script language="javascript" src="lib/DataTables-1.10.4/media/js/jquery.dataTables.min.js"></script>
<script language="javascript" src="javascripts/jquery-ui.min.js"></script>
<script language="javascript" src="javascripts/datepicker-zh.js"></script>

<script type="application/javascript">
//    $(function() {
//        $(".knob").knob();
//    });
    //=========日期================

    //========================datatable==============
    // 表格刷新
    function flushPage() {
        $("#dataTable").refreshCurrent();
    }

    $(document).ready(function () {
        $('#dataTable').dataTable({
            "processing": true,
            "serverSide": true,
            "bDestroy": true,
            "sDom": 'rt<"bottom"lip>', // 元素布局
            "bPaginate": false,          // 翻页功能
            "bStateSave": false,       // 保存条件等状态在cookie里
            "bSort": false,
            "bFilter": false,
            "bInfo": false,
            "sCharSet": "utf-8",
            "bLengthChange": false,
            "sServerMethod": "POST",
            "sAjaxSource": "queryImportLog.do",

            "columns": [
                { "data": "id" },
                { "data": "importFileName" },
                { "data": "importTimeStr" }
            ]
        });
    });

</script>

<body class=" theme-blue">
<jsp:include page="top.jsp"/>


<div class="content">
    <div class="header">
        <h1 class="page-title">导入文件记录</h1>
    </div>

    <div class="main-content">
        <table id="dataTable" class="display" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>序号</th>
                <th style="max-width:100px;">导入文件名称</th>
                <th style="max-width:100px;">导入时间</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

</body>
</html>
