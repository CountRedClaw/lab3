<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored ="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>The List Of Groups Of Students</title>
    <link rel="stylesheet" href="libs/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="styles/main.css">
</head>
<body>
    <div class="container">
        <h1>THE LIST</h1>
        <h2>OF GROUPS</h2>

        <form action="findGroup" method="get">
            <input type="text" name="id" placeholder="GROUP">
            <button type="submit">SEARCH</button>
        </form>

        <c:if test="${fn:length(groups) eq 0}">
            <p>THE LIST IS EMPTY</p>
        </c:if>
        <ul type="none">
            <c:forEach var="group" items="${groups}">
                <li>
                    <a href="editGroup?id=${group.id}"><span>${group.name}</span></a>
                    <a href="deleteGroup?id=${group.id}"><i class="fa fa-minus-circle" aria-hidden="true"></i></a>
                    <ul type="none">
                        <c:forEach var="student" items="${group.studentList}">
                            <li>
                                <a href="editStudent?id=${student.id}">${student.name} ${student.surname}</a>
                                <a href="deleteStudent?id=${student.id}"><i class="fa fa-minus-circle" aria-hidden="true"></i></a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
        <p>
            <a href="editGroup?id=-1">NEW GROUP</a>
            <i class="fa fa-circle" aria-hidden="true"></i>
            <a href="editStudent?id=-1">NEW STUDENT</a>
        </p>
    </div>
</body>
</html>
