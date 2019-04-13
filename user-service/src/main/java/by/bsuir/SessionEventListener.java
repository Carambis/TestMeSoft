package by.bsuir;

import by.bsuir.entity.UserEntity;
import by.bsuir.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionEventListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(5);
        System.out.println("open");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        String id=null;
        SessionRegistry sessionRegistry = getBean(event, "sessionRegistry");
        SessionInformation sessionInfo = (sessionRegistry != null ? sessionRegistry
                .getSessionInformation(event.getSession().getId()) : null);
        UserEntity ud = null;
        if (sessionInfo != null) ud = (UserEntity) sessionInfo.getPrincipal();
        if (ud != null) {
            id=ud.getId();
            getAnyBean(event, "userService").deleteUser(id);
        }
    }

    private UserService getAnyBean(HttpSessionEvent event, String name){
        HttpSession session = event.getSession();
        ApplicationContext ctx =
                WebApplicationContextUtils.
                        getWebApplicationContext(session.getServletContext());
        return (UserService) ctx.getBean(name);
    }

    private SessionRegistry getBean(HttpSessionEvent event, String name){
        HttpSession session = event.getSession();
        ApplicationContext ctx =
                WebApplicationContextUtils.
                        getWebApplicationContext(session.getServletContext());
        return (SessionRegistry) ctx.getBean(name);
    }
}
