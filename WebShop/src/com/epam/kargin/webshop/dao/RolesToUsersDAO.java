package com.epam.kargin.webshop.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
public class RolesToUsersDAO extends AbstractDAO
{
    public static boolean roleToUserAppend(int userid, int roleid)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {
            CallableStatement callableStatement = connection.prepareCall("{call SP_RolesToUsers_Append(?, ?)}");
            callableStatement.setInt(1, userid);
            callableStatement.setInt(2, roleid);
            return callableStatement.execute();
        }
        catch (SQLException sqlEx)
        {
            LOGGER.error(sqlEx.getMessage());
            return false;
        }
    }

    public static boolean roleToUserUpdate(int id, int userid, int roleid)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {
            CallableStatement callableStatement = connection.prepareCall("{call SP_RolesToUsers_Update(?, ?, ?)}");
            callableStatement.setInt(1, id);
            callableStatement.setInt(2, userid);
            callableStatement.setInt(3, roleid);
            return callableStatement.execute();
        }
        catch (SQLException sqlEx)
        {
            LOGGER.error(sqlEx.getMessage());
            return false;
        }
    }

    public static boolean roleToUserRemove(int id)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {
            CallableStatement callableStatement = connection.prepareCall("{call SP_RolesToUsers_Remove(?)}");
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
