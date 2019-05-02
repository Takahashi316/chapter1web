package org.smart4j.chapter1.helper;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter1.util.PropsUtil;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @author chen
 * @date 2019/5/2
 * @description
 */
public final class DataBaseHelper {

    private static final Logger logger=LoggerFactory.getLogger(DataBaseHelper.class);

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties conf=PropsUtil.loadProps("config.properties");

        DRIVER=conf.getProperty("jdbc.driver");
        URL=conf.getProperty("jdbc.url");
        USERNAME=conf.getProperty("jdbc.username");
        PASSWORD=conf.getProperty("jdbc.password");

        try{
            Class.forName(DRIVER);
        }
        catch (ClassNotFoundException e){
            logger.error("cannot load jdbc driver",e);
        }
    }


    public static <T> List<T> queryEntityList(Class<T> entityClass,String sql,Object... params){
        List<T> entityList;
        try{
            entityList=QUERY_RUNNER.query(getConnection(),sql,new BeanListHandler<>(entityClass),params);
        }
        catch (SQLException e){
            logger.error("query entitylist failure",e);
            throw new RuntimeException(e);

        }
        finally {
            //errorrrrrrrrrrrr
            closeConnection(getConnection());
        }
        return entityList;



    }

    public static Connection getConnection() {

        Connection connection=null;

        try{
            connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }
        catch (SQLException e){
            logger.error("get connection failure",e);
        }
        return connection;
    }

    public static void closeConnection(Connection connection){
        if(connection!=null){
            try{
                connection.close();
            }
            catch (SQLException e){
                logger.error("close connection failure",e);

            }

        }

    }


}
