package io.github.wouterbauweraerts.sociabletesting.util;

import java.lang.reflect.Field;

import io.github.wouterbauweraerts.sociabletesting.core.exception.SociableTestException;
import io.github.wouterbauweraerts.sociabletesting.extension.SociableTestExtension;

public class ReflectionUtil {

    public static void setField(SociableTestExtension instance, Object value, String fieldName) throws Exception {
        Field field = SociableTestExtension.class.getDeclaredField(fieldName);
        boolean originalAccessibility = field.canAccess(instance);

        field.setAccessible(true);
        field.set(instance, value);
        field.setAccessible(originalAccessibility);
    }

    public static Object getFieldValue(Field field, Object instance) {
        boolean originalAccessibility = field.canAccess(instance);

        try {
            field.setAccessible(true);
            return field.get(instance);
        } catch (Exception e) {
            throw new SociableTestException("Unable to extract value from field: %s".formatted(field.getName()), e);
        } finally {
            field.setAccessible(originalAccessibility);
        }
    }

    public static void setFieldValue(Field field, Object instance, Object value) {
        boolean originalAccessibility = field.canAccess(instance);

        try {
            field.setAccessible(true);
            field.set(instance, value);
        } catch (Exception e) {
            throw new SociableTestException("Unable to set value of field: %s".formatted(field.getName()), e);
        } finally {
            field.setAccessible(originalAccessibility);
        }
    }
}