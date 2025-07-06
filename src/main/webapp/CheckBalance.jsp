<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Check Balance - New Bank</title>
  <link rel="stylesheet" href="CheckBalance.css" />
</head>
<body>
  <!-- Navbar -->
  	
	<main>
	<%
	String option=request.getParameter("option");
	if(option==null || option.isEmpty()){
		option="Home.jsp";
	}
	switch(option){
	case "home":
		request.getRequestDispatcher("Home.jsp").include(request, response);
		break;
	case "deposit":
		request.getRequestDispatcher("Deposite.jsp").include(request, response);
		break;
	case "transfer":
		request.getRequestDispatcher("Transfer.jsp").include(request, response);
		break;
	case "passbook":
		request.getRequestDispatcher("Passbook.jsp").include(request, response);
		break;
	}
	%>
	</main>

  <!-- Check Balance Content Fragment -->
<div class="background">
  <div class="form-container">
    <h2>Check Balance</h2>

    <%
      Double balObj = (Double) request.getAttribute("balance");
      if (balObj != null) {
    %>
      <p style="color:green;">Your balance is: ₹<%= String.format("%.2f", balObj) %></p>
    <%
      } else {
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
      <p style="color:red;"><%= error %></p>
    <%
        }
      }
    %>

    <form action="CheckBalance" method="post">
      <input type="password" name="pin" placeholder="Enter PIN" required />
      <button type="submit">Check Balance</button>
    </form>
  </div>
</div>

</body>
</html>
