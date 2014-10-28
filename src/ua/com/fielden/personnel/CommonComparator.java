package ua.com.fielden.personnel;

import java.util.Comparator;

public class CommonComparator implements Comparator<Person> {
	public enum SortBy {
		SalaryASC, SalaryDESC, NameASC, NameDESC, SurnameASC, SurnameDESC, BirthdayASC, BirthdayDECS
	}

	final Person o1 = new Person();
	final Person o2 = new Person();
	final SortBy sortParameter;

	public CommonComparator(final SortBy parameter) {
		sortParameter = parameter;
	}

	@Override
	public int compare(final Person o1, final Person o2) {
		int result = 0;
		if (sortParameter == SortBy.BirthdayASC) {
			result = sort(o1.getBirthday(), o2.getBirthday());
		}
		if (sortParameter == SortBy.BirthdayDECS) {
			result = sort(o2.getBirthday(), o1.getBirthday());
		}
		if (sortParameter == SortBy.NameASC) {
			result = sort(o1.getName(), o2.getName());
		}
		if (sortParameter == SortBy.NameDESC) {
			result = sort(o2.getName(), o1.getName());
		}
		if (sortParameter == SortBy.SalaryASC) {
			result = sort(o1.getSalary(), o2.getSalary());
		}
		if (sortParameter == SortBy.SalaryDESC) {
			result = sort(o2.getSalary(), o1.getSalary());
		}
		if (sortParameter == SortBy.SurnameASC) {
			result = sort(o1.getSurname(), o2.getSurname());
		}
		if (sortParameter == SortBy.SurnameDESC) {
			result = sort(o2.getSurname(), o1.getSurname());
		}
		return result;
	}

	private <T extends Comparable<T>> int sort(final T thisValue,
			final T thatValue) {
		if (thisValue == null && thatValue != null) {
			return -1;
		} else if (thisValue != null) {
			if (thatValue != null && !thisValue.equals(thatValue)) {
				return thisValue.compareTo(thatValue);
			} else if (thatValue == null) {
				return 1;
			}
		}
		return 0;
	}

}