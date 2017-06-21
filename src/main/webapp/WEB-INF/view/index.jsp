<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
	<%-- <h1>${message}</h1>	
	<h1>${error}</h1> --%>
	
	<%-- <form action="processExcel" method="post"
		enctype="multipart/form-data">
		<div>Excel File 2003:</div>
		<input name="excelfile" type="file">
		<input type="submit" value="processExcel">
	</form> --%>
	<hr>
	<form action="processExcel" method="post"
		enctype="multipart/form-data">
		<div>Excel File 2007:</div>
		<input name="excelfile2007" type="file">		
		<input type="submit" value="processExcel2007">
	</form>
	<hr>
	<h3>Users List</h3>
	<c:if test="${!empty lstMemberData}">
		<table class="tg">
			<tr>
				<th width="80">DoorNo</th>
				
			</tr>
			<c:forEach items="${lstMemberData}" var="memberdata">
				<tr>
					<td>${DoorNo}</td>
								
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
		</body>
</html>
