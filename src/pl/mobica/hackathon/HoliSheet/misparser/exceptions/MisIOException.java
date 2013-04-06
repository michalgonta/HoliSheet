package pl.mobica.hackathon.HoliSheet.misparser.exceptions;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: defecins
 * Date: 06.04.13
 * Time: 11:20
 * To change this template use File | Settings | File Templates.
 */
public class MisIOException extends MisException {
    public MisIOException(IOException e) {
        super(e);
    }
}
