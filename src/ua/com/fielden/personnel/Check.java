package ua.com.fielden.personnel;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ua.com.fielden.personnel.Result.Parameter;
import ua.com.fielden.personnel.selectors.IFieldSelector;
import ua.com.fielden.personnel.structure.FifoQueue;
import ua.com.fielden.personnel.structure.FiloQueue;
import ua.com.fielden.personnel.structure.IQueue;
import ua.com.fielden.personnel.structure.Pair;

public class Check {
	public enum CheckBy {
		Dfs, Bfs;
	}

	private static IQueue queue;

	public static <T> Result deepEquals(final T obj1, final T obj2,
			final IFieldSelector parameter, final CheckBy checkBy)
			throws IllegalArgumentException, IllegalAccessException {
		if (checkBy == CheckBy.Bfs) {
			queue = new FifoQueue();
		} else if (checkBy == CheckBy.Dfs) {
			queue = new FiloQueue();
		}
		final Set<Set<Object>> compared = new HashSet<>();
		final Set<Object> road = new HashSet<>();
		return deepEqualsNext(obj1, obj2, compared, road, parameter, checkBy);
	}

	private static <T> Result deepEqualsNext(final T object1, final T object2,
			final Set<Set<Object>> compared, final Set<Object> road,
			final IFieldSelector parameter, final CheckBy checkBy) throws IllegalArgumentException, IllegalAccessException{

		if (object1.getClass() != object2.getClass()) {
			throw new IllegalArgumentException(
					"You can`t compare different class objects");
		}
		final Class<?> objectClass = object1.getClass();
		final Set<Field> listOfObject1 = parameter.selectedFields(objectClass);
		final Iterator<Field> iterator = listOfObject1.iterator();
		for (;iterator.hasNext();) {
			final Field field = iterator.next();
			field.setAccessible(true);
			final Object newObject1 = field.get(object1);
			final Object newObject2 = field.get(object2);
			queue.push(new Pair<Pair<Object, Object>, Pair<Object, Object>>(new Pair<Object, Object>(
					newObject1, field.getName()), new Pair<Object, Object>(
					newObject2, field.getName())));
		}
		while (!queue.isEmpty()) {
			final Pair<Pair<Object, Object>, Pair<Object, Object>> newPair = queue.pop();
			final Pair<Object, Object> new1 = newPair.getLeftOfPair(newPair);
			final Pair<Object, Object> new2 = newPair.getRightOfPair(newPair);
			if ((new1.getLeft() == null && new2.getLeft() != null)
					|| (new1.getLeft() != null && new2.getLeft() == null)) {

				System.out.println("NullParameter: " + "this");
				road.add(new1.getRight());
				for (final Object fieldOfRoad : road) {
					System.out.println("." + fieldOfRoad);
				}

				return new Result(Parameter.nullParameter, new1.getLeft(), new2.getLeft());
			}
			if (new1.getLeft() != null && new2.getLeft() != null && (new1.getLeft().getClass() == objectClass)
					&& (new2.getLeft().getClass() == objectClass)) {
				final Set<Object> setWithCompareObjects = new HashSet<>();
				setWithCompareObjects.add(new1.getLeft());
				setWithCompareObjects.add(new2.getLeft());
				if (!compared.contains(setWithCompareObjects)) {
					road.add(new1.getRight());
					compared.add(setWithCompareObjects);
					return deepEqualsNext(new1.getLeft(), new2.getLeft(), compared, road,
							parameter, checkBy);
				}
			}
			if (new1.getLeft() != null && new2.getLeft() != null
					&& new1.getLeft().hashCode() != new2.getLeft().hashCode()) {
				road.add(new1.getRight());
				System.out.println("NotEqual: " + "this");
				for (final Object fieldOfRoad : road) {
					System.out.println("." + fieldOfRoad);
				}
				return new Result(Parameter.notEqual, new1, new2);
			}
		}
		return new Result(Parameter.equal, object1, object2);
	}
}
