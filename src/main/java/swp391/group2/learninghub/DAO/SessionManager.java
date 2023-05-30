package swp391.group2.learninghub.DAO;

import swp391.group2.learninghub.Model.UserSession;

public interface SessionManager {
    void createSession(String sessionId, UserSession userSession);
    UserSession getSession(String sessionId);
    void invalidateSession(String sessionId);
}
