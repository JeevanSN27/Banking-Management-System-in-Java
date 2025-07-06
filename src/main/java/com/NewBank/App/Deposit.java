package com.NewBank.App;

import java.io.IOException;
import java.sql.Date;

import com.NewBank.DTO.Customer;
import com.NewBank.DTO.Transaction;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Deposit")
public class Deposit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int account_number = (int) session.getAttribute("account");

        String money = req.getParameter("amount");
        double amount = Double.parseDouble(money);
        String pass = req.getParameter("pin");
        int pin = Integer.parseInt(pass);

        try {
            CustomerDAO cdao = new CustomerDAO();
            Customer customer = cdao.getCustomer(account_number, pin);

            if (customer != null) {
                double currentBalance = customer.getBalance();
                double newBalance = currentBalance + amount;
                customer.setBalance(newBalance);

                boolean updated = cdao.updateCustomer(customer);

                if (updated) {
                    Transaction t = new Transaction();
                    t.setAccno(account_number);
                    t.setAmount(amount);
                    t.setTransactiontype("DEPOSIT");
                    t.setDate(new Date(System.currentTimeMillis()).toString());
                    t.setBalance(newBalance);

                    cdao.insertTransaction(t);

                    req.setAttribute("success", "Rs." + amount + " has been successfully deposited. Updated balance: Rs." + newBalance);
                } else {
                    req.setAttribute("error", "Deposit failed. Please try again.");
                }
            } else {
                req.setAttribute("error", "Invalid account number or PIN.");
            }

            RequestDispatcher rd = req.getRequestDispatcher("Home.jsp?option=deposit");
            rd.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred! Please retry.");
            RequestDispatcher rd = req.getRequestDispatcher("Home.jsp?option=deposit");
            rd.forward(req, resp);
        }
    }
}
