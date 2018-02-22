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
            <h3>ENTER THE NAME <br> OF NEW GROUP</h3>

                <form action="editGroup" method="post">
                    <input value="${group.name}" type="text" name="group" placeholder="NAME">
                    <button type="submit">REGISTER</button>
                </form>
        </div>
    </body>
</html>
