package com.epam.kargin.webshop;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Gennadiy_Kargin on 14.01.14.
 */
public class FrontController extends AbstractWebShopServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            IAction action = ActionFactory.getAction(request);
            String view = action.execute(request, response);
            if (view.equals(request.getPathInfo().substring(1)))
            {
                request.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(request, response);
            }
            else
            {
                response.sendRedirect(view);
            }
        }
        catch (Exception ex)
        {
            LOGGER.error(ex.getMessage());
        }
    }
}
