/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 *
 * @author ma-user
 */
public class Helper {

    public static Connection connection;

    public static synchronized boolean openConnection() {
        if (connection != null) {
            return true;
        } else {
            try {
                String u = System.getenv("sa_user");
                String pw = System.getenv("sa_pass");
                String h = System.getenv("sa_host");
                String p = System.getenv("sa_port");
                String d = System.getenv("sa_db");
                String url = "jdbc:sqlserver://" + h + ":" + p + ";databaseName=" + d;
                connection = DriverManager.getConnection(url, u, pw);
            } catch (Exception e) {
                connection = null;
                return false;
            }
        }
        return true;
    }

    public static synchronized void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
        } finally {
            connection = null;
        }
    }
    
    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replace("đ", "d");
    }
//     1234

//    public static final HashMap<String, Object> accounts = new HashMap<>();
//
//    public static void createMap() {
//        accounts.put("user", "String");
//        accounts.put("pass", "String");
//        accounts.put("role", "String");
//        accounts.put("stat", "String");
//    }
}
