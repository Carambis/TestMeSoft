package by.bsuir;

import by.bsuir.entity.UserEntity;
import by.bsuir.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Objects;

@WebListener
public class SessionEventListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(5);
        System.out.println("create");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        String id;
        SessionRegistry sessionRegistry = getBean(event, SessionRegistry.class.getName());
        SessionInformation sessionInfo = sessionRegistry
                .getSessionInformation(event.getSession().getId());
        UserEntity ud = null;
        if (Objects.nonNull(sessionInfo)) {
            ud = (UserEntity) sessionInfo.getPrincipal();
        }
        if (Objects.nonNull(ud)) {
            id = ud.getId();
            getAnyBean(event, UserService.class.getName()).deleteUser(id);
        }
    }

    private UserService getAnyBean(HttpSessionEvent event, String name) {
        HttpSession session = event.getSession();
        ApplicationContext ctx =
                WebApplicationContextUtils.
                        getWebApplicationContext(session.getServletContext());
        return (UserService) Objects.requireNonNull(ctx).getBean(name);
    }

    private SessionRegistry getBean(HttpSessionEvent event, String name) {
        HttpSession session = event.getSession();
        ApplicationContext ctx =
                WebApplicationContextUtils.
                        getWebApplicationContext(session.getServletContext());
        return (SessionRegistry) Objects.requireNonNull(ctx).getBean(name);
    }
}
