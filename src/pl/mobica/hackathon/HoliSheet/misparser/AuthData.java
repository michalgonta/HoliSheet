package pl.mobica.hackathon.HoliSheet.misparser;

/**
 * Created with IntelliJ IDEA.
 * User: defecins
 * Date: 06.04.13
 * Time: 11:09
 * To change this template use File | Settings | File Templates.
 */
public class AuthData {
    private final String userlogin;
    private final String userdomain;
    private final String phpsesid;

    public String getUserDomain() {
        return userdomain;
    }

    public String getPhpsesid() {
        return phpsesid;
    }

    private String userLogin;

    private AuthData(String userlogin, String userdomain, String phpsesid) {
        this.userlogin = userlogin;
        this.userdomain = userdomain;
        this.phpsesid = phpsesid;
    }

    public static AuthData create(String userlogin, String userdomain, String phpsesid) {
        return new AuthData(userlogin, userdomain, phpsesid);
    }

    public String getUserLogin() {
        return userLogin;
    }
}
