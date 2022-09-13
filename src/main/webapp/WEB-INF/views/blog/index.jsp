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
                <h1 class="page-header">블로그 게시글 현황</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-red">
                    <div class="panel-heading">
                        블로그 게시글 크롤링 후 이동하는 기능 목록
                    </div>
                    <div class="panel-body">
                        <button type="button"
                                class="btn btn-danger bulkBtn"
                                onclick="location.href='/blog/list/bulk'">크롤링 후 데이터 바로 옮기기</button>
                        <button type="button"
                                class="btn btn-danger diffBulkBtn"
                                onclick="location.href='/blog/list/diff-bulk'">크롤링 후 다른 데이터만 옮기기</button>
                    </div>
                    <div class="panel-footer">
                        <p>1. 첫번째 버튼은 초기화 후에 크롤링 데이터를 전부 옮기는 기능입니다.</p>
                        <p>2. 두번째 버튼은 크롤링 데이터와 기존 DB리스트를 비교한 후 데이터를 전부 옮기는 기능입니다.</p>
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
                                    <th>포스팅 번호</th>
                                    <th>제목</th>
                                    <th>포스팅 등록일자</th>
                                    <th>데이터 등록일자</th>
                                </tr>
                            </thead>
                            <!-- tbody start -->
                            <c:forEach items="${blogList}" var="blog">
                                    <tr>
                                        <td><c:out value="${blog.postingNo}"/></td>
                                        <td><c:out value="${blog.title}"/></td>
                                        <td><c:out value="${blog.postingRegDt}"/></td>
                                        <td><c:out value="${blog.regDt}"/></td>
                                    </tr>
                            </c:forEach>
                            <!-- tbody end -->
                        </table>
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
  //
  $("#regBtn").on("click", function() {
   self.location = "/blog/register";
  })
 });
</script>

</body>
</html>
<%@ include file="../includes/meta_footer.jsp"%>
<%@ include file="../includes/footer.jsp"%>
