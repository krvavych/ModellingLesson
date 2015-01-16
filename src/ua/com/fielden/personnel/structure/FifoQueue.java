package ua.com.fielden.personnel.structure;

import java.util.LinkedList;
import java.util.Queue;

public class FifoQueue implements IQueue {
	final Queue<Pair<Pair<Object, Object>, Pair<Object, Object>>> q = new LinkedList<>();

	@Override
	public FifoQueue push(final Pair<Pair<Object, Object >, Pair<Object, Object>> pair) {
		q.add(pair);
		return this;
	}

	@Override
	public Boolean isEmpty() {
		return q.isEmpty();
	}

	@Override
	public Pair<Pair<Object, Object >, Pair<Object, Object>> pop() {
		return q.remove();
	}
}