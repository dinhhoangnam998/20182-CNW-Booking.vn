package webtech.gr14.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateCommonUtil {

	public static Date stringToDate(String format, String dateInStr) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date parsedDate = new Date();
		try {
			parsedDate = formatter.parse(dateInStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	}

	public static List<Date> getDatesBetweenBeginAndEnd(Date startDate, Date endDate) {
		List<Date> datesInRange = new ArrayList<>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);

		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endDate);

		while (calendar.before(endCalendar)) {
			Date result = calendar.getTime();
			datesInRange.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		return datesInRange;
	}
}
