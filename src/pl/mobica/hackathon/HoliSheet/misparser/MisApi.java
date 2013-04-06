package pl.mobica.hackathon.HoliSheet.misparser;

import pl.mobica.hackathon.HoliSheet.misparser.exceptions.FillException;
import pl.mobica.hackathon.HoliSheet.misparser.exceptions.MisException;
import pl.mobica.hackathon.HoliSheet.misparser.exceptions.MisIOException;

import java.util.Date;



/**
 * Created with IntelliJ IDEA.
 * User: defecins
 * Date: 06.04.13
 * Time: 11:04
 * To change this template use File | Settings | File Templates.
 */
public interface MisApi {

    AuthData login (String user, String pass) throws MisException;

    void fillHoliday(AuthData authentication, Date date) throws MisException;
}
