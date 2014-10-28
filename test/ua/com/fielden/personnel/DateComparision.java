package ua.com.fielden.personnel;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import ua.com.fielden.personnel.BirthdayComparator.SortOrder;


public class DateComparision {
	@Test
	public void birthdayCompare_have_to_sort_TreeSet_correct() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Tania")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person2 = new Person()
				.setBirthday(LocalDate.of(2013, Month.MAY, 12)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(1000));
		final Person person3 = new Person()
				.setBirthday(LocalDate.of(2010, Month.MAY, 12)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(10));

		final Set<Person> persons = new TreeSet<Person>(new BirthdayComparator(SortOrder.ASC));
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		final Iterator<Person> iter = persons.iterator();
		final Person p1FromSet = iter.next();
		final Person p2FromSet = iter.next();
		final Person p3FromSet = iter.next();

		assertTrue(p1FromSet.getBirthday().compareTo(p2FromSet.getBirthday()) < 0);
		assertTrue(p1FromSet.getBirthday().compareTo(p3FromSet.getBirthday()) < 0);
		assertTrue(p3FromSet.getBirthday() .compareTo(p2FromSet.getBirthday()) > 0);
		assertTrue(p3FromSet.getBirthday().compareTo(p1FromSet.getBirthday()) > 0);
		assertTrue(p2FromSet.getBirthday().compareTo(p1FromSet.getBirthday()) > 0);
		assertTrue(p2FromSet.getBirthday().compareTo(p3FromSet.getBirthday()) < 0);
	}

	@Test
	public void birthdayCompare_shoul_sort_in_corect_order() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Tania")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person2 = new Person()
				.setBirthday(LocalDate.of(2013, Month.MAY, 12)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(1000));
		final Person person3 = new Person()
				.setBirthday(LocalDate.of(2010, Month.MAY, 12)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(10));

		final Set<Person> persons = new TreeSet<Person>(new BirthdayComparator(SortOrder.DESC));
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		final Iterator<Person> iter = persons.iterator();
		final Person p1FromSet = iter.next();
		final Person p2FromSet = iter.next();
		final Person p3FromSet = iter.next();

		assertTrue(p1FromSet.getBirthday().compareTo(p2FromSet.getBirthday()) > 0);
		assertTrue(p1FromSet.getBirthday().compareTo(p3FromSet.getBirthday()) > 0);
		assertTrue(p3FromSet.getBirthday() .compareTo(p2FromSet.getBirthday()) < 0);
		assertTrue(p3FromSet.getBirthday().compareTo(p1FromSet.getBirthday()) < 0);
		assertTrue(p2FromSet.getBirthday().compareTo(p1FromSet.getBirthday()) < 0);
		assertTrue(p2FromSet.getBirthday().compareTo(p3FromSet.getBirthday()) > 0);
	}

}
