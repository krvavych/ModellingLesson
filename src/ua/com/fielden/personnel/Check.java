package ua.com.fielden.personnel;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ua.com.fielden.personnel.Result.Parameter;
import ua.com.fielden.personnel.structure.FifoQueue;
import ua.com.fielden.personnel.structure.FiloQueue;
import ua.com.fielden.personnel.structure.IQueue;

public class Check implements IFieldSelector<Object> {
	public enum CheckBy {
		Dfs, Bfs;
	}

	private IQueue queue1;
	private IQueue queue2;

	@Override
	public Set<Field> selectedFields(final Class<?> className) {
		final Field[] fieldsOfClass = className.getDeclaredFields();
		final Set<Field> newListOfField = new HashSet<>();
		for (final Field field : fieldsOfClass) {
			newListOfField.add(field);
		}
		return newListOfField;
	}

	public <T> Result deepEquals(final T obj1, final T obj2,
			final Check parameter, final CheckBy checkBy)
			throws IllegalArgumentException, IllegalAccessException {
		if (checkBy == CheckBy.Bfs) {
			queue1 = new FifoQueue();
			queue2 = new FifoQueue();
		} else if (checkBy == CheckBy.Dfs) {
			queue1 = new FiloQueue();
			queue2 = new FiloQueue();
		}
		final Set<Set<Object>> compared = new HashSet<>();
		final Set<String> road = new HashSet<>();
		return deepEqualsNext(obj1, obj2, compared, road, parameter, checkBy);
	}

	private <T> Result deepEqualsNext(final T object1, final T object2,
			final Set<Set<Object>> compared, final Set<String> road,
			final Check parameter, final CheckBy checkBy)
			throws IllegalArgumentException, IllegalAccessException {

		final Class<?> class1 = object1.getClass();
		final Class<?> class2 = object2.getClass();
		if (class1 != class2) {
			throw new IllegalArgumentException(
					"You can`t compare different class objects");
		}
		final Set<String> roadList = new HashSet<>();
		final Set<Field> listOfObject1 = parameter.selectedFields(class1);
		final Iterator<Field> iterator = listOfObject1.iterator();
		for (; iterator.hasNext();) {
			final Field field = iterator.next();
			field.setAccessible(true);
			final Object newObject1 = field.get(object1);
			final Object newObject2 = field.get(object2);
			queue1.push(newObject1);
			queue2.push(newObject2);
			roadList.add(field.getName());
		}

		while (!queue1.isEmpty()) {
			final Object new1 = queue1.pop();
			final Object new2 = queue2.pop();
			for (final String fieldName : roadList) {
				if ((new1 == null && new2 != null)
						|| (new1 != null && new2 == null)) {

					System.out.println("NullParameter: " + "this");
					road.add(fieldName);
					for (final String fieldOfRoad : road) {
						System.out.println("." + fieldOfRoad);
					}

					return new Result(Parameter.nullParameter, new1, new2);
				}
				if (new1 != null && new2 != null && (new1.getClass() == class1)
						&& (new2.getClass() == class2)) {
					final Set<Object> setWithCompareObjects = new HashSet<>();
					setWithCompareObjects.add(new1);
					setWithCompareObjects.add(new2);
					if (!compared.contains(setWithCompareObjects)) {
						road.add(fieldName);
						compared.add(setWithCompareObjects);
						return deepEqualsNext(new1, new2, compared, road,
								parameter, checkBy);
					}
				}
				if (new1 != null && new2 != null
						&& new1.hashCode() != new2.hashCode()) {
					road.add(fieldName);
					System.out.println("NotEqual: " + "this");
					for (final String fieldOfRoad : road) {
						System.out.println("." + fieldOfRoad);
					}
					return new Result(Parameter.notEqual, new1, new2);
				}
			}
		}
		return new Result(Parameter.equal, object1, object2);
	}
}
