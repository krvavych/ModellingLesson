package ua.com.fielden.personnel;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

public class PersonComparison {

	@Test
	public void same_object_must_return_0() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person2 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		assertEquals(0, person1.compareTo(person2));
	}

	@Test
	public void if_this_is_less_must_return_1() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 12)).setName("Ann")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person2 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY,1)).setName("Ann")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		assertTrue(person1.compareTo(person2) > 0);
	}
	
	@Test
	public void if_object_is_less_must_return_negative1() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Ann")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person2 = new Person()
				.setBirthday(LocalDate.of(2001, Month.MAY, 1)).setName("Ann")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		assertTrue(person1.compareTo(person2) < 0);
	}
	
	

}
