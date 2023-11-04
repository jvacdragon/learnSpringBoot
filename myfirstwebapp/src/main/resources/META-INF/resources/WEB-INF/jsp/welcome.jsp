<html>
     <%@ include file="common/header.jspf"%>
     <body>
        <div class="container">
            <%@ include file="common/navigation.jspf"%>

            <h1>Welcome ${name}</h1><br>
            <div>
            <a href="http://localhost:8080/list-todos">todos page</a>
            </div>
        </div>
        <%@ include file="common/footer.jspf"%>
     </body>
</html>