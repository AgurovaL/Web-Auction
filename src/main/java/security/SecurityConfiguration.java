package security;

import java.util.*;

public class SecurityConfiguration implements models.ApplicationConstants {

    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }

    private static void init() {
        mapConfig.put(Roles.USER, getUserUrls());
        mapConfig.put(Roles.GUEST, getGuestUrls());
    }

    // Конфигурация для роли "USER"
    private static List<String> getUserUrls() {
        List<String> urlPatternsUser = new ArrayList<String>();

        urlPatternsUser.add(PagesURLs.SHOW_MY_ITEM_URL);
        urlPatternsUser.add(PagesURLs.CREATE_ITEM_URL);
        urlPatternsUser.add(PagesURLs.SHOW_ITEMS_URL);
        return urlPatternsUser;
    }

    // Конфигурация для роли "GUEST"
    private static List<String> getGuestUrls() {
        List<String> urlPatternsGuest = new ArrayList<String>();

        urlPatternsGuest.add(PagesURLs.SHOW_ITEMS_URL);
        return urlPatternsGuest;
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}
