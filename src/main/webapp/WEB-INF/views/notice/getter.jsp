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
                <h1 class="page-header">공지사항 조회하기</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="form-group">
                            <label>번호</label>
                            <input class="form-control" name="title" value='<c:out value="${notice.noticeNo}"/>' readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label>제목</label>
                            <input class="form-control" name="title" value='<c:out value="${notice.title}"/>' readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label>내용</label>
                            <input class="form-control" name="content" value='<c:out value="${notice.content}"/>' readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label>사용여부</label>
                            <input class="form-control" name="content" value='<c:out value="${notice.useYnEnum}"/>' readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label>등록일자</label>
                            <input class="form-control" name="content" value='<c:out value="${notice.regDt}"/>' readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label>수정일자</label>
                            <input class="form-control" name="content" value='<c:out value="${notice.modDt}"/>' readonly="readonly"/>
                        </div>
                        <div class="pull-right">
                            <button
                                    data-oper="modify"
                                    class="btn btn-default"
                                    onclick="location.href='/notice/modifier?noticeNo=<c:out value="${notice.noticeNo}"/>'">수정하기</button>
                        </div>
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

</body>
</html>
<%@ include file="../includes/meta_footer.jsp"%>
<%@ include file="../includes/footer.jsp"%>
