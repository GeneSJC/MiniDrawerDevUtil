package uk.gov.hmrc.mobile.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

import myapp.R;

public class DateUtil {

    final static SimpleDateFormat HMRC_NEWS_DATE_FORMAT = new SimpleDateFormat("d MMM yyyy");
    final static SimpleDateFormat INBOX_MESSAGE_FORMAT  = new SimpleDateFormat("M/d/yy");
    final static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("h:mm a");

    // static PrettyTime p = new PrettyTime();

    public static String getTimeString(Date date) {

        String dateStr = HMRC_NEWS_DATE_FORMAT.format(date);

        return dateStr;
    }

    public static String getTimeString(String prefix, Date date) {

        String dateStr = DateUtil.getTimeString(date);

        dateStr = prefix + dateStr;

        return dateStr;
    }

    public static String getInboxTimeSpanString(Map<Integer, String> resIdToStringMap, Date now, Date before) {

        return DateUtil.getTimeSpanString(resIdToStringMap, now, before);
    }

    public static String getMessageTimeSpanString(Map<Integer, String> resIdToStringMap, Date now, Date before) {

        String sent = resIdToStringMap.get(R.string.sent);
        String at   = resIdToStringMap.get(R.string.at);

        String result = DateUtil.getTimeSpanString(resIdToStringMap, now, before);

        String timeStr = TIME_FORMAT.format(before);
        timeStr = timeStr.toLowerCase(Locale.UK);

        StringBuffer sb = new StringBuffer();
        sb.append(sent);
        sb.append(" ");
        sb.append(result);
        sb.append(" ");
        sb.append(at);
        sb.append(" ");
        sb.append(timeStr);

        result = sb.toString();

        return result;
    }

    private static String getTimeSpanString(Map<Integer, String> resIdToStringMap, Date now, Date before) {

        String today        = resIdToStringMap.get(R.string.today);
        String yesterday    = resIdToStringMap.get(R.string.yesterday);

        String result = null;

        int days = DateUtil.getDayCount(now, before);
        if (days == 0) {
            result = today;
        }
        else if (days == 1) {
            result = yesterday;
        }
        else if (days < 7) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(before);
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
            result = dateFormat.format(cal.getTime());
        }
        else  {
            result = INBOX_MESSAGE_FORMAT.format(before);
        }

        return result;
    }

    public static int getDayCount(Date now, Date before) {
        long diff = -1;
        try {
            //time is always 00:00:00 so rounding should help to ignore the missing hour when going from winter to summer time as well as the extra hour in the other direction
            diff = Math.round((now.getTime() - before.getTime()) / (double) 86400000);
        } catch (Exception e) {
            //handle the exception according to your own situation
        }
        return (int) diff;
    }
}
