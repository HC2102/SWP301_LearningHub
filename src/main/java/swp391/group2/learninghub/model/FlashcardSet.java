package swp391.group2.learninghub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


import java.util.Date;


@Entity
public class FlashcardSet {
    @Id
    private int id;
    private String userId;

    private String title;
    private String description;
    private Date createdDate;
    private boolean isActive;
    private boolean isLearned;

    public FlashcardSet() {
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isLearned() {
        return isLearned;
    }

    public void setLearned(boolean learned) {
        isLearned = learned;
    }

    @Override
    public String toString() {
        return "FlashcardSet{" +
                "id=" + id +
                ", user_id='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", created_date=" + createdDate +
                ", isActive=" + isActive +
                ", isLearned=" + isLearned +
                '}';
    }
}
