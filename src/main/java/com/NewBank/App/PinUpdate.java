package com.NewBank.App;

import java.io.IOException;

import com.NewBank.DTO.Customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/ForgotPin")
public class PinUpdate extends HttpServlet {
 @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String accno=req.getParameter("accno");
	String phone=req.getParameter("phone");
	String password=req.getParameter("pin");
	String confirmPassword=req.getParameter("cnfpin");
	
	try {
		CustomerDAO cdao=new CustomerDAO();
		int account_number=Integer.parseInt(accno);
		int pin=Integer.parseInt(password);
		int cnfPin=Integer.parseInt(confirmPassword);
		Customer c=cdao.getCustomerByAccno(account_number);
		if(pin==cnfPin) {
			c.setPin(pin);
			boolean res=cdao.updateCustomer(c);
			if(res) {
				req.setAttribute("success", "Pin generated Successfully");
				RequestDispatcher rd=req.getRequestDispatcher("ForgotPin.jsp");
				rd.forward(req, resp);
			}
			else {
				req.setAttribute("error", "Pin generation Failed ");
				RequestDispatcher rd=req.getRequestDispatcher("ForgotPin.jsp");
				rd.forward(req, resp);
			}
		}
		else {
		req.setAttribute("error", "Pin generation Failed ");
		RequestDispatcher rd=req.getRequestDispatcher("ForgotPin.jsp");
		rd.forward(req, resp);
		}
	}
	catch(Exception e) {
		req.setAttribute("error", "Pin generation Failed ");
		RequestDispatcher rd=req.getRequestDispatcher("ForgotPin.jsp");
		rd.forward(req, resp);
	}
 
 }
}
