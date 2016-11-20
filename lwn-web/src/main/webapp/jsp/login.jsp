<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>用户登录</title>

        <!-- CSS -->

        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link rel="stylesheet" href="/css/font-awesome.min.css">
		<link rel="stylesheet" href="/css/form-elements.css">
        <link rel="stylesheet" href="/css/style.css">

    </head>

    <body>

        <!-- Top content -->
        <div class="top-content">

                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text" style="margin-top: 20px">
                            <h1><strong>用户登录</strong></h1>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box" style="margin-top: 10px">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>登录罗威妮系统</h3>
                            		<p>请 输 入 你 的 用 户 名 和 密 码 :</p>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="${pageContext.request.contextPath}/user/login" method="post" class="login-form">
			                    	<div class="form-group">
                                        <%--<div>${requestScope.userName} </div>--%>
			                        	<input type="text" name="userName" placeholder="用户名..." class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<input type="password" name="password" placeholder="密码..." class="form-password form-control" id="form-password">
			                        </div>
                                    <div style="color: red; font-size: 20px; margin-left: 150px">
                                        ${requestScope.userName}
                                    </div>
			                        <button type="submit" class="btn">登 录</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>

        </div>


        <!-- Javascript -->
        <script src="/js/jquery-1.11.1.min.js"></script>
        <script src="/js/bootstrap.min.js"></script>
        <script src="/js/jquery.backstretch.min.js"></script>
        <script src="/js/scripts.js"></script>
        
        <!--[if lt IE 10]>
            <script src="/js/placeholder.js"></script>
        <![endif]-->

    </body>

</html>