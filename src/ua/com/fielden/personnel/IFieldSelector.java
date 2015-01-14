package ua.com.fielden.personnel;

import java.lang.reflect.Field;
import java.util.Set;


public interface IFieldSelector<T> {
	Set<Field> selectedFields(final Class<?> className);

}
