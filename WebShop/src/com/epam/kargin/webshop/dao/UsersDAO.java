package com.epam.kargin.webshop.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
public class UsersDAO extends AbstractDAO
{
    public static boolean userAppend(String name, String lastName, String login, String email, String password, double account)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {
            CallableStatement callableStatement = connection.prepareCall("{call SP_Users_Append(?, ?, ?, ?, ?, ?)}");
            callableStatement.setString(1, name);
            callableStatement.setString(2, lastName);
            callableStatement.setString(3, login);
            callableStatement.setString(4, email);
            callableStatement.setString(5, password);
            callableStatement.setDouble(6, account);
            return callableStatement.execute();
        }
        catch (SQLException sqlEx)
        {
            LOGGER.error(sqlEx.getMessage());
            return false;
        }
    }
}
