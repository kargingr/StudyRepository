package com.epam.kargin.webshop.utils;

import com.epam.kargin.webshop.EDBConnectionFactory;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
public class InjectUtils
{
    private static final Logger LOGGER = Logger.getLogger("InjectUtilsLogger");

    public static void injectConnectionFields(List<Field> fields, EDBConnectionFactory injectType, Object object)
    {
        try
        {
            for(Field field : fields)
            {
                field.setAccessible(true);
                if(injectType.name().equals(field.getAnnotation(Inject.class).value()))
                {
                    field.set(object, injectType.getInstance());
                }
            }
        }
        catch (Exception ex)
        {
            LOGGER.error(ex.getMessage());
        }
    }

    public static List<Field> getFieldsForInjections(Class clazz)
    {
        List<Field> resultFields = new ArrayList<>();
        try
        {
            Field[] tmpFields = clazz.getDeclaredFields();
            for(Field field : tmpFields)
            {
                //field.setAccessible(true);
                Inject annotation = field.getAnnotation(Inject.class);
                if(annotation != null)
                {
                    resultFields.add(field);
                }
            }
            return resultFields;
        }
        catch (Exception ex)
        {
            LOGGER.error(ex.getMessage());
            return resultFields;
        }
    }

    public static List<Field> getAllFieldsForInjections(Class currentClass, String highLevelClassName)
    {
        List<Field> fields = new ArrayList<>();

        if(!currentClass.getName().equals(highLevelClassName) || !currentClass.getName().equals(Objects.class.getName()))
        {
            fields.addAll(getFieldsForInjections(currentClass));
            fields.addAll(getAllFieldsForInjections(currentClass.getSuperclass(), highLevelClassName));
        }
        else
        {
            return getFieldsForInjections(currentClass);
        }
        return fields;
    }
}
