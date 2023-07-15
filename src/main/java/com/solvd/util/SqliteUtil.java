package com.solvd.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SqliteUtil {

    private static final Logger LOG = LogManager.getLogger(SqliteUtil.class);
    private static final String URL = "jdbc:sqlite:./db/atm_app.db";

    public static void main(String[] args) {
        runSQLScript("src/main/resources/sql/sqlite/createSchema.sql");
        runSQLScript("src/main/resources/sql/sqlite/insertsSmall.sql");
    }

    private static void runSQLScript(String filepath) {
        try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();
            BufferedReader reader = new BufferedReader(new FileReader(filepath))
        ) {
            connection.setAutoCommit(false);  // Start a transaction
            StringBuilder sqlScript = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                // Append each line to the SQL script
                sqlScript.append(line);

                // Execute the SQL statement if it ends with a semicolon
                if (line.trim().endsWith(";")) {
                    statement.execute(sqlScript.toString());
                    sqlScript.setLength(0);  // Clear the script
                }
            }
            connection.commit();  // Commit the transaction
            connection.setAutoCommit(true);  // Enable auto-commit
            LOG.info("Script: " + filepath + " created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Script Failed: " + filepath);
        }
    }

}
