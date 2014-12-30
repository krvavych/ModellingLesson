package ua.com.fielden.personnel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ua.com.fielden.personnel.Result.Parameter;

public class EqualCheck<T> {
	public EqualCheck(final Object obj1, final Object obj2) throws IllegalArgumentException, IllegalAccessException {
		deepEquals(obj1, obj2);
	}
	public static Result deepEquals(final Object o1, final Object o2) throws IllegalArgumentException, IllegalAccessException{
		final Set<Set<Object>> compared = new HashSet<Set<Object>>();
		return deepEqualsNext(o1, o2, compared);
	}

	private static <T> Result deepEqualsNext(final T object1, final T object2, final Set<Set<Object>> compared)
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

					compared.add(setWithCompareObjects);
					return deepEqualsNext(newObject1, newObject2, compared);
				}
			}
			if (newObject1 != null && newObject2 != null
					&& newObject1.hashCode() != newObject2.hashCode()) {
				return new Result(Parameter.notEqual, newObject1, newObject2);
			}
		}
		return new Result(Parameter.equal, object1, object2);
	}

}
