package com.hzq.feign.service;

import java.sql.*;

/**
 * jdbc测试
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-28
 */
public class MyBatisMain {
    public static void test(String[] args) {
        Connection connection = getConn();

        String sql = "select id , user_name , address from user where user_name = '姓名222'";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

//            statement.setString(1,"姓名222");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String user_name = resultSet.getString("user_name");
                String address = resultSet.getString("address");

                System.out.println(id+"/"+user_name+"/"+address);
            }

            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn(){
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://192.168.50.51:3306/mydb?useUnicode=true&characterEncoding=utf-8","root","123456");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
