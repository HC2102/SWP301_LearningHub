package swp391.group2.learninghub.Model;

import java.time.LocalDateTime;

public class UserSession {
    private String email;
    private String featureStatus;
    private LocalDateTime lastActivityTime;

    // Getters and setters

    public boolean isValid() {
        LocalDateTime currentTime = LocalDateTime.now();
        return lastActivityTime != null &&
                lastActivityTime.plusMinutes(30).isAfter(currentTime);
    }

    public UserSession() {
    }

    public UserSession(String email, String featureStatus, LocalDateTime lastActivityTime) {
        this.email = email;
        this.featureStatus = featureStatus;
        this.lastActivityTime = lastActivityTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeatureStatus() {
        return featureStatus;
    }

    public void setFeatureStatus(String featureStatus) {
        this.featureStatus = featureStatus;
    }

    public LocalDateTime getLastActivityTime() {
        return lastActivityTime;
    }

    public void setLastActivityTime(LocalDateTime lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
    }
}
