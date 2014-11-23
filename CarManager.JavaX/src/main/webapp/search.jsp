<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="link.jsp"/>

<head>
    <title>查询</title>
</head>

<body class=" theme-blue">
<jsp:include page="top.jsp"/>

<script type="text/javascript">
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
</script>


<div class="content">
    <div class="header">
        <h1 class="page-title">查询条件</h1>
        <table>
            <tr class="border_bottom">
                <td style="width:60px;" align="right">车牌</td>
                <td><input type="text" value="" style="width:180px" class="form-control"></td>
                <td style="width:60px" align="right">车主</td>
                <td><input type="text" value="" style="width:200px" class="form-control"></td>
                <td style="width:60px" align="right">电话</td>
                <td><input type="text" value="" style="width:150px" class="form-control"></td>
            </tr>
            <tr class="border_bottom">
                <td style="width:60px" align="right">车辆品牌</td>
                <td><input type="text" value="" style="width:180px" class="form-control"></td>
                <td style="width:60px" align="right">车辆型号</td>
                <td><input type="text" value="" style="width:200px" class="form-control"></td>
                <td style="width:60px" align="right">身份证</td>
                <td><input type="text" value="" style="width:150px" class="form-control"></td>
            </tr>
            <tr class="border_bottom">
                <td style="width:60px" align="right">发动机</td>
                <td><input type="text" value="" style="width:180px" class="form-control"></td>
                <td style="width:60px" align="right">车架号</td>
                <td><input type="text" value="" style="width:200px" class="form-control"></td>
                <td style="width:60px" align="right">地址</td>
                <td colspan="3"><input type="text" value="" style="width:250px" class="form-control"></td>
            </tr>
            <tr class="border_bottom">
                <td align="right">登记日期</td>
                <td colspan="3">
                    <table>
                        <tr>
                            <td><input type="text" value="" style="width:120px" class="form-control" id="datepickerS1">
                            </td>
                            <td>至</td>
                            <td><input type="text" value="" style="width:120px" class="form-control" id="datepickerE1">
                            </td>
                        </tr>
                    </table>
                </td>
                <td align="right">保险到期</td>
                <td colspan="3">
                    <table>
                        <tr>
                            <td><input type="text" value="" style="width:120px" class="form-control" id="datepickerS2">
                            </td>
                            <td>至</td>
                            <td><input type="text" value="" style="width:120px" class="form-control" id="datepickerE2">
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr style="height:35px">
                <td colspan="6" align="right"><input type="button" value="查询" class="btn btn-primary"
                                                     style="width:80px"/></td>
            </tr>
        </table>

    </div>
    <div class="main-content">

        <div class="header">
            <li class="active">查询结果</li>
            </ul>

            <table class="table" style="overflow:scroll;">
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
                <tbody>
                <tr>
                    <td>1</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">苏AN331D</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">郭玥</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">13851888197</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">一汽解放青岛汽车有限公司</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">CA5043XXYP40K2L1EA84-3</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">01991983</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">LFNA4LBA7DAD83141</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">320106198108073223</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">2014/1/4</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">2014/1/4</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">地址一</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">苏AN366D</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">郭玥</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">13851888197</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">一汽解放青岛汽车有限公司</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">CA5043XXYP40K2L1EA84-3</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">01991983</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">LFNA4LBA7DAD83141</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">320106198108073223</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">2014/1/4</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">2014/1/4</td>
                    <td style="max-width:100px;WORD-WRAP: break-word;">地址一</td>
                </tr>
                </tbody>
            </table>

            <ul class="pagination">
                <li><a href="#">&laquo;</a></li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">&raquo;</a></li>
            </ul>
        </div>

        <jsp:include page="footer.jsp"></jsp:include>
    </div>
</div>

<script src="lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
    $("[rel=tooltip]").tooltip();
    $(function() {
        $('.demo-cancel-click').click(function(){return false;});
    });
</script>

</body>
</html>
