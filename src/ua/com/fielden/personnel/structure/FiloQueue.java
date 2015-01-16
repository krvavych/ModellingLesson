package ua.com.fielden.personnel.structure;

import java.util.Stack;

public class FiloQueue implements IQueue {
	final private Stack<Pair<Pair<Object, Object>, Pair<Object, Object>>> stack = new Stack<>();

	@Override
	public FiloQueue push(final Pair<Pair<Object, Object>, Pair<Object, Object>> object) {
		stack.push(object);
		return this;
	}

	@Override
	public Boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public Pair<Pair<Object, Object>, Pair<Object, Object>> pop() {
		return stack.pop();
	}

}