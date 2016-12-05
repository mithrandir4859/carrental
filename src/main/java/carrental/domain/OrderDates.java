package carrental.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.joda.time.DateTimeFieldType;
import org.joda.time.LocalDate;

public class OrderDates {

	private Map<String, Integer> fields = new HashMap<>();

	public OrderDates() {
	}

	public OrderDates(LocalDate givenDate) {
		Integer year = givenDate.get(DateTimeFieldType.year());
		Integer month = givenDate.get(DateTimeFieldType.monthOfYear());
		Integer day = givenDate.get(DateTimeFieldType.dayOfMonth());
		for (String key : fields.keySet())
			if (key.contains("Year"))
				fields.put(key, year);
			else if (key.contains("Month"))
				fields.put(key, month);
			else if (key.contains("Day"))
				fields.put(key, day);
			else
				assert false;
	}

	public Map<String, Integer> toMap() {
		return Collections.unmodifiableMap(fields);
	}

	public Integer getEndYear() {
		return fields.get("endYear");
	}

	public void setEndYear(Integer endYear) {
		fields.put("endYear", endYear);
	}

	public Integer getEndMonth() {
		return fields.get("endMonth");
	}

	public void setEndMonth(Integer endMonth) {
		fields.put("endMonth", endMonth);
	}

	public Integer getEndDay() {
		return fields.get("endDay");
	}

	public void setEndDay(Integer endDay) {
		fields.put("endDay", endDay);
	}

	public Integer getStartYear() {
		return fields.get("startYear");
	}

	public void setStartYear(Integer startYear) {
		fields.put("startYear", startYear);
	}

	public Integer getStartMonth() {
		return fields.get("startMonth");
	}

	public void setStartMonth(Integer startMonth) {
		fields.put("startMonth", startMonth);
	}

	public Integer getStartDay() {
		return fields.get("startDay");
	}

	public void setStartDay(Integer startDay) {
		fields.put("startDay", startDay);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDates other = (OrderDates) obj;
		if (fields == null) {
			if (other.fields != null)
				return false;
		} else if (!fields.equals(other.fields))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final int maxLen = 5;
		StringBuilder builder = new StringBuilder();
		builder.append("OrderDates [fields=").append(fields != null ? toString(fields.entrySet(), maxLen) : null).append("]");
		return builder.toString();
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

}
