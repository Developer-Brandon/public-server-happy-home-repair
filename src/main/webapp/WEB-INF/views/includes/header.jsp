<%--
  Created by IntelliJ IDEA.
  User: BrandonLee
  Date: 2022-08-25
  Time: 오전 6:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/main/index">&nbsp;<i class="fa fa-home fa-fw"></i>&nbsp;해피 홈 리페어 어드민 페이지</a>
    </div>
    <!-- /.navbar-header -->

    <ul class="nav navbar-top-links navbar-right">
        <li class="dropdown">
        </li>
        <!-- /.dropdown -->
        <li class="dropdown">
        </li>
        <!-- /.dropdown -->
        <li class="dropdown">
        </li>
        <!-- /.dropdown -->
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                </li>
                <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                </li>
                <li class="divider"></li>
                <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                </li>
            </ul>
            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links -->

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li>
                    <a href="/main/index" class="text-muted" style="font-weight:500">
                        <i class="fa fa-table fa-fw"></i>
                        수리 신청 현황
                    </a>
                </li>
                <li>
                    <a href="/notice/index" class="text-muted" style="font-weight:500">
                        <i class="fa fa-info-circle fa-fw"></i>
                        공지사항
                    </a>
                </li>
                <li>
                    <a href="/faq/index" class="text-muted" style="font-weight:500">
                        <i class="fa fa-question-circle fa-fw"></i>
                        자주하는질문
                    </a>
                </li>
                <li>
                    <a href="/blog/index" class="text-muted" style="font-weight:500">
                        <i class="fa fa-clipboard fa-fw"></i>
                        블로그 게시글
                    </a>
                </li>
            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>
