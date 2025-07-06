<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Transfer Amount - New Bank</title>
  <link rel="stylesheet" href="Transfer.css" />
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
	case "checkbalance":
		request.getRequestDispatcher("CheckBalance.jsp").include(request, response);
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
    <h2>Transfer Amount</h2>

    <%
      String success = (String) request.getAttribute("success");
      if (success != null) {
    %>
      <p style="color:green;"><%=success%></p>
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

      <form action="Transfer" method="post">
        <input type="text" placeholder="Beneficiary Account Number" name="rec_account" required />
        <input type="number" placeholder="Amount" name="amount" required />
        <input type="password" placeholder="Enter PIN" name="pin" required />
        <button type="submit">Transfer</button>
      </form>
    </div>
  </div>

 
</body>
</html>
