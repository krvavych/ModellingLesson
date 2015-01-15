package ua.com.fielden.personnel.structure;

import java.util.Stack;

public class FiloQueue implements IQueue {
	final private Stack<Object> stack = new Stack<>();

	@Override
	public FiloQueue push(final Object object) {
		stack.push(object);
		return this;
	}

	@Override
	public Boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public Object pop() {
		return stack.pop();
	}

}