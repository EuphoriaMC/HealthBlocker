package me.wsman217.healthblocker.database;

import me.wsman217.healthblocker.HealthBlocker;
import me.wsman217.healthblocker.utils.FileManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;

    public Database openDatabaseConnection() {
        FileConfiguration config = HealthBlocker.getFileManager().getFile(FileManager.Files.CONFIG);
        try {
            if (config.getBoolean("Database.SQLite")) {
                try {
                    Class.forName("org.sqlite.JDBC");
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                File file = new File(
                        HealthBlocker.getInstance().getDataFolder().toString() + File.separator + "HealthBlocker.db");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                }
                connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
            } else {
                String path = "Database.MySQL.";
                connection = DriverManager.getConnection("jdbc:mysql://" + config.getString(path + "IP") + ":" + config.getString(path + "port") + "/"
                        + config.getString(path + "Database"), config.getString(path + "User"), config.getString(path + "Password"));
            }
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