<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>비트캠프 - NCP 1기</title>
</head>
<body>
<h1>강사(JSP + MVC2 + EL + JSTL)</h1>

<div><a href='form'>새 강사</a></div>

<table border='1'>
<tr>
  <th>번호</th> <th>이름</th> <th>전화</th> <th>학위</th> <th>전공</th> <th>시강료</th>
</tr>

<c:forEach items="${teachers}" var="t">

  <tr>
      <td>${t.no}</td> 
      <td><a href='view?no=${t.no}'>${t.name}</a></td> 
      <td>${t.tel}</td> 
      
      <td>
<c:choose>
 <c:when test="${t.degree == 1}">
 고졸
</c:when>
 <c:when test="${t.degree == 2}">
 전문학사
</c:when>
 <c:when test="${t.degree == 3}">
 학사
</c:when>
 <c:when test="${t.degree == 4}">
 석사
</c:when>
 <c:when test="${t.degree == 5}">
 박사
</c:when>
<c:otherwise>
기타
</c:otherwise>
</c:choose>
      </td> 
      
      <td>${t.major}</td> 
      <td>${t.wage}</td>
  </tr>
  </c:forEach>
</table>

</body>
</html>


