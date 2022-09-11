<%--
  Created by IntelliJ IDEA.
  User: BrandonLee
  Date: 2022-08-25
  Time: 오전 6:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <!-- 모바일 화면일때 -->
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="index.html">해피 홈 리페어</a>
            <div class="nav-collapse">
                <!-- -->
                <!-- <ul class="nav pull-right">
                  <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-cog"></i>
                      Account <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <li><a href="javascript:;">Settings</a></li>
                      <li><a href="javascript:;">Help</a></li>
                    </ul>
                  </li>
                  <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-user"></i>
                      EGrappler.com <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <li><a href="javascript:;">Profile</a></li>
                      <li><a href="javascript:;">Logout</a></li>
                    </ul>
                  </li>
                </ul> -->
                <form class="navbar-search pull-right">
                    <input type="text" class="search-query" placeholder="Search">
                </form>
            </div>
            <!--/.nav-collapse -->
        </div>
        <!-- /container -->
    </div>
    <!-- /navbar-inner -->
</div>
<!-- /navbar -->
<div class="subnavbar">
    <div class="subnavbar-inner">
        <div class="container">
            <ul class="mainnav">
                <li class="active">
                    <a href="index.html">
                        <i class="icon-dashboard"></i>
                        <span>대시보드</span>
                    </a>
                </li>
                <li>
                    <a href="reports.html">
                        <i class="icon-list-alt"></i>
                        <span>접수현황</span>
                    </a>
                </li>
                <li><a href="guidely.html">
                    <i class="icon-bullhorn">
                    </i>
                    <span>공지사항</span>
                </a>
                </li>
                <li>
                    <a href="charts.html">
                        <i class="icon-question"></i>
                        </i><span>자주하는질문</span>
                    </a>
                </li>
                <li><a href="shortcodes.html">
                    <i class="icon-code"></i>
                    <span>Shortcodes</span>
                </a>
                </li>
                <li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i
                        class="icon-long-arrow-down"></i><span>Drops</span> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="icons.html">Icons</a></li>
                        <li><a href="faq.html">FAQ</a></li>
                        <li><a href="pricing.html">Pricing Plans</a></li>
                        <li><a href="login.html">Login</a></li>
                        <li><a href="signup.html">Signup</a></li>
                        <li><a href="error.html">404</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /container -->
    </div>
    <!-- /subnavbar-inner -->
</div>
<!-- /subnavbar -->
