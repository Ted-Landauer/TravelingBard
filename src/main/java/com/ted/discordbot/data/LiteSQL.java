package com.ted.discordbot.data;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LiteSQL {

    //initialize the SQL connection instance
    private static Connection connection;

    //open a connection to the database
    public static void openConnection() {

        //make sure there isn't an already open connection
        connection = null;

        try {

            //try to write out a new DB file
            File file = new File("database.db");

            //if the file doesn't exist then create it
            if(!file.exists()) {

                file.createNewFile();
            }

            String link = "jdbc:sqlite:" + file.getPath();

            //connect to database with the path for the newly created DB file
            connection = DriverManager.getConnection(link);
            System.out.println("Connected to the database");

        } catch (SQLException | IOException exception) {

            exception.printStackTrace();
        }
    }

    //disconnect from the DB
    public static void disconnect() {

        //if the connection is open then try to disconnect
        if(connection != null) {

            try {

                connection.close();

            } catch (SQLException exception) {

                exception.printStackTrace();
            }
        }
    }
}
