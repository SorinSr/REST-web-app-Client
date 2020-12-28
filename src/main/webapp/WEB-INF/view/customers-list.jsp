<%--
  Created by IntelliJ IDEA.
  User: sorin
  Date: 25.04.2020
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form-mvc" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of customers</title>
    <link type="text/css"
            rel="stylesheet"
            href="${pageContext.request.contextPath}/StaticResources/css/style.css">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>
    <div id="center-container">
        <div id="content">

            <input type="button" value="Add customer" onclick="window.location.href='showFormAddCustomer'; return false;"
                   class="add-button"/>

            <form-mvc:form action="searchCustomerByName" method="GET">
                Search customer by name:
                <label>
                    <input type="text" name="searchName" />
                    <input type="submit" value="Submit" />
                </label>
            </form-mvc:form>

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
        </div>
    </div>
</body>
</html>
