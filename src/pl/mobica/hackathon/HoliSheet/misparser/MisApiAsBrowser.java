package pl.mobica.hackathon.HoliSheet.misparser;

import com.google.common.base.Strings;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import pl.mobica.hackathon.HoliSheet.misparser.exceptions.FillException;
import pl.mobica.hackathon.HoliSheet.misparser.exceptions.LoginException;
import pl.mobica.hackathon.HoliSheet.misparser.exceptions.MisException;
import pl.mobica.hackathon.HoliSheet.misparser.exceptions.MisIOException;

import java.io.IOException;
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
    private static final String PARAM_WINX = "winx";
    private static final java.lang.String WINX_CONST = "1584";
    private static final String PARAM_FORCELOGIN = "forcelogin";
    private static final String PARAM_USERLOGIN = "userlogin";
    private static final String PARAM_USERPASS = "userpass";
    private static final String PARAM_USERDOMAIN = "userdomain";
    private static final String DOMAIN_CONST = "MOBICAPL";
    private static final String RESP_PHPSESSID = "PHPSESSID";
    private static final String RESP_USER_DOMAIN = "UserDomain";
    private static final String RESP_USER_LOGIN = "UserLogin";


    @Override
    public AuthData login(String user, String password) throws MisException {
        try {
            Connection.Response response = Jsoup.connect(MIS_LOGIN_URL)
            .data(PARAM_WINX, WINX_CONST).data(PARAM_USERLOGIN, user)
                    .data(PARAM_USERPASS, password)
                    .data(PARAM_USERDOMAIN, DOMAIN_CONST).method(Connection.Method.POST).timeout(20000).execute();

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
    public void fillHoliday(AuthData authentication, Date date) throws FillException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
