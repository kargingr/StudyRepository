package com.epam.kargin.webshop.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
public class UsersBlackListDAO extends AbstractDAO
{
    public static boolean userBlackListAppend(int userid, String description)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {
            CallableStatement callableStatement = connection.prepareCall("{call SP_UsersBlackList_Append(?, ?)}");
            callableStatement.setInt(1, userid);
            callableStatement.setString(2, description);
            return callableStatement.execute();
        }
        catch (SQLException sqlEx)
        {
            LOGGER.error(sqlEx.getMessage());
            return false;
        }
    }

    public static boolean userBlackListUpdate(int id, String description)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {
            CallableStatement callableStatement = connection.prepareCall("{call SP_UsersBlackList_Update(?, ?)}");
            callableStatement.setInt(1, id);
            callableStatement.setString(2, description);
            return callableStatement.execute();
        }
        catch (SQLException sqlEx)
        {
            LOGGER.error(sqlEx.getMessage());
            return false;
        }
    }

    public static boolean userBlackListRemove(int id)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {
            CallableStatement callableStatement = connection.prepareCall("{call SP_UsersBlackList_Remove(?)}");
            callableStatement.setInt(1, id);
            return callableStatement.execute();
        }
        catch (SQLException sqlEx)
        {
            LOGGER.error(sqlEx.getMessage());
            return false;
        }
    }
}
