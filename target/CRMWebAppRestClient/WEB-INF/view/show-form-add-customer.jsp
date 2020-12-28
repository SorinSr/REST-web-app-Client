<%--
  Created by IntelliJ IDEA.
  User: sorin
  Date: 02.05.2020
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form-mvc" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add customer form</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/StaticResources/css/style.css">
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/StaticResources/css/add-customer-style.css">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>
    <div id="center-container">
        <h3>Save customer</h3>
            <form-mvc:form action="saveCustomer" modelAttribute="customer" method="POST">

                <form-mvc:hidden path="id"/>

                <table>
                    <tbody>
                    <tr>
                        <td><label>First name:</label></td>
                        <td><form-mvc:input path="firstName"/></td>
                    </tr>
                    <tr>
                        <td><label>Last name:</label></td>
                        <td><form-mvc:input path="lastName"/></td>
                    </tr>
                    <tr>
                        <td><label>Email:</label></td>
                        <td><form-mvc:input path="email"/></td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td><input type="button" value="Back to list" onclick="window.location.href='${pageContext.request.contextPath}/customer/list'; return false;"
                                   class="save"/></td>
                        <td><input type="submit" value="Save" class="save"></td>
                    </tr>
                    </tbody>
                </table>
            </form-mvc:form>

    </div>
</body>
</html>
