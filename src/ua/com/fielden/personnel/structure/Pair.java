package ua.com.fielden.personnel.structure;

public class Pair<L, R> {

	private final L left;
	private final R right;

	public Pair(final L left, final R right) {
		this.left = left;
		this.right = right;
	}

	public L getLeft() {
		return left;
	}
	public L getLeftOfPair(final Pair<L, R> pair) {
		return pair.getLeft();
	}


	public R getRight() {
		return right;
	}
	public R getRightOfPair(final Pair<L, R> pair) {
		return pair.getRight();
	}

	@Override
	public int hashCode() {
		return left.hashCode() ^ right.hashCode();
	}

	@Override
	public boolean equals(final Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof Pair)) {
			return false;
		}
		final Pair<L, R> pairo = (Pair<L, R>) o;
		return this.left.equals(pairo.getLeft())
				&& this.right.equals(pairo.getRight());
	}

}
