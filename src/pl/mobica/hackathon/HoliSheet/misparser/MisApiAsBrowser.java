package pl.mobica.hackathon.HoliSheet.misparser;

import com.google.common.base.Strings;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import pl.mobica.hackathon.HoliSheet.misparser.exceptions.LoginException;
import pl.mobica.hackathon.HoliSheet.misparser.exceptions.MisException;
import pl.mobica.hackathon.HoliSheet.misparser.exceptions.MisIOException;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: defecins
 * Date: 06.04.13
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */
public class MisApiAsBrowser implements MisApi  {
    private static final String MIS_LOGIN_URL = "https://mis.mobica.com/mis/login.php";
    private static final String MIS_HOURS_ADD = "https://mis.mobica.com/mis/hoursadd.php";
    private static final String PARAM_WINX = "winx";
    private static final java.lang.String WINX_CONST = "1584";
    private static final String PARAM_FORCELOGIN = "forcelogin";
    private static final String PARAM_USERLOGIN = "userlogin";
    private static final String PARAM_USERPASS = "userpass";
    private static final String PARAM_USERDOMAIN = "userdomain";
    private static final String DOMAIN_CONST = "MOBICAPL";
    private static final String RESP_PHPSESSID = "PHPSESSID";
    private static final String PARAM_PHPSESSID = "PHPSESSID";
    private static final String RESP_USER_DOMAIN = "UserDomain";
    private static final String RESP_USER_LOGIN = "UserLogin";
    public static final String MONDAY_MIS_PARAM = "d0";
    public static final String TUESDAY_MIS_PARAM = "d1";
    public static final String WEDNESDAT_MIS_PARAM = "d2";
    public static final String THURSDAY_MIS_PARAM = "d3";
    public static final String FRIDAY_MIS_PARAM = "d4";
    public static final String SATURDAY_MIS_PARAM = "d5";
    public static final String SUNDAY_MIS_PARAM = "d6";
    public static final String WEEK_PARAM = "week";
    public static final String YEAR_PARAM = "y";
    public static final String RELOAD_PARAM = "reload";
    public static final String NO_RELOAD_PARAM = "0";
    public static final int DEFAULT_TIMEOUT_MILLIS = 20000;
    public static final String PRODID_PARAM = "prodid";
    public static final String ACTID_PARAM = "actid";
    public static final String HOLIDAY_PAID = "177";
    public static final String STRANGE_ACTION = "3";
    private final DateHelper dateHelper = new DateHelper();


    @Override
    public AuthData login(String user, String password) throws MisException {
        try {
            Connection.Response response = Jsoup.connect(MIS_LOGIN_URL)
            .data(PARAM_WINX, WINX_CONST).data(PARAM_USERLOGIN, user)
                    .data(PARAM_USERPASS, password)
                    .data(PARAM_USERDOMAIN, DOMAIN_CONST).method(Connection.Method.POST).timeout(DEFAULT_TIMEOUT_MILLIS).execute();

            // get page title
            String userlogin = response.cookie(RESP_USER_LOGIN);
            String userdomain = response.cookie(RESP_USER_DOMAIN);
            String phpsesid = response.cookie(RESP_PHPSESSID);
            if (Strings.isNullOrEmpty(userlogin) || Strings.isNullOrEmpty(userdomain)
                    || Strings.isNullOrEmpty(phpsesid)) {
                throw new LoginException("Login exception");
            }
            AuthData ad = AuthData.create(userlogin, userdomain, phpsesid);
            return ad;

        } catch (IOException e) {
            throw new MisIOException(e);
        }
    }

    @Override
    public void fillHoliday(AuthData authentication, Date date) throws MisException {
        /*
        proid=177&actid=3&d0=16&d1=0&d2=0&d3=0&d4=0&d5=0&d6=0&week=15&y=2013&reload=1
         */
        try {
            Connection.Response response = Jsoup.connect(MIS_HOURS_ADD)
                    .cookie(PARAM_USERLOGIN, authentication.getUserLogin())
                    .cookie(PARAM_USERDOMAIN, authentication.getUserDomain())
                    .cookie(PARAM_PHPSESSID, authentication.getPhpsesid())
                    .data(PRODID_PARAM, HOLIDAY_PAID)
                    .data(ACTID_PARAM, STRANGE_ACTION)
                    .data(MONDAY_MIS_PARAM, dateHelper.calculateDayHours(date, Calendar.MONDAY))
                    .data(TUESDAY_MIS_PARAM, dateHelper.calculateDayHours(date, Calendar.TUESDAY))
                    .data(WEDNESDAT_MIS_PARAM, dateHelper.calculateDayHours(date, Calendar.WEDNESDAY))
                    .data(THURSDAY_MIS_PARAM, dateHelper.calculateDayHours(date, Calendar.THURSDAY))
                    .data(FRIDAY_MIS_PARAM, dateHelper.calculateDayHours(date, Calendar.FRIDAY))
                    .data(SATURDAY_MIS_PARAM, dateHelper.calculateDayHours(date, Calendar.SATURDAY))
                    .data(SUNDAY_MIS_PARAM, dateHelper.calculateDayHours(date, Calendar.SUNDAY))
                    .data(WEEK_PARAM, dateHelper.calculateWeek(date))
                    .data(YEAR_PARAM, String.valueOf(date.getYear()))
                    .data(RELOAD_PARAM, NO_RELOAD_PARAM)
                    .method(Connection.Method.POST).timeout(DEFAULT_TIMEOUT_MILLIS).execute();
        } catch (IOException e) {
            throw new MisException(e);
        }
    }

    private String calculateWeek(Date date) {
        return dateHelper.calculateWeek(date);
    }

    private String calculateDayHours(Date date, int dayOfWeek) {
        return dateHelper.calculateDayHours(date, dayOfWeek);
    }
}
