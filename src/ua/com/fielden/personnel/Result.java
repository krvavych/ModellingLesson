package ua.com.fielden.personnel;

public class Result {
	private final Parameter parameter;

	public Result(final Parameter parameter, final Object o1, final Object o2) {
		this.parameter = parameter;
	}

	public Result() {
		this.parameter = null;
	}

	public enum Parameter {
		equal, notEqual, nullParameter
	}

	public Parameter getParameter() {
		return parameter;
	}

}
