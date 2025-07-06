<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Admin Dashboard</title>
  <link rel="stylesheet" href="EmployeeHome.css" />
</head>
<body>
  <header class="header">
    <div class="logo-container">
      <img src="logo.png" alt="Bank Logo" class="logo" />
      <span class="bank-name">New Bank Admin</span>
    </div>
    <a href="Login.jsp" class="logout-btn">Logout</a>
  </header>
	<form action="EmployeeHome" method="post"></form>
  <div class="container">
    <h1>Welcome, Admin</h1>
    <div class="menu">
      <a href="EmployeePassbook.jsp" class="menu-item">📘 Passbook</a>
      <a href="EmployeeSetting.jsp" class="menu-item">⚙️ Settings</a>
    </div>
  </div>
</body>
</html>
