package ua.com.fielden.personnel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import ua.com.fielden.personnel.Check.CheckBy;
import ua.com.fielden.personnel.Result.Parameter;

public class DfsOrDfsTest {
	@Test
	public void dfs_should_check_in_breadth() throws IllegalArgumentException, IllegalAccessException{
		final ForTest smth1 = new ForTest().setTitle("title").setInteger(1);
		final ForTest smth2 = new ForTest().setTitle("title").setInteger(2);
		final ForTest obj1 = new ForTest().setInteger(2).setSmthing(smth1).setTitle("title");
		final ForTest obj2 = new ForTest().setSmthing(smth2).setTitle("title");
		assertEquals(Parameter.nullParameter, Check.checkEquals(obj1, obj2, CheckBy.Bfs).getParameter());
		assertEquals(Parameter.notEqual, Check.checkEquals(obj1, obj2, CheckBy.Dfs).getParameter());
	}

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
		assertEquals(Parameter.notEqual,
				(Check.checkEquals(person1, person2, CheckBy.Bfs).getParameter()));
		assertEquals(Parameter.notEqual,
				(Check.checkEquals(person1, person2, CheckBy.Dfs).getParameter()));


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
		assertEquals(
				Parameter.nullParameter,
				Check.checkEquals(personWithSuperA,
						personWithSuperAWithPartner, CheckBy.Bfs).getParameter());
		assertEquals(
				Parameter.nullParameter,
				Check.checkEquals(personWithSuperA,
						personWithSuperAWithPartner, CheckBy.Dfs).getParameter());
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
		assertEquals(Parameter.equal, Check.checkEquals(personA, personB, CheckBy.Bfs)
				.getParameter());
		assertEquals(Parameter.equal, Check.checkEquals(personA, personB, CheckBy.Dfs)
				.getParameter());
	}

	@Test
	public void it_should_be_impossible_to_compare_different_objects() {
		try {
			Check.checkEquals(new Result(), new Person(), CheckBy.Bfs);
			fail();
			Check.checkEquals(new Result(), new Person(), CheckBy.Dfs);
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
		assertEquals(Parameter.equal,
				(Check.checkEquals(person1, person2, CheckBy.Bfs)).getParameter());
		assertEquals(Parameter.equal,
				(Check.checkEquals(person1, person2, CheckBy.Dfs)).getParameter());
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
		assertEquals(Parameter.notEqual, Check
				.checkEquals(person1, person2, CheckBy.Bfs).getParameter());
		assertEquals(Parameter.notEqual, Check
				.checkEquals(person1, person2, CheckBy.Dfs).getParameter());

	}

}