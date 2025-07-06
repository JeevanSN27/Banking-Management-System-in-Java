package com.NewBank.DTO;

public class Transaction {
private int sino;
private int accno;
private double amount;
String transactiontype;
String date;
double balance;
public Transaction(int sino, int accno, double amount, String transactiontype, String date, double balance) {
	super();
	this.sino = sino;
	this.accno = accno;
	this.amount = amount;
	this.transactiontype = transactiontype;
	this.date = date;
	this.balance = balance;
}
public Transaction() {
	super();
	// TODO Auto-generated constructor stub
}
public int getSino() {
	return sino;
}
public void setSino(int sino) {
	this.sino = sino;
}
public int getAccno() {
	return accno;
}
public void setAccno(int accno) {
	this.accno = accno;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public String getTransactiontype() {
	return transactiontype;
}
public void setTransactiontype(String transactiontype) {
	this.transactiontype = transactiontype;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}

}
