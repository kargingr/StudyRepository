package com.epam.kargin.webshop;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
public class JDBCConnectionPool implements DataSource
{
    private static volatile JDBCConnectionPool instance;
    private static int maxConnection = 100;
    private static int minConnection = 5;
    private static int createdConnections = 0;
    private static String url;
    private static String user;
    private static String password;
    private Queue<Connection> dbConnections = new ConcurrentLinkedQueue<>();

    private class ConnectionWrapper implements Connection
    {
        private Connection connection;
        private boolean isClosed = true;
        public ConnectionWrapper(Connection connection)
        {
            this.connection = connection;
        }

        @Override
        public Statement createStatement() throws SQLException
        {
            return connection.createStatement();
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException
        {
            return connection.prepareStatement(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException
        {
            return connection.prepareCall(sql);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException
        {
            return connection.nativeSQL(sql);
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException
        {
            connection.setAutoCommit(autoCommit);
        }

        @Override
        public boolean getAutoCommit() throws SQLException
        {
            return connection.getAutoCommit();
        }

        @Override
        public void commit() throws SQLException
        {
            connection.commit();
        }

        @Override
        public void rollback() throws SQLException
        {
            connection.rollback();
        }

        @Override
        public void close() throws SQLException
        {
            freeConnection(this);
        }

        @Override
        public boolean isClosed() throws SQLException
        {
            return connection.isClosed();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException
        {
            return connection.getMetaData();
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException
        {
            connection.setReadOnly(readOnly);
        }

        @Override
        public boolean isReadOnly() throws SQLException
        {
            return connection.isReadOnly();
        }

        @Override
        public void setCatalog(String catalog) throws SQLException
        {
            connection.setCatalog(catalog);
        }

        @Override
        public String getCatalog() throws SQLException
        {
            return connection.getCatalog();
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException
        {
            connection.setTransactionIsolation(level);
        }

        @Override
        public int getTransactionIsolation() throws SQLException
        {
            return connection.getTransactionIsolation();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException
        {
            return connection.getWarnings();
        }

        @Override
        public void clearWarnings() throws SQLException
        {
            connection.clearWarnings();
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException
        {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException
        {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException
        {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException
        {
            return connection.getTypeMap();
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException
        {
            connection.setTypeMap(map);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException
        {
            connection.setHoldability(holdability);
        }

        @Override
        public int getHoldability() throws SQLException
        {
            return connection.getHoldability();
        }

        @Override
        public Savepoint setSavepoint() throws SQLException
        {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException
        {
            return connection.setSavepoint(name);
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException
        {
            connection.rollback();
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException
        {
            connection.releaseSavepoint(savepoint);
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return null;
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return null;
        }

        @Override
        public Clob createClob() throws SQLException {
            return null;
        }

        @Override
        public Blob createBlob() throws SQLException {
            return null;
        }

        @Override
        public NClob createNClob() throws SQLException {
            return null;
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return null;
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return false;
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {

        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {

        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return null;
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return null;
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return null;
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return null;
        }

        @Override
        public void setSchema(String schema) throws SQLException {

        }

        @Override
        public String getSchema() throws SQLException {
            return null;
        }

        @Override
        public void abort(Executor executor) throws SQLException {

        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return 0;
        }

        public void setClosed(boolean isClosed)
        {
            this.isClosed = isClosed;
        }

        public boolean getClosed()
        {
            return isClosed;
        }

        public void closeConnection() throws SQLException
        {
            connection.close();
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException
    {
        return getConnectionWrapper();
    }

    private JDBCConnectionPool(String url, String user, String password)
    {
        this.url = url;
        this.user = user;
        this.password = password;
        fillPool(minConnection);
    }

    private Connection createConnection()
    {
        Connection con = null;
        ConnectionWrapper conWrapper = null;
        if(createdConnections < maxConnection)
        {
            try
            {
                con = DriverManager.getConnection(url, user, password);
                conWrapper = new ConnectionWrapper(con);
                createdConnections++;
            }
            catch (SQLException sqlEx)
            {
                return  null;
            }
        }
        return conWrapper;
    }

    private void closeConnection(ConnectionWrapper connectionWrapper) throws SQLException
    {
        connectionWrapper.closeConnection();
        createdConnections--;
    }

    private void clearPool()
    {
        clearPool(0);
    }

    private void clearPool(int minConnection)
    {
        try
        {
            while(dbConnections.size() > minConnection)
            {
                closeConnection((ConnectionWrapper) dbConnections.poll());
            }
            createdConnections = dbConnections.size();
        }
        catch (SQLException sqlEx)
        {

        }
    }

    private boolean putConnectionToPool(Connection connection)
    {
        if(connection != null)
        {
            return dbConnections.add(connection);
        }
        return false;
    }

    private void fillPool(int count)
    {
        for(int i = 0; i < count; i ++)
        {
            putConnectionToPool(createConnection());
        }
    }

    public static JDBCConnectionPool getInstance(String url, String user, String password)
    {
        if(instance == null)
        {
            synchronized (JDBCConnectionPool.class)
            {
                if(instance == null)
                {
                    instance = new JDBCConnectionPool(url, user, password);
                }
            }
        }
        return instance;
    }

    private synchronized void freeConnection(Connection connection)
    {
        ((ConnectionWrapper)connection).setClosed(true);
        putConnectionToPool(connection);
    }

    private synchronized Connection getConnectionWrapper()
    {
        Connection con = dbConnections.poll();
        if(con == null)
        {
            con = createConnection();
            if(con != null)
            {
                putConnectionToPool(con);
            }
        }
        if(con != null)
        {
            ((ConnectionWrapper)con).setClosed(false);
        }
        return con;
    }

    public synchronized void truncatePool()
    {
        clearPool(minConnection);
    }
    public synchronized void shutdownPool()
    {
        clearPool();
    }
}
