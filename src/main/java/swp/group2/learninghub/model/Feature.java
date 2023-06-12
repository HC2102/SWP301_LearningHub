package swp.group2.learninghub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Feature {
    @Id
    private int id;
    private String name;
    private boolean isActive;
    private String description;

    public Feature() {
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", is_Active=" + isActive +
                ", description='" + description + '\'' +
                '}';
    }
}
