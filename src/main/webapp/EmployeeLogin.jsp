<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>New Bank Login</title>
  <link rel="stylesheet" href="EmployeeLogin.css" />
</head>
<body>
  <div class="background">
    <div class="login-container">
      <div class="logo">
        <img src="logo.png" alt="New Bank Logo" />
      </div>
      <form action="EmployeeLogin" method="post">
      <%String error=(String) request.getAttribute("error"); %>
      <%if(error !=null){ %>
      <p style="color:red;"><%=error %></p>
      <%System.out.println(error); %>
      <%} %>
        <input type="text" placeholder="Employee UserId" name="accno" required />
        <input type="password" placeholder="PIN" name="pin" required />
		<button type="submit">Login</button>
        <div class="links">
        </div>
      </form>
    </div>
    

  </div>
</body>
</html>
