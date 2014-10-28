package ua.com.fielden.personnel;

import java.util.Comparator;


public class SalaryComparator implements Comparator<Person>{
	public enum SortOrder{
		ASC,DESC
	}
	final Person o1 = new Person();
	final Person o2 = new Person();
	final  SortOrder sortParameter;
	public SalaryComparator(final SortOrder order) {
		sortParameter = order;
	}
	@Override
	public int compare(final Person o1, final Person o2) {
		if (sortParameter == SortOrder.ASC){
		if ((o1.getSalary() == null) && (o2.getSalary() == null)){
			return 0;
		}
		if((o1.getSalary() == null) && (o2.getSalary() != null)) {
			return -1;
		}
		if (o1.getSalary() == o2.getSalary()){
			return 0;
		}else {
			if(o1.getSalary().compareTo(o2.getSalary())>0) {
				return 1;
			} else {
				return -1;
			}
		}
		}else if ((o2.getSalary() == null) && (o1.getSalary() == null)){
			return 0;
		}
		if((o2.getSalary() == null) && (o1.getSalary() != null)) {
			return -1;
		}
		if (o2.getSalary() == o1.getSalary()){
			return 0;
		}else {
			if(o2.getSalary().compareTo(o1.getSalary())>0) {
				return 1;
			} else {
				return -1;
			}
		}
		  
	}
}
		
		
