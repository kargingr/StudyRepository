package com.epam.kargin.webshop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Gennadiy_Kargin on 14.01.14.
 */
public interface IAction
{
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}

