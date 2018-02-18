<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <select name="group_user">
            <option disabled>Выберите группу</option>
            <%
                ArrayList<String> list = (ArrayList<String>) request.getAttribute("groups");
                for (String str : list) {
            %>

            <%=str%>
            <option value="${str.id}">${group.name}</option>
            <%}%>

        </select>

    </body>
</html>
