package ua.com.fielden.personnel.structure;

import java.util.LinkedList;
import java.util.Queue;

public class FifoQueue implements IQueue {
	final Queue<Object> q = new LinkedList<>();

	@Override
	public FifoQueue push(final Object vertex) {
		q.add(vertex);
		return this;
	}

	@Override
	public Boolean isEmpty() {
		return q.isEmpty();
	}

	@Override
	public Object pop() {
		return q.remove();
	}
}