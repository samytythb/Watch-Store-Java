<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
  	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  	<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ page import = "java.io.*,java.util.*" %>
	<%@ page import = "javax.servlet.*,java.text.*" %>
  <!-- Start header section -->
<%--   <c:if test="${empty sessionScope.myNhanVien}">
	<c:redirect url="http://localhost:9999/BanDongHo/index/sign-in.htm" />
</c:if> --%>
  
 <head>
	<title>Nhập đồng hồ</title>
</head>
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
	<c:url value="nhaphang/insertPN.htm" var="pagedLink">
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

	<div>
		Mã đồng hồ chưa có? <a href="quantri/insertDH.htm">Thêm mã đồng hồ</a>
					
	</div>
	<c:if test="${add == 0}">
		<a class="btn btn-primary" href="nhaphang/insertPN.htm?addPN">
			Thêm Phiếu nhập
		</a>
	</c:if>
	<c:if test="${add == 1}">
	<div id="form-nhap" class="mb-3">
        <div class="container">
          <div class="row mt-3">
            <div class="col-lg-12">
              <div class="card">
                <div class="card-body">
                  <div class="card-title">Nhập hàng - Mã phiếu nhập: ${maphieunhap }
              
                  </div>
                  <hr>
    
                  	<c:forEach var="ctpn" items="${CTPNS }">
                  	<form method="post" action="nhaphang/insertPN/updatectpn.htm#form-nhap" > 
                  	<div class="row">
                  		<input type="hidden" name="PN_CTPN" value="${ctpn.PN_CTPN.MAPN }">
                  		<input type="hidden" name="DH_CTPN_OLD" value="${ctpn.DH_CTPN.MADH }">
                  		<div class="form-group col-md-6 mb-3">
                  			
                      	  <label for="DH_CTPN">Chọn đồng hồ</label>
	                        <div>
	                          <!-- <select class="form-control valid form-select" multiple="multiple" id="MADH_NHAP" name="MADH_NHAP" aria-label="multiple select example" > -->
	                          <select class="form-control valid form-select" id="DH_CTPN" name="DH_CTPN" path="DH_CTPN" onblur="this.form.submit()">
	                          	<c:forEach items="${donghos}" var="dongho">
		                          	<c:if test="${dongho.MADH == ctpn.DH_CTPN.MADH }">
		                          		<option value="${dongho.MADH}" selected="selected" >${dongho.MADH  } | ${dongho.TENDH }</option>
		                          	</c:if>
		                          	<c:if test="${dongho.MADH != ctpn.DH_CTPN.MADH }">
		                          		  <option value="${dongho.MADH}" >${dongho.MADH  } | ${dongho.TENDH }</option>
		                          	</c:if>
	                          	</c:forEach>
	                          </select>
	                      
	                        </div>
                   		</div>
                    	<div class="form-group col-md-2 mb-3">
                  			<label for="SOLUONG">Số lượng</label>
                     		<input type="number" class="form-control" id="SOLUONG" placeholder="SL" name="SOLUONG" 
                     			value="${ctpn.SOLUONG }" path="SOLUONG" onblur="this.form.submit()" />
                    	</div>
                    	<div class="form-group col-md-2 mb-3">
                  			<label for="DONGIA">Đơn giá</label>
                      		<input type="number" class="form-control" id="DONGIA" placeholder="Nhập đơn giá" name="DONGIA" 
                      			value="${ctpn.DONGIA }" path="DONGIA" onblur="this.form.submit()"/>
                    	</div>
                    	<div class="form-group col-md-2 mb-3 text-center">
                    	
                  			<a style="margin-top: 25px; " id="btnXoa" class="btn btn-danger" 
                  			href="nhaphang//insertPN/${ctpn.PN_CTPN.MAPN}${ctpn.DH_CTPN.MADH}.htm?delProduct">Xóa</a>
                    	</div>
                    
                  </div>
                  <hr>
                  </form>
                  	</c:forEach>
         
                  
                  	<form:form method="post" modelAttribute="ctpnn" action="nhaphang/insertPN/${maphieunhap}.htm?addProduct#form-nhap">
                  		<div class="row">
                  		
                  		<div class="form-group col-md-6 mb-3">
                  			<form:label path="PN_CTPN"  ></form:label>
                      	  <label for="DH_CTPNN">Chọn đồng hồ</label>
	                        <div>
	                          <!-- <select class="form-control valid form-select" multiple="multiple" id="MADH_NHAP" name="MADH_NHAP" aria-label="multiple select example" > -->
	                          <form:select class="form-control valid form-select"  id="DH_CTPNN" name="DH_CTPNN" path="DH_CTPN" >
	                          <%-- <c:forEach items="${donghos}" var="dongho">
	                              <option value="${dongho}">${dongho.MADH  } | ${dongho.TENDH }</option>
	                          </c:forEach> --%>
	                          <c:forEach items="${donghos}" var="dongho">
	                              <form:option value="${dongho.MADH}">${dongho.MADH  } | ${dongho.TENDH }</form:option>
	                          </c:forEach>
	                          </form:select> 
	                          <form:errors path="DH_CTPN" cssClass="errors" />
	                        </div>
                   		</div>
                    	<div class="form-group col-md-2 mb-3">
                  			<label for="SOLUONG">Số lượng</label>
                     		<form:input type="number" class="form-control" id="SOLUONG" placeholder="SL" name="SOLUONG" path="SOLUONG" value="${ctpnn.SOLUONG }"/>
                    		<form:errors path="SOLUONG" cssClass="errors" />
                    	</div>
                    	<div class="form-group col-md-2 mb-3">
                  			<label for="DONGIA">Đơn giá</label>
                      		<form:input type="number" class="form-control" id="DONGIA" placeholder="Nhập đơn giá" name="DONGIA" path="DONGIA" value="${ctpnn.DONGIA }"/>
                    		<form:errors path="DONGIA" cssClass="errors" />
                    	</div>
                    	<div class="form-group col-md-2 mb-3 text-center">
                    		
                  			<%-- <a style="margin-top: 25px; " id="btnXoa" class="btn btn-danger" href="nhaphang/insertPN/${ctpn.DH_CTPN }.htm$btnXoa" onclick="" >Xóa</a> --%>
                    	</div>
                  </div>
                  	
                  	<button type="submit" data-toggle="modal" data-target="#exampleModal" class="btn btn-primary mb-3"><i class="fa fa-check-square-o"></i> Add product</button>
                  	<div class="form-footer mb-3">
                      <a class="btn btn-danger" href="nhaphang/huyphieunhap/${maphieunhap}.htm">Hủy</a>
                      <a  class="btn btn-success" href="nhaphang/insertPN/${maphieunhap}.htm?success"><i class="fa fa-check-square-o"></i> Lưu</a>
                  </div>
                  	</form:form>
                  
                  <!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
     			<%-- <c:set var="MAPN" value="${MAPN_title}"/>
     			<c:set var="admin" value="NV0"/>
     			<c:set var="MANV_PN" value="${admin}"/> --%>
     			<%-- <jsp:useBean id="now" class="java.util.Date" />
				<fmt:formatDate value ="${now}"
				                pattern="yyyy-MM-dd HH-mm-ss"  />
     			<c:set var="ngaynhap" value="${now}"/> --%>
                 
               <%--    </form:form> --%>
                  
                </div>
              </div>
            </div>
          </div>
          <div class="overlay toggle-menu"></div>
        </div>
      </div>
	</c:if>
</div>



</body>
<script type="text/javascript">
 
   function close(){
	   document.getElementById("baoloi").style.visibility = "hidden"; 
	   request.getSession().removeAttribute("message");
     /* const element = document.getElementById('baoloi'); //clears the field
     element.remove(); */
   }
</script>
</html>