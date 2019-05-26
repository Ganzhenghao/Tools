import javax.servlet.http.Cookie;

/**
 * ClassName CookieTools
 * Description Cookie方法集合
 * Author Ganzhenghao
 * Date  2019/5/21 17:08:10
 * Version 1.0
 **/
public class CookieTools {
    /**
     *  找到相应的Cookie
     * @param cookieName
     * @param cookies
     * @return Cookie
     */
    public static Cookie findCookie(String cookieName, Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    return c;
                }
            }
        }
        return null;
    }
}
