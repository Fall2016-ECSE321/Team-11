package ca.mcgill.ecse321.FTMS.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;
/**
 * this class formats the pattern for the date and time of this project
 * @author Group 11 and Taken from ECSE 321 Assignment 1
 *
 */
public class DateLabelFormatter extends AbstractFormatter {
	private static final long serialVersionUID = -2169252224419341678L;
	private String datePattern = "yyyy-MM-dd";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	@Override
	public Object stringToValue(String text) throws ParseException {
	return dateFormatter.parseObject(text);
	}
	@Override
	public String valueToString(Object value) throws ParseException {
	if (value != null) {
	Calendar cal = (Calendar) value;
	return dateFormatter.format(cal.getTime());
	}
	return "";
	}

}