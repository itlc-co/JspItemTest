package org.smbms.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Description: 操作数据库的连接等工具类
 * @ClassName: SqlUtils
 * @Pacakage_name: org.smms.dao.base
 * @Project_name: SupermarketOrderManagerSystem
 * @Author: lc_co
 * @Date: 2021.10.13 19:58
 * @Version: 1.0
 * @Company: xxxxx
 * @Copyright: (c) 2021 lc_co All rights reserved.
 * @javaVersion: jdk1.8
 */
public class SqlUtils {

    /**
     * 驱动名
     */
    private static String DRIVERNAME;
    /**
     * 用户名
     */
    private static String USERNAME;
    /**
     * 密码
     */
    private static String PASSWORD;
    /**
     * url
     */
    private static String URL;

    // 在静态代码块在加载配置信息并赋值即实现类加载时读取配置信息
    static {
        // 1.获取配置文件信息的输入流通过properties存储
        Properties properties = new Properties();
        InputStream resourceAsStream = SqlUtils.class.getClassLoader().getResourceAsStream("mysql.properties");
        try {
            // 2.将输入流加载到properties中
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 3.在properties中获取相应key的value赋值给静态变量配置信息
        SqlUtils.URL = properties.getProperty("url");
        SqlUtils.USERNAME = properties.getProperty("username");
        SqlUtils.PASSWORD = properties.getProperty("password");
        SqlUtils.DRIVERNAME = properties.getProperty("driver");
    }

    /**
     * @return 数据库的连接对象
     * @throws ClassNotFoundException
     * @throws SQLException
     * @Description: 获取数据库的连接对象
     * @Date: 2021.10.27 16:38
     */
    public static Connection getConnectionMysql() throws ClassNotFoundException, SQLException {
        // 加载mysql驱动类
        Class.forName(DRIVERNAME);
        // 通过DriverManager获取mysql数据库连接
        return DriverManager.getConnection(SqlUtils.URL, SqlUtils.USERNAME, SqlUtils.PASSWORD);
    }

    /**
     * @param ps   PreparedStatement对象
     * @param conn Connection对象
     * @param rs   ResultSet对象
     * @Description: 关闭数据连接后的资源
     */
    public static void closeResource(PreparedStatement ps, Connection conn, ResultSet rs) {
        // PreparedStatement对象如果不为null则关闭PreparedStatement资源对象
        try {
            if (ps != null) ps.close();
            ps = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Connection对象如果不为null则关闭Connection资源对象
        try {
            if (conn != null) conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // ResultSet对象如果不为null则关闭ResultSet资源对象
        try {
            if (rs != null) rs.close();
            rs = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
