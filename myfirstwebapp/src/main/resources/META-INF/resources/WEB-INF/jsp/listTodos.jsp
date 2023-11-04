<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
         <%@ include file="common/header.jspf"%>
     <body>
         <div class="container">
            <%@ include file="common/navigation.jspf"%>
            <h1>Welcome ${name}</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>Description</th>
                        <th>Target Date</th>
                        <th>Is Done?</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${todos}" var="todo">
                        <tr>
                            <td>${todo.id}</td>
                            <td>${todo.description}</td>
                            <td>${todo.formattedDate}</td>
                            <c:set var = "isDone" value = "${todo.done}"/>
                            <c:if test="${todo.done == true}">
                                <td><a href="toggle-boolean?id=${todo.id}" class="btn btn-success"> Yep </a> </td>
                            </c:if>
                            <c:if test="${todo.done == false}">
                                <td><a href="toggle-boolean?id=${todo.id}" class="btn btn-danger"> Nop </a> </td>
                            </c:if>
                            <td><a href="delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a></td>
                            <td><a href="update-todo?id=${todo.id}" class="btn btn-success">Update</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="add-todo" class="btn btn-success">Add Todo Page</a>
         </div>
        <%@ include file="common/footer.jspf"%>
     </body>
</html>