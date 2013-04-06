package pl.mobica.hackathon.HoliSheet.misparser.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: defecins
 * Date: 06.04.13
 * Time: 11:29
 * To change this template use File | Settings | File Templates.
 */
public class MisException extends Exception {
    public MisException(Throwable throwable) {
        super(throwable);
    }

    public MisException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public MisException() {
    }

    public MisException(String detailMessage) {
        super(detailMessage);
    }
}
