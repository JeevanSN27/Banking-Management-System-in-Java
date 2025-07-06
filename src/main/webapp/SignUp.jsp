<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>New Bank - Sign Up</title>
  <link rel="stylesheet" href="SignUp.css">
</head>
<body>
  <div class="signup-container">
    <div class="form-box">
      <img src="logo.png" alt="Bank Logo" class="logo">
      

      <form action="SignUp" method="post">
      <% String error = (String) request.getAttribute("error"); %>
		<% if (error != null) { %>
  	<p style="color: red; font-weight: bold;"><%= error %></p>
		<% } %>

	<% Integer accno = (Integer) request.getAttribute("accno"); %>
		<% if (accno != null && accno > 0) { %>
		      <h2 class="title">Login <span><a href="Login.jsp">Click here</a></span></h2>
		
  	<p style="color: green; font-weight: bold;">
    Account created successfully!<br>
    Your Account Number is: <%= accno %>
 	 </p>
		<% } %>
		
        <input type="text" name="name" placeholder="Enter the Name" required>
        <input type="tel" name="phone" placeholder="Enter the Phone Number"  required>
        <input type="email" name="email" placeholder="Enter the Mail ID" required>
        <input type="password" name="pin" placeholder="Enter the PIN" required>
        <input type="password" name="confirmPin" placeholder="Confirm PIN" required>

        <button type="submit" class="btn">Sign Up</button>
      </form>
    </div>
  </div>
</body>
</html>
