<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="style.css">

<!-- <link rel="canonical"
	href="https://getbootstrap.com/docs/5.0/examples/starter-template/" >
<link href="resources/assets/dist/css/bootstrap.min.css" rel="stylesheet"> -->

<title>PTIT Watch</title>
<style>
/*CSS variables section*/
:root {
	background-color: rgba(0, 0, 21, 1);
}

/*Extra Large screens - Extra large desktops*/
.header {
	
}

.heeder-large-screen {
	display: flex !important;
	justify-content: space-evenly !important;
	color: orange;
	font-size: 18px !important;
}

#navDemo {
	background-color: #000015;
}

#navDemo a {
	font-size: 13px !important;
	color: orange;
}

.hero {
	height: 650px;
	background-image:
		url("https://hieumobile.com/wp-content/uploads/2018/07/xv08U.jpg");
	background-repeat: no-repeat;
	background-size: cover;
	background-position: center;
	background-color: #000015;
}

.hero-description {
	height: 650px;
	background-color: rgba(0, 0, 21, .7);
	display: flex;
}

.hero-text {
	width: 70% !important;
	margin: auto !important;
	display: flex;
	flex-wrap: wrap;
}

.hero-text p {
	color: white;
	width: 50%;
}

.hero-title {
	/* padding-top:80px; */
	font-weight: 800;
	text-transform: uppercase;
	font-size: 80px;
	color: orange;
	text-align: center;
	margin-left: 15%
}

.social {
	width: 100%;
	margin-left: 40%
}

.icons {
	font-size: 20px;
	letter-spacing: 5px;
}

main {
	width: 80% !important;
	margin: auto !important;
}

.search {
	margin-left: 20%;
	width: 80%
}

.input-search {
	width: 60%;
	height: 150%;
	border-radius: %;
}

.btn-search {
	width: 10%;
	height: 150%;
	overflow: hidden;
}

.btn-navbar {
	display: flex;
	justify-content: center;
}

.btn-signin, .btn-signup {
	margin: auto;
	color: white;
	background-color: violet;
}

.ml-5 {
	margin-left: 5px;
}

.row {
	margin: 20px;
}

.btn-circle {
	width: 30px;
	height: 30px;
	text-align: center;
	padding: 6px 0;
	font-size: 12px;
	line-height: 1.428571429;
	border-radius: 15px;
	cursor:pointer;
}

.title {
	color: white;
	font-weight: 600;
	font-size: 40px;;
	margin: 50px 0px;
	text-align: center;
	text-transform: uppercase;
}

.product-logo img {
	width: 100%;
	height: 100%;
	object-fit: contain;
}

h3 {
	color: white;
	text-align: center;
}

.products {
	display: flex;
	flex-wrap: wrap;
	align-items: center;
	/* background-color:	rgba(0, 0, 47,0.3); */
	justify-content: space-evenly;
}

.product-list {
	margin: 30px;;
	padding: 20px;
	height: 200px;
	width: 250px;
	display: flex;
	flex-direction: column;
	align-items: center;
	border: 1px solid gray;
	background: rgba(255, 75, 255, 0.1);
	box-shadow: 0 8px 40px 0 rgba(45, 57, 231, 0.37);
	backdrop-filter: blur(4px);
	-webkit-backdrop-filter: blur(4px);
	border-radius: 10px;
	border: 1px solid rgba(255, 255, 255, 0.18);
	overflow: hidden;
}

.product-list:hover, .product-store-list :hover {
	transform: scale(1.05);
	cursor: pointer
}

.store {
	margin-top: 50px;
}

.product-store {
	margin-top: 20px !important;
	display: flex;
	justify-content: space-evenly;
	flex-wrap: wrap;
}

.product-image {
	width: 300px;
	height: 180px;
}

.product-image img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.product-store-list {
	width: 300px;
	height: 300px;
	margin: 20px;
	overflow: hidden;
	border-top: none;
	-webkit-box-shadow: 0 8px 40px 0 rgba(45, 57, 231, 0.37);
	box-shadow: 0 8px 40px 0 rgba(45, 57, 231, 0.37);
	backdrop-filter: blur(4px);
	-webkit-backdrop-filter: blur(4px);
	border-bottom: 1px solid blueviolet;
	color: white;
}

.store-text-description {
	padding-left: 20px;
	display: flex;
	flex-direction: column;
}

h4 {
	color: white;
	font-weight: 700;
}

.event-hero {
	display: flex;
	flex-wrap: wrap;
	background: rgba(255, 75, 255, 0.1);
	justify-content: space-evenly;
	align-items: center;
	padding: 50px;
	margin-top: 50px;
	border: 1px solid black;
	border-radius: 10px;
}

.event-text {
	width: 50%;
	color: orange;
}

.event-title {
	font-size: 40px;
	text-transform: uppercase;
	font-weight: 600;
}

.event-image {
	width: 400px;
	height: 500px;
}

.event-image img {
	width: 100%;
	height: 100%;
	object-fit: contain;
}

.event-text p {
	color: white;
}

.contact {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-evenly;
}

.contact-list {
	margin-top: 15px !important;
	color: white;
}

h5 {
	color: white;
}

span {
	padding-right: 10px;
}

.contact-list p {
	
}

.email-contact {
	width: 80%;
	color: gray;
	display: flex;
	flex-direction: column;
	justify-content: space-evenly;
}

/*General CSS and CSS reset*/
html {
	scroll-behavior: smooth;
}

* {
	margin: 0;
	padding: 0;
}

/* ____________________________________________________________________*/
/*MEDIA QUERIES*/
/*Large screens - Desktop*/
@media screen and (max-width: 1500px) {
	.hero {
		height: 550px !important;;
		background-image: url("");
	}
	.hero-description {
		height: 550px
	}
	.hero-title {
		font-size: 50px;
	}
}

/*medium screens - Laptops*/
@media screen and (max-width: 1024px) {
	main {
		width: 100% !important;
		margin: auto !important;
	}
}

/*Small screens - tablets*/
@media screen and (max-width: 768px) {
	.heeder-large-screen {
		display: block !important;
	}
	header a {
		font-size: 14px;
	}
	.hero {
		height: 400px !important;
		background-image:
			url("https://hieumobile.com/wp-content/uploads/2018/07/xv08U.jpg");
	}
	.hero-description {
		height: 400px;
		background-color: rgba(0, 0, 21, .7);
	}
	.hero-title {
		font-size: 35px;;
	}
	.hero-text p {
		width: 80%;;
		font-size: 13px;
	}
	.title {
		text-align: center;
		font-size: 30px;
	}
	.event-text {
		width: 80%;
		color: blueviolet;
	}
	.event-text p {
		font-size: 13px;
	}
	.event-title {
		font-size: 30px;
		text-transform: uppercase;
		font-weight: 600;
	}
	.event-hero {
		width: 80%;
		margin: auto;
	}
	.event-image {
		width: 250px;
		height: 400px;
	}
	.event-image img {
		width: 100%;
		height: 100%;
		object-fit: cover;
	}
	.contact-list {
		width: 300px;
	}
	.contact {
		justify-content: space-around;
	}
	.contact-list p {
		font-size: 14px;
	}
	h5 {
		font-size: 17px;
	}
}

/*Extra small screens - phones*/
@media screen and (max-width: 480px) {
	.heeder-large-screen {
		display: block !important;
	}
	.hero {
		height: 450px !important;
		background-image:
			url("https://hieumobile.com/wp-content/uploads/2018/07/xv08U.jpg");
	}
	.hero-description {
		height: 450px;
		background-color: rgba(0, 0, 21, .7);
	}
	.hero-title {
		font-size: 30px;;
		padding-top: 70px;
	}
	.hero-text p {
		width: 100%;;
		font-size: 13px;
	}
	.title {
		text-align: center;
		font-size: 25px;
	}
	.event-text {
		width: 100%;
		color: blueviolet;
	}
	.event-text p {
		font-size: 13px;
	}
	.event-title {
		font-size: 30px;
		text-transform: uppercase;
		font-weight: 600;
	}
	.event-hero {
		width: 90%;
		margin: auto;
	}
	.event-image {
		width: 250px;
		height: 400px;
	}
	.event-image img {
		width: 100%;
		height: 100%;
		object-fit: cover;
	}
	.contact-list {
		width: 300px;
		padding: 25px;
		border: 1px solid rgba(49, 45, 45, 0.5);
		border-radius: 10px;
	}
	.contact-list p {
		font-size: 12px;
	}
	h5 {
		font-size: 15px;
	}
	.badge {
		
	}
	p {
		font-size: 12px;
	}
}
</style>



</head>
<body>
	<header style="position: relative;">

		<div class="w3-top" class="header" id="home">
			<div
				class="w3-bar  w3-card w3-left-align w3-large heeder-large-screen"
				style="background-color: #000015;">
				<a
					class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-black w3-large "
					href="javascript:void(0);" onclick="myFunction()"
					title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a> <a
					href="#home"
					class="w3-bar-item w3-button w3-padding-large  w3-hover-black">PTIT
					Watch</a>
				<div>
					<a href="/BanDongHo/quantri/index.htm"
						class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-black">Admin</a>
					<a href="#products"
						class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-black">
						Products</a> <a href="#shop"
						class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-black">Trademark</a>
					<a href="#events"
						class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-black">Events</a>
					<a href="#contact"
						class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-black">Contact</a>


				</div>

				<div class="btn-navbar">
					<button type="button" class="btn btn-info btn-circle btn-signin">
						<i class="glyphicon glyphicon-ok"></i>
					</button>

				</div>
			</div>
			<!-- Navbar on small screens -->
			<div id="navDemo"
				class="w3-bar-block  w3-hide w3-hide-large w3-hide-medium w3-large">
				<a href="#about" class="w3-bar-item w3-button w3-padding-large"
					onclick="myFunction()">About</a> <a href="#products"
					class="w3-bar-item w3-button w3-padding-large"
					onclick="myFunction()">Products</a> <a href="#shop"
					class="w3-bar-item w3-button w3-padding-large"
					onclick="myFunction()">Shop</a> <a href="#events"
					class="w3-bar-item w3-button w3-padding-large"
					onclick="myFunction()">Events</a> <a href="#contact"
					class="w3-bar-item w3-button w3-padding-large"
					onclick="myFunction()">Contact</a>

			</div>
		</div>
	</header>
	<section>
		<div class="hero" id="about">
			<div class="hero-description ">
				<div class="hero-text ">
					<h1 class="hero-title ">Time is priceless</h1>
					<form class="d-flex search">
						<input class="form-control me-2 input-search" type="search"
							placeholder="Search" aria-label="Search">
						<button class="btn btn-outline-success btn-search" type="submit">Search</button>
					</form>
					<span class="w3-text-white social mx-auto d-block"
						style="margin-top: 050px">
						<p>Follow us on Social media</p>
						<div class="w3-margin-top">
							<a href="#"> <i
								class="fa fa-facebook-official w3-hover-opacity icons"></i></a> <a
								href="#"><i class="fa fa-instagram w3-hover-opacity icons"></i></a>
							<a href="#"><i class="fa fa-snapchat w3-hover-opacity icons"></i></a>
							<a href="#"><i
								class="fa fa-pinterest-p w3-hover-opacity icons"></i></a> <a
								href="#"><i class="fa fa-twitter w3-hover-opacity icons"></i></a>
							<a href="#"><i class="fa fa-linkedin w3-hover-opacity icons"></i></a>
						</div>
					</span>
				</div>
			</div>
		</div>
	</section>
	<main style="padding-bottom: 40px;">
		<h2 class="title" id="products">List of products</h2>
		<div class="products">
			<div class="product-list">
				<img src="imgweb/man.jpg" alt="Man"
					style="width: 200px; height: 300px;" />
				<h3>Man</h3>
				<p></p>
			</div>
			<div class="product-list">
				<img src="imgweb/women.jpg" alt="Women"
					style="width: 200px; height: 300px;" />
				<h3>Women</h3>
				<p></p>
			</div>
			<div class="product-list ">
				<img src="imgweb/couple1.jpg" alt="Couple"
					style="width: 200px; height: 240px;" />
				<h3>Couple</h3>
				<p></p>
			</div>
			<div class="product-list">
				<img src="imgweb/sale.jpg" alt="Sale"
					style="width: 200px; height: 300px;" />
				<h3>Sale</h3>
				<p></p>
			</div>
			<div class="product-list">
				<img src="imgweb/top.jpg" alt="Top"
					style="width: 200px; height: 300px;" />
				<h3>Top</h3>
				<p></p>
			</div>
			<div class="product-list">
				<img src="imgweb/accessory.jpg" alt="Accessories"
					style="width: 200px; height: 350px;" />
				<h3>Accessories</h3>
				<p></p>
			</div>


		</div>
		<div class="store" id="shop">
			<h2 class="title">Our Trademark</h2>

			<div class="product-store">

				<div class="product-store-list">
					<div class="product-image">
						<img src="imgweb/dong-ho-tag-heuer-carrera-calibre-16.jpg" />
					</div>
					<div class="store-text-description">
						<h4>Đồng hồ Thụy Sỹ Tag Heuer</h4>
						<span> 12 items </span> <span>$1700-$8700</span>
					</div>

				</div>
				<div class="product-store-list">
					<div class="product-image">
						<img src="imgweb/rolex-swiss-made.jpg" />
					</div>
					<div class="store-text-description">
						<h4>Đồng hồ Rolex Swiss Made</h4>
						<span> 15 items </span> <span>$500-$6500</span>
					</div>

				</div>
				<div class="product-store-list">
					<div class="product-image">
						<img src="imgweb/calvin-klein.png" />
					</div>
					<div class="store-text-description">
						<h4>Đồng hồ Calvin Klein</h4>
						<span> 25 items </span> <span>$100-$3000</span>
					</div>

				</div>
				<div class="product-store-list">
					<div class="product-image">
						<img src="imgweb/omega.jpg" alt=" Đồng hồ Omega" />
					</div>
					<div class="store-text-description">
						<h4>Đồng hồ Omega</h4>
						<span> 40 items </span> <span>$3000-$95000</span>
					</div>

				</div>
				<div class="product-store-list">
					<div class="product-image">
						<img src="imgweb/longines.jpg" />
					</div>
					<div class="store-text-description">
						<h4>Đồng hồ Longines</h4>
						<span> 10 items </span> <span>$1500-$6700</span>
					</div>

				</div>
				<div class="product-store-list">
					<div class="product-image">
						<img src="imgweb/tissot.jpg" />
					</div>
					<div class="store-text-description">
						<h4>Đồng hồ Tissot</h4>
						<span> 10 items </span> <span>$200-$3600</span>
					</div>

				</div>

			</div>
		</div>
		<div class="event" id="events">
			<h2 class="title">Upcoming Events</h2>

			<div class="event-hero" style="position: relative;">


				<div class="event-text">
					<h2 class="badge"
						style="background-color: orange; color: #000015; padding: 10px">
						Research on rolex | Sunday March 6</h2>
					<h1 class="event-title">A CROWN FOR EVERY ACHIEVEMENT</h1>


					<p>Participate in learning and researching about the Rolex
						watch brand to receive unexpected valuable prizes from Rolex</p>
					<h4>Save your place</h4>
					<div>
						<label id="icon" for="name">Name</label> <br> <input
							type="text" name="name" id="name" placeholder="Name" required />
						<br> <br> <label id="icon" for="name">Email</label> <br>
						<input type="email" name="name" id="name" placeholder="Email"
							required />

					</div>
					<button class="w3-btn w3-red w3-margin-top w3-round">Register</button>

				</div>
				<div class="event-image">
					<img src="imgweb/event.jpg" alt="erik-mclean" />
				</div>

			</div>

		</div>
		<div>

			<h2 class="title">Reach out to us</h2>
			<div class="contact" id="contact"
				style="padding-bottom: 10px !important;">
				<div class="contact-list contact-address">
					<h5>
						<span><i
							class="fa fa-map-marker w3-xlarge w3-text-light-grey"></i></span>Address
					</h5>
					<p>96A Trần Phú, Mộ Lao, Hà Đông, Hà Nội</p>
					<p>11 Nguyễn Đình Chiểu, ĐaKao, Quận 1, Hồ Chí Minh</p>
					<p>97 Man Thiện, Hiệp Phú, Thủ Đức, Hồ Chí Minh</p>
				</div>
				<div class="contact-list">
					<h5>
						<span><i
							class="fa fa-envelope w3-xlarge w3-text-light-grey"></i></span>Receive
						weekly update email
					</h5>
					<div class="email-contact">
						<input placeholder="Email" type="email"
							style="margin: 8px 0px !important;" />

						<button class="w3-btn w3-red w3-round "
							style="width: 100px; padding: 3px 10px !important">Subscribe</button>
					</div>

				</div>
				<div class="contact-list">
					<h5>
						<span><i class="fa fa-phone w3-xlarge w3-text-light-grey"></i></span>Phone
					</h5>
					<p>123-456-789</p>
					<p>987-654-321</p>
				</div>

			</div>
		</div>

	</main>
	<div class="w3-padding w3-center"
		style="background: #FFF4A3; padding: 10px 0px !important; color: #282A35;">
		<div
			style="display: flex; justify-content: center; align-items: center; flex-wrap: wrap;">
			<p style="margin: 10px !important">This website is copyrighted by
				PTIT-WATCH Company</p>

		</div>

	</div>

	<script>
		// Used to toggle the menu on small screens when clicking on the menu button
		function myFunction() {
			var x = document.getElementById("navDemo");
			if (x.className.indexOf("w3-show") == -1) {
				x.className += " w3-show";
			} else {
				x.className = x.className.replace(" w3-show", "");
			}
		}
	</script>
</body>
</html>
