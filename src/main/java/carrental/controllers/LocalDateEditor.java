package carrental.controllers;

import java.beans.PropertyEditorSupport;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class LocalDateEditor extends PropertyEditorSupport {
	public static final String DATE_PATTERN = "dd.MM.yyyy";

	// {System.out.println("LocalDateEditor");}

	// Converts a String to a DateTime (when submitting form)
	@Override
	public void setAsText(String text) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern(DATE_PATTERN);
		final LocalDate date = dtf.parseLocalDate(text);
		this.setValue(date);
	}

	// Converts a Category to a DateTime (when displaying form)
	@Override
	public String getAsText() {
		LocalDate date = (LocalDate) this.getValue();
		return date.toString();
	}

}