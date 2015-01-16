package ua.com.fielden.personnel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import ua.com.fielden.personnel.Result.Parameter;
import ua.com.fielden.personnel.selectors.AllFieldSelector;
import ua.com.fielden.personnel.selectors.IFieldSelector;

public class BfsCheckTest {
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
		final IFieldSelector check = new AllFieldSelector();
		assertEquals(Parameter.notEqual,
				(BfsCheck.deepEquals(person1, person2, check).getParameter()));

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
		System.out.println(personWithSuperA.toString());
		System.out.println(personWithSuperAWithPartner.toString());
		final IFieldSelector check = new AllFieldSelector();
		assertEquals(
				Parameter.nullParameter,
				BfsCheck.deepEquals(personWithSuperA,
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
		final IFieldSelector check = new AllFieldSelector();
		assertEquals(Parameter.equal, BfsCheck.deepEquals(personA, personB, check)
				.getParameter());
	}

	@Test
	public void it_should_be_impossible_to_compare_different_objects() {
		final IFieldSelector  check = new AllFieldSelector();
		try {
			BfsCheck.deepEquals(new Result(), new Person(), check);
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
		final IFieldSelector check = new AllFieldSelector();
		assertEquals(Parameter.equal,
				(BfsCheck.deepEquals(person1, person2, check)).getParameter());
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
		final IFieldSelector check = new AllFieldSelector();
		assertEquals(Parameter.notEqual, BfsCheck
				.deepEquals(person1, person2, check).getParameter());
	}
}
