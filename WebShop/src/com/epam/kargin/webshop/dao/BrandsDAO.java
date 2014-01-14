package com.epam.kargin.webshop.dao;

import com.epam.kargin.webshop.EDBConnectionFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
public class BrandsDAO extends AbstractDAO
{
    public static boolean brandAppend(String name)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {

            CallableStatement callableStatement = connection.prepareCall("{call SP_Brands_Append(?)}");
            callableStatement.setString(1, name);
            return callableStatement.execute();
        }
        catch (SQLException sqlEx)
        {
            LOGGER.error(sqlEx.getMessage());
            return false;
        }
    }

}
