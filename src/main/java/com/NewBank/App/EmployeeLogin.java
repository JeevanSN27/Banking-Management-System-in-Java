package com.NewBank.App;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/EmployeeLogin")
public class EmployeeLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accno = req.getParameter("accno");
        String password = req.getParameter("pin");
//        HttpSession session = req.getSession();

        try {
            	
            int account_number = Integer.parseInt(accno);
            int pin = Integer.parseInt(password);

            if (account_number == 7000 && pin == 8005) {
                req.setAttribute("account", account_number);
                req.setAttribute("name", "Demo User"); 
                resp.sendRedirect("EmployeeHome.jsp");
            } else {
                req.setAttribute("error", "Invalid accno or pin! Please retry");
                RequestDispatcher rd = req.getRequestDispatcher("EmployeeLogin.jsp");
                rd.forward(req, resp);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Please enter valid numeric values for Account No and PIN");
            RequestDispatcher rd = req.getRequestDispatcher("EmployeeLogin.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An unexpected error occurred. Please try again.");
            RequestDispatcher rd = req.getRequestDispatcher("EmployeeLogin.jsp");
            rd.forward(req, resp);
        }
    }
}

