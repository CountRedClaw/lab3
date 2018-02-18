<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored ="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Show All Users</title>
</head>
<body>


    <%--<%
        for (Student student : studentList.getAllStudents()) {
    %>
    <li>
        <strong>Имя:</strong> <%=student.getName()%>
        <strong>Фамилия:</strong> <%=student.getSurname()%>
        <strong>Группа:</strong> <%=student.getGroup() %>
        <a href="#">Удалить</a>
        <a href="#">Изменить</a>
    </li>

    <%}%>--%>

    <ul type="none">
        <c:forEach var="group" items="${groups}">
        <li>
            <span>'${group.name}'</span>
            <a href="editgroup?id=${group.id}">edit</a>
            <a href="deletegroup?id=${group.id}">delete</a>
            <ul type="none">
                <c:forEach var="student" items="${group.studentList}">
                    <li>
                        <span>
                            ${student.name}
                            ${student.surname}
                        </span>
                        <a href="edituser?id=${student.id}">update</a>
                        <a href="deleteuser?id=${student.id}">delete</a>
                    </li>
                </c:forEach>
            </ul>
        </li>
        </c:forEach>
    </ul>

    <%--<p>
        <ul>
        <c:forEach items="${groups}" var="group">
            <li>
            <c:out value="${group.name}" />
            </li>
        </c:forEach>
        </ul>
    </p>--%>
<%--<p><a href="UserController?action=insert">Add User</a></p>--%>
</body>
</html>