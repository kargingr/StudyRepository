package com.epam.kargin.webshop;

import com.epam.kargin.webshop.utils.PropertyUtils;

import javax.sql.DataSource;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
public enum EDBConnectionFactory
{
    DBConnectionPool;

    public DataSource getInstance()
    {
        switch (this)
        {
            case DBConnectionPool:
            {
                String dbUrl = PropertyUtils.getDBConnectionURL();
                String userName = PropertyUtils.getDBUserName();
                String userPassword = PropertyUtils.getDBUserPassword();
                return JDBCConnectionPool.getInstance(dbUrl, userName, userPassword);
            }
        }
        return null;
    }
}
