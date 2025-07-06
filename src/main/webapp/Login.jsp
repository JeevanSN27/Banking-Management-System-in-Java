<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>New Bank Login</title>
  <link rel="stylesheet" href="Login.css" />
</head>
<body>
  <div class="background">
    <div class="login-container">
      <div class="logo">
        <img src="logo.png" alt="New Bank Logo" />
      </div>
      <form action="Login" method="post">
      <%String error=(String) request.getAttribute("error"); %>
      <%if(error !=null){ %>
      <p style="color:red;"><%=error %></p>
      <%System.out.println(error); %>
      <%} %>
        <input type="text" placeholder="Account Number" name="accno" required />
        <input type="password" placeholder="PIN" name="password" required />
        <button type="submit"><a href="Home.jsp"> Login</a></button>
        <div class="links">
          <a href="ForgotPin.jsp">Forgot PIN?</a>
        </div>
      </form>
    </div>
    <div class="footer-links">
  <a href="EmployeeLogin.jsp" class="employee-btn">Employee Login</a>
  <a href="SignUp.jsp" class="pin-btn">Don't have an Account?</a>
</div>

  </div>
</body>
</html>
