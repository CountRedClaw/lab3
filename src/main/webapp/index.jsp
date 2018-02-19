<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <%--<h1>The List Of Groups Of Students</h1>--%>
    <div class="container">
        <h1>THE LIST</h1>
        <h2 class="container1">OF GROUPS</h2>
        <ul type="none">
            <c:forEach var="group" items="${groups}">
                <li>
                    <span>${group.name}</span>
                    <a href="editGroup?id=${group.id}"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                    <a href="deleteGroup?id=${group.id}"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
                    <ul type="none">
                        <c:forEach var="student" items="${group.studentList}">
                            <li>
                                ${student.name}
                                ${student.surname}
                                <a href="edituser?id=${student.id}"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                                <a href="deleteuser?id=${student.id}"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>