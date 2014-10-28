package ua.com.fielden.personnel;

import java.util.Comparator;

public class BirthdayComparator implements Comparator<Person>{
	public enum SortOrder{
		ASC,DESC
	}
	final Person o1 = new Person();
	final Person o2 = new Person();
	final SortOrder sortParameter;
	public BirthdayComparator(final SortOrder order) {
		sortParameter = order;
	}

	@Override
	public int compare(final Person o1, final Person o2) {
		int result = 0;
		if(sortParameter == SortOrder.ASC){
		if ((o1.getBirthday() == null) && (o2.getBirthday() == null)){
			result = 0;
		}
		if((o1.getBirthday() == null) && (o2.getBirthday() != null)) {
			result = -1;
		}
		if (o1.getBirthday() == o2.getBirthday()){
			result = 0;
		}else {
			if(o1.getBirthday().compareTo(o2.getBirthday())>0) {
				result = 1;
			} else {
				result = -1;
			}
			
		}
		}else if( sortParameter == SortOrder.DESC ){
			if ((o2.getBirthday() == null) && (o1.getBirthday() == null)){
				result = 0;
			}
			if((o2.getBirthday() == null) && (o1.getBirthday() != null)) {
				result = -1;
			}
			if (o2.getBirthday() == o1.getBirthday()){
				result = 0;
			}else {
				if(o2.getBirthday().compareTo(o1.getBirthday())>0) {
					result = 1;
				} else {
					result = -1;
				}
			}
		}
		return result;
	}
}
