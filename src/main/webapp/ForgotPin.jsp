<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Forgot PIN - New Bank</title>
  <link rel="stylesheet" href="ForgotPin.css">
</head>
<body>
  <header class="navbar">
    <nav class="nav-links">
   </nav>
    <button class="logout-btn"><a href="Login.jsp">Logout</a></button>
  </header>
  <div class="overlay">
    <div class="forgot-container">
      <img src="logo.png" alt="Bank Logo" class="logo">
      <h2>Reset PIN</h2>
      <form action="ForgotPin" method="post">
      	<%String success=(String) request.getAttribute("success"); %>
      	<%if(success!=null){ %>
      	<%=success %>
      	<%} %>
      	
      	<%String error=(String)request.getAttribute("error"); %>
      	<%if(error!=null){ %>
      	<%=error %>
      	<%} %>
      	
        <input type="text" placeholder="Account Number" name="accno" required>
        <input type="tel" placeholder="Phone Number" name="phone" required>
        <input type="password" placeholder="New PIN" name="pin" required>
        <input type="password" placeholder="Confirm PIN" name="cnfpin" required>
        <button type="submit">Submit</button>
      </form>
    </div>
  </div>
</body>
</html>
