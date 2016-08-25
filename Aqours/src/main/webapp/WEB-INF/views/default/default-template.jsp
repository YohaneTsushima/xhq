<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/js/angular.js"></script>
<title></title>
</head>
<body>
	<div class="container">
		<t:insertAttribute name="header"/>
		<t:insertAttribute name="main"/>
	</div>
</body>
</html>