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
                <h1 class="page-header">공지사항 수정하기</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <!-- /.panel-heading -->
                    <div class="panel-body">

                        <form role="form" action="/notice/modify" method="post">
                            <div class="form-group">
                                <label>번호</label>
                                <input class="form-control" name="noticeNo" value='<c:out value="${notice.noticeNo}"/>' readonly="readonly"/>
                            </div>
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name="title" value='<c:out value="${notice.title}"/>'/>
                            </div>
                            <div class="form-group">
                                <label>내용</label>
                                <input class="form-control" name="content" value='<c:out value="${notice.content}"/>'/>
                            </div>
                            <div class="form-group">
                                <label>사용여부</label>
                                <input class="form-control" name="useYnEnum" value='<c:out value="${notice.useYnEnum}"/>'/>
                            </div>
                            <div class="form-group">
                                <label>등록일자</label>
                                <input class="form-control" name="regDt" value='<c:out value="${notice.regDt}"/>' readonly="readonly"/>
                            </div>
                            <div class="pull-right">
                                <button
                                        type="submit"
                                        data-oper="modify"
                                        class="btn btn-default">수정하기</button>
                                <button
                                        data-oper="delete"
                                        class="btn btn-default">삭제하기</button>
                            </div>
                        </form>
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
<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
 $(document).ready(function() {

  let formObject = $('form');

  $('button').on("click", function(e) {

   e.preventDefault();


   let operation = $(this).data("oper");

   if(operation === 'delete') {
    formObject.attr("action", "/notice/remove")
   }

   formObject.submit();
  })
 });
</script>
</body>
</html>
<%@ include file="../includes/meta_footer.jsp"%>
<%@ include file="../includes/footer.jsp"%>
