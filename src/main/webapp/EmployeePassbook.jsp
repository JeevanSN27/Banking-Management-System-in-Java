<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.NewBank.DTO.Transaction" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Passbook - New Bank</title>
  <link rel="stylesheet" href="EmployeePassbook.css" />
</head>
<body>

<div class="background">
  <div class="form-container">

    <form method="post" action="EmployeePassbook" class="input-form">
      <h2>View Passbook</h2>

      <% String err = (String) request.getAttribute("error"); %>
      <% if (err != null) { %>
        <p class="error-message"><%= err %></p>
      <% } %>

      <label>Account Number:</label>
      <input type="text" name="accno" required>

      <label>PIN:</label>
      <input type="password" name="pin" required>

      <div class="button-group">
        <button type="submit">View Passbook</button>
        <a href="EmployeeHome.jsp" class="home-button">Back to Home</a>
      </div>
    </form>

    <h2>Passbook</h2>
    <table>
      <thead>
        <tr>
          <th>S.No</th>
          <th>Account No</th>
          <th>Amount</th>
          <th>Type</th>
          <th>Date</th>
          <th>Balance</th>
        </tr>
      </thead>
      <tbody>
        <%
          List<Transaction> transactions = (List<Transaction>) request.getAttribute("passbook");
          if (transactions != null && !transactions.isEmpty()) {
              for (Transaction t : transactions) {
        %>
            <tr>
              <td><%= t.getSino() %></td>
              <td><%= t.getAccno() %></td>
              <td><%= t.getAmount() %></td>
              <td><%= t.getTransactiontype() %></td>
              <td><%= t.getDate() %></td>
              <td><%= t.getBalance() %></td>
            </tr>
        <%
              }
          } else {
        %>
            <tr><td colspan="6" style="text-align:center;">No transactions found</td></tr>
        <%
          }
        %>
      </tbody>
    </table>

  </div>
</div>

</body>
</html>
