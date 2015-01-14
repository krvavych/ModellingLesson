package ua.com.fielden.personnel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
		final EqualCheck check = new EqualCheck();
		assertEquals(Parameter.notEqual,
				(EqualCheck.deepEquals(person1, person2).getParameter()));
		assertEquals(Parameter.notEqual,
				(EqualCheck.deepEquals(person1, person2, check).getParameter()));

	}

	@Test
	public void make_sure_comparison_goes_deeper_than_first_level_of_field_declarations()
			throws IllegalArgumentException, IllegalAccessException {
		final Person superA = new Person().setName("A").setSurname("A");
		final Person superB = new Person().setName("B").setSurname("B");
		final Person superAWithPartner = new Person().setName("A")
				.setSurname("A").setPartner(superB);
		final Person personWithSuperA = new Person().setName("P1")
				.setSurname("P1").setSupervisor(superA);
		final Person personWithSuperAWithPartner = new Person().setName("P1")
				.setSurname("P1").setSupervisor(superAWithPartner);
		final EqualCheck check = new EqualCheck();
		assertEquals(
				Parameter.nullParameter,
				EqualCheck.deepEquals(personWithSuperA,
						personWithSuperAWithPartner).getParameter());
		assertEquals(
				Parameter.equal,
				EqualCheck.deepEquals(personWithSuperA,
						personWithSuperAWithPartner, check).getParameter());


	}

	@Test
	public void supervisor_of_person_can_be_partner_of_this_person()
			throws IllegalArgumentException, IllegalAccessException {
		final Person supervisor = new Person().setName("supervisor")
				.setSurname("supervisor");
		final Person personA = new Person().setName("A").setSurname("A")
				.setSupervisor(supervisor);
		final Person personB = new Person().setName("A").setSurname("A")
				.setSupervisor(supervisor);
		supervisor.setPartner(personA);
		final EqualCheck check = new EqualCheck();
		assertEquals(Parameter.equal, EqualCheck.deepEquals(personA, personB)
				.getParameter());
		assertEquals(Parameter.equal, EqualCheck.deepEquals(personA, personB, check)
				.getParameter());


	}

	@Test
	public void it_should_be_impossible_to_compare_different_objects() {
		final EqualCheck check = new EqualCheck();
		try {
			EqualCheck.deepEquals(new Result(), new Person(), check);
			fail();
		} catch (final Exception e) {
		}
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
		final EqualCheck check = new EqualCheck();
		assertEquals(Parameter.equal,
				(EqualCheck.deepEquals(person1, person2)).getParameter());
		assertEquals(Parameter.equal, EqualCheck
				.deepEquals(person1, person2, check).getParameter());


	}

	@Test
	public void recursion_should_work_correct()
			throws IllegalArgumentException, IllegalAccessException {
		final Person person1 = new Person().setName("A");
		final Person person2 = new Person().setName("A");
		final Person supervisor1 = new Person().setPartner(person1).setName("");
		final Person supervisor2 = new Person().setPartner(person2).setName("name");
		person1.setSupervisor(supervisor1).setPartner(supervisor1);
		person2.setSupervisor(supervisor2).setPartner(supervisor2);
		final EqualCheck check = new EqualCheck();
		assertEquals(Parameter.notEqual, EqualCheck
				.deepEquals(person1, person2).getParameter());
		assertEquals(Parameter.equal, EqualCheck
				.deepEquals(person1, person2, check).getParameter());
		assertEquals(Parameter.equal,
				(EqualCheck.deepEquals(new Person().setName(""), new Person(), check).getParameter()));
	}
	@Test
	public void interfase_method_should_work_as_expected(){
		final Class<?> type = new Person().getClass();
		final EqualCheck p = new  EqualCheck();
		final Class<?> class1 = new Result().getClass();
		System.out.println(p.selectedFields(type));
		System.out.println(p.selectedFields(class1));
	}
}
