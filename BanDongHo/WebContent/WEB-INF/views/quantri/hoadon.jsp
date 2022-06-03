<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Start header section -->
<%-- <c:if test="${empty sessionScope.myNhanVien}">
	<c:redirect url="http://localhost:9999/BanDongHo/index/sign-in.htm" />
 		</c:if>--%>
<jsp:include page = "./headeradmin.jsp" flush = "true" />
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fabcart</title>
    <style>
        body{
            background-color: #F6F6F6; 
            margin: 0;
            padding: 0;
        }
        h1,h2,h3,h4,h5,h6{
            margin: 0;
            padding: 0;
        }
        p{
            margin: 0;
            padding: 0;
        }
        .container{
            width: 80%;
            margin-right: auto;
            margin-left: auto;
        }
        .brand-section{
           background-color: #0d1033;
           padding: 10px 40px;
        }
        .logo{
            width: 50%;
        }

        .row{
            display: flex;
            flex-wrap: wrap;
        }
        .col-6{
            width: 50%;
            flex: 0 0 auto;
        }
        .text-white{
            color: #fff;
        }
        .company-details{
            float: right;
            text-align: right;
        }
        .body-section{
            padding: 16px;
            border: 1px solid gray;
            background-color: white;
        }
        .heading{
            font-size: 20px;
            margin-bottom: 08px;
            color: black;
        }
        .sub-heading{
            color: #262626;
            margin-bottom: 05px;
        }
        table{
            background-color: #fff;
            width: 100%;
            border-collapse: collapse;
        }
        table thead tr{
            border: 1px solid #111;
            background-color: #f2f2f2;
        }
        table td {
            vertical-align: middle !important;
            text-align: center;
        }
        table th, table td {
            padding-top: 08px;
            padding-bottom: 08px;
        }
        .table-bordered{
            box-shadow: 0px 0px 5px 0.5px gray;
            background-color: white;
        }
        .table-bordered td, .table-bordered th {
            border: 1px solid #dee2e6;
            color: black;
        }
        .text-right{
            text-align: end;
        }
        .w-20{
            width: 20%;
        }
        .float-right{
            float: right;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="brand-section">
            <div class="row">
                <div class="col-6">
                    <h1 class="text-white">CARA</h1>
                </div>
                <div class="col-6">
                    <div class="company-details">
                        <p class="text-white">Người nhận: ${phieudat.HOTENNGUOINHAN}</p>
                        <p class="text-white">Địa chỉ: ${phieudat.DIACHI }</p>
                        <p class="text-white">Call: ${phieudat.SDT}</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="body-section">
            <div class="row">
                <div class="col-6">
                    <h2 class="heading">Mã phiếu đặt: ${phieudat.MAPD }</h2>
                    <p class="sub-heading">Ngày đặt: ${phieudat.NGAYDAT} </p>
                    <p class="sub-heading">Ngày giao(ước tính): ${phieudat.NGAYDAT} </p>
                    <p class="sub-heading">Email Address: ${phieudat.MAKH_PD.EMAIL} </p>
                </div>
                <div class="col-6">
                    <p class="sub-heading">Họ tên: ${phieudat.MAKH_PD.HOTEN } </p>
                 
                    <p class="sub-heading">Địa chỉ: ${phieudat.MAKH_PD.DIACHI} </p>
                    <p class="sub-heading">Phone Number:  ${phieudat.MAKH_PD.SDT}</p>
                 
                </div>
            </div>
        </div>

        <div class="body-section">
            <h3 class="heading">Sản phẩm đã đặt</h3>
            <br>
            <table class="table-bordered">
                <thead>
                    <tr>
                        <th>Sản phẩm</th>
                        <th class="w-20">Đơn giá</th>
                        <th class="w-20">Số lượng</th>
                        <th class="w-20">Tổng</th>
                    </tr>
                </thead>
                <tbody>
                	<c:set var="subtotal" value="0"/>
                	<c:forEach var="ctpd" items="${phieudat.CTPDS_PD }" >
	                	<tr>
	                        <td>${ctpd.DH_CTPD.TENDH}</td>
	                        <td>${ctpd.DONGIA}</td>
	                        <td>${ctpd.SOLUONG}</td>
	                        <td>${ctpd.DONGIA * ctpd.SOLUONG}</td>
	                        <c:set var="total" value="${ctpd.DONGIA * ctpd.SOLUONG}"/>
	                        <c:set var="subtotal" value="${subtotal + total }" />
	                    </tr>
                	</c:forEach>
                    
                    <tr>
                        <td colspan="3" class="text-right">Tổng tiền hàng </td>
                        <td> ${subtotal}</td>
                    </tr>
                    <tr>
                        <td colspan="3" class="text-right">Phí vận chuyển</td>
                        <td> 0</td>
                    </tr>
                    <tr>
                        <td colspan="3" class="text-right">Tổng tiền</td>
                        <td> ${subtotal}</td>
                    </tr>
                </tbody>
            </table>
            <br>
            <h3 class="heading">Trạng thái đơn: ${phieudat.TRANGTHAI }</h3>
            <h3 class="heading">Phương thức thanh toán: Thanh toán khi nhận hàng</h3>
        </div>

        <div class="body-section">
            <p>&copy; Copyright 2022 - Cara. All rights reserved. 
                <a href="/index.htm" class="float-right">www.cara.com</a>
            </p>
        </div>      
    </div>      

</body>
</html>