package ua.com.fielden.personnel;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import ua.com.fielden.personnel.Result.Parameter;

public class Check {
	public enum CheckBy {
		Dfs, Bfs;
	}

	public static <T> Result checkEquals(final T o1, final T o2,
			final CheckBy parameter) throws IllegalArgumentException,
			IllegalAccessException {
		final Set<Set<Object>> compared = new HashSet<>();
		final Set<String> road = new HashSet<>();
		System.out.println("This");
		return checkEqualsNext(o1, o2, compared, parameter, road);
	}

	private static <T> Result checkEqualsNext(final T object1, final T object2,
			final Set<Set<Object>> compared, final CheckBy parameter, final Set<String> road)
			throws IllegalArgumentException, IllegalAccessException {
		final Class<?> classObject1 = object1.getClass();
		final Class<?> classObject2 = object2.getClass();

		if (classObject1 != classObject2) {
			throw new IllegalArgumentException(
					"You can`t compare different class objects");
		}
		final Set<Object> setWithCompareObject = new HashSet<Object>();

		setWithCompareObject.add(object1);
		setWithCompareObject.add(object2);
		compared.add(setWithCompareObject);

		Object newObjectToCompare1 = new Object();
		Object newObjectToCompare2 = new Object();

		final Field[] fieldsOfObject1 = classObject1.getDeclaredFields();
		final Field[] fieldsOfObject2 = classObject2.getDeclaredFields();

		for (int fieldNum = 0; fieldNum < fieldsOfObject1.length; fieldNum++) {
			fieldsOfObject1[fieldNum].setAccessible(true);
			fieldsOfObject2[fieldNum].setAccessible(true);
			final Object newObject1 = fieldsOfObject1[fieldNum].get(object1);
			final Object newObject2 = fieldsOfObject2[fieldNum].get(object2);
			if ((newObject1 == null && newObject2 != null)
					|| (newObject1 != null && newObject2 == null)) {
				road.add(fieldsOfObject1[fieldNum].getName());
				for (final String field : road) {
					System.out.println("." + field);
				}
				return new Result(Parameter.nullParameter, newObject1,
						newObject2);
			}

			if (newObject1 != null && newObject2 != null
					&& newObject1.hashCode() != newObject2.hashCode()
					&& (newObject1.getClass() != classObject1)
					&& (newObject2.getClass() != classObject2)) {
				road.add(fieldsOfObject1[fieldNum].getName());
				for (final String field : road) {
					System.out.println("." + field);
				}
				return new Result(Parameter.notEqual, newObject1, newObject2);
			}
			if (parameter == CheckBy.Bfs) {
				if (newObject1 != null && newObject2 != null
						&& (newObject1.getClass() == classObject1)
						&& (newObject2.getClass() == classObject2)) {
					 //road.add(fieldsOfObject1[fieldNum].getName());
					newObjectToCompare1 = newObject1;
					newObjectToCompare2 = newObject2;
				}
				if (fieldNum == fieldsOfObject1.length - 1) {
					final Set<Object> setWithCompareObjects = new HashSet<>();
					setWithCompareObjects.add(newObjectToCompare1);
					setWithCompareObjects.add(newObjectToCompare2);
					road.add(fieldsOfObject1[fieldNum].getName());
					if (!compared.contains(setWithCompareObjects)) {
						compared.add(setWithCompareObjects);
						return checkEqualsNext(newObjectToCompare1,
								newObjectToCompare2, compared, parameter, road);
					}
				}
			} else if(parameter == CheckBy.Dfs){
				if (newObject1 != null && newObject2 != null
						&& (newObject1.getClass() == classObject1)
						&& (newObject2.getClass() == classObject2)) {
					final Set<Object> setWithCompareObjects = new HashSet<>();
					setWithCompareObjects.add(newObject1);
					setWithCompareObjects.add(newObject2);
					road.add(fieldsOfObject1[fieldNum].getName());
					if (!compared.contains(setWithCompareObjects)) {
						compared.add(setWithCompareObjects);
						return checkEqualsNext(newObject1, newObject2, compared,parameter, road);
					}
				}
			}
		}
		System.out.println(" and that are equal");
		return new Result(Parameter.equal, object1, object2);
	}

}
