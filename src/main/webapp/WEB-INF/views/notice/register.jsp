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
                <h1 class="page-header">공지사항 등록</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <!-- /.panel-heading -->
                    <div class="panel-body">

                        <form role="form" action="/notice/content" method="post">
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name="title"/>
                            </div>
                            <div class="form-group">
                                <label>내용</label>
                                <input class="form-control" name="content"/>
                            </div>
                            <div class="pull-right">
                                <button type="submit" class="btn btn-default">등록하기</button>
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
<script>
 $(document).ready(function() {
 });
</script>

</body>
</html>
<%@ include file="../includes/meta_footer.jsp"%>
<%@ include file="../includes/footer.jsp"%>
