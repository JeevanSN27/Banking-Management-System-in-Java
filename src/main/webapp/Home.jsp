<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Home - New Bank</title>
  <link rel="stylesheet" href="Home.css" />
</head>
<body>
  <!-- Navbar -->
  <header class="navbar">
    <img src="logo.png" alt="New Bank Logo" class="logo" />
     <%String name=(String) session.getAttribute("cust_name"); %>
  	<%if(name!=null){ %>
    <h1>Welcome <%=name %></h1>
    <%} %>
    <nav class="nav-links">
      <a href="Home.jsp">Home</a>
      <a href="Home.jsp?option=checkbalance">Check Balance</a>
      <a href="Home.jsp?option=deposit">Deposit</a>
      <a href="Home.jsp?option=transfer">Transfer Amount</a>
      <a href="Home.jsp?option=passbook">Passbook</a>
      <a href="#">Settings</a>
    </nav>
    <button class="logout-btn"><a href="Login.jsp">Logout</a></button>  
  </header>
	<main>
<%
String option = request.getParameter("option");
if (option == null) {
    option = (String) request.getAttribute("option");
}
if (option == null || option.isEmpty()) {
    option = "home";
}



switch(option){	  
  case "checkbalance":
%>
   <jsp:include page="CheckBalance.jsp" />
<%
    break;
  case "deposit":
%>
    <jsp:include page="Deposit.jsp" />
<%
    break;
  case "passbook":
%>
	    <jsp:include page="Passbook.jsp" />
<% 
	break;
  case "transfer":
%>
    <jsp:include page="Transfer.jsp" />
<%
    break;
  case "home":
  default:
%>
    <div class="welcome-box">
      <h2>Welcome to New Bank Dashboard</h2>
      <p>Choose an option from the menu to get started.</p>
    </div>
<%
    break;
}
%>
</main>

  <!-- Background -->
  <div class="background">
    
  </div>

  <!-- Footer -->
  <footer>
    <p>&copy; 2023 New Bank</p>
  </footer>
</body>
</html>
