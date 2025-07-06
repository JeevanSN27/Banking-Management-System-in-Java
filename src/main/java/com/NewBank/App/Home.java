package com.NewBank.App;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/Home")
public class Home extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		
		if(isNewSessionAccountNotSet(session)) {
			resp.sendRedirect("Login.jsp");
			return;
		}
	int account_number=(int) session.getAttribute("account");
	String name=(String) session.getAttribute("name");
	
	session.setAttribute("cust_acc_no",account_number);
	session.setAttribute("cust_name", name);
	RequestDispatcher rd=req.getRequestDispatcher("Home.jsp");
	rd.forward(req, resp);
	
	}
	

	private boolean isNewSessionAccountNotSet(HttpSession session) {
		return session.isNew()||session.getAttribute("account")==null;
	}
	
	
}
