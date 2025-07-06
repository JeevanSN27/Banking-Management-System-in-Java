package com.NewBank.App;

import java.io.IOException;
import java.util.Scanner;

import com.NewBank.DTO.Customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/Login")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accno=req.getParameter("accno");
		String password=req.getParameter("password");
		HttpSession s=req.getSession();
		
		try {
			CustomerDAO cdao=new CustomerDAO();
			int account_number=Integer.parseInt(accno);
			int pin=Integer.parseInt(password);
			Customer c=cdao.getCustomer(account_number, pin);
			if(c!=null) {
				String name=c.getName();
				s.setAttribute("account", account_number);
				s.setAttribute("name", name);
				resp.sendRedirect("Home");
			
			}
			else {
				req.setAttribute("error", "Invalid accno or pin! Please retry");
				RequestDispatcher rd=req.getRequestDispatcher("Login.jsp");
				rd.forward(req, resp);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Invalid accno or pin! Please retry");
			RequestDispatcher rd=req.getRequestDispatcher("Login.jsp");
			rd.forward(req, resp);
		}
	
	}
}
