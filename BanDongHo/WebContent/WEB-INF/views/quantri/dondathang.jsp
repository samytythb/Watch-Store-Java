<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Start header section -->
<%-- <c:if test="${empty sessionScope.myNhanVien}">
	<c:redirect url="http://localhost:9999/BanDongHo/index/sign-in.htm" />
</c:if> --%>
<jsp:include page = "./headeradmin.jsp" flush = "true" />
<body>
		<c:if test="${enableSearch == 1}">
		<div style="margin-top: 20px; text-align: center; overflow: hidden;">
			<span id="result1"></span>
			<form class="d-inline-flex">
				<input name="searchInput" id="searchInput"
					class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search" value="${searchInput }">
				<!-- 			<input name="searchInput" id="searchInput" class="form-control me-2" type="search"
					placeholder="Search" aria-label="Search" onkeyup="searchValue()"> -->
				
			
			<select class="form-control valid form-select" id="filter" name="filter" style="width: 190px; float: right;">                        
                    <option value="all" >Tất cả</option>
                    <option value="time">Thời gian</option>
                    <option value="id">Mã phiếu đặt</option>
                    <option value="daduyet">Đã duyệt</option>
                    <option value="dahuy">Đã hủy</option>
                    <option value="dangcho">Đang chờ</option>
            </select>
            <c:set var="filter" value="${filter }"></c:set>
            <button name="btnsearch" id="searchProduct"
					class="btn btn-success" type="submit">Search</button>
			
			</form>
			
		</div>
		</c:if>
		<section id="cart" class="section-p1">
		<c:forEach var="i" items="${phieudats }">
		
		<div class="card">
			<div class="row" style="margin: 0px 0px 0px 0px; ">
	
			<div class="col-md-8">
				ID: ${i.MAPD}        Ngày đặt: ${i.NGAYDAT } |  Người đặt: ${i.MAKH_PD.HOTEN } | Người nhận: ${i.HOTENNGUOINHAN }
			</div>
			<div class="col-md-4" style="text-align: right;">
				<c:if test="${i.TRANGTHAI > 0 }">
						<label for="status" style="color: lightgreen; ">Đã duyệt </label>
						
					</c:if>
					<c:if test="${i.TRANGTHAI == 0 }">
						<label for="status" style="color: darkgreen;">Đang chờ</label>
					</c:if>
					<c:if test="${i.TRANGTHAI < 0 }">
						<label for="status" style="color: red;">Đã hủy</label>
					</c:if>
					<p id="status"></p>
			</div>
		
		</div>
			
			
			<table width="100%" class="table " style="margin-bottom: 0px;">
				<thead>
					<tr>
						<td>Product</td>
						<td>Type</td>
						<td>Price</td>
						<td>Quantity</td>
						<td>Subtotal</td>
					</tr>
				</thead>
				<tbody>
				<c:set var="sumtotal" value="${0}"/>
					<c:forEach var="j" items="${i.CTPDS_PD }">
						<tr>
								<td>
							        <div class="d-flex align-items-center">
							          <img
							              src="imgweb/dongho/${j.DH_CTPD.HINHANH}"
							              alt=""
							              style="width: 45px; height: 45px"
							              class="rounded-circle"
							              />
							          <div class="ms-3">
								            <p class="fw-bold mb-1">${j.DH_CTPD.MADH}</p>
							            	<p class="text-muted mb-0">${j.DH_CTPD.TENDH}</p>
							          </div>
							        </div>
  						      </td>
								<td>${j.DH_CTPD.LOAIDH}</td>
								<td>${j.DONGIA}</td>
								<td>${j.SOLUONG}</td>
								
								<c:set var="total" value="${j.SOLUONG * j.DONGIA}"/>
								<c:set var="sumtotal" value="${sumtotal + total}"/>
								<td>$<c:out value="${total}"></c:out></td>
								
							</tr>
						</c:forEach>
				</tbody>
			</table>
			<div style="text-align: right;  border-width :2px 0px 0px 0px; border-color: gray; border-style: solid;" class="mb-3">
				
				<div style="color: red; margin: 30px 100px 30px 100px;">
					<p style="font-size: 25px; color: red;">Total: $ ${sumtotal }</p>
				</div>
				
				<c:if test="${i.TRANGTHAI >= 0}">
					<div class="">
					<!-- Lúc này lấy manv gán cho phiếu đặt -->
						<c:set var="dondathang" value="${i}"/>
						<c:if test="${i.TRANGTHAI == 1 }">
						<a href="quantri/hoadon/${i.MAPD }.htm" class="btn btn-primary " style="width: 100px; margin-bottom: 30px;margin-right: 40px;">
							Xem hóa đơn
						</a>
						</c:if>
						<c:if test="${i.TRANGTHAI == 0 }">
						<a href="quantri/dondathang/${i.MAPD }.htm?btnDuyet" class="btn btn-primary " style="width: 100px; margin-bottom: 30px;margin-right: 40px;">
							Duyệt
						</a>
						<a href="quantri/dondathang/${i.MAPD }.htm?btnHuy" class="btn btn-primary " style="width: 100px; background-color: orangered; margin-bottom: 30px; margin-right: 40px;">
							Hủy
						</a>
						</c:if>
						
					</div>
				</c:if>
				
			
			</div>
		</div>
		
		</c:forEach>
		</section>
</body>
</html>