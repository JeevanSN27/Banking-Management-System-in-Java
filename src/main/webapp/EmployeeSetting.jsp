<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.NewBank.DTO.Customer" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Data</title>
    <link rel="stylesheet" href="EmployeeSetting.css" />
</head>
<body>

<!-- Header with Logo and Logout -->
<div class="header-bar">
    <div class="header-left">
        <img src="logo.png" alt="Logo" />
        <h2>New Bank Admin</h2>
    </div>
    <form >
        <button type="submit" class="logout-btn"><a href="EmployeeHome.jsp"> Logout</a></button>
    </form>
</div>

<!-- Main Container -->
<div class="container-box">
    <h2 class="text-center">Customer Data</h2>
    <div class="table-responsive">
        <table class="table table-bordered table-striped bg-white text-dark">
            <thead class="thead-dark">
                <tr>
                    <th>Acc No</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Balance</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Customer> list = (List<Customer>) request.getAttribute("customers");
                    if (list != null && !list.isEmpty()) {
                        for (Customer c : list) {
                %>
                <tr>
                    <form method="post" action="EmployeeSettingServlet">
                        <input type="hidden" name="action" value="edit" />
                        <input type="hidden" name="accno" value="<%= c.getAccno() %>" />
                        <td><%= c.getAccno() %></td>
                        <td><input type="text" name="name" value="<%= c.getName() %>" class="form-control" /></td>
                        <td><input type="text" name="phone" value="<%= c.getPhone() %>" class="form-control" /></td>
                        <td><input type="text" name="mail" value="<%= c.getMail() %>" class="form-control" /></td>
                        <td><input type="text" name="balance" value="<%= c.getBalance() %>" class="form-control" /></td>
                        <input type="hidden" name="pin" value="<%= c.getPin() %>" />
                        <td><button class="btn btn-success btn-block">Edit</button></td>
                    </form>
                    <td>
                        <a href="EmployeeSettingServlet?action=delete&accno=<%= c.getAccno() %>" class="btn btn-danger btn-block"
                           onclick="return confirm('Are you sure to delete?');">Delete</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr><td colspan="7" class="text-center">No customer data available</td></tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
