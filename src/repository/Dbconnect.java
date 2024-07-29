/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author khuat
 */
public class Dbconnect {
    public static final String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=DuAn1;user=sa;password=123";

    public static Connection getDBConect() {
        try {
            Connection con = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDataSource");
            con = DriverManager.getConnection(connectionUrl);
            return con;
        } catch (Exception e) {
            System.out.println("Error Connect: " + e.toString());
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(" "+getDBConect());
    }
}
