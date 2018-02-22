<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<html>
    <head>
        <title>Title</title>
        <link rel="stylesheet" href="styles/main.css">
        <link rel="stylesheet" href="styles/form.css">
    </head>
    <body>
        <div id="feedback-form" class="container">
            <h3 class="header">ENTER THE NAME OF NEW STUDENT</h3>
            <form action="editStudent" method="post">
                <input value="${student.name}" type="text" name="name" placeholder="NAME">
                <input value="${student.surname}" type="text" name="surname" placeholder="SURNAME">

                <select name="st_group">
                    <option disabled>CHOOSE THE OPTION</option>
                    <c:forEach var="group" items="${groups}">
                        <c:if test = "${student.group != group.id}">
                            <option value="${group.id}">${group.name}</option>
                        </c:if>
                        <c:if test = "${student.group == group.id}">
                            <option selected value="${group.id}">${group.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <button type="submit">REGISTER</button>
            </form>
        </div>
    </body>
</html>
