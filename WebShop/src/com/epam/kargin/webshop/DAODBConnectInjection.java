package com.epam.kargin.webshop;

import com.epam.kargin.webshop.utils.InjectUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
public class DAODBConnectInjection
{
    protected void init(Object object)
    {
        List<Field> fields = InjectUtils.getAllFieldsForInjections(object.getClass(), DAODBConnectInjection.class.getName());
        InjectUtils.injectConnectionFields(fields, EDBConnectionFactory.DBConnectionPool, object);
    }
}
