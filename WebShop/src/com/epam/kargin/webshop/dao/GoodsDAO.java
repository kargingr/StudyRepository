package com.epam.kargin.webshop.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
public class GoodsDAO extends AbstractDAO
{
    public static boolean goodAppend(String name, int typeid, int brandid, double price, String description)
    {
        try(Connection connection = dbConnectionPool.getConnection())
        {

            CallableStatement callableStatement = connection.prepareCall("{call SP_Goods_Append(?, ?, ?, ?, ?)}");
            callableStatement.setString(1, name);
            callableStatement.setInt(2, typeid);
            callableStatement.setInt(3, brandid);
            callableStatement.setDouble(4, price);
            callableStatement.setString(5, description);
            return callableStatement.execute();
        }
        catch (SQLException sqlEx)
        {
            LOGGER.error(sqlEx.getMessage());
            return false;
        }
    }

}
