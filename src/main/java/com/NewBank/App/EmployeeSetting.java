package com.NewBank.App;

import com.NewBank.DTO.Connector;
import com.NewBank.DTO.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/EmployeeSettingServlet")
public class EmployeeSetting extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            int accno = Integer.parseInt(req.getParameter("accno"));
            deleteCustomer(accno);
            resp.sendRedirect("EmployeeSettingServlet");
            return;
        }

        List<Customer> customers = new ArrayList<>();
        try (
            Connection con = Connector.requestConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM CUSTOMER");
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Customer c = new Customer();
                c.setAccno(rs.getInt("accno"));
                c.setName(rs.getString("name"));
                c.setPhone(rs.getLong("phone"));
                c.setMail(rs.getString("mail"));
                c.setBalance(rs.getDouble("balance"));
                c.setPin(rs.getInt("pin"));
                customers.add(c);
            }
            req.setAttribute("customers", customers);
            req.getRequestDispatcher("EmployeeSetting.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("edit".equals(action)) {
            try {
                int accno = Integer.parseInt(req.getParameter("accno"));
                String name = req.getParameter("name");
                long phone = Long.parseLong(req.getParameter("phone"));
                String mail = req.getParameter("mail");
                double balance = Double.parseDouble(req.getParameter("balance"));
                int pin = Integer.parseInt(req.getParameter("pin"));

                try (
                    Connection con = Connector.requestConnection();
                    PreparedStatement ps = con.prepareStatement(
                        "UPDATE CUSTOMER SET NAME=?, PHONE=?, MAIL=?, BALANCE=?, PIN=? WHERE ACCNO=?"
                    )
                ) {
                    ps.setString(1, name);
                    ps.setLong(2, phone);
                    ps.setString(3, mail);
                    ps.setDouble(4, balance);
                    ps.setInt(5, pin);
                    ps.setInt(6, accno);
                    ps.executeUpdate();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect("EmployeeSettingServlet");
        }
    }

    private void deleteCustomer(int accno) {
        try (
            Connection con = Connector.requestConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM CUSTOMER WHERE ACCNO=?")
        ) {
            ps.setInt(1, accno);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
