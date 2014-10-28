package ua.com.fielden.personnel;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

public class PersonCreation {

	@Test
	public void peson_should_be_created_from_its_string_representation() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1)).setName("Jhon")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person2 = Person.mkFromString(person1.toString());
		assertEquals(person1, person2);
	}

	@Test
	public void construction_of_not_fully_initialised_person_from_its_string() {
		final Person person1 = new Person()
				.setBirthday(LocalDate.of(2000, Month.MAY, 1))
				.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		final Person person2 = Person.mkFromString(person1.toString());
		assertEquals(person1, person2);
	}

	@Test
	public void it_should_not_be_possible_to_construct_person_from_an_incorrect_string_representaion() {
		try {
			Person.mkFromString("bla-bla-incorrect");
			fail();
		} catch (Exception e) {
		}

	}
	@Test
	public void negative_should_not_be_permited(){
		final Person p = new Person()
		.setBirthday(LocalDate.of(2000, Month.MAY, 1))
		.setSurname("Smith").setSalary(BigDecimal.valueOf(100));
		try{
			p.setSalary( new BigDecimal("-35"));
			fail();
		}catch (final IllegalArgumentException ex){
			assertEquals("Salary -35 is not permited", ex.getMessage());
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void negative_should_not_be_permiteddifferent_approch(){
		new Person()
		.setBirthday(LocalDate.of(2000, Month.MAY, 1))
		.setSurname("Smith").setSalary(new BigDecimal("-35"));
	}
	
	
}
