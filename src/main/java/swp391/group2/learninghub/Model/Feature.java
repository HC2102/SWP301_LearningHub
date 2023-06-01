package swp391.group2.learninghub.Model;

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

    public Feature(int id, String name, boolean isActive, String description) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
