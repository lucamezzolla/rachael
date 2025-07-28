package cutalab.rachael.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	
	public static final String formatDateInDayMonthYear(LocalDate localDate) {
		var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return formatter.format(localDate);
	}

}
