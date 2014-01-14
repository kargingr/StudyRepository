package com.epam.kargin.webshop;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
public class AbstractWebShopServlet extends HttpServlet
{
    protected static final Logger LOGGER = Logger.getLogger("ControllerLogger");

    @Override
    public void init() throws javax.servlet.ServletException
    {
        //Dependency Injection to DAO fields
    }
}
