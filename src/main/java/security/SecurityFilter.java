package security;

import servlets.ServletUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static models.ApplicationConstants.PagesURLs.LOGIN_URL;
import static models.ApplicationConstants.Roles.*;
import static models.ApplicationConstants.UserAttributes.LOGIN_AS_USERNAME;

@WebFilter
public class SecurityFilter implements Filter {
    public SecurityFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        //привести к виду HttpServlet
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath(); //получить url

        // получить имя пользователя из session
        String userLogin = ServletUtils.getUserLogin(request.getSession());

        String role = GUEST;
        if (!userLogin.equals(GUEST_LOGIN)) { //если это не гость, значит пользователь
            role = USER;
        }

        //получить доступные для него страницы
        List<String> urlsList = SecurityConfiguration.getUrlPatternsForRole(role);
        boolean isPermitted = false;

        //есть ли такая url среди разрешенных
        for (String url : urlsList) {
            if (url.equals(servletPath)) {
                isPermitted = true;
            }
        }

        if (isPermitted) {
            request.setAttribute(LOGIN_AS_USERNAME, userLogin);   //put login to jsp
            request.getRequestDispatcher(servletPath).
                    forward(request, response);
        } else {
            response.sendRedirect(LOGIN_URL);
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }
}
