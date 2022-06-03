<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.0/examples/starter-template/">
<link href="resources/assets/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
.gradient-custom-3 {
	/* fallback for old browsers */
	background: #84fab0;
	/* Chrome 10-25, Safari 5.1-6 */
	background: -webkit-linear-gradient(to right, rgba(132, 250, 176, 0.5),
		rgba(143, 211, 244, 0.5));
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	background: linear-gradient(to right, rgba(132, 250, 176, 0.5),
		rgba(143, 211, 244, 0.5))
}

.gradient-custom-4 {
	/* fallback for old browsers */
	background: #84fab0;
	/* Chrome 10-25, Safari 5.1-6 */
	background: -webkit-linear-gradient(to right, rgba(132, 250, 176, 1),
		rgba(143, 211, 244, 1));
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	background: linear-gradient(to right, rgba(132, 250, 176, 1),
		rgba(143, 211, 244, 1))
}

.mb-0 {
	color: #F25F48;
}
</style>
<title>Sign up</title>
</head>
<section class="vh-200 bg-image"
	style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
	<div class="mask d-flex align-items-center h-100 gradient-custom-3">
		<div class="container h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12 col-md-9 col-lg-7 col-xl-6">
					<div class="card" style="border-radius: 15px;">
						<div class="card-body p-5">
							<h2 class="text-uppercase text-center mb-5">Create an
								account</h2>
							<h5 class="mb-0">${message}</h5>
							<form:form method="POST" action="sign-up.htm"
								commandName="newaccount">
								<div class="form-outline mb-4">
									<label class="form-label" for="form3Example1cg">Username</label>
									<form:input path="username" type="text" id="form3Example1cg"
										class="form-control form-control-lg"
										placeholder="Please enter your username" />
									<label class="mb-1">
										<h6 class="mb-0 text-sm">
											<form:errors path="username" cssClass="errors" />
										</h6>
										<h6 class="mb-0">${message1}</h6>
									</label>
								</div>

								<div class="form-outline mb-4">
									<label class="form-label" for="form3Example3cg">Your
										Email</label>
									<form:input path="email" type="email" id="form3Example3cg"
										class="form-control form-control-lg"
										placeholder="Please enter your email" />
									<label class="mb-1">
										<h6 class="mb-0 text-sm">
											<form:errors path="email" cssClass="errors" />
										</h6>
										<h6 class="mb-0">${message2}</h6>
									</label>
								</div>

								<div class="form-outline mb-4">
									<label class="form-label" for="form3Example4cg">Password</label>
									<form:input path="password" type="password"
										id="form3Example4cg" class="form-control form-control-lg"
										placeholder="Please enter your password" />
									<label class="mb-1">
										<h6 class="mb-0 text-sm">
											<form:errors path="password" cssClass="errors" />
										</h6>
									</label>
								</div>

								<div class="form-outline mb-4">
									<label class="form-label" for="form3Example4cdg">Repeat
										your password</label>
									<form:input path="repassword" type="password"
										id="form3Example4cdg" class="form-control form-control-lg"
										placeholder="Please enter repeat your password" />
									<label class="mb-1">
										<h6 class="mb-0 text-sm">
											<form:errors path="repassword" cssClass="errors" />
										</h6>
									</label>
								</div>


								<div class="d-flex justify-content-center">
									<button type="submit"
										class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Register</button>
								</div>

								<p class="text-center text-muted mt-5 mb-0">
									Have already an account? <a href="sign-in.htm"
										class="fw-bold text-body"><u>Login here</u></a>
								</p>
							</form:form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
</body>
</html>