package com.epam.kargin.webshop.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
public class RolesDAO extends AbstractDAO
{
    public static boolean roleAppend(String name)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {
            CallableStatement callableStatement = connection.prepareCall("{call SP_Roles_Append(?)}");
            callableStatement.setString(1, name);
            return callableStatement.execute();
        }
        catch (SQLException sqlEx)
        {
            LOGGER.error(sqlEx.getMessage());
            return false;
        }
    }

    public static boolean roleUpdate(int id, String name)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {
            CallableStatement callableStatement = connection.prepareCall("{call SP_Roles_Update(?, ?)}");
            callableStatement.setInt(1, id);
            callableStatement.setString(2, name);
            return callableStatement.execute();
        }
        catch (SQLException sqlEx)
        {
            LOGGER.error(sqlEx.getMessage());
            return false;
        }
    }
}
