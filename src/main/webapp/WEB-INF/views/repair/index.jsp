<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"
         contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page session="false" import="java.util.*" %>
<!doctype html>
<%@ include file="../includes/meta_head.jsp"%>
<%@ include file="../includes/header.jsp"%>
<html lang="en">
<body>

<div id="wrapper">
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">수리 신청 현황</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        타입 번호에 따른 맵핑값
                    </div>
                    <div class="panel-body">
                        <c:forEach items="${repairTypeList}" var="repairType">
                            <ul>
                                <li><c:out value="${repairType.repairTypeNo}"/> / <c:out value="${repairType.title}"/> / <c:out value="${repairType.explanation}"/></li>
                            </ul>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        지역 번호에 따른 맵핑값
                    </div>
                    <div class="panel-body">
                        <c:forEach items="${repairLocationList}" var="repairLocation">
                            <ul>
                                <li><c:out value="${repairLocation.repairLocationNo}"/> / <c:out value="${repairLocation.name}"/> / <c:out value="${repairLocation.explanation}"/></li>
                            </ul>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        상태 번호에 따른 맵핑값
                    </div>
                    <div class="panel-body">
                        <c:forEach items="${repairStateList}" var="repairState">
                            <ul>
                                <li><c:out value="${repairState.repairStateNo}"/> / <c:out value="${repairState.name}"/> / <c:out value="${repairState.explanation}"/></li>
                            </ul>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        &nbsp;
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <table width="100%" class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>신청 번호</th>
                                    <th>타입 번호</th>
                                    <th>지역 번호</th>
                                    <th>상태 번호</th>
                                    <th>유저 타입</th>
                                    <th>전화번호</th>
                                    <th>설명</th>
                                    <th>등록일자</th>
                                    <th>수정일자</th>
                                </tr>
                            </thead>
                            <!-- tbody start -->
                            <c:forEach items="${repairApplyList}"
                                       var="repairApply">
                                <tr>
                                    <td><c:out value="${repairApply.repairApplyNo}"/></td>
                                    <td><c:out value="${repairApply.repairTypeNo}"/></td>
                                    <td><c:out value="${repairApply.repairLocationNo}"/></td>
                                    <td><c:out value="${repairApply.repairStateNo}"/></td>
                                    <td><c:out value="${repairApply.userTypeEnum}"/></td>
                                    <td><c:out value="${repairApply.phoneNumber}"/></td>
                                    <td><c:out value="${repairApply.explanation}"/></td>
                                    <td><c:out value="${repairApply.regDt}"/></td>
                                    <td><c:out value="${repairApply.modDt}"/></td>
                                </tr>
                            </c:forEach>
                            <!-- tbody end -->
                        </table>
                        <!-- pagination start -->
                        <c:if test="${pageHandler.totalCnt != null && pageHandler.totalCnt != 0}">
                            <div class="pull-right">
                                <ul class="pagination">
                                    <c:if test="${pageHandler.showPrev}">
                                        <li class="paginate_button previous">
                                            <a href="<c:url value="/repair/content/list${pageHandler.sc.getQueryString(pageHandler.beginPage - 1)}"/>">이전</a>
                                        </li>
                                    </c:if>

                                    <c:forEach var="i"
                                               begin="${pageHandler.beginPage}"
                                               end="${pageHandler.endPage}">
                                        <li class="paginate_button ${i==pageHandler.sc.page? "active" : ""}">
                                            <a href="<c:url value="/repair/content/list${pageHandler.sc.getQueryString(i)}"/>">${i}</a>
                                        </li>
                                    </c:forEach>

                                    <c:if test="${pageHandler.showNext}">
                                        <li class="paginate_button next">
                                            <a href="<c:url value="/repair/content/list${pageHandler.sc.getQueryString(pageHandler.endPage + 1)}"/>">다음</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                        </c:if>
                        <!-- pagination end -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
 $(document).ready(function() {
  $('#dataTables-example').DataTable({
   responsive: true
  });
 });
</script>

</body>
</html>
<%@ include file="../includes/meta_footer.jsp"%>
<%@ include file="../includes/footer.jsp"%>
