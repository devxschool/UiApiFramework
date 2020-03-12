package utilities;

import domainsOrPojo.DBType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import step_defs.GorestSteps;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utilities.ConfigReader.getProperty;

//JDBC - Java Data base connection
public class DBUtils {

    private static Logger LOGGER = LogManager.getLogger(DBUtils.class);

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void establishConnection(DBType dbType) throws SQLException {

        LOGGER.debug("Establishing JDBC Connection to " + getProperty("dbUrl") + " username " + getProperty("dbUserName") + " password " + getProperty("dbPassword"));
        LOGGER.info("Establishing JDBC Connection");

        try {
            switch (dbType) {
                case ORACLE:
                    Class.forName("com.oracle.jdbc.Driver");
                    connection = DriverManager.getConnection(getProperty("dbUrl"), getProperty("dbUserName"), getProperty("dbPassword"));
                case MYSQL:
                    //Loads the mysql jdbc driver class to the classpath.
                    Class.forName("com.mysql.jdbc.Driver");

                    //Establish connection by passing jdbc Url -- username -- password
                    connection = DriverManager.getConnection(getProperty("dbUrl"), getProperty("dbUserName"), getProperty("dbPassword"));
                    break;
                case MARIADB:
                    //Loads the mysql jdbc driver class to the classpath.
                    Class.forName("com.mariadb.jdbc.Driver");

                    //Establish connection by passing jdbc Url -- username -- password
                    connection = DriverManager.getConnection(getProperty("dbUrl"), getProperty("dbUserName"), getProperty("dbPassword"));
                    break;
            }

            LOGGER.info("DB Connection is successfully established");

        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "Failed to establish connection to DB with url: " + getProperty("dbUrl") + " username:" + getProperty("dbUserName") + " password:" + getProperty("dbPassword"));
            LOGGER.error("Failed to establish connection to DB with url: " + getProperty("dbUrl") + " username:" + getProperty("dbUserName") + " password:" + getProperty("dbPassword"));
            closeConnection();
            throw new SQLException("Failed to establish connection to DB with url: " + getProperty("dbUrl") + " username:" + getProperty("dbUserName") + " password:" + getProperty("dbPassword"));
        }
    }

    /**
     * This method can be dynamically used to query db
     * Use this method for select queries only
     * @param sqlQuery
     * @return -- list of maps. list represents rows. map represents the row, the object.
     * @throws SQLException
     */
    public static List<Map<String, Object>> runSqlSelectQuery(String sqlQuery) throws SQLException {


        List<Map<String, Object>> dbResultList = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);


            //Result Meta is info about our result data.
            ResultSetMetaData metaData = resultSet.getMetaData();

            int columnCount = metaData.getColumnCount(); //5

            while (resultSet.next()) {

                Map<String, Object> rowMap = new HashMap<>();

                for (int col = 1; col <= columnCount; col++) {

                    rowMap.put(metaData.getColumnName(col), resultSet.getObject(col));
                }

                dbResultList.add(rowMap);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Failed to execute query");
            closeConnection();
            throw new SQLException("Failed to execute query");
        }

        return dbResultList;
    }

    /**
     * Use this method for deleting and truncating the tables
     * @param sqlQuery
     * @throws SQLException
     */
    public static void runSqlUpdateQuery(String sqlQuery) throws SQLException {
        try {
            statement = connection.createStatement();
            statement.executeQuery(sqlQuery);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Failed to execute query");
            closeConnection();
            throw new SQLException("Failed to execute query");
        }
    }


    /**
     * Use this method for insert Queries
     * @param sqlQuery
     * @return number of rows updated, or 0 when no action were taken
     * @throws SQLException
     */
    public static int runSqlInsertQuery(String sqlQuery) throws SQLException {
        int rowsUpdated = 0;
        try {
            statement = connection.createStatement();
            rowsUpdated =  statement.executeUpdate(sqlQuery);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Failed to execute Update query");
            closeConnection();
            throw new SQLException("Failed to execute Update query");
        }
        return rowsUpdated;
    }


        public static void closeConnection () {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                LOGGER.error("Failed to close the connection");
            }
        }
    }
