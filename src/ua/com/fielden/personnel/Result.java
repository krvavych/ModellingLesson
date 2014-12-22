package ua.com.fielden.personnel;

public class Result {
	final private Parameter parameter;
	final private Object o1;
	final private Object o2;
	final private Parameter checkParameter;

	public Result(final Parameter parameter, final Object o1, final Object o2) {
		this.parameter = parameter;
		this.o1 = o1;
		this.o2 = o2;
		this.checkParameter = check();
		check();
	}
	public enum Parameter{
		equal, notEqual, nullParameter
	}

	public Result() {
		this.parameter = null;
		this.o1 = new Object();
		this.o2 = new Object();
		this.checkParameter = null;

	}

	public Parameter check() {
		if (parameter == Parameter.equal) {
			System.out.println("These objects are equal");
			return Parameter.equal;
		}
		if (parameter == Parameter.notEqual) {
			System.out.println("These objects are not equal");
			System.out.println(o1 + "  is not equal  " + o2);
			return Parameter.notEqual;
		}
		if (parameter ==Parameter.nullParameter ) {
			if (o1 == null) {
				System.out.println("These objects are not equal");
				System.out.println("null is not equal " + o2);
				return Parameter.nullParameter;
			} else {
				System.out.println("These objects are not equal");
				System.out.println(o1 + "is not equal null");
				return Parameter.nullParameter;
			}
		}
		return Parameter.equal;
	}

	public Parameter getCheckParameter() {
		return checkParameter;
	}

}
