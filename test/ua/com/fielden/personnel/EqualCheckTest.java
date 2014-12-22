package ua.com.fielden.personnel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import ua.com.fielden.personnel.Result.Parameter;

public class EqualCheckTest {

	@Test
	public void different_person_should_return_notEqual_result()
			throws IllegalArgumentException, IllegalAccessException {
		final Person testPerson = new Person().setName("July")
				.setSurname("Bush")
				.setBirthday(LocalDate.of(2014, Month.APRIL, 12))
				.setSalary(BigDecimal.valueOf(100)).setOrganisationRole(null);
		final Person person1 = new Person().setName(null).setName("Ann")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(50))
				.setBirthday(LocalDate.of(2012, Month.JULY, 12))
				.setOrganisationRole(new OrganisationRole("menager", null))
				.setSupervisor(testPerson).setPartner(testPerson);
		final Person person2 = new Person().setName("Natalie")
				.setSurname("Kruel").setSalary(BigDecimal.valueOf(5000))
				.setBirthday(LocalDate.of(2050, Month.JANUARY, 12))
				.setOrganisationRole(new OrganisationRole("someone", null))
				.setSupervisor(testPerson).setPartner(testPerson);
		assertTrue(Parameter.notEqual.equals(EqualCheck.deepEquals(person1,
				person2).getCheckParameter()));

	}

	@Test
	public void the_same_person_with_different_supervisors_or_partners_should_return_notEqual_result()
			throws IllegalArgumentException, IllegalAccessException {
		final Person supervisor1 = new Person().setName(null).setSurname(null)
				.setSalary(BigDecimal.valueOf(10))
				.setPartner(new Person().setName("Ann"));
		final Person supervisor2 = new Person().setName(null).setSurname(null)
				.setSalary(BigDecimal.valueOf(10)).setPartner(new Person());
		final Person partner1 = new Person().setName("Jul").setSurname("Bush")
				.setBirthday(LocalDate.of(2014, Month.APRIL, 12))
				.setSalary(BigDecimal.valueOf(100)).setOrganisationRole(null)
				.setSupervisor(supervisor1);
		final Person partner2 = new Person().setName("Jul").setSurname("Bush")
				.setBirthday(LocalDate.of(2014, Month.APRIL, 12))
				.setSalary(BigDecimal.valueOf(100)).setOrganisationRole(null)
				.setSupervisor(supervisor2);
		final Person testPerson1 = new Person().setName("July")
				.setSurname("Bush")
				.setBirthday(LocalDate.of(2014, Month.APRIL, 12))
				.setSalary(BigDecimal.valueOf(100)).setOrganisationRole(null)
				.setPartner(partner2);
		final Person testPerson2 = new Person().setName("July")
				.setSurname("Bush")
				.setBirthday(LocalDate.of(2014, Month.APRIL, 12))
				.setSalary(BigDecimal.valueOf(100)).setOrganisationRole(null)
				.setPartner(partner1);
		final Person person1 = new Person().setName(null).setName("Ann")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(50))
				.setBirthday(LocalDate.of(2012, Month.JULY, 12))
				.setOrganisationRole(new OrganisationRole("menager", null))
				.setSupervisor(testPerson1).setPartner(testPerson1);
		final Person person2 = new Person().setName(null).setName("Ann")
				.setSurname("Smith").setSalary(BigDecimal.valueOf(50))
				.setBirthday(LocalDate.of(2012, Month.JULY, 12))
				.setOrganisationRole(new OrganisationRole("menager", null))
				.setSupervisor(testPerson1).setPartner(testPerson2);
		assertTrue(Parameter.notEqual.equals(EqualCheck.deepEquals(person1,
				person2).getCheckParameter()));

	}

	@Test
	public void the_same_person_should_return_equal_result()
			throws IllegalArgumentException, IllegalAccessException {
		final Person person1 = new Person().setSurname("Smith").setName("Ann")
				.setSalary(BigDecimal.valueOf(50))
				.setBirthday(LocalDate.of(2012, Month.JULY, 12))
				.setOrganisationRole(null).setSupervisor(null).setPartner(null);
		final Person person2 = new Person().setSurname("Smith").setName("Ann")
				.setSalary(BigDecimal.valueOf(50))
				.setBirthday(LocalDate.of(2012, Month.JULY, 12))
				.setSupervisor(null).setOrganisationRole(null).setPartner(null);
		assertEquals(Parameter.equal,
				(EqualCheck.deepEquals(person1, person2)).getCheckParameter());

	}

}
