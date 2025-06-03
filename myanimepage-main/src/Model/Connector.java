/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author BINTORO
 */
public class Connector {
    private static String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private static String nama_db = "linkanime";
    private static String url_db = "jdbc:mysql://localhost:3306/" + nama_db;
    private static String username_db = "root";
    private static String password_db = "";

    static Connection conn;

    //constructor
    public static Connection Connect() {
        try {
            // 1.Register JDBC 
            Class.forName(jdbc_driver);

            // 2. Koneksi database mysql
            conn = DriverManager.getConnection(url_db, username_db, password_db);

            // 3. Pesan koneksi berhasil
            System.out.println("Koneksi Berhasil");
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Koneksi Gagal : " + exception.getLocalizedMessage());
        }
        return conn;
    }
}
