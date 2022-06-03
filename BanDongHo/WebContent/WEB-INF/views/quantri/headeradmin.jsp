<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Quản lí Đồng Hồ</title>
	<base href="${pageContext.servletContext.contextPath }/" />	
	<link rel="stylesheet" href="<c:url value='/resources/fontawesome-free-6.1.1-web/css/all.css' />" />
	<link rel="stylesheet" href="<c:url value='/resources/style.css' />" />
	<link href="resources/bootstrap5/css/bootstrap.css" rel = "stylesheet" >
	<link rel="icon" type="x-icon" href="imgweb/omega.jpg">
	
  <link href="resources/assets/css/pace.min.css" rel="stylesheet" />
  <link rel="icon" href="/resources/assets/images/favicon.ico" type="image/x-icon">
  <link href="resources/assets/css/bootstrap.min.css" rel="stylesheet" />
  <link href="resources/assets/css/icons.css" rel="stylesheet" type="text/css" />
  <link href="resources/assets/css/app-style.css" rel="stylesheet" />
  
<style type="text/css">
	*[id$=errors]{
			color: red;
			font-style: italic;
		}
     #menu ul {
      list-style-type: none;
      background: transparent;
      text-align: center;
    }

    #menu ul li {
      color: black;
      display: inline-table;
      width: 100px;
      height: auto;
      line-height: auto;
      position: relative;
      border-bottom: 5px;
      border-bottom-color: black;
      
    }

    #menu ul li a {
      color: black;
      text-decoration: none;
      display: block;
    }

    #menu ul li a:hover {
      
      color: green;
    }
    #menu ul li > .sub-menu {
      display: none;
      position: absolute;
      margin-left: -20px;
      padding-left: -20px;
    
    }
    
    #menu ul li:hover .sub-menu{
      display: block;
      background: white;
      position: absolute;
      text-align: center;
      border-radius: 10%;
    }

#form-input{
	padding: 20px;
	margin-bottom: 20px;
}
.form-input{
	background-color: beige;
	padding: 20px;
}

#cart{
	overflow: auto;
}

#cart table{
	width: 100%;
	border-collapse: collapse;
	table-layout: fixed;
	white-space: nowrap;
}

#cart table img{
	width: 70px;
}

#cart table td:nth-child(1) {
	width: 100px;
	text-align: center;
}

#cart table td:nth-child(2) {
	width: 150px;
	text-align: center;
}

#cart table td:nth-child(3) {
	width: 200px;
	text-align: center;
}

#cart table td:nth-child(4),
#cart table td:nth-child(5),
#cart table td:nth-child(6) {
	width: 150px;
	text-align: center;
}

#cart table td:nth-child(5) input{
	width: 70px;
	padding: 10px 5px 10px 15px;
}

#cart table thead{
	border: 1px solid #e2e9e1;
	border-left: none;
	border-right: none;
}

#cart table thead td{
	font-weight: 700;
	text-transform: uppercase;
	font-size: 13px;
	padding: 18px;
}

#cart table body tr td{
	padding-top: 15px;
}

#cart table body td{
	font-size: 13px;
}

#cart-add{
	display:flex;
	flex-wrap: wrap;
	justify-content: space-between;
}

#coupon{
	width: 50%;
	margin-bottom: 30px;
}

#coupon h3,
#subtotal h3{
	padding-bottom: 15px;
}

#coupon input{
	padding: 10px 20px;
	outline: none;
	width: 60%;
	margin-right: 10px;
	border: 1px solid;
}

#coupon button,
#subtotal button{
	background-color: #088178;
	color: #fff;
	padding: 12px 20px;
}

#subtotal{
	width: 50%;
	margin-bottom: 30px;
	border: 1px solid #e2e9e1;
	padding: 30px;
}

#subtotal table{
	border-collapse: collapse;
	width: 100%;
	margin-bottom: 20px;
}

.color-black{
	color: black;
}

#subtotal table td{
</style> 
	
</head>
<body class="bg-theme bg-theme1">

<section id="header">
		
		<a href="#"><img src="<c:url value='/img/logo.png' />" class="logo" alt=""></a>
		 <div class="d-flex align-items-start ">
	          <img src="imgweb/avatar.png"
			              alt=""
			              style="width: 45px; height: 45px"
			              class="rounded-circle" > 
	          <div class="ms-3">
	            <p class="fw-bold mb-1">${sessionScope.myNhanVien.MANV}</p>
	            <p class="text-muted mb-0">${sessionScope.myNhanVien.HOTEN}</p>
	          </div>
	        </div>
<%-- 		<li style="text-align: right;"><a href="index/sign-in.htm"> 
					<img src="imgweb/avatar.png"
			              alt=""
			              style="width: 45px; height: 45px"
			              class="d-flex align-items-center rounded-circle" > 
			         
				            <p class="ms-3 fw-bold mb-1">${i.MADH}</p>
				            <p class=" ms-3 text-muted mb-0">${i.TENDH}</p>
				       
			        </a></li> --%>
		<div id = "menu">
			<ul id="idnavbar">
				
				<li><a href="index.htm">Home</a></li>
				<li><a href="quantri/addNV.htm" style="width: 100px; padding-top: 10px; ">Thêm NV</a></li>
				<li><a href="quantri/index.htm">Shop</a></li>
				<li><a href="quantri/insertDH.htm" style="width: 100px;">Mặt hàng</a></li>
				<li>
					<a style="width: 100px;" href="quantri/dondathang.htm">Đơn đặt</a>
					<ul class="sub-menu" style="padding: 0px 0px 0px 0px; 	background: #e3e6f3; ">
						<li><a href="quantri/dondathang.htm" style="width: 100px; padding-top: 10px; ">Tra cứu</a></li>
						<li><a href="quantri/duyetdon.htm" style="width: 100px; padding-top: 5px;">Duyệt</a></li>
						<li><a href="quantri/hoadon.htm" style="width: 100px; padding-top: 5px;">Xem hóa đơn</a></li>
					</ul>
				</li>
				<li><a style="width: 100px;" href="nhaphang/insertPN.htm">Nhập hàng</a></li>
				<li><a href="index/log-out-admin.htm"> <img alt="Đăng xuất" src="imgweb/log-out.png" style="width: 30px; height: 30px;"> </a></li>
			</ul>
		</div>
	</section>
</body>
</html>