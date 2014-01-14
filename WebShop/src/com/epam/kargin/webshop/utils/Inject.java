package com.epam.kargin.webshop.utils;

import com.epam.kargin.webshop.EDBConnectionFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Gennadiy_Kargin on 13.01.14.
 */
@Target(value= ElementType.FIELD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface Inject
{
    EDBConnectionFactory value();
}
