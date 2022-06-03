<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
<title>Forgot password</title>
<style>
body {
	background-position: center;
	background-color: #eee;
	background-repeat: no-repeat;
	background-size: cover;
	color: #505050;
	font-family: "Rubik", Helvetica, Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	line-height: 1.5;
	text-transform: none;
}

.forgot {
	background-color: #fff;
	padding: 12px;
	border: 1px solid #dfdfdf;
}

.padding-bottom-3x {
	padding-bottom: 72px !important;
}

.card-footer {
	background-color: #fff;
}

.btn {
	font-size: 13px;
}

.form-control:focus {
	color: #495057;
	background-color: #fff;
	border-color: #76b7e9;
	outline: 0;
	box-shadow: 0 0 0 0px #28a745;
}

.mb-0 {
	color: #F25F48;
}

.colormess {
	color: #36AA00;
}
</style>
</head>
<body>

	<div class="container padding-bottom-3x mb-2 mt-5">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10">
				<div class="forgot">

					<h2>Forgot your password?</h2>
					<p>Change your password in three easy steps. This will help you
						to secure your password!</p>
					<ol class="list-unstyled">
						<li><span class="text-primary text-medium">1. </span>Enter
							your email address below.</li>
						<li><span class="text-primary text-medium">2. </span>Our
							system will send you an email containing the new password</li>
						<li><span class="text-primary text-medium">3. </span>Use new
							password to login</li>
					</ol>

				</div>

				<form:form method="POST" class="card mt-4"
					action="forgotpassword.htm" commandName="newpass">
					<div class="card-body">
						<div class="form-group">
							<label for="email-for-pass">Enter your email address</label>
							<form:input path="email" class="form-control" type="email"
								id="email-for-pass" placeholder="Please enter your email" />
							<small class="form-text text-muted">Enter the email
								address you used during the registration on PTITWATCH. Then we
								will send you an email containing the new password.</small> <label
								class="mb-1">
								<h6 class="mb-0 text-sm">
									<form:errors path="email" cssClass="errors" />
								</h6>
							</label>
							<h6 class="colormess">${message}</h6>
						</div>
					</div>
					<div class="card-footer">
						<button class="btn btn-success" type="submit">Get New
							Password</button>
						<button class="btn btn-danger" type="submit">
							<a href="sign-in.htm">Back to Login</a>
						</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>