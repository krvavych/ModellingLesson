package ua.com.fielden.personnel;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import ua.com.fielden.personnel.SalaryComparator.SortOrder;

public class SalaryComparision {

	@Test
	public void salaryCompareASC_have_to_sort_TreeSet_correct() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Tania")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person2 = new Person()
				.setBirthday(LocalDate.of(2010, Month.MAY, 12)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(1000));
		final Person person3 = new Person()
				.setBirthday(LocalDate.of(2010, Month.MAY, 12)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(10));

		final Set<Person> persons = new TreeSet<Person>(new SalaryComparator(
				SortOrder.ASC));
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		final Iterator<Person> iter = persons.iterator();
		final Person p1FromSet = iter.next();
		final Person p2FromSet = iter.next();
		final Person p3FromSet = iter.next();

		assertTrue(p1FromSet.getSalary().compareTo(p2FromSet.getSalary()) < 0);
		assertTrue(p1FromSet.getSalary().compareTo(p3FromSet.getSalary()) < 0);
		assertTrue(p3FromSet.getSalary().compareTo(p2FromSet.getSalary()) > 0);
		assertTrue(p3FromSet.getSalary().compareTo(p1FromSet.getSalary()) > 0);
		assertTrue(p2FromSet.getSalary().compareTo(p1FromSet.getSalary()) > 0);
		assertTrue(p2FromSet.getSalary().compareTo(p3FromSet.getSalary()) < 0);
	}

	@Test
	public void salaryCompareDESC_have_to_sort_TreeSet_correct() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 12)).setName("John")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(1));
		final Person person2 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 12)).setName("Jon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person3 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 12)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(10));

		final Set<Person> persons = new TreeSet<Person>(new SalaryComparator(
				SortOrder.DESC));
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		final Iterator<Person> iter = persons.iterator();
		final Person p1FromSet = iter.next();
		final Person p2FromSet = iter.next();
		final Person p3FromSet = iter.next();

		assertTrue(p1FromSet.getSalary().compareTo(p2FromSet.getSalary()) > 0);
		assertTrue(p1FromSet.getSalary().compareTo(p3FromSet.getSalary()) > 0);
		assertTrue(p3FromSet.getSalary().compareTo(p2FromSet.getSalary()) < 0);
		assertTrue(p3FromSet.getSalary().compareTo(p1FromSet.getSalary()) < 0);
		assertTrue(p2FromSet.getSalary().compareTo(p1FromSet.getSalary()) < 0);
		assertTrue(p2FromSet.getSalary().compareTo(p3FromSet.getSalary()) > 0);
	}

	@Test
	public void salary_shoul_not_be_negative() {
		try {
			new Person().setBirthday(LocalDate.of(2000, Month.MAY, 12))
					.setName("John").setSurname("Smith")
					.setSalary(BigDecimal.valueOf(-11));

			fail();
		} catch (final Exception e) {

		}
	}

	@Test
	public void null_salary_must_be_zero() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.FEBRUARY, 12))
				.setName("Tim").setSurname("Smith").setSalary(null);
		assertEquals(BigDecimal.ZERO, person1.getSalary());
	}

}
