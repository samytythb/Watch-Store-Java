<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Cart</title>
<link rel="stylesheet" href="<c:url value='/resources/FontAweSomeWeb/css/all.css' />" />
<link rel="stylesheet" href="<c:url value='/resources/style.css' />" />
<style type="text/css">
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

#coupon #subtotal input{
	width: inherit;
 	
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

#subtotal table td{
	width: 50%;
	border: 1px solid #e2e9e1;
	padding: 10px;
	font-size: 13px;
}

footer{
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
}

footer .col{
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	margin-bottom: 20px;
}

footer .logo{
	margin-bottom: 30px;
}

footer h4{
	font-size: 14px;
	padding-bottom: 20px;
}

footer p{
	font-size: 13px;
	margin: 0 0 8px 0;
}

footer a{
	font-size: 13px;
	text-decoration: none;
	color: #222;
	margin: 10px;
}

footer .follow{
	margin-top: 20px;
}

footer .follow i{
	color: #465b52;
	padding-right: 4px;
	cursor: pointer;
}

footer .install .row img{
	border: 1px solid #088178;
	border-radius: 6px;
}

footer .install img{
	margin: 10px 0 15px 0;
}

footer .follow i:hover,
footer a:hover{
	color: #088178;
}

footer .copyright{
	width: 100%;
	text-align: center;
}
</style> 
</head>

<body>
	${message }
	<section id="header">
		<a href="#"><img src="<c:url value='/img/logo.png' />" class="logo" alt=""></a>
		<div id="menu">
			<ul id="idnavbar">
				<li><a href="/BanDongHo/index.htm">Home</a></li>
				<c:if test="${not empty sessionScope.myNhanVien}">
					<li><a href="/BanDongHo/quantri/index.htm">Admin</a></li>
				</c:if>
				<form:form action="index.htm?timkiem" method="post">
					<li style="width: auto;"><input type="text" placeholder="Tên sản phẩm" name="tendh"/><button style="float: right; border: none; padding-left: 5px;">
					<span><i class="fas fa-search"></i></span></button></li>
				</form:form>
<!-- 				<li><a href="blog.htm">Blog</a></li> -->
<!-- 				<li><a href="about.htm">About</a></li> -->
<!-- 				<li><a href="contact.htm">Contact</a></li> -->
				<c:if test="${not empty sessionScope.tk}">
					<li><a href="/BanDongHo/bandongho/cart.htm"><i class="fa-solid fa-bag-shopping"></i><span style="
				right: -2px;
	position: static;
	padding: 0.3rem 0.4rem;
	background-color: green;
	color: white;
	border-radius: 50%;
	font-size: 0.7rem;
	height: 0.7rem;
	width: 0.7rem;
	justify-content: center;">${cart.size()}</span></a></li>
	
	
				</c:if>
					
			<li >
					<c:set var="urlAccount" value="user/info.htm"/>
					<c:set var="isSigned" value=""/>
					<c:if test="${(not empty sessionScope.myNhanVien) || (not empty sessionScope.tk) }">
					<c:set var="isSigned" value="1"/>
					</c:if>
					<a style="width: 150px;" href="${urlAccount}">
					<img src="<c:url value='/imgweb/avatar.png' />" 
			              alt=""
			              style="width: 45px; height: 45px"
			              class="rounded-circle" >
			              <c:set value="Account" var="usernameindex"/>
			              <c:if test=" ${empty isSigned}">
			              	<c:set value="Log in" var="usernameindex"/>
			              	<c:set var="urlAccount" value="index/sign-in.htm"/>
			              </c:if> 
			              
			              <c:if test=" ${not empty isSigned }">
			              	<c:set var="urlAccount" value="/BanDongHo/user/info.htm"/>
			              	<c:if test="${not empty sessionScope.myNhanVien }">
			              	<c:set var="usernameindex" value="${sessionScope.myNhanVien.MANV}"/></c:if>
			              	<c:if test="${not empty sessionScope.tk }">
			              	<c:set var="usernameindex" value="${sessionScope.tk.TENTK}"/></c:if>
			              </c:if>
			              
			              <p style="float: right;"><c:out value="${usernameindex }"></c:out></p>
			              
			              </a>
					<ul class="sub-menu" style="padding: 0px 0px 0px 0px; 	background: #e3e6f3; ">
						<c:if test="${not empty sessionScope.tk}">
							<li style="padding: 10px 0px;"><a href="/BanDongHo/index/myCart/${sessionScope.tk.MAKH_TK.MAKH}.htm">Đơn đã đặt</a></li>
							<li style="padding: 10px 0px;"><a href="/BanDongHo/index/log-out.htm">Đăng xuất</a></li>
						</c:if>
						<c:if test="${empty sessionScope.tk}">
							<li style="padding: 10px 0px;"><a href="index/sign-in.htm">Đăng nhập</a></li>
							<li style="padding: 10px 0px;"><a href="index/sign-up.htm">Đăng ký</a></li>
						</c:if>
						
					</ul>
				</li>
				
			</ul>
		</div>
	</section>		
	<section id="cart" class="section-p1">
		<table width="100%">
			<thead>
				<tr>
					<td>Remove</td>
					<td>Image</td>
					<td>Product</td>
					<td>Price</td>
					<td>Quantity</td>
					<td>Subtotal</td>
				</tr>
			</thead>
			<tbody>
					<c:forEach var = "d" items = "${cart}">
					<form:form action="cart.htm" method="post" modelAttribute="${dh}">
					<tr>
						<td><a href="xoa/${d.getMADH()}.htm"><i class="far fa-times-circle"></i></a></td>
						<td><img src="<c:url value='/imgweb/dongho/${d.getHINHANH()}'/>"></td>
						<td>${d.getTENDH()}</td>
						<td>${d.getGIA()}</td>
						<td>
						<input type="hidden" name="MaDH" value = "${d.getMADH()}" />
						<input type="number" value="${d.getSOLUONGTON()}" name="soluong" onblur="this.form.submit()"/>
						</td>
						<td>${d.getGIA()*d.getSOLUONGTON()}</td>
					<tr/>
					</form:form>
					</c:forEach>
				
			</tbody>
		</table>
	</section>
	<form:form action="cart.htm" params="btnTinhTien" method="post" modelAttribute="KH">
		<section id="cart-add" class="section-p1">
		<div id="coupon">
			<h3>Apply coupon</h3>
			<div>
				<input type="text" placeholder="Enter Your Coupon">
				<button class="normal">Apply</button>
			</div>
		</div>
		
			<div id="subtotal">
				<h3>Cart Totals</h3>
				<table>
					<tr>
						<td>Cart Subtotal</td>
						<td>$${TongTien}</td>
					</tr>
					<tr>
						<td>Shipping </td>
						<td>Free</td>
					</tr>
					<tr>
						<td><strong>Total</strong></td>
						<td>$${TongTien}</td>
					</tr>
					
					<tr>
						<td>Người nhận: <span><input type="text" maxlength="50" value = "${KH.getHOTEN()}"
						placeholder="Họ tên người nhận"/></span></td>
					</tr>
					
					<tr>
						<td>Địa chỉ giao hàng: <span><input type="text" placeholder="Địa chỉ giao hàng" value = "${KH.getDIACHI()}" 
						 maxlength="100"/></span></td>
					</tr>
					
					<tr>
						<td>Số điện thoại: <span><input type="text" placeholder="Số điện thoại" value = "${KH.getSDT()}" 
						 oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');" maxlength="10"/></span></td>
					</tr>
				</table>
				<button class="normal" name="btnTinhTien">Proceed to checkout</button>
			</div>
		</section>
	</form:form>
	
	
	<footer class="section-p1">
		<div class="col">
			<img class="logo" src="<c:url value = '/img/logo.png'/>" alt="" >
			<h4>Contact</h4>
			<p><strong>Address:</strong> 562 Wellington Road, Street 32, San Francisco</p>
			<p><strong>Phone:</strong> 0123456789</p>
			<p><strong>Hours:</strong> 10:00 - 18:00, Mon-Sat</p>
			<div class="follow">
				<h4>Follow us</h4>
				<div class="icon">
					<i class="fab fa-facebook-f"></i>
					<i class="fab fa-twitter"></i>
					<i class="fab fa-instagram"></i>
					<i class="fab fa-pinterest-p"></i>
					<i class="fab fa-youtube"></i>
				</div>
			</div>
		</div>
		
		<div class="col">
			<h4>About</h4>
			<a href="#">About us</a>
			<a href="#">Delivery Information</a>
			<a href="#">Privacy Policy</a>
			<a href="#">Term & Conditions</a>
			<a href="#">Contact Us</a>
		</div>
		
		<div class="col">
			<h4>My Account</h4>
			<a href="#">Sign In</a>
			<a href="#">View Cart</a>
			<a href="#">My Wishlist</a>
			<a href="#">Track My Order</a>
			<a href="#">Help</a>
		</div>
		
		<div class="col install">
			<h4>Install App</h4>
			<p>From App Store or Google Play</p>
			<div class="row">
				<img src="<c:url value = '/img/pay/app.jpg'/>" alt="" >
				<img src="<c:url value = '/img/pay/play.jpg'/>" alt="" >
			</div>
			<p>Secured Payment Gateways</p>
			<img src="<c:url value = '/img/pay/pay.png'/>" alt="" >
		</div>
		
		<div class="copyright">
			<p>@ 2021, Tech2 etc - HTML CSS Ecommerce Template</p>
		</div>		
	</footer>
	
<!-- 	<script src="script.js"> -->
	<script>
		
	</script>
</body>

</html>