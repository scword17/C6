package com.conhj.db;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidDB {
    private static Connection connection = null;
    private static DataSource ds = null;
    private static Properties pro = new Properties();

    static {
        try {
            pro.load(DruidDB.class.getClassLoader().getResourceAsStream("druid.ini"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


}
