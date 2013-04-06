package pl.mobica.hackathon.HoliSheet.misparser;

import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;

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
}
