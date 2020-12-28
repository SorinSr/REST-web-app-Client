<%--
  Created by IntelliJ IDEA.
  User: sorin
  Date: 12.05.2020
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Show Customer</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/StaticResources/css/style.css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Searched customer</h2>
    </div>
</div>
<div id="center-container">
    <div id="content">
        <table>
            <tr>
                <th>Unique identifier</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
            <c:forEach var="tmpCustomers" items="${customers}">

                <c:url var="updateLink" value="/customer/showFormForUpdate">
                    <c:param name="customerId" value="${tmpCustomers.id}"/>
                </c:url>
                <c:url var="deleteLink" value="/customer/deleteCustomer">
                    <c:param name="customerId" value="${tmpCustomers.id}"/>
                </c:url>

                <tr>
                    <td>${tmpCustomers.id}</td>
                    <td>${tmpCustomers.firstName}</td>
                    <td>${tmpCustomers.lastName}</td>
                    <td>${tmpCustomers.email}</td>
                    <td><a href="${updateLink}">Update</a> |
                        <a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete customer with id '+ ${tmpCustomers.id} +'?'))) return false">Delete</a></td>

                </tr>
            </c:forEach>
        </table>
        <br>
        <input type="button" value="Back to list" onclick="window.location.href='${pageContext.request.contextPath}/customer/list'; return false;"
               class="save"/>
    </div>
</div>
</body>
</html>
