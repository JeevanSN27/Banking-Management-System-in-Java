package com.NewBank.App;

import java.sql.*;
import java.util.*;

import com.NewBank.DTO.Connector;
import com.NewBank.DTO.Customer;
import com.NewBank.DTO.Transaction;

public class CustomerDAO {

	public static Customer getCustomerByAccno(int accno) {
		Customer c=null;
		Connection con=null; 
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query="SELECT * FROM CUSTOMER WHERE ACCNO=?";
		try {
			con=Connector.requestConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1,accno);
			rs=ps.executeQuery();
			if(rs.next()) {
				c=new Customer();
				c.setAccno(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setPhone(rs.getLong(3));
				c.setMail(rs.getString(4));
				c.setBalance(rs.getDouble(5));
				c.setPin(rs.getInt(6));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(con!=null)con.close();
			}
			catch(Exception e2) {
				
			}
		}
		return c;
	}
	
	public static int signup(String name, long phone, String mail, int pin, int confirm) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int accountNumber = 0;

	    String insertQuery = "insert into customer(name, phone, mail, pin) values (?, ?, ?, ?)";
	    String fetchQuery = "select accno from customer where phone = ?";

	    try {
	        con = Connector.requestConnection();
	        ps = con.prepareStatement(insertQuery);

	        ps.setString(1, name);
	        ps.setLong(2, phone);
	        ps.setString(3, mail);

	        if (pin == confirm && pin >= 1000 && pin <= 9999) {
	            ps.setInt(4, pin);
	            int rowsInserted = ps.executeUpdate();
	            ps.close();

	            if (rowsInserted > 0) {
	                ps = con.prepareStatement(fetchQuery);
	                ps.setLong(1, phone);
	                rs = ps.executeQuery();
	                if (rs.next()) {
	                    accountNumber = rs.getInt("accno");
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception e2) {}
	    }
	    return accountNumber; 
	}

	
	public static Customer getCustomer(int accno,int pin) {
		Customer c=null;
		Connection con=null; 
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query="SELECT * FROM CUSTOMER WHERE ACCNO=? AND Pin=?";
		try {
			con=Connector.requestConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1,accno);
			ps.setInt(2, pin);
			rs=ps.executeQuery();
			if(rs.next()) {
				c=new Customer();
				c.setAccno(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setPhone(rs.getLong(3));
				c.setMail(rs.getString(4));
				c.setBalance(rs.getDouble(5));
				c.setPin(rs.getInt(6));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(con!=null)con.close();
			}
			catch(Exception e2) {
				
			}
		}
		
		return c;
		
	}

public boolean updateCustomer(Customer c) {
		Connection con=null;
		PreparedStatement ps=null;
		String query="UPDATE CUSTOMER SET NAME=?,PHONE=?,MAIL=?,BALANCE=?,PIN=? WHERE ACCNO=?";
		int res=0;
		try {
			con=Connector.requestConnection();
			ps=con.prepareStatement(query);
			ps.setString(1, c.getName());
			ps.setLong(2, c.getPhone());
			ps.setString(3, c.getMail());
			ps.setDouble(4,c.getBalance());
			ps.setInt(5, c.getPin());
			ps.setInt(6, c.getAccno());
			res=ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				if(ps!=null)ps.close();
				if(con!=null)con.close();
			}
			catch(Exception e2) {
				
			}
		}
		if(res>0) {
			return true;
		}
		else
		{
		return false;
		}
	}
	
	 
public int insertTransaction(Transaction transaction) {
    int result = 0;
    Customer c=null;
	Connection con=null; 
	PreparedStatement ps=null;
	String query="INSERT INTO Transaction (sino, accno, amount, transactiontype, date, balance) VALUES (?, ?, ?, ?, ?, ?)";
	try {
		con=Connector.requestConnection();
		ps=con.prepareStatement(query);
            

        ps.setInt(1, transaction.getSino());
        ps.setInt(2, transaction.getAccno());
        ps.setDouble(3, transaction.getAmount());
        ps.setString(4, transaction.getTransactiontype());
        ps.setString(5, transaction.getDate());
        ps.setDouble(6, transaction.getBalance());

        result = ps.executeUpdate();

        ps.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}

public List<Transaction> getTransactionsByAccno(Integer accno) {
    List<Transaction> list = new ArrayList<>();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String query = "SELECT * FROM Transaction WHERE accno=? ORDER BY date DESC";
    
    try {
        con = Connector.requestConnection();
        ps = con.prepareStatement(query);
        ps.setInt(1, accno);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            Transaction t = new Transaction();
            t.setSino(rs.getInt("sino"));
            t.setAccno(rs.getInt("accno"));
            t.setAmount(rs.getDouble("amount"));
            t.setTransactiontype(rs.getString("transactiontype"));
            t.setDate(rs.getString("date"));
            t.setBalance(rs.getDouble("balance"));
            list.add(t);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e2) {
        	
        }
    }

    return list;
}

public boolean deleteCustomer(int accno) {
    Connection con = null;
    PreparedStatement ps = null;
    String query = "DELETE FROM CUSTOMER WHERE ACCNO=?";
    int result = 0;
    try {
        con = Connector.requestConnection();
        ps = con.prepareStatement(query);
        ps.setInt(1, accno);
        result = ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e2) {}
    }
    return result > 0;
}

public List<Customer> getAllCustomers() {
    List<Customer> list = new ArrayList<>();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String query = "SELECT * FROM CUSTOMER";
    
    try {
        con = Connector.requestConnection();
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            Customer c = new Customer();
            c.setAccno(rs.getInt("accno"));
            c.setName(rs.getString("name"));
            c.setPhone(rs.getLong("phone"));
            c.setMail(rs.getString("mail"));
            c.setBalance(rs.getDouble("balance"));
            c.setPin(rs.getInt("pin"));
            list.add(c);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    return list;
}


	
}
