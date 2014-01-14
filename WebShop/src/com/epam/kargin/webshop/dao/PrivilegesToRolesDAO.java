package com.epam.kargin.webshop.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
public class PrivilegesToRolesDAO extends AbstractDAO
{
    public static boolean privilegeToRoleAppend(int privilegeid, int roleid)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {
            CallableStatement callableStatement = connection.prepareCall("{call SP_PrivilegesToRoles_Append(?, ?)}");
            callableStatement.setInt(1, privilegeid);
            callableStatement.setInt(2, roleid);
            return callableStatement.execute();
        }
        catch (SQLException sqlEx)
        {
            LOGGER.error(sqlEx.getMessage());
            return false;
        }
    }

    public static boolean privilegeToRoleUpdate(int id, int privilegeid, int roleid)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {
            CallableStatement callableStatement = connection.prepareCall("{call SP_PrivilegesToRoles_Update(?, ?, ?)}");
            callableStatement.setInt(1, id);
            callableStatement.setInt(2, privilegeid);
            callableStatement.setInt(3, roleid);
            return callableStatement.execute();
        }
        catch (SQLException sqlEx)
        {
            LOGGER.error(sqlEx.getMessage());
            return false;
        }
    }

    public static boolean privilegeToRoleRemove(int id)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {
            CallableStatement callableStatement = connection.prepareCall("{call SP_PrivilegesToRoles_Remove(?)}");
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
