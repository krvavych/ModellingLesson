package ua.com.fielden.personnel;

import static java.lang.String.format;

public class OrganisationRole {
	private String title;
	private String description;

	public OrganisationRole(final String title, final String description) {
		this.title = title;
		this.description = description;
	}
	public OrganisationRole() {
	}

	public OrganisationRole setTitle(final String title){
		this.title = title;
		return this;
	}

	public OrganisationRole setDescription(final String description){
		this.description = description;
		return this;
	}

	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	@Override
	public String toString() {
		return format("title: %s\tdescription: %s", title, description);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final OrganisationRole other = (OrganisationRole) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}


}
