package pl.mobica.hackathon.HoliSheet.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;
import pl.mobica.hackathon.HoliSheet.misparser.AuthData;
import pl.mobica.hackathon.HoliSheet.misparser.MisApiAsBrowser;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: defecins
 * Date: 06.04.13
 * Time: 11:41
 * To change this template use File | Settings | File Templates.
 */
public class MisApiAsBrowserTest extends AndroidTestCase{

    public void testLogin() throws Exception {
        new MisApiAsBrowser().login("made", "password");
    }

    public void testFill() throws  Exception {
        AuthData login = new MisApiAsBrowser().login("made", "yebNij07");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 10);
        new MisApiAsBrowser().fillHoliday(login, cal.getTime());
    }
}
