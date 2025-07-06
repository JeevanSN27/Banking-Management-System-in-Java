package com.NewBank.App;

import java.io.IOException;
import java.util.List;

import com.NewBank.DTO.Customer;
import com.NewBank.DTO.Transaction;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/EmployeePassbook")
public class EmployeePassbook extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accStr = req.getParameter("accno");
        String pinStr = req.getParameter("pin");

        try {
            int accno = Integer.parseInt(accStr);
            int pin = Integer.parseInt(pinStr);

            Customer customer = new CustomerDAO().getCustomer(accno, pin);
            if (customer != null) {
                List<Transaction> transactions = new CustomerDAO().getTransactionsByAccno(accno);
                req.setAttribute("passbook", transactions);
                req.getRequestDispatcher("EmployeePassbook.jsp").forward(req, resp);  
            } else {
                req.setAttribute("error", "Invalid account number or PIN.");
                req.getRequestDispatcher("EmployeePassbook.jsp").forward(req, resp);  
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred.");
            req.getRequestDispatcher("EmployeeHome.jsp").forward(req, resp);  
        }
    }
}


