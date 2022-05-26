package vsms;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author caleb
 */
public class DateTools {

    public static java.sql.Date StringToDate(String str, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        java.util.Date d1 = sdf.parse(str);
        sdf.applyPattern("yyyy-mm-dd");
        String tmp = sdf.format(d1); //yyyy-[m]m-[d]d format to this format because mysql date class is stupid and only understands american freedom formats
        System.out.println("In StringToDate: " + tmp);
        return java.sql.Date.valueOf(tmp);

    }

    public static java.sql.Date JavaDateToSqlDate(java.util.Date javaDate) {
        return new java.sql.Date(javaDate.getTime());
    }

    public static String SqlDateToString(java.util.Date date, String format) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        String dateStr = date.toString();
        System.out.println(dateStr);
        java.util.Date d1 = sdf.parse(dateStr);

        sdf.applyPattern("dd/mm/yyyy");
        String tmp = sdf.format(d1);
        System.out.println(d1.toString());
        return tmp;

    }
}
