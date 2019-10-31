package me.wsman217.healthblocker.database;

import me.wsman217.healthblocker.HealthBlocker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;
    private HealthBlocker plugin = HealthBlocker.getInstance();

    public Database openDatabaseConnection() {
        try {

            connection = DriverManager.getConnection("jdbc:mysql://" + "178.63.157.86" + ":" + "3306" + "/"
                    + "s736_HealthBlocker", "u736_PjCkOAmgDv", "mtetjErRgtdkcs7bx9qcvE6X");

        } catch (SQLException sQLException) {
            System.out.println("ERROR CONNECTING TO DATABASE!");
            sQLException.printStackTrace();
        }
        return this;
    }

    public Database closeConnection() {
        try {
            connection.close();
        } catch (SQLException sQLException) {
            System.out.println("COULD NOT CLOSE CONNECTION!");
            sQLException.printStackTrace();
        }
        return this;
    }

    Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                this.openDatabaseConnection();
            }
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return connection;
    }
}
//This is a comment to get git to work hopefully.