package org.smart4j.chapter1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter1.helper.DataBaseHelper;
import org.smart4j.chapter1.model.Customer;
import org.smart4j.chapter1.util.PropsUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author chen
 * @date 2019/5/2
 * @description
 */
public class CustomerService {

    private static final Logger logger=LoggerFactory.getLogger(CustomerService.class);

    private static final String DRIVER;

    private static final String URL;

    private static final String USERNAME;

    private static final String PASSWORD;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");


        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("can not load jdbc driver", e);
        }
    }

    public List<Customer> getCustomerList(String keyword){
        Connection conn =null;
        try{

            List<Customer> customerList=new ArrayList<>();
            String sql="select * from customer";
            conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement stmt=conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                Customer customer=new Customer();

                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));


            }
            return customerList;
        }
        catch (SQLException e){
            logger.error("execute sql failure",e);

        }
        finally {
            DataBaseHelper.closeConnection(conn);

        }





    }

    public Customer getCustomer(long id){
        return null;
    }

    public boolean createCustomer(Map<String,Object> fieldMap){
        return false;

    }

    public boolean updateCustomer(long id,Map<String,Object> fieldMap){
        return false;
    }

    public boolean deleteCustomer(long id){
        return false;

    }
}
