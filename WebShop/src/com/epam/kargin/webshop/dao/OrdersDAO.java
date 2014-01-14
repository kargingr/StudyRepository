package com.epam.kargin.webshop.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
public class OrdersDAO extends AbstractDAO
{
    public static boolean orderAppend(int userid, int goodid, boolean ispayed)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {
            CallableStatement callableStatement = connection.prepareCall("{call SP_Orders_Append(?, ?, ?)}");
            callableStatement.setInt(1, userid);
            callableStatement.setInt(2, goodid);
            callableStatement.setBoolean(3, ispayed);
            return callableStatement.execute();
        }
        catch (SQLException sqlEx)
        {
            LOGGER.error(sqlEx.getMessage());
            return false;
        }
    }

    public static boolean orderUpdate(int id, boolean ispayed)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {
            CallableStatement callableStatement = connection.prepareCall("{call SP_Orders_Update(?, ?)}");
            callableStatement.setInt(1, id);
            callableStatement.setBoolean(2, ispayed);
            return callableStatement.execute();
        }
        catch (SQLException sqlEx)
        {
            LOGGER.error(sqlEx.getMessage());
            return false;
        }
    }
}
