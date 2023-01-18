<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
        <title>List Todos Page </title>
<title>
    You wanna login bro</title>


<body>
            <div>Welcome ${name}</div>
            <hr>
            <h1> Enter Todo Details </h1>

  <form:form method = "post" modelAttribute = "todo">
        <fieldset>
        <form:label path="description">Description</form:label>
        <form:input type = "txt"      path="description"  required="required"/>
        <form:errors path="description"/>
        </fieldset>

         <fieldset>
         <form:label path="targetDate">Target Date</form:label>
         <form:input type = "txt"      path="targetDate"  required="required"/>
         <form:errors path="targetDate"/>
         </fieldset>

        <form:input type="hidden" path="id"/>
        <form:input type="hidden" path="done"/>


        <input type = "submit">
   </form:form>

</body>

</html>


