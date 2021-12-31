package com.ted.discordbot.data;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LiteSQL {

    private static Connection connection;

    public static void openConnection() {

        connection = null;

        try {

            File file = new File("database.db");

            if(!file.exists()) {
                file.createNewFile();

            }

            String link = "jdbc:sqlite:" + file.getPath();

            connection = DriverManager.getConnection(link);
            System.out.println("Connected to the database");

        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void disconnect() {

        if(connection != null) {
            try {
                connection.close();

            } catch (SQLException exception) {

                exception.printStackTrace();
            }
        }
    }

}
