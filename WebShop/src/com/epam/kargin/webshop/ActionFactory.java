package com.epam.kargin.webshop;

import com.epam.kargin.webshop.actions.LoginAction;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Gennadiy_Kargin on 14.01.14.
 */
public class ActionFactory
{
    private static Map<String, IAction> actions = new TreeMap<>();

    static
    {
        fillActions();
    }

    public static IAction getAction(HttpServletRequest request)
    {
        return actions.get(request.getMethod() + request.getPathInfo());
    }

    public static IAction putAction(String actionName, IAction action)
    {
        return actions.put(actionName, action);
    }

    public static IAction removeAction(String actionName)
    {
        return actions.remove(actionName);
    }

    private static void fillActions()
    {
        actions.put("POST/register", new LoginAction());
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LoginAction());
    }
}
