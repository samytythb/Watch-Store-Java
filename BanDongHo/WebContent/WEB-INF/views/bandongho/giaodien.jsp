<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Giao dien</title>
<link rel="stylesheet" href="<c:url value='/resource/style.css' />" />
<link rel="stylesheet" href="<c:url value='/resource/FontAweSomeWeb/css/all.css' />" />
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"> -->
<style type="text/css">

#hero{
	background-image:url("<c:url value = '/img/hero4.png'/>");
	height: 90vh;
	width: 100%;
	background-size: cover;
	background-position: top 25% right 0;
	padding: 0 80px;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	justify-content: center;
}

#hero button{
	background-image: url("<c:url value='/img/button.png' />");
	background-color: transparent;
	color: #088178;
	border: 0;
	padding: 14px 80px 14px 65px;
	background-repeat: no-repeat;
	cursor: pointer;
	font-weight: 700;
	font-size: 15px; 
}

/* #product1{ */
/* 	text-align: center; */
/* } */

#product1 .pro-container{
	display: flex;
	justify-content: space-between;
	padding-top: 20px;
	flex-wrap: wrap;	
}

#product1 .pro-container .pro{ 
	width: 23%;  
  	min-width: 250px;  
  	padding: 10px 12px;  
  	border: 1px solid #cce7d0;  
  	border-radius: 25px;	  
  	cursor: pointer;  
  	box-shadow: 20px 20px 30px rgba(0, 0, 0, 0.02);  
  	margin: 15px 0;  
	transition: 0.2s ease;  
 	position: relative; 
 	flex-basis: 24%;
/* 	float: left;  */
} 



#product1 .pro:hover{ 
 	box-shadow: 20px 20px 30px rgba(0, 0, 0, 0.06); 
} 

#banner{
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	text-align: center;
	background-image: url("<c:url value='/img/banner/b2.jpg' />");
	width: 100%;
	height: 40vh;
	background-size: cover;
	background-position: center;
}

#banner .normal{
	font-size: 14px;
	font-weight: 600;
	padding: 15px 30px;
	color: #000;
	background-color: #fff;
	border-radius: 4px;
	cursor: pointer;
	border: none;
	outline: none;
	transition: 0.2s;
}

#sm-banner{
	display: flex;
	justify-content: space-between;
	flex-wrap: wrap;
}
#sm-banner .banner-box{
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: flex-start;
	text-align: center;
	background-image: url("<c:url value='/img/banner/b17.jpg' />");
	min-width: 580px;
	height: 50vh;
	background-size: cover;
	background-position: center;
	padding: 30px;
}

#sm-banner .banner-box2{
	background-image: url("<c:url value='/img/banner/b10.jpg' />");
}

#sm-banner .banner-box .white{
	font-size: 13px;
	font-weight: 600;
	padding: 11px 18px;
	color: #fff;
	background-color: transparent;
	cursor: pointer;
	border: 1px solid #fff;
	outline: none;
	transition: 0.2s;
}

#banner3{
	display: flex;
	justify-content: space-between;
	flex-wrap: wrap;
	padding: 0 80px;
}


#banner3 .banner-box{
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: flex-start;
	text-align: center;
	background-image: url("<c:url value='/img/banner/b7.jpg' />");
	min-width: 30%;
	height: 30vh;
	background-size: cover;
	background-position: center;
	padding: 30px;
	margin-bottom: 20px; 
}

#banner3 h2{
	color: #fff;
	font-weight: 900;
	font-size: 22px;
}

#banner3 h3{
	color: #ec554e;
	font-weight: 800;
	font-size: 15px;
}

#banner3 .banner-box2{
	background-image: url("<c:url value='/img/banner/b4.jpg' />");
}

#banner3 .banner-box3{
	background-image: url("<c:url value='/img/banner/b18.jpg' />");
}

#newsletter{
	display: flex;
	justify-content: space-between;
	flex-wrap: wrap;
	align-items: center;
	background-image: url("<c:url value='/img/banner/b14.png' />");
	background-repeat: no-repeat;
	background-position: 20% 30%;
	background-color: #041e42;
}

#newsletter h4{
	font-size: 22px;
	font-weight: 700;
	color: #fff;
}

#newsletter p{
	font-size: 14px;
	font-weight: 600;
	color: #818ea0;
}

#newsletter .form{
	display: flex;
	width: 40%;
}

#newsletter p span{
	color: #ffbd27;
}

#newsletter input{
	height: 3.125rem;
	padding: 0 1.25rem;
	font-size: 14px;
	width: 100%;
	border: 1px solid transparent;
	border-radius: 4px;
	outline: none;
	color: #818ea0;
	border-bottom-right-radius: 0;
	border-top-right-radius: 0;
}

#newsletter button{
	background-color: #088178;
	color: #fff;
	white-space: nowrap; 
	border-bottom-left-radius: 0;
	border-top-left-radius: 0;
}

#newsletter .form .normal{
	font-size: 14px;
	font-weight: 600;
	padding: 15px 30px;
	color: #fff;
	background-color: #088178;
	border-radius: 4px;
	cursor: pointer;
	border: none;
	outline: none;
	transition: 0.2s;
	border-bottom-left-radius: 0;
	border-top-left-radius: 0;
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
	<section id="header">
		<a href="#"><img src="<c:url value='/img/logo.png' />" class="logo" alt=""></a>
		<div>
			<ul id="idnavbar">
				<li><a href="giaodien.htm">Home</a></li>
				<li><a href="shop.htm">Shop</a></li>
				<form:form action="timkiem.htm" method="post">
					<li><input type="text" placeholder="Tên sản phẩm" name="tendh"/><button style="border: none; padding-left: 5px;">
					<span><i class="fas fa-search"></i></span></button></li>
				</form:form>
<!-- 				<li><a href="blog.htm">Blog</a></li> -->
<!-- 				<li><a href="about.htm">About</a></li> -->
<!-- 				<li><a href="contact.htm">Contact</a></li> -->
				<li><a href="cart.htm"><i class="fa-solid fa-bag-shopping"></i><span style="
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
<%-- 	<form:form action="logout.htm" method="get"> --%>
<!-- 		<li><button>Logout</button></li> -->
<%-- 	</form:form> --%>
					<li><a href="logout.htm">Logout</a></li>
	
	
			</ul>
		</div>
	</section>		
	
<%-- 	<input type="hidden" value="${cart}"> --%>
	<section id="hero">
		<h4>Trade-in-offer</h4>
		<h2>Super value deals</h2>
		<h1>On all products</h1>
		<p>Save more with coupons and up to 70% off</p>
		<button>Shop Now</button>
	</section>
	<section id = "features" class="section-p1">
		<div class = "fe-box">
			<img src="<c:url value='/img/features/f1.png'/>" alt="">
			<h6>Free shipping</h6>
		</div>
		
		<div class = "fe-box">
			<img src="<c:url value='/img/features/f2.png'/>" alt="">
			<h6>Online Order</h6>
		</div>
		
		<div class = "fe-box">
			<img src="<c:url value='/img/features/f3.png'/>" alt="">
			<h6>Save Money</h6>
		</div>
		
		
		<div class = "fe-box">
			<img src="<c:url value='/img/features/f4.png'/>" alt="">
			<h6>Promotions</h6>
		</div>
		
		<div class = "fe-box">
			<img src="<c:url value='/img/features/f5.png'/>" alt="">
			<h6>Happy Sell</h6>
		</div>
		
		<div class = "fe-box">
			<img src="<c:url value='/img/features/f6.png'/>" alt="">
			<h6>F24/7 Support</h6>
		</div>
	</section>
	<section id="product1" class ="section-p1">
		<h2>Featured Products</h2>
		<p>Summer Collection New Morden Design</p>
		<div class="pro-container">
			<c:forEach var = "d" items = "${dh}">
					<div class="pro">
<%-- 					<form action="them/${d.getMaDH()}.htm" method="post"> --%>
					<img src="<c:url value = '${d.getHINHANH()}'/>" alt="" >
				<div class="des">
					<c:if test="${d.getSOLUONGTON() == 0 }"><h5 style="color:red">Đã hết hàng</h5>
					</c:if>
					<h5>${d.getTENDH()}</h5>
					<div>
						<i class="fas fa-star"></i>
						<i class="fas fa-star"></i>
						<i class="fas fa-star"></i>
						<i class="fas fa-star"></i>
						<i class="fas fa-star"></i>
					</div>
					<h4>$${d.getGIA()}</h4>
				</div>
				<c:if test="${d.getSOLUONGTON() > 0 }"><a href="them/${d.getMADH()}.htm"><i class="fas fa-shopping-cart cart"></i></a>
					</c:if>
			</div>
			</c:forEach>
			
		</div>
	</section>
	<section id="banner" class="section-m1">
		<h4>Repair Services</h4>
		<h2>Up to <span>70% Off</span> All t-shirt & Accessories </h2>
		<button class="normal">Explore More</button>
	</section>
	<section id="product1" class ="section-p1">
		<h2>New Arrivals</h2>
		<p>Summer Collection New Morden Design</p>
		<div class="pro-container">
			<c:forEach var = "d" items = "${dh}">
				<div class="pro">
				<img src="<c:url value = '${d.getHINHANH()}'/>" alt="" >
				<div class="des">
<%-- 					<span>${d.getHangDH().getTenHangDH()}</span> --%>
					<h5>${d.getTENDH()}</h5>
					<div>
						<i class="fas fa-star"></i>
						<i class="fas fa-star"></i>
						<i class="fas fa-star"></i>
						<i class="fas fa-star"></i>
						<i class="fas fa-star"></i>
					</div>
					<h4>$${d.getGIA()}</h4>
				</div>
				<a href="#"><i class="fas fa-shopping-cart cart"></i></a>
			</div>
			</c:forEach>
		</div>
	</section>
	<section id="sm-banner" class="section-p1">
		<div class="banner-box">
			<h4>crazy deals</h4>
			<h2>buy 1 get 1 free</h2>
			<span>The best classic dress is on sale at cara</span>
			<button class="white">Learn More</button>
		</div>
		
		<div class="banner-box banner-box2">
			<h4>spring/summer</h4>
			<h2>upcoming season</h2>
			<span>The best classic dress is on sale at cara</span>
			<button class="white">Collection</button>
		</div>
	</section>
	<section id="banner3">
		<div class="banner-box">
			<h2>SEASONAL SALE</h2>
			<h3>Winter Collection - 50% OFF</h3>
		</div>
		
		<div class="banner-box banner-box2">
			<h2>SEASONAL SALE</h2>
			<h3>Winter Collection - 50% OFF</h3>
		</div>
		
		<div class="banner-box banner-box3">
			<h2>SEASONAL SALE</h2>
			<h3>Winter Collection - 50% OFF</h3>
		</div>
	</section>
	<section id="newsletter" class="section-p1 section-m1">
		<div class="newstext">
			<h4>Sign Up For Newsletters</h4>
			<p>Get E-mail updates about our latest shop and <span>special offers.</span>
			</p>
		</div>
		<div class="form">
			<input type="text" placeholder="Your email address">
			<button class="normal">Sign Up</button>
		</div>
	</section>
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
	<script src="script.js"></script>
</body>

</html>