<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WatchStore</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>


</head>
<body  style="background: linear-gradient(-45deg,#EE7752,#E73C7E,#23A6D5,#23D5AB); color:white; ">

<form:form action="${pageContext.request.contextPath}/user/info.htm" modelAttribute = "user">
	<div class="container">
  	<main>
    <div class="py-5 text-center">
      <h2>Hồ Sơ Của Tôi</h2>
      <p class="lead">Quản lý thông tin hồ sơ để bảo mật tài khoản</p>
    </div>
    <div class="row g-5 ">
      <div class="col-sm-7 mx-auto">
        <form class="needs-validation" novalidate>
            <div class="row g-3">
            <div class="col-12">
              <label for="Name" class="form-label">Họ tên</label>
              <form:input type="text" class="form-control" id="Name" placeholder="Họ tên" path="HOTEN"  />
              <form:errors path="HOTEN" class="text-danger"/>
            </div>
            
            <div class="col-12">
              <label for="sdt" class="form-label">Số điện thoại</label>
              <form:input type="text" class="form-control" id="sdt" placeholder="Số điện thoại" path="SDT" 
              oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/>
              <form:errors path="SDT" class="text-danger"/>
            </div>


            <div class="col-12">
              <label for="email" class="form-label">Email</label>
              <form:input type="email" class="form-control" id="email" placeholder="you@example.com" path="EMAIL" />
            </div>

            <div class="col-12">
              <label for="address" class="form-label">Địa chỉ</label>
              <form:input type="text" class="form-control" id="address" placeholder="1234 Main St" path="DIACHI" />
              <form:errors path="DIACHI" class="text-danger"/>
            </div>

<%--             <div class="col-md-4" >
           		 <label for="birthday" class="form-label">Ngày sinh</label>
                 <form:input type = "text"  class="form-control" id="birthday" path="NGAYSINH" />
                 <!-- type="datetime-local" -->
                  <form:errors path="NGAYSINH" class="text-danger"/>
			</div> --%>
          <div class="my-3">
            <div class="form-check">
            <label class="form-check-label" for="Nam">Nam</label>
            <form:radiobutton class="form-check-input" path="GIOITINH" value="true" id="Nam"/>
            </div>
            <div class="form-check">
            <label class="form-check-label" for="Nu">Nữ</label>
            <form:radiobutton class="form-check-input" path="GIOITINH" value="false" id="Nu"/>
            </div>
          </div>
			</div>
          <button class="w-100 btn btn-primary btn-lg" type="submit">Lưu</button>
          
        </form>
        <a class="w-100 btn btn-success btn-lg" type="button" href="/BanDongHo/index.htm">Trang chủ</a>
      </div>
    </div>

  </main>

  <footer class="my-5 pt-5 text-muted text-center text-small">
    <p class="mb-1">&copy; 2017–2021 Company Name</p>
    <ul class="list-inline">
      <li class="list-inline-item"><a href="#">Privacy</a></li>
      <li class="list-inline-item"><a href="#">Terms</a></li>
      <li class="list-inline-item"><a href="#">Support</a></li>
    </ul>
  </footer>
</div>
</form:form>
</body>
</html>