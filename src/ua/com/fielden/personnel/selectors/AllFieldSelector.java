package ua.com.fielden.personnel.selectors;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class AllFieldSelector implements IFieldSelector{
	@Override
	public Set<Field> selectedFields(final Class<?> className) {
		final Field[] fieldsOfClass = className.getDeclaredFields();
		final Set<Field> newListOfField = new HashSet<>();
		for (final Field field : fieldsOfClass) {
			newListOfField.add(field);
		}
		return newListOfField;
	}

}
