<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!-- Start header section -->


<jsp:include page = "./headeradmin.jsp" flush = "true" />

<div class="">

    <div class="container">
        <div class="card mt-3" style="background-color: gray">
            <div class="card-content">
                <div class="row row-group m-0">
                    <div class="col-12 col-lg-6 col-xl-3 border-light">
                        <div class="card-body">
                            <h5 class="text-white mb-0">${sodondat } <span class="float-right"><i class="fa fa-shopping-cart"></i></span>
                            </h5>
                            <div class="progress my-3" style="height:3px;">
                                <div class="progress-bar" style="width:55%"></div>
                            </div>
                            <p class="mb-0 text-white small-font">Tổng đơn hàng đã bán</p>
                        </div>
                    </div>
                    <div class="col-12 col-lg-6 col-xl-3 border-light">
                        <div class="card-body">
                            <h5 class="text-white mb-0"> ${doanhthu } <span class="float-right">VNĐ</span></h5>
                            <div class="progress my-3" style="height:3px;">
                                <div class="progress-bar" style="width:55%"></div>
                            </div>
                            <p class="mb-0 text-white small-font">Tổng doanh thu</p>
                        </div>
                    </div>
                    <div class="col-12 col-lg-6 col-xl-3 border-light">
                        <div class="card-body">
                            <h5 class="text-white mb-0">${cookie.hitCounter.value} <span class="float-right"><i class="fa fa-eye"></i></span></h5>
                            <div class="progress my-3" style="height:3px;">
                                <div class="progress-bar" style="width:55%"></div>
                            </div>
                            <p class="mb-0 text-white small-font">Ghé trang (Theo tháng)</p>
                        </div>
                    </div>
                    <div class="col-12 col-lg-6 col-xl-3 border-light">
                        <div class="card-body">
                            <h5 class="text-white mb-0">${loinhuan } <span class="float-right">VNĐ</span></h5>
                            <div class="progress my-3" style="height:3px;">
                                <div class="progress-bar" style="width:55%"></div>
                            </div>
                            <p class="mb-0 text-white small-font">Lợi nhuận</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-6 col-xl-6">
                <div class="card">
                    <div class="card-header">Danh mục bán chạy
                    </div>
                   <%--  <div class="card-body">
                        <div class="chart-container-2">
                            <canvas id="chart2"></canvas>
                        </div>
                    </div> --%>
                    <div class="table-responsive">
                        <table class="table align-items-center">
                            <tbody>
                            
                            	<c:forEach var="item" items="${donghosbanchay }">
                            		<tr>
                                    <td><i class="fa fa-circle text-white mr-2"></i>${item.TENDH}</td>
                                    <td>${item.GIA} VNĐ</td>
                                    <td>remaining: ${item.SOLUONGTON }</td>
                                </tr>
                            	</c:forEach>
                            	
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-12 col-lg-6 col-xl-6">
                <div class="card">
                    <div class="card-header">Danh mục tồn kho
                    </div>
                    <%-- <div class="card-body">
                        <div class="chart-container-2">
                            <canvas id="chart3"></canvas>
                        </div>
                    </div> --%>
                    <div class="table-responsive">
                        <table class="table align-items-center">
                            <tbody>
                            <c:forEach var="item" items="${donghostonkho }">
                            	<tr>
                                    <td><i class="fa fa-circle text-white mr-2"></i>${item.TENDH}</td>
                                    <td>${item.GIA} VNĐ</td>
                                    <td>remaining: ${item.SOLUONGTON }</td>
                            </c:forEach>
                                
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<a href="javaScript:void();" class="back-to-top"><i class="fa fa-angle-double-up"></i> </a>


<%-- <jsp:include page = "./footer/footer.jsp" flush = "true" /> --%>