package ua.com.fielden.personnel;

import java.lang.reflect.Field;

import ua.com.fielden.personnel.Result.Parameter;

public class EqualCheck<T> {
	private  Parameter checkParameter;
	private final T o1;
	private final T o2;

	public EqualCheck(final T o1, final T o2) throws IllegalArgumentException,
			IllegalAccessException {
		this.o1 = o1;
		this.o2 = o2;
		this.checkParameter = deepEquals(this.o1, this.o2).getCheckParameter();
		//deepEquals(this.o1, this.o2);
	}
	public Parameter getCheckParameter() {
		return checkParameter;
	}

	public static <T> Result deepEquals(final T o1, final T o2)
			throws IllegalArgumentException, IllegalAccessException {
		final Class<?> c1 = o1.getClass();
		final Class<?> c2 = o2.getClass();
		final Field[] fields1 = c1.getDeclaredFields();
		final Field[] fields2 = c2.getDeclaredFields();
		Object object1 = new Object() ;
		Object object2 = new Object();
		Parameter parameter = Parameter.equal;
		for (int i = 0; i < fields1.length; i++) {
			fields1[i].setAccessible(true);
			fields2[i].setAccessible(true);
			final Object newObject1 = fields1[i].get(o1);
			final Object newObject2 = fields2[i].get(o2);
			if ((newObject1 == null && newObject2 != null)
					|| (newObject1 != null && newObject2 == null)) {
				object1 = newObject1;
				object2 = newObject2;
				parameter = Parameter.nullParameter;

				//return new Result(Parameter.nullParameter, newObject1,
					//	newObject2);
			}
			if (newObject1 != null && newObject2 != null) {
				if (newObject1.hashCode() != newObject2.hashCode()) {
					object1 = newObject1;
					object2 = newObject2;
					parameter = Parameter.notEqual;
					//return new Result(Parameter.notEqual, newObject1,
						//	newObject2);

				}
			}

			if ((i == fields1.length)
					&& ((newObject1.hashCode() == newObject2.hashCode())||(newObject1==null&&newObject2==null))) {
				object1 = newObject1;
				object2 = newObject2;
				parameter = Parameter.equal;
				//return new Result(Parameter.equal, newObject1, newObject2);
			}
		}

		return new Result(parameter,object1, object2);
	}


}
