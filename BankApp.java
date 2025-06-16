package com.skyllx.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BankApp {

    private static Connection con;
    private static PreparedStatement pstmt;
    private static PreparedStatement pstmt2;
    private static PreparedStatement pstmt3;
    private static ResultSet res;
    static int choice = 0;

    public static void main(String[] args) {
        try {
            con = MyConnection.connect();
            Scanner scan = new Scanner(System.in);

            System.out.println("Welcome to Kanchana Bank");

            // User Login
            System.out.print("Enter your Account Number: ");
            int acc_no = scan.nextInt();

            System.out.print("Enter your PIN: ");
            int pin = scan.nextInt();

            String query = "SELECT * FROM account WHERE acc_no = ? AND pin = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, acc_no);
            pstmt.setInt(2, pin);
            res = pstmt.executeQuery();

            if (res.next()) {
                System.out.println("Welcome " + res.getString("name"));

                // Menu
                System.out.println("1. Check Balance");
                System.out.println("2. Transfer Money");
                System.out.print("Enter your choice: ");
                choice = scan.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Your balance is: ₹" + res.getInt("bal"));
                        break;

                    case 2:
                        System.out.print("Enter receiver account number: ");
                        int racc_no = scan.nextInt();

                        System.out.print("Enter amount to transfer: ");
                        int amt = scan.nextInt();

                        if (amt <= 0) {
                            System.out.println("Invalid amount entered.");
                            return;
                        }

                        int currentBal = res.getInt("bal");
                        if (amt > currentBal) {
                            System.out.println("Insufficient balance.");
                            return;
                        }

                        con.setAutoCommit(false); // Begin transaction

                        // Deduct from sender
                        String deduct = "UPDATE account SET bal = bal - ? WHERE acc_no = ?";
                        pstmt2 = con.prepareStatement(deduct);
                        pstmt2.setInt(1, amt);
                        pstmt2.setInt(2, acc_no);
                        int rows1 = pstmt2.executeUpdate();

                        // Credit to receiver
                        String credit = "UPDATE account SET bal = bal + ? WHERE acc_no = ?";
                        pstmt3 = con.prepareStatement(credit);
                        pstmt3.setInt(1, amt);
                        pstmt3.setInt(2, racc_no);
                        int rows2 = pstmt3.executeUpdate();

                        // Fetch receiver name
                        PreparedStatement pstmt4 = con.prepareStatement("SELECT name FROM account WHERE acc_no = ?");
                        pstmt4.setInt(1, racc_no);
                        ResultSet res2 = pstmt4.executeQuery();

                        if (res2.next()) {
                            String receiverName = res2.getString("name");

                            if (rows1 > 0 && rows2 > 0) {
                               
                                System.out.println("₹" + amt + " transferred successfully to " + receiverName + " (Account No: " + racc_no + ")");
                            } else {
                                con.rollback();
                                System.out.println("Transaction failed.");
                            }
                        } else {
                            con.rollback();
                            System.out.println("Receiver account not found. Transaction cancelled.");
                        }
                        con.commit();
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Invalid User Credentials. Please try again.");
            }

        } catch (Exception e) {
            try {
                if (con != null) con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        MyConnection.close(res, pstmt, con);
    }
}
