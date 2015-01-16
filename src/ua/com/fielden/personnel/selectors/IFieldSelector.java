package ua.com.fielden.personnel.selectors;

import java.lang.reflect.Field;
import java.util.Set;


public interface IFieldSelector {
	Set<Field> selectedFields(final Class<?> className);

}
