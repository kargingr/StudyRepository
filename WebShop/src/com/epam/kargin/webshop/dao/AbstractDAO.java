package com.epam.kargin.webshop.dao;

import com.epam.kargin.webshop.*;
import com.epam.kargin.webshop.utils.Inject;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
public class AbstractDAO// extends DAODBConnectInjection
{
    protected static final Logger LOGGER = Logger.getLogger("DAOLogger");

    @Inject(EDBConnectionFactory.DBConnectionPool)
    protected static DataSource dbConnectionPool = EDBConnectionFactory.DBConnectionPool.getInstance();

}
