<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--<jsp:include page="link.jsp"/>--%>

<link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
<link rel="stylesheet" type="text/css" href="stylesheets/premium.css">
<link rel="stylesheet" type="text/css" href="stylesheets/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="lib/DataTables-1.10.4/media/css/jquery.dataTables.min.css">

<script language="javascript" src="lib/DataTables-1.10.4/media/js/jquery.js"></script>
<%--<script language="javascript" src="lib/jquery-1.11.1.min.js"></script>--%>
<script language="javascript" src="javascripts/jquery-ui.min.js"></script>
<script language="javascript" src="lib/DataTables-1.10.4/media/js/jquery.dataTables.min.js"></script>

<script language="javascript" src="javascripts/datepicker-zh.js"></script>

<head>
    <title>查询</title>
</head>

<body class=" theme-blue">
<jsp:include page="top.jsp"/>

<script type="text/javascript">
    //=========日期================
    $(function () {
        $("#datepickerS1").datepicker({
            changeMonth: true, showButtonPanel: true});
        $("#datepickerE1").datepicker({
            changeMonth: true, showButtonPanel: true});
        $("#datepickerS2").datepicker({
            changeMonth: true, showButtonPanel: true});
        $("#datepickerE2").datepicker({
            changeMonth: true, showButtonPanel: true});
    });
    //=========表格=========
    $(document).ready(function () {
        $('#dataTable').dataTable({
            "processing": true,
            "serverSide": true,
            "bSort": false,
            "bFilter": false,
            "bInfo": false,
            "bLengthChange": false,
            "sAjaxSource": "queryx.do",
            "fnServerData": function (sSource, aoData, fnCallback) {
                var postData = aoData.concat($('#searchForm').serializeArray());
                $.post(sSource, postData, function (json) {
                    fnCallback(json.data);
                }, "json");
            },
//"ajax": "queryx.do",
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


    //============查询============
    $("btnSearch").click(function () {

    });
</script>


<div class="content">
    <div class="header">
        <h1 class="page-title">查询条件</h1>

        <form id="searchForm" onsubmit="$('#dataTable').refreshData();return false;">
            <table>
                <tr class="border_bottom">
                    <td style="width:60px;" align="right">车牌</td>
                    <td><input name="chePai" type="text" value="" style="width:180px" class="form-control"></td>
                    <td style="width:60px" align="right">车主</td>
                    <td><input name="cheZhu" type="text" value="" style="width:200px" class="form-control"></td>
                    <td style="width:60px" align="right">电话</td>
                    <td><input name="dianHua" type="text" value="" style="width:150px" class="form-control"></td>
                </tr>
                <tr class="border_bottom">
                    <td style="width:60px" align="right">车辆品牌</td>
                    <td><input name="chePingPai" type="text" value="" style="width:180px" class="form-control"></td>
                    <td style="width:60px" align="right">车辆型号</td>
                    <td><input name="cheXinHao" type="text" value="" style="width:200px" class="form-control"></td>
                    <td style="width:60px" align="right">身份证</td>
                    <td><input name="shenFengZheng" type="text" value="" style="width:150px" class="form-control"></td>
                </tr>
                <tr class="border_bottom">
                    <td style="width:60px" align="right">发动机</td>
                    <td><input name="faDongJi" type="text" value="" style="width:180px" class="form-control"></td>
                    <td style="width:60px" align="right">车架号</td>
                    <td><input name="cheJiaHao" type="text" value="" style="width:200px" class="form-control"></td>
                    <td style="width:60px" align="right">地址</td>
                    <td colspan="3"><input name="diZhi" type="text" value="" style="width:250px" class="form-control">
                    </td>
                </tr>
                <tr class="border_bottom">
                    <td align="right">登记日期</td>
                    <td colspan="3">
                        <table>
                            <tr>
                                <td><input name="dengJiRQS" type="text" value="" style="width:120px"
                                           class="form-control"
                                           id="datepickerS1">
                                </td>
                                <td>至</td>
                                <td><input name="dengJiRQE" type="text" value="" style="width:120px"
                                           class="form-control"
                                           id="datepickerE1">
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td align="right">保险到期</td>
                    <td colspan="3">
                        <table>
                            <tr>
                                <td><input name="baoXianRQS" type="text" value="" style="width:120px"
                                           class="form-control"
                                           id="datepickerS2">
                                </td>
                                <td>至</td>
                                <td><input name="baoXianRQE" type="text" value="" style="width:120px"
                                           class="form-control"
                                           id="datepickerE2">
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr style="height:35px">
                    <td colspan="6" align="right">
                        <input type="button" id="btnSearch" value="查询" class="btn btn-primary" onclick="$('searchForm').submit();"
                               style="width:80px"/></td>
                </tr>
            </table>
        </form>
    </div>
    <div class="main-content">
        <div class="header">
            <table  id="dataTable" class="display" cellspacing="0" width="100%">
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

        </div>

        <jsp:include page="footer.jsp"></jsp:include>
    </div>
</div>

<script src="lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">

</script>

</body>
</html>
