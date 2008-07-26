package com.googlecode.swtfinder.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * Utility class provide reflection functions
 * @author Ben.Xu (xufengbing@gmail.com)
 */
public class ReflectionUtil {
	public static Object invokeMethod(String name, Object o) {
		Object returnObject = null;
		try {
			Method m = o.getClass().getDeclaredMethod(name);
			m.setAccessible(true);
			returnObject = m.invoke(o);

		} catch (Exception e) {
			return null;
		}
		return returnObject;
	}
	
    public static Object getField(String name, Object o) {
        Object returnObject = null;
        try {
            Field m = o.getClass().getDeclaredField(name);
            m.setAccessible(true);
            returnObject = m.get(o);
        } catch (Exception e) {
            return null;
        }
        return returnObject;

    }
}
