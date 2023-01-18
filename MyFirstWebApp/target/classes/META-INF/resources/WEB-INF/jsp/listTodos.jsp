<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
        <title>List Todos Page </title>
<title>
    You wanna login bro</title>


<body>

            <div>Welcome ${name}</div>
            <hr>
            <h1> Your todos are</h1>
            <table>
                   <thead>
                        <tr>

                            <th>Description</th>
                            <th>Target Date</th>
                            <th>Is done? </th>
                            <th></th>
                        </tr>
                   </thead>

                   <tbody>
                        <c:forEach items = "${todos}" var = "todo">
                            <tr>

                                <td>${todo.description}</td>
                                <td>${todo.targetDate}</td>
                                <td>${todo.done}</td>
                                <td><a href="delete-todo?id=${todo.id}">DELETE ${todo.id}</a></td>
                                <td><a href="update-todo?id=${todo.id}">UPDATE ${todo.id}</a></td>


                        </c:forEach>
                    </tbody>
             </table>
             <a href="add-todo">Add Todo</a>


</body>

</html>


