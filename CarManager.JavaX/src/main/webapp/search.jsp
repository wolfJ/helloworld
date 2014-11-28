<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--<jsp:include page="link.jsp"/>--%>

<link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
<link rel="stylesheet" type="text/css" href="stylesheets/premium.css">
<link rel="stylesheet" type="text/css" href="stylesheets/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="lib/DataTables-1.10.4/media/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="lib/font-awesome/css/font-awesome.css">

<script language="javascript" src="lib/DataTables-1.10.4/media/js/jquery.js"></script>
<script type="text/javascript" src="lib/jQuery-Knob/js/jquery.knob.js"></script>
<%--<script language="javascript" src="lib/jquery-1.11.1.min.js"></script>--%>
<script language="javascript" src="javascripts/jquery-ui.min.js"></script>
<script language="javascript" src="lib/DataTables-1.10.4/media/js/jquery.dataTables.min.js"></script>

<script language="javascript" src="javascripts/datepicker-zh.js"></script>

<head>
    <title>查询</title>
</head>

<body class=" theme-blue">
<jsp:include page="top.jsp"/>

<style type="text/css">
    .table-tbody td {
        max-width: 100px;
        WORD-WRAP: break-word;
    }
</style>

<script type="text/javascript">
//    $(function() {
//        $(".knob").knob();
//    });
    //=========日期================
    $(function () {
        $("#dengJiRQS").datepicker({
            changeMonth: true, showButtonPanel: true});
        $("#dengJiRQE").datepicker({
            changeMonth: true, showButtonPanel: true});
        $("#baoXianRQS").datepicker({
            changeMonth: true, showButtonPanel: true});
        $("#baoXianRQE").datepicker({
            changeMonth: true, showButtonPanel: true});
    });
    //========================datatable==============
    // 表格刷新
    function flushPage() {
        $("#dataTable").refreshCurrent();
    }

    $(document).ready(function () {
        $('#dataTable').dataTable({
//            "sPaginationType": "full_numbers",
            "processing": true,
            "serverSide": true,
            "bDestroy": true,
            "sDom": 'rt<"bottom"lip>', // 元素布局
            "bPaginate": true,          // 翻页功能
//            "bLengthChange":true,      // 改变每页显示数据数量
            "bAutoWidth": false,        // 自动宽度
            "bStateSave": false,       // 保存条件等状态在cookie里
            "bSort": false,
            "bFilter": false,
            "bInfo": false,
            "sCharSet": "utf-8",
            "bLengthChange": false,
            "sServerMethod": "POST",
            "sAjaxSource": "queryx.do",
//            "fnServerData": function (sSource, aoData, fnCallback, oSettings) {
//                oSettings.jqXHR = $.ajax({
//                    "url": sSource,
//                    "contentType": "application/json; charset=utf-8",
//                    "success": fnCallback
//                });
//            },
            "fnServerParams": function (aoData) {
                aoData.push({ "name": "chePai", "value": $("#chePai").val() });
                aoData.push({ "name": "cheZhu", "value": $("#cheZhu").val() });
                aoData.push({ "name": "dianHua", "value": $("#dianHua").val() });
                aoData.push({ "name": "chePingPai", "value": $("#chePingPai").val() });
                aoData.push({ "name": "cheXinHao", "value": $("#cheXinHao").val() });
                aoData.push({ "name": "faDongJi", "value": $("#faDongJi").val() });
                aoData.push({ "name": "cheJiaHao", "value": $("#cheJiaHao").val() });
                aoData.push({ "name": "shenFengZheng", "value": $("#shenFengZheng").val() });
                aoData.push({ "name": "diZhi", "value": $("#diZhi").val() });
                aoData.push({ "name": "dengJiRQS", "value": $("#dengJiRQS").val() });
                aoData.push({ "name": "dengJiRQE", "value": $("#dengJiRQE").val() });
                aoData.push({ "name": "baoXianRQS", "value": $("#baoXianRQS").val() });
                aoData.push({ "name": "baoXianRQE", "value": $("#baoXianRQE").val() });
            },
            "aoColumns": [
                { "data": "chePai", "defaultContent": ""},
                { "data": "cheZhu", "defaultContent": ""},
                { "data": "dianHua", "defaultContent": ""},
                { "data": "chePingPai", "defaultContent": ""},
                { "data": "cheXinHao", "defaultContent": ""},
                { "data": "faDongJi", "defaultContent": ""},
                { "data": "cheJiaHao", "defaultContent": ""},
                { "data": "dengJiRQStr", "defaultContent": ""},
                { "data": "baoXianRQStr", "defaultContent": ""},
                { "data": "shenFengZheng", "defaultContent": ""},
                { "data": "diZhi", "defaultContent": ""}
            ],
            "oLanguage": {
                "sLengthMenu": "每页显示 _MENU_ 行",
                "sZeroRecords": "没有查到记录.",
                "sProcessing": '<img src="/images/ajax-loader-snake.gif"/>正在查询...',
                "sInfo": "当前第 _START_ - _END_ 行　共计 _TOTAL_ 行",
                "sInfoEmpty": "没有符合条件的记录",
                "sSearch": "搜索：",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast": "尾页"
                }
            }
        });
    });

    // 刷新 到第一页
    $.fn.refreshData = function () {
        var oTable = $(this).dataTable();
        oTable.fnPageChange("first");
    };

    // 刷新 到当前页
    $.fn.refreshCurrent = function () {
        var oTable = $(this).dataTable();
        oTable.fnPageChange(Number($("a[class=paginate_active]").text()) - 1);
    };
</script>


<div class="content">
    <h4 class="page-title">查询条件</h4>

    <form id="searchForm" onsubmit="$('#dataTable').refreshData();return false;">
        <table>
            <tr class="border_bottom">
                <td style="width:60px;" align="right">车牌</td>
                <td><input id="chePai" type="text" value="" style="width:180px" class="form-control"></td>
                <td style="width:60px" align="right">车主</td>
                <td><input id="cheZhu" type="text" value="" style="width:200px" class="form-control"></td>
                <td style="width:60px" align="right">电话</td>
                <td><input id="dianHua" type="text" value="" style="width:150px" class="form-control"></td>
            </tr>
            <tr class="border_bottom">
                <td style="width:60px" align="right">车辆品牌</td>
                <td><input id="chePingPai" type="text" value="" style="width:180px" class="form-control"></td>
                <td style="width:60px" align="right">车辆型号</td>
                <td><input id="cheXinHao" type="text" value="" style="width:200px" class="form-control"></td>
                <td style="width:60px" align="right">身份证</td>
                <td><input id="shenFengZheng" type="text" value="" style="width:150px" class="form-control"></td>
            </tr>
            <tr class="border_bottom">
                <td style="width:60px" align="right">发动机</td>
                <td><input id="faDongJi" type="text" value="" style="width:180px" class="form-control"></td>
                <td style="width:60px" align="right">车架号</td>
                <td><input id="cheJiaHao" type="text" value="" style="width:200px" class="form-control"></td>
                <td style="width:60px" align="right">地址</td>
                <td colspan="3"><input id="diZhi" type="text" value="" style="width:250px" class="form-control">
                </td>
            </tr>
            <tr class="border_bottom">
                <td align="right">登记日期</td>
                <td colspan="3">
                    <table>
                        <tr>
                            <td><input id="dengJiRQS" type="text" value="" readonly="true" style="width:120px"
                                       class="form-control">
                            </td>
                            <td>至</td>
                            <td><input id="dengJiRQE" type="text" value="" readonly="true" style="width:120px"
                                       class="form-control">
                            </td>
                        </tr>
                    </table>
                </td>
                <td align="right">保险到期</td>
                <td colspan="2">
                    <table>
                        <tr>
                            <td><input id="baoXianRQS" type="text" value="" readonly="true" style="width:120px"
                                       class="form-control">
                            </td>
                            <td>至</td>
                            <td><input id="baoXianRQE" type="text" value="" readonly="true" style="width:120px"
                                       class="form-control">
                            </td>
                        </tr>
                    </table>
                </td>
                <td><input type="button" id="btnSearch" value="查询" class="btn btn-primary"
                           onclick="$('#searchForm').submit();"
                           style="width:80px; margin-left: 20px;"/></td>
            </tr>
            <tr style="height:35px">
                <td colspan="6" align="right">
                </td>
            </tr>
        </table>
    </form>
    <div class="main-content">
        <div class="header">
            <table id="dataTable" class="table table-striped table-bordered table-tbody" cellspacing="0" width="100%">
                <%--class="display"--%>
                <thead>
                <tr>
                    <%--<th>序号</th>--%>
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

        </div>

        <jsp:include page="footer.jsp"></jsp:include>
    </div>
</div>

<script src="lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">

</script>

</body>
</html>
