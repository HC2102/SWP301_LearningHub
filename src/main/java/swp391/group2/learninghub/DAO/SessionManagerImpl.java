package swp391.group2.learninghub.DAO;

import swp391.group2.learninghub.Model.UserSession;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManagerImpl implements SessionManager {
    private Map<String, UserSession> sessionMap;

    public SessionManagerImpl() {
        this.sessionMap = new ConcurrentHashMap<>();
    }

    @Override
    public void createSession(String sessionId, UserSession userSession) {
        sessionMap.put(sessionId, userSession);
    }

    @Override
    public UserSession getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }

    @Override
    public void invalidateSession(String sessionId) {
        sessionMap.remove(sessionId);
    }
}
