package org.smart4j.chapter1.helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter1.util.PropsUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author chen
 * @date 2019/5/2
 * @description
 */
public final class DataBaseHelper {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseHelper.class);

    private static final ThreadLocal<Connection> CONNECTION_HOLDER;

    private static final QueryRunner QUERY_RUNNER;

    private static final BasicDataSource DATA_SOURCE;

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        CONNECTION_HOLDER=new ThreadLocal<Connection>();
        QUERY_RUNNER=new QueryRunner();





        Properties conf = PropsUtil.loadProps("config.properties");

        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");

        DATA_SOURCE=new BasicDataSource();
        DATA_SOURCE.setDriverClassName(DRIVER);
        DATA_SOURCE.setUrl(URL);
        DATA_SOURCE.setUsername(USERNAME);
        DATA_SOURCE.setPassword(PASSWORD);

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("cannot load jdbc driver", e);
        }
    }


    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList;
        try {
            Connection connection=getConnection();
            entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<>(entityClass), params);
        } catch (SQLException e) {
            logger.error("query entitylist failure", e);
            throw new RuntimeException(e);

        } finally {
            //errorrrrrrrrrrrr
            closeConnection();
        }
        return entityList;
    }

    public static <T> T queryEntity(Class<T> entityClass,String sql,Object... params){
        T entity;
        try{
            Connection connection=getConnection();
            entity=QUERY_RUNNER.query(connection,sql,new BeanHandler<T>(entityClass),params);
        }
        catch (SQLException e){
            logger.error("query entity failure",e);
            throw new RuntimeException(e);

        }
        finally {
            closeConnection();
        }
        return entity;

    }

    public static Connection getConnection() {

        Connection connection = CONNECTION_HOLDER.get();
        if(connection == null) {
            try {
                connection = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                logger.error("get connection failure", e);
                throw new RuntimeException(e);
            }
            finally {
                CONNECTION_HOLDER.set(connection);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        Connection connection1=CONNECTION_HOLDER.get();
        if (connection1 != null) {
            try {
                connection1.close();
            } catch (SQLException e) {
                logger.error("close connection failure", e);
                throw new RuntimeException(e);

            }
            finally {
                CONNECTION_HOLDER.remove();
            }

        }

    }

    public static List<Map<String ,Object>> executeQuery(String sql,Object... params){
        List<Map<String ,Object>> result;

        try{
            Connection connection=getConnection();
            result=QUERY_RUNNER.query(connection,sql,new MapListHandler(),params);
        }
        catch (Exception e){
            logger.error("execute query failure",e);
            throw new RuntimeException(e);

        }
        return result;

    }


}
