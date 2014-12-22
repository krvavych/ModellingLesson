package ua.com.fielden.personnel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

public class PersonEquolyty {

	@Test
	public void reflexivity_should_work() {
		final Person person = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		assertTrue(person.equals(person));
	}

	@Test
	public void summerty_should_work() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person2 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));

		assertTrue(person1.equals(person2) && (person2.equals(person1)));
	}

	@Test
	public void transitivity_should_work() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person2 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person3 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));

		assertTrue(person1.equals(person2) && person2.equals(person3)
				&& person1.equals(person3));

	}

	@Test
	public void same_objects_must_have_same_hashCode() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1))
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person2 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1))
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		assertTrue(person1.hashCode() == person2.hashCode());
	}

	@Test
	public void feald_must_be_notNull() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Jhon")
				.setSurname("Smith");
		final Person person2 = new Person().setBirthday(null).setName(null)
				.setSurname(null);
		person1.getSurname();
		if (((person1.getSurname() == null) && (person2.getSurname() == null))
				|| ((person1.getName() == null) && (person2.getName() == null))
				|| ((person1.getBirthday() == null) && (person2.getBirthday() == null))) {
			assertTrue(person1.equals(person2));
		}
		if (((person1.getSurname() != null) && (person2.getSurname() == null))
				|| ((person1.getName() != null) && (person2.getName() == null))
				|| ((person1.getBirthday() != null) && (person2.getBirthday() == null))) {
			assertFalse(person1.equals(person2));
		}
		if (((person1.getSurname() == null) && (person2.getSurname() != null))
				|| ((person1.getName() == null) && (person2.getName() != null))
				|| ((person1.getBirthday() == null) && (person2.getBirthday() != null))) {
			assertFalse(person1.equals(person2));
		}

	}

	@Test
	public void equality_between_not_fully_initialised_persons_should_work() {
		final Person person1 = new Person().setBirthday(
				LocalDate.of(2000, Month.MAY, 1)).setSurname("Smith");
		final Person person2 = new Person().setBirthday(
				LocalDate.of(2000, Month.MAY, 1)).setSurname("Smith");
		assertEquals(person1, person2);
	}

}
