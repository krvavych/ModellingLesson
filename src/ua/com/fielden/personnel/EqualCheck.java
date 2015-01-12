package ua.com.fielden.personnel;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import ua.com.fielden.personnel.Result.Parameter;

public class EqualCheck {

	public static <T> Result deepEquals(final T o1, final T o2) throws IllegalArgumentException, IllegalAccessException{
		final Set<Set<Object>> compared = new HashSet<>();
		return deepEqualsNext(o1, o2, compared);
	}

	private static <T> Result deepEqualsNext(final T object1, final T object2, final Set<Set<Object>> compared)
			throws IllegalArgumentException, IllegalAccessException {
		final Class<?> classObject1 = object1.getClass();
		final Class<?> classObject2 = object2.getClass();
		final  Set<String> road = new HashSet<>();
		if (classObject1 != classObject2) {
			throw new IllegalArgumentException(
					"You can`t compare different class objects");
		}
		final Set<Object> setWithCompareObject = new HashSet<Object>();

		setWithCompareObject.add(object1);
		setWithCompareObject.add(object2);
		compared.add(setWithCompareObject);


		final Field[] fieldsOfObject1 = classObject1.getDeclaredFields();
		final Field[] fieldsOfObject2 = classObject2.getDeclaredFields();

		for (int fieldNum = 0; fieldNum < fieldsOfObject1.length; fieldNum++) {
			fieldsOfObject1[fieldNum].setAccessible(true);
			fieldsOfObject2[fieldNum].setAccessible(true);
			final Object newObject1 = fieldsOfObject1[fieldNum].get(object1);
			final Object newObject2 = fieldsOfObject2[fieldNum].get(object2);
			if ((newObject1 == null && newObject2 != null)
					|| (newObject1 != null && newObject2 == null)) {

				System.out.println("NullParameter: "+"this");
				road.add(fieldsOfObject1[fieldNum].getName());
				for (final String field : road) {
					System.out.println("."+field);
				}

				return new Result(Parameter.nullParameter, newObject1,
						newObject2);
			}
			if (newObject1 != null && newObject2 != null
					&& (newObject1.getClass() == classObject1)
					&& (newObject2.getClass() == classObject2)) {
				final Set<Object> setWithCompareObjects = new HashSet<>();
				setWithCompareObjects.add(newObject1);
				setWithCompareObjects.add(newObject2);
				if (!compared.contains(setWithCompareObjects)) {
					road.add(fieldsOfObject1[fieldNum].getName());

					compared.add(setWithCompareObjects);
					return deepEqualsNext(newObject1, newObject2, compared);
				}
			}
			if (newObject1 != null && newObject2 != null
					&& newObject1.hashCode() != newObject2.hashCode()) {
				road.add(fieldsOfObject1[fieldNum].getName());
				System.out.println("NotEqual: "+"this");
				for (final String field : road) {
					System.out.println("."+field);
				}
				return new Result(Parameter.notEqual, newObject1, newObject2);
			}
		}
		return new Result(Parameter.equal, object1, object2);
	}

}