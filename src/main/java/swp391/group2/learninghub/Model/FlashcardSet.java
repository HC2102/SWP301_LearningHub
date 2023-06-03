package swp391.group2.learninghub.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


import java.util.Date;


@Entity
public class FlashcardSet {
    @Id
    private int id;
    private String user_id;

    private String title;
    private String description;
    private Date createdDate;
    private boolean isActive;
    private boolean isLearned;

    public FlashcardSet() {
    }

    public FlashcardSet(int id, String user_id, String title, String description, Date createdDate, boolean isActive, boolean isLearned) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.isActive = isActive;
        this.isLearned = isLearned;
    }

    // Getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActive() {
        return isActive;
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
                ", user_id='" + user_id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", created_date=" + createdDate +
                ", isActive=" + isActive +
                ", isLearned=" + isLearned +
                '}';
    }
}
