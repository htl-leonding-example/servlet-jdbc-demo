package at.htl.simpleservlet.controller;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    static final String DATABASE = "db";
    static final String USERNAME = "app";
    static final String PASSWORD = "app";
    public static final String URL = "jdbc:postgresql://" + getDatabaseHost() + ":5432/" + DATABASE;


    public static DataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName(DATABASE);
        dataSource.setUser(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setURL(URL);
        return dataSource;
    }

    public static void createTablePerson() {
        System.out.println(URL);
        try (Connection conn = getDataSource().getConnection();
             Statement stmt = conn.createStatement()
        ) {
            String sql = "CREATE TABLE person (id int CONSTRAINT person_pk PRIMARY KEY, name varchar(255))";
            stmt.execute(sql);
            stmt.addBatch("INSERT INTO person (id, name) VALUES (1, 'Susi')");
            stmt.addBatch("INSERT INTO person (id, name) VALUES (2, 'Hansi')");
            stmt.addBatch("INSERT INTO person (id, name) VALUES (3, 'Mimi')");
            stmt.addBatch("INSERT INTO person (id, name) VALUES (4, 'Karli')");
            stmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getDatabaseHost() {
        String dbHostEnvironmentVar = System.getenv("DB_HOST");
        if (dbHostEnvironmentVar == null) {
            return "localhost";
        }
        return dbHostEnvironmentVar;
    }
}
