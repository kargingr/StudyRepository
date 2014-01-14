package com.epam.kargin.webshop.utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
public class PropertyUtils
{
    private static final Logger LOGGER = Logger.getLogger("PropertyUtilsLogger");
    private static Properties properties = new Properties();
    static
    {
        try
        {
            properties.load(new FileInputStream("dbconnection.params"));
        }
        catch (IOException ioEx)
        {
            LOGGER.error(ioEx.getMessage());
        }
    }

    public static String getDBConnectionURL()
    {
        return properties.getProperty("DBURL");
    }

    public static String getDBUserName()
    {
        return properties.getProperty("DBUser");
    }

    public static String getDBUserPassword()
    {
        return properties.getProperty("DBUserPassword");
    }
}
