package ua.com.fielden.personnel;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import ua.com.fielden.personnel.CommonComparator.SortBy;

public class ComparatorSorting {

	@Test
	public void comparator_should_sort_nameASC_corect() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Tania")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person2 = new Person()
				.setBirthday(LocalDate.of(2013, Month.MAY, 12)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(1000));
		final Person person3 = new Person()
				.setBirthday(LocalDate.of(2010, Month.MAY, 12)).setName("Aron")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(10));

		final Set<Person> persons = new TreeSet<Person>(new CommonComparator(
				SortBy.NameASC));
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		final Iterator<Person> iter = persons.iterator();
		final Person p1FromSet = iter.next();
		final Person p2FromSet = iter.next();
		final Person p3FromSet = iter.next();

		assertTrue(p1FromSet.getName().compareTo(p2FromSet.getName()) < 0);
		assertTrue(p1FromSet.getName().compareTo(p3FromSet.getName()) < 0);
		assertTrue(p3FromSet.getName().compareTo(p2FromSet.getName()) > 0);
		assertTrue(p3FromSet.getName().compareTo(p1FromSet.getName()) > 0);
		assertTrue(p2FromSet.getName().compareTo(p1FromSet.getName()) > 0);
		assertTrue(p2FromSet.getName().compareTo(p3FromSet.getName()) < 0);

	}

	@Test
	public void comparator_should_sort_surnameASC_corect() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Tania")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person2 = new Person()
				.setBirthday(LocalDate.of(2013, Month.MAY, 12)).setName("Jhon")
				.setSurname("Kar").setSalary(BigDecimal.valueOf(1000));
		final Person person3 = new Person()
				.setBirthday(LocalDate.of(2010, Month.MAY, 12)).setName("Aron")
				.setSurname("Hopkin").setSalary(BigDecimal.valueOf(10));

		final Set<Person> persons = new TreeSet<Person>(new CommonComparator(
				SortBy.SurnameASC));
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		final Iterator<Person> iter = persons.iterator();
		final Person p1FromSet = iter.next();
		final Person p2FromSet = iter.next();
		final Person p3FromSet = iter.next();

		assertTrue(p1FromSet.getSurname().compareTo(p2FromSet.getSurname()) < 0);
		assertTrue(p1FromSet.getSurname().compareTo(p3FromSet.getSurname()) < 0);
		assertTrue(p3FromSet.getSurname().compareTo(p2FromSet.getSurname()) > 0);
		assertTrue(p3FromSet.getSurname().compareTo(p1FromSet.getSurname()) > 0);
		assertTrue(p2FromSet.getSurname().compareTo(p1FromSet.getSurname()) > 0);
		assertTrue(p2FromSet.getSurname().compareTo(p3FromSet.getSurname()) < 0);

	}
	@Test
	public void comparator_should_sort_surnameDESC_corect() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Tania")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person2 = new Person()
				.setBirthday(LocalDate.of(2013, Month.MAY, 12)).setName("Jhon")
				.setSurname("Kar").setSalary(BigDecimal.valueOf(1000));
		final Person person3 = new Person()
				.setBirthday(LocalDate.of(2010, Month.MAY, 12)).setName("Aron")
				.setSurname("Hopkin").setSalary(BigDecimal.valueOf(10));

		final Set<Person> persons = new TreeSet<Person>(new CommonComparator(
				SortBy.SurnameDESC));
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		final Iterator<Person> iter = persons.iterator();
		final Person p1FromSet = iter.next();
		final Person p2FromSet = iter.next();
		final Person p3FromSet = iter.next();

		assertTrue(p1FromSet.getSurname().compareTo(p2FromSet.getSurname()) > 0);
		assertTrue(p1FromSet.getSurname().compareTo(p3FromSet.getSurname()) > 0);
		assertTrue(p3FromSet.getSurname().compareTo(p2FromSet.getSurname()) < 0);
		assertTrue(p3FromSet.getSurname().compareTo(p1FromSet.getSurname()) < 0);
		assertTrue(p2FromSet.getSurname().compareTo(p1FromSet.getSurname()) < 0);
		assertTrue(p2FromSet.getSurname().compareTo(p3FromSet.getSurname()) > 0);

	}


	@Test
	public void comparator_should_sort_nameDESC_corect() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Tania")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person2 = new Person()
				.setBirthday(LocalDate.of(2013, Month.MAY, 12)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(1000));
		final Person person3 = new Person()
				.setBirthday(LocalDate.of(2010, Month.MAY, 12)).setName("Aron")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(10));

		final Set<Person> persons = new TreeSet<Person>(new CommonComparator(
				SortBy.NameDESC));
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);

		assertEquals(3, persons.size());

		final Iterator<Person> iter = persons.iterator();
		final Person p1FromSet = iter.next();
		final Person p2FromSet = iter.next();
		final Person p3FromSet = iter.next();

		assertTrue(p1FromSet.getName().compareTo(p2FromSet.getName()) > 0);
		assertTrue(p1FromSet.getName().compareTo(p3FromSet.getName()) > 0);
		assertTrue(p3FromSet.getName().compareTo(p2FromSet.getName()) < 0);
		assertTrue(p3FromSet.getName().compareTo(p1FromSet.getName()) < 0);
		assertTrue(p2FromSet.getName().compareTo(p3FromSet.getName()) > 0);
		assertTrue(p2FromSet.getName().compareTo(p1FromSet.getName()) < 0);

	}
	
}
