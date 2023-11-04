<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <%@ include file="common/header.jspf"%>
     <body>
         <div class="container">
         <%@ include file="common/navigation.jspf"%>
            <h1>Enter Todo Details</h1>
            <form:form method="post" modelAttribute = "todo">
                <fieldset class="mb-3">
                    <form:label path="description"> Desciption </form:label>
                    <form:input path="description" type="text" required="required"/>
                    <form:errors path="description" cssClass = "text-warning"/>
                </fieldset>

                <fieldset class="mb-3">
                    <form:label path="targetDate">Target Date </form:label>
                    <form:input path="targetDate" type="text" required="required"/>
                    <form:errors path="targetDate" cssClass = "text-warning"/>
                </fieldset>


                <form:input path="id" type="hidden"/>
                <form:input path="done" type="hidden"/>
                <input type="submit" class="btn btn-success"/>
            </form:form>
         </div>
        <%@ include file="common/footer.jspf"%>
        <script type="text/javascript">
            $('#targetDate').datepicker({
                format:'dd/mm/yyyy',
                startDate: "+1d"
            });
        </script>
     </body>
</html>