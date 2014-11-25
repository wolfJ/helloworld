<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<link rel="stylesheet" type="text/css" href="lib/DataTables-1.10.4/media/css/jquery.dataTables.min.css">
<script language="javascript" src="lib/DataTables-1.10.4/media/js/jquery.js"></script>
<script language="javascript" src="lib/DataTables-1.10.4/media/js/jquery.dataTables.min.js"></script>

<script type="application/javascript">
    $(document).ready(function () {
        $('#mainTable').dataTable({
            "processing": true,
            "serverSide": true,
            "bSort": false,
            "bFilter": false,
            "bInfo": false,
            "bLengthChange": false,
            "ajax": "queryx.do",
            "columns": [
                { "data": "id" },
                { "data": "chePai" },
                { "data": "cheZhu" },
                { "data": "dianHua" },
                { "data": "chePingPai" },
                { "data": "cheXinHao" },
                { "data": "faDongJi" },
                { "data": "cheJiaHao" },
                { "data": "dengJiRQ" },
                { "data": "baoXianRQ" },
                { "data": "shenFengZheng" },
                { "data": "diZhi" }
            ],
            "oLanguage": {
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "未查询到记录.",
                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据"
            },
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
            }
        });
    });
    //    $(document).ready(function () {
    //        $('#mainTable').dataTable({
    //            "processing": true,
    //            "serverSide": true,
    //            "ajax": "tmp.json",
    //            "columns": [
    //                { "data": "first_name" },
    //                { "data": "last_name" },
    //                { "data": "position" },
    //                { "data": "office" },
    //                { "data": "start_date" },
    //                { "data": "salary" }
    //            ]
    //        });
    //    });

</script>

<body>

<h2>Hello World</h2>

<br/>

<table id="mainTable" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>序号</th>
        <th style="max-width:100px;">车牌</th>
        <th style="max-width:100px;">车主</th>
        <th style="max-width:100px;">电话</th>
        <th style="max-width:100px;">车辆品牌</th>
        <th style="max-width:100px;">车辆型号</th>
        <th style="max-width:100px;">发动机</th>
        <th style="max-width:100px;">车架号</th>
        <th style="max-width:100px;">登记日期</th>
        <th style="max-width:100px;">保险到期</th>
        <th style="max-width:100px;">身份证号</th>
        <th style="max-width:100px;">地址</th>
    </tr>
    </thead>

</table>

<table id="example" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Position</th>
        <th>Office</th>
        <th>Start date</th>
        <th>Salary</th>
    </tr>
    </thead>
</table>

</body>
</html>
