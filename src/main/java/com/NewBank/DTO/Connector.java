package com.NewBank.DTO;

import java.sql.*;

public class Connector {
	public static Connection requestConnection() throws ClassNotFoundException, SQLException {
		Connection con=null;
		String url="jdbc:mysql://localhost:3306/Bank";
		String user="root";
		String password="tiger";
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection(url, user, password);
		return con;
	}
}
