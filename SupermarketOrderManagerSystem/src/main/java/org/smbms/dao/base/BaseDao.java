package org.smbms.dao.base;

import org.smbms.util.SqlUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Description: 操作数据库的基础公共类
 * @ClassName: BaseDao
 * @PackageName: org.smms.dao.base
 * @ProjectName: SupermarketOrderManagerSystem
 * @Author: lc_co
 * @Date: 2021.10.13 19:58
 * @Version: 1.0
 * @Company: xxxxx
 * @Copyright: (c) 2021 lc_co All rights reserved.
 * @javaVersion: jdk1.8
 */
public class BaseDao {

    /**
     * @Description: 无结果集的通用基础增删改操作
     * @param connection 数据库Connection对象
     * @param sql        预编译的sql语句(含占位符)
     * @param objects    填充占位符的字段
     * @return 操作数
     * @Date: 2021.10.27 18:00
     */
    public static int DDU(Connection connection, String sql, Object... objects) throws SQLException {
        PreparedStatement ps = null;
        int executeUpdateNum = -1;
        try {
            // 设置不自动提交
            connection.setAutoCommit(false);
            // 预编译sql返回PreparedStatement对象
            ps = connection.prepareStatement(sql);
            // 填充sql中的占位符
            for (int i = 0; i < objects.length; i++) {
                ps.setObject(i + 1, objects[i]);
            }
            // 执行sql语句返回操作数
            executeUpdateNum = ps.executeUpdate();
            // 提交sql语句
            connection.commit();
            // 并把操作数返回
            return executeUpdateNum;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // 如果执行sql过程中出现异常则调用rollback进行回滚操作
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // 最后无论是否出现异常都得关闭资源(返回操作数之前)
            SqlUtils.closeResource(ps, connection, null);
        }
        throw new RuntimeException();
    }

    /**
     * @Description: 带有结果集的通用基础查询操作
     * @param beanClass  实体类Class对象
     * @param connection 数据库连接对象
     * @param sql        sql语句
     * @param objects    填充占位符的值
     * @param <T>        泛型参数实体类
     * @return 带有结果集的查询的查询结果数据容器
     * @Date: 2021.10.27 18:24
     */
    public static <T> List<T> DQL(Class<T> beanClass, Connection connection, String sql, Object... objects) throws SQLException {
        List<T> result = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 获取PreparedStatement实例对象
            preparedStatement = connection.prepareStatement(sql);
            // 填充占位符
            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1, objects[i]);
            }
            // 执行sql语句获取查询结果数据集
            resultSet = preparedStatement.executeQuery();
            // 从数据集中获取元数据信息
            ResultSetMetaData metaData = resultSet.getMetaData();
            // 从元数据获取col数即字段数
            int columnCount = metaData.getColumnCount();
            // 创建返回的结果集的容器
            List<T> resultSetList = new ArrayList<>();
            // 循环遍历结果集中的每行数据
            while (resultSet.next()) {
                // 通过反射创建实体类对象
                T newInstance = beanClass.getConstructor().newInstance();
                // 获取每一个字段名
                for (int i = 0; i < columnCount; i++) {
                    // 获取字段名
                    String columnName = metaData.getColumnLabel(i + 1);
                    // 获取字段值
                    Object columnValue = resultSet.getObject(columnName);
                    // 创建实体类对象中对应的字段
                    Field beanClassField = beanClass.getField(columnName);
                    // 给字段扩大权限(私有字段公有化)
                    beanClassField.setAccessible(true);
                    // 给实体类对象中对应的字段赋值
                    if ((columnName.equals("id") || columnName.equals("userRole") || columnName.equals("createBy") || columnName.equals("regenerator")) && columnValue != null) {
                        columnValue = Integer.parseInt(String.valueOf(columnValue));
                    }
                    if (columnName.equals("birthday")) {
                        Calendar instance = Calendar.getInstance();
                        instance.setTime((Date)columnValue);
                        columnValue = instance;
                    }
                    if ( (columnName.equals("createionDate") || columnName.equals("modifyDate")) && columnValue != null) {
                        Calendar instance = Calendar.getInstance();
                        instance.setTime((Timestamp)columnValue);
                        columnValue = instance;
                    }
                    if( (columnName.equals("providerId"))){
                        columnValue = Integer.parseInt(String.valueOf(columnValue));
                    }
                    beanClassField.set(newInstance, columnValue);
                }
                // 依次加入结果容器中
                resultSetList.add(newInstance);
            }
            // 最后将结果集容器返回即可
            result = resultSetList;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            // 返回数据集容器之前执行finally代码块进行关闭资源
            SqlUtils.closeResource(preparedStatement, connection, resultSet);
        }
        if (result == null) {// 如果能执行到这步说明程序前面出现异常状况则抛出异常
            throw new RuntimeException();
        }
        return result;
    }

    /**
     * @Description: 带有结果集的通用基础查询数量操作
     * @param connection 数据库连接对象
     * @param sql        sql语句
     * @param objects    填充占位符的值
     * @return 结果集的count字段的value值即查询到满足条件的数量
     * @Date: 2021.10.27 18:24
     */
    public static int DQLCount(Connection connection, String sql, Object... objects) throws SQLException {
        int resultCount = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 获取PreparedStatement实例对象
            preparedStatement = connection.prepareStatement(sql);
            // 填充占位符
            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1, objects[i]);
            }
            // 执行sql语句获取查询结果数据集
            resultSet = preparedStatement.executeQuery();
            // 从结果集中通过列标签进行提取对应列的值
            while (resultSet.next())
                resultCount = resultSet.getInt("count");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            // 返回数据集容器之前执行finally代码块进行关闭资源
            SqlUtils.closeResource(preparedStatement, connection, resultSet);
        }
        return resultCount;
    }
}
