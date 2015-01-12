package ua.com.fielden.personnel;

public class ForTest {
	public String title;
	public ForTest samthing;
	public Integer integer;

	public ForTest(){

	}

	public ForTest setTitle(final String title) {
		this.title = title;
		return this;
	}

	public ForTest setSmthing(final ForTest smthing) {
		this.samthing = smthing;
		return this;
	}

	public ForTest setInteger(final Integer integer) {
		this.integer = integer;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((integer == null) ? 0 : integer.hashCode());
		result = prime * result + ((samthing == null) ? 0 : samthing.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ForTest other = (ForTest) obj;
		if (integer == null) {
			if (other.integer != null) {
				return false;
			}
		} else if (!integer.equals(other.integer)) {
			return false;
		}
		if (samthing == null) {
			if (other.samthing != null) {
				return false;
			}
		} else if (!samthing.equals(other.samthing)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return  title +  samthing+ integer ;
	}


}
