package com.NewBank.App;

import java.io.IOException;

import com.NewBank.DTO.Customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CheckBalance")
public class CheckBalance extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // false avoids creating a new session if one doesn't exist

        Object accNoObj = (session != null) ? session.getAttribute("cust_acc_no") : null;

        if (accNoObj == null) {
            req.setAttribute("error", "Session expired. Please login again.");
            RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
            rd.forward(req, resp);
            return;
        }

        int account_number = (int) accNoObj;
        String pass = req.getParameter("pin");

        try {
            int pin = Integer.parseInt(pass);

            CustomerDAO cdao = new CustomerDAO();
            Customer c = cdao.getCustomer(account_number, pin);

            if (c != null) {
                double bal = c.getBalance();
                req.setAttribute("balance", bal);
                req.setAttribute("option", "checkbalance");
                RequestDispatcher rd = req.getRequestDispatcher("Home.jsp");
                rd.forward(req, resp);
            } else {
                req.setAttribute("error", "Invalid pin!");
                req.setAttribute("option", "checkbalance");
                RequestDispatcher rd = req.getRequestDispatcher("Home.jsp");
                rd.forward(req, resp);
            }

        } catch (NumberFormatException e) {
            req.setAttribute("error", "PIN must be numeric.");
            req.setAttribute("option", "checkbalance");
            RequestDispatcher rd = req.getRequestDispatcher("Home.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "An error occurred while processing your request.");
            req.setAttribute("option", "checkbalance");
            RequestDispatcher rd = req.getRequestDispatcher("Home.jsp");
            rd.forward(req, resp);
        }
    }
}
