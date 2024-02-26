package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final int MAX_POOL_SIZE = 40;

    private static final DataSource ds;

    static {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(DB_DRIVER);
        hikariDataSource.setJdbcUrl(URL);
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("");
        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);
        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE);

        ds = hikariDataSource;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static DataSource getDataSource(){
        return ds;
    }

}
