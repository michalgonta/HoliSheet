package pl.mobica.hackathon.HoliSheet.misparser;

import java.util.Date;



/**
 * Created with IntelliJ IDEA.
 * User: defecins
 * Date: 06.04.13
 * Time: 11:04
 * To change this template use File | Settings | File Templates.
 */
public interface MisApi {

    AuthData login (String user, String pass) throws LoginException;

    void fillHoliday(AuthData authentication, Date date);
}
