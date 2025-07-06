package com.NewBank.App;

import java.io.IOException;

import com.NewBank.DTO.Customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String phoneStr = req.getParameter("phone");
        String email = req.getParameter("email");
        String pinStr = req.getParameter("pin");
        String confirmStr = req.getParameter("confirmPin");

        try {
            long phone = Long.parseLong(phoneStr);
            int pin = Integer.parseInt(pinStr);
            int confirm = Integer.parseInt(confirmStr);

            int accno = CustomerDAO.signup(name, phone, email, pin, confirm);

            if (accno > 0) {
                req.setAttribute("accno", accno);
                RequestDispatcher rd = req.getRequestDispatcher("SignUp.jsp");
                rd.forward(req, resp);
            } else {
                req.setAttribute("error", "Signup failed! Please ensure PINs match and try again.");
                RequestDispatcher rd = req.getRequestDispatcher("SignUp.jsp");
                rd.forward(req, resp);
            }

        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid input! Phone and PIN must be numeric.");
            RequestDispatcher rd = req.getRequestDispatcher("SignUp.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Something went wrong. Please try again.");
            RequestDispatcher rd = req.getRequestDispatcher("SignUp.jsp");
            rd.forward(req, resp);
        }
    }
}
