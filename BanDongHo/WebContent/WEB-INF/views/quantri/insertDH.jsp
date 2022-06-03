<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
  	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  	<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
  <!-- Start header section -->
<%--   <c:if test="${empty sessionScope.myNhanVien}">
	<c:redirect url="http://localhost:9999/BanDongHo/index/sign-in.htm" />
</c:if> --%>
<jsp:include page = "./headeradmin.jsp" flush = "true" />
<c:if test="${not empty message }">
		<div id="baoloi" style="top: 70px; right: 5px; position: sticky;" >
		<div >
			<div class="alert alert-warning " style="width: 20%; height: 30px; right: -78%; top: 30px; " > 
				  <strong>Thông báo!</strong> ${message }
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close" >
				    <a  href="javascript:close()" aria-hidden="true">&times;</a>
				  </button>
				</div>
				<c:set var="message" value=""></c:set>
		</div>
		</div>
	</c:if>
<body>
<div class="container">

	<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
	<c:url value="quantri/insertDH.htm" var="pagedLink">
			<c:param name="p" value="~" />
	</c:url>
	

	<div class="card" style="margin-top: 30px;">
		<div class="card-title">
			<p style="text-align: center; color: white;">DANH SÁCH THÔNG TIN ĐỒNG HỒ</p>
		</div>
	
	</div>
	<table class="table align-middle mb-0 " style="color: black;">
  <thead class="bg-light"  style="color: black;">
    <tr>
      <th>Name</th>
      <th>Mô tả</th>
      <th>Giá</th>
      <th>Số Lượng</th>
      <th>Loại</th>
      <th>Chỉnh sửa</th>
      <th> Delete</th>
    </tr>
  </thead>
  <tbody>
    <%-- <c:forEach var = "i" items = "${donghos}">  --%>
    <c:forEach var = "i" items = "${pagedListHolder.pageList}"> 
    	<tr>
	      <td>
	        <div class="d-flex align-items-center" style="color: black;">
	          <img
	              src="imgweb/dongho/${i.HINHANH}"
	              alt=""
	              style="width: 45px; height: 45px"
	              class="rounded-circle"
	              />
	          <div class="ms-3">
	            <p class="fw-bold mb-1 color-black" >${i.MADH}</p>
	            <p class="mb-0 color-black">${i.TENDH}</p>
	          </div>
	        </div>
	      </td>
	      <td>
	        <p class=" mb-1 color-black">${i.MOTA}</p>
	        
	      </td>
	      <td>
	        <span class=" mb-1" >${i.GIA} $</span>
	      </td>
	      <td >${i.SOLUONGTON }</td>
	      <td >${i.LOAIDH}</td>
	      <c:set var="thisdongho" value="${i}"/>
	      <td style="text-align: center;">
	      	<a name="btnEdit"
				href="quantri/editDH/${i.MADH}.htm?linkEdit#form-input"
				role="button"><img width="30" height="30"
							src="<c:url value="/resources/icons/edit1.png"/>"></a>
		  </td>
	      <td style="text-align: center;">
	      	<a name="btnDelete"
				href="quantri/deleteDH/${i.MADH}.htm"
				role="button"><img width="30" height="30"
							src="<c:url value="/resources/icons/delete1.png"/>"></a>
		  </td>
	    </tr>
    </c:forEach>
 
	</tbody>
	</table>
	<div>
		<tg:paging pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />
	</div>
	<br>
	
	<main>

		<div id="form-input" >
		<form:form action="quantri/insertDH.htm" modelAttribute="dongho" method="post" enctype="multipart/form-data"  >
			<div class="row">
				<div class="col-md-8 mb-3">
				    <label for="MADH" class="form-label">Mã đồng hồ</label>
				    <c:if test="${empty canEdit}">
				    	<input type="text" class="form-control" id="MADH" placeholder="Enter MÃ ĐH" name="MADH"  value="${dongho.MADH }"/>
				    </c:if>
				    <c:if test="${not empty canEdit}">
				    	<input type="text" class="form-control" id="MADH" placeholder="Enter MÃ ĐH" name="MADH"  value="${dongho.MADH }" readonly="readonly"/>
				    </c:if>
				    <form:errors path="MADH" cssClass="errors" />
				  </div>
				  <div class="col-md-4 mb-3">
				    <label for="GIA" class="form-label">Giá</label>
				    <input type="number" step="0.1"  class="form-control" id="GIA" placeholder="Enter GIÁ" name="GIA" value="${dongho.GIA }"/>
				  	<form:errors path="GIA" cssClass="errors" />
				  </div>
			</div>
			  <div class="row">
			  	<div class="col-md-8 mb-3">
				    <label for="TENDH" class="form-label">Tên đồng hồ</label>
				    <input type="text" class="form-control" id="TENDH" placeholder="Enter TÊN ĐH" name="TENDH" value="${dongho.TENDH }"/>
				  	<form:errors path="TENDH" cssClass="errors" />
				  </div>
				  
				  <div class="col-md-4 mb-3">
				    <label for="SOLUONG" class="form-label">Số lượng</label>
				    <input type="number" class="form-control" id="SOLUONG" placeholder="Enter SỐ LƯỢNG" name="SOLUONGTON" value="${dongho.SOLUONGTON}" readonly="readonly"/>
				  	<form:errors path="SOLUONGTON" cssClass="errors" />
				  </div>
			  </div>
			  
			  <div class="row">
				  <div class="col-md-8 mb-3">
					  <span class="form-label">Mô tả</span>
					  <textarea class="form-control" aria-label="With textarea" id="MOTA" name="MOTA" placeholder="ENTER mô tả">${dongho.MOTA }</textarea>
					</div>
				  <div class="col-md-4 mb-3">
				    <label for="LOAIDH" class="form-label">Loại đồng hồ</label>
				    <input type="text" class="form-control" id="LOAIDH" placeholder="Enter LOẠI ĐH" name="LOAIDH" value="${dongho.LOAIDH }"/>
				  	<form:errors path="LOAIDH" cssClass="errors" />
				  </div>
			  </div>
			  
			  
			  <div class="input-group mb-3">
					
				  <label class="input-group-text" for="HINHANHF">Hình ảnh   </label>
				  <input type="file" class="form-control" id="HINHANHF" name="HINHANHF"  />
					<form:errors path="HINHANH" cssClass="errors" />
			  </div>
			  <div class="mb-3">
				  <c:if test="${not empty dongho.HINHANH}">
				  		<img  style="width: 100px; height: 100px" alt="${dongho.HINHANH } " src="imgweb/dongho/${dongho.HINHANH}">
				  </c:if>
			  </div>
	
		  <button type="submit" class="btn btn-primary">${submit }</button>
		  
		</form:form>
		
		
		
	</div>
	
	</main>
	
</div>
</body>
<script type="text/javascript">
 
   function close(){
	   document.getElementById("baoloi").style.visibility = "hidden"; 
	   request.getSession().removeAttribute("message");

   }
</script>
</html>