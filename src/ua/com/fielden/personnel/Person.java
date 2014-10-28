package ua.com.fielden.personnel;

import static java.lang.String.format;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

import ua.com.fielden.personnel.CommonComparator.SortBy;


public class Person implements Comparable<Person> {

	public Person(final String name, final String surname,
			final LocalDate birthday, final BigDecimal salary) {
		this.birthday = birthday;
		this.name = name;
		this.surname = surname;
		this.salary = salary;
	}

	public Person() {
	}

	private String surname;
	private LocalDate birthday;
	private String name;
	private BigDecimal salary;

	@Override
	public int compareTo(final Person obj) {
		if (this == obj) {
			return 0;
		} else if (obj == null) {
			return 1;
		}

		final Person that = obj;

		if (comTo(this.getName(), that.getName()) == 0) {
			if (comTo(this.getSurname(), that.getSurname()) == 0) {
				return comTo(this.getBirthday(), that.getBirthday());
			} else {
				return comTo(this.getSurname(), that.getSurname());
			}
		} else {
			return comTo(this.getName(), that.getName());
		}

	}

	private <T extends Comparable<T>> int comTo(final T thisValue,
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

	public String getName() {
		return name;
	}

	public Person setName(final String name) {
		this.name = name;
		return this;
	}

	public Person setBirthday(final LocalDate birthday) {
		this.birthday = birthday;
		return this;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public String getSurname() {
		return surname;
	}

	public Person setSurname(final String surname) {
		this.surname = surname;
		return this;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public Person setSalary(final BigDecimal amont) {
		if ((amont != null) && (amont.compareTo(BigDecimal.ZERO) < 0)) {
			throw new IllegalArgumentException(format(
					"Salary %s is not permited", amont));
		}
		if (amont == null){
		 this.salary = BigDecimal.ZERO;
		 return this;
		}
		this.salary = amont;
		return this;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Person)) {
			return false;
		}

		final Person that = (Person) obj;

		return eq(this.getName(), that.getName())
				&& eq(this.getSurname(), that.getSurname())
				&& eq(this.getBirthday(), that.getBirthday());
	}

	private boolean eq(final Object thisValue, final Object thatValue) {

		if (thisValue == null && thatValue != null) {
			return false;
		} else if (thisValue != null && thatValue == null) {
			return false;
		} else {
			return (thisValue == null && thatValue == null)
					|| thisValue.equals(thatValue);
		}
	}

	@Override
	public int hashCode() {
		final int firstHash = 15;
		int result = 1;
		result = firstHash * result
				+ ((birthday == null) ? 0 : birthday.hashCode());
		result = firstHash * result + ((name == null) ? 0 : name.hashCode());
		result = firstHash * result
				+ ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return name + "\t" + surname + "\t" + birthday + "\t"
				+ salary.toString();
	}

	public static Person mkFromString(final String strPerson) {
		final Person person = new Person();
		person.setName(strPerson.split("\t")[0]);
		person.setSurname(strPerson.split("\t")[1]);
		final String date = strPerson.split("\t")[2];
		person.setBirthday((LocalDate.parse(date,
				DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
		final String sal = strPerson.split("\t")[3];
		final long l = Long.parseLong(sal);
		person.setSalary(BigDecimal.valueOf(l));
		return person;
	}

	/*
	 * private static boolean contains(final Set<Person> persons, final Person
	 * person) { for (Person elem : persons) { if (elem == (person)) { return
	 * true; } } return false;
	 * 
	 * }
	 */

	public static void main(final String[] args) {
		final Set<Person> persons = new TreeSet<Person>(new CommonComparator(SortBy.NameDESC));
		final Person person1 = new Person().setName("Tania")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(50))
				.setBirthday(LocalDate.of(2012, Month.JULY, 12));
		final Person person2 = new Person().setName("Natalie")
				.setSurname("Krue").setSalary(BigDecimal.valueOf(5000))
				.setBirthday(LocalDate.of(2050, Month.JANUARY, 12));
		final Person person3 = new Person().setName("Edd").setSurname("Allen")
				.setBirthday(LocalDate.of(2001, Month.APRIL, 12))
				.setSalary(BigDecimal.valueOf(500));
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		for (final Person person : persons) {
			System.out.println(person);

		}

	}

}
