package ua.com.fielden.personnel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ua.com.fielden.personnel.Result.Parameter;

public class EqualCheck<T> {
	private  static Set<Set<Object>> compared = new HashSet<>();

	public static <T> Result deepEquals(final T object1, final T object2)
			throws IllegalArgumentException, IllegalAccessException {
		final Class<?> classObject1 = object1.getClass();
		final Class<?> classObject2 = object2.getClass();
		if (classObject1 != classObject2) {
			throw new IllegalArgumentException(
					"You can`t compate different class objects");
		}
		final Set<Object> setWithCompareObject = new HashSet<Object>();
		setWithCompareObject.add(object1);
		setWithCompareObject.add(object2);
		compared.add(setWithCompareObject);
		final Field[] fieldsOfObject1 = classObject1.getDeclaredFields();
		final Field[] fieldsOfObject2 = classObject2.getDeclaredFields();
		final ArrayList<Object> roadList = new ArrayList<>();
		roadList.add(fieldsOfObject1[0].getName());
		for (int fieldNum = 0; fieldNum < fieldsOfObject1.length; fieldNum++) {
			fieldsOfObject1[fieldNum].setAccessible(true);
			fieldsOfObject2[fieldNum].setAccessible(true);
			final Object newObject1 = fieldsOfObject1[fieldNum].get(object1);
			final Object newObject2 = fieldsOfObject2[fieldNum].get(object2);
			if ((newObject1 == null && newObject2 != null)
					|| (newObject1 != null && newObject2 == null)) {
				return new Result(Parameter.nullParameter, newObject1,
						newObject2);
			}
			if (newObject1 != null && newObject2 != null
					&& (newObject1.getClass() == (classObject1))
					&& (newObject2.getClass() == (classObject2))) {
				final Set<Object> setWithCompareObjects = new HashSet<Object>();
				setWithCompareObjects.add(newObject1);
				setWithCompareObjects.add(newObject2);
				if (!(compared.contains(setWithCompareObjects))) {
					final Field[] fields1 = newObject1.getClass()
							.getDeclaredFields();
					final Field[] fields2 = newObject2.getClass()
							.getDeclaredFields();
					for (int fieldNumber = 0; fieldNumber < fields1.length; fieldNumber++) {
						fields1[fieldNumber].setAccessible(true);
						fields2[fieldNumber].setAccessible(true);
						for (final Set<Object> set : compared) {
							if (fields1[fieldNumber].getClass() == object1
									.getClass()
									&& fields2[fieldNumber].getClass() == object2
											.getClass()
									&& set.contains(fields1[fieldNumber])
									&& set.contains(fields2[fieldNumber])) {
								return new Result(Parameter.equal, newObject1,
										newObject2);
							} else {
								return deepEquals(newObject1, newObject2);
							}
						}
					}
				}
			}
			if (newObject1 != null && newObject2 != null
					&& newObject1.hashCode() != newObject2.hashCode()) {
				return new Result(Parameter.notEqual, newObject1, newObject2);
			}
		}
		return new Result(Parameter.equal, object1, object2);
	}

	public static Set<Set<Object>> getCompared() {
		return compared;
	}
}
