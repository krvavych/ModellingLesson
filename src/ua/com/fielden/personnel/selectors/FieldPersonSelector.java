package ua.com.fielden.personnel.selectors;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import ua.com.fielden.personnel.Person;

public class FieldPersonSelector implements IFieldSelector {
	@Override
	public Set<Field> selectedFields(final Class<?> klass) {
		final Field[] fieldsOfClass = klass.getDeclaredFields();
		final Set<Field> newListOfField = new HashSet<>() ;

		final String string = new String();
		final Class<?> class1 = string.getClass();
		for (final Field field : fieldsOfClass) {
			if(klass == Person.class && field.getType().equals(class1)){
				newListOfField.add(field);
			}else{
				if(klass != Person.class){
					newListOfField.add(field);
				}
			}
		}
		return newListOfField;
	}

}
