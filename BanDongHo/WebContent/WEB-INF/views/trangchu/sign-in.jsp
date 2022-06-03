<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
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
.gradient-custom-2 {
	/* fallback for old browsers */
	background: #fccb90;
	/* Chrome 10-25, Safari 5.1-6 */
	background: -webkit-linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	background: linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);
}

@media ( min-width : 768px) {
	.gradient-form {
		height: 100vh !important;
	}
}

@media ( min-width : 769px) {
	.gradient-custom-2 {
		border-top-right-radius: .3rem;
		border-bottom-right-radius: .3rem;
	}
}

.mb-0 {
	color: #F25F48;
}
</style>
<title>Sign in</title>
</head>
<section class="h-100 gradient-form" style="background-color: #eee;">
	<div class="container py-5 h-100">
		<div
			class="row d-flex justify-content-center align-items-center h-100">
			<div class="col-xl-10">
				<div class="card rounded-3 text-black">
					<div class="row g-0">
						<div class="col-lg-6">
							<div class="card-body p-md-5 mx-md-4">

								<div class="text-center">
									<img
										src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/lotus.webp"
										style="width: 185px;" alt="logo">
									<h4 class="mt-1 mb-5 pb-1">We are The PTIT Watch</h4>
								</div>

								<form:form method="POST" action="sign-in.htm"
									commandName="account">
									<p>Please sign in to your account</p>

									<div class="form-outline mb-4">
										<label class="form-label" for="form2Example11">Username</label>
										<form:input path="username" type="text" id="form2Example11"
											class="form-control" placeholder="Please enter your username" />
										<label class="mb-1">
											<h6 class="mb-0 text-sm">
												<form:errors path="username" cssClass="errors" />
											</h6>
										</label>
									</div>

									<div class="form-outline mb-4">
										<label class="form-label" for="form2Example22">Password</label>
										<form:input path="password" type="password"
											id="form2Example22" class="form-control"
											placeholder="Please enter your password" />
										<label class="mb-1">
											<h6 class="mb-0 text-sm">
												<form:errors path="password" cssClass="errors" />
											</h6>
										</label>
									</div>

									<div class="text-center pt-1 mb-5 pb-1">
										<button
											class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3"
											type="submit">Log in</button>
										<a class="text-muted" href="forgotpassword.htm">Forgot password?</a>
									</div>

									<div
										class="d-flex align-items-center justify-content-center pb-4">
										<p class="mb-0 me-2">Don't have an account?</p>
										<button type="button" class="btn btn-outline-danger">
											<a href="sign-up.htm">Create new</a>
										</button>
									</div>

								</form:form>

							</div>
						</div>
						<div class="col-lg-6 d-flex align-items-center gradient-custom-2">
							<div class="text-white px-3 py-4 p-md-5 mx-md-4">
								<h4 class="mb-4">We are more than just a company</h4>
								<p class="small mb-0">Nos coetus vigilantium amantium et
									fanaticorum est. PTIT Watch ad optimos fructus et officia
									providendi clientibus nostris qui vigilias intersunt.Admodum
									servituri sumus vobis.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
</body>
</html>