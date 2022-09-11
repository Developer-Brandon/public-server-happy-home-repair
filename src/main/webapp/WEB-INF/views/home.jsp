<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"
         contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page session="false" import="java.util.*" %>
<!doctype html>
<html lang="en">
<%@ include file="includes/meta_head.jsp"%>
<body>
    <%-- 해피 홈 리페어 메인 --%>
    <div class="wrap-announce-box">
        <h1 class="title">해피 홈 리페어<br /></h1>
        <p class="explain">어드민페이지에 오신것을 환영합니다</p>
        <p class="build-time text-primary">BuildTime<br />${serverTime}.</p>
        <br />
        <button type="button"
                class="btn go-to-admin-button"
                onclick="location.href='main/index'">이동하기
        </button>
    </div>

    <style type="text/css">
        .wrap-announce-box{
            position: absolute;
            top: 50%;
            left: 72%;
            width: 320px;
            height: 240px;
            margin: -140px 0 0 -180px;
        }

        .title {
            text-align: right;
        }

        .explain {
            text-align: right;
        }

        .build-time {
            font-size: 13px;
            text-align: right;
        }

        .go-to-admin-button {
            float: right;
        }
    </style>
</body>
</html>
