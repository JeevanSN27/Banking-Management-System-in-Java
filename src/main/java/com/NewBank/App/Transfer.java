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

@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int account_number = (int) session.getAttribute("account");

        String rec_acc = req.getParameter("rec_account");
        int benificiary = Integer.parseInt(rec_acc);
        String money = req.getParameter("amount");
        double amount = Double.parseDouble(money);
        String pass = req.getParameter("pin");
        int pin = Integer.parseInt(pass);

        try {
            CustomerDAO cdao = new CustomerDAO();
            Customer sender = cdao.getCustomer(account_number, pin);
            Customer receiver = cdao.getCustomerByAccno(benificiary);

            double senderBal = sender.getBalance();
            double receiverBal = receiver.getBalance();

            if (amount <= senderBal && account_number != benificiary) {
                // Update balances
                senderBal -= amount;
                receiverBal += amount;
                sender.setBalance(senderBal);
                receiver.setBalance(receiverBal);

                boolean senderUpdated = cdao.updateCustomer(sender);
                boolean receiverUpdated = cdao.updateCustomer(receiver);

                if (senderUpdated && receiverUpdated) {
                    // Date for transaction
                    String today = new Date(System.currentTimeMillis()).toString();

                    // Transaction for sender
                    Transaction tSender = new Transaction();
                    tSender.setAccno(account_number);
                    tSender.setAmount(amount);
                    tSender.setTransactiontype("TRANSFER_OUT");
                    tSender.setDate(today);
                    tSender.setBalance(senderBal);
                    cdao.insertTransaction(tSender);

                    // Transaction for receiver
                    Transaction tReceiver = new Transaction();
                    tReceiver.setAccno(benificiary);
                    tReceiver.setAmount(amount);
                    tReceiver.setTransactiontype("TRANSFER_IN");
                    tReceiver.setDate(today);
                    tReceiver.setBalance(receiverBal);
                    cdao.insertTransaction(tReceiver);

                    req.setAttribute("success", "The amount of Rs." + amount + " has been sent to Account number " + benificiary +
                            ". Your Updated balance is Rs." + senderBal);
                } else {
                    req.setAttribute("error", "Transfer failed during balance update.");
                }
            } else {
                req.setAttribute("error", "Insufficient balance or invalid recipient.");
            }

            RequestDispatcher rd = req.getRequestDispatcher("Home.jsp?option=transfer");
            rd.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred! Please retry.");
            RequestDispatcher rd = req.getRequestDispatcher("Home.jsp?option=transfer");
            rd.forward(req, resp);
        }
    }
}
