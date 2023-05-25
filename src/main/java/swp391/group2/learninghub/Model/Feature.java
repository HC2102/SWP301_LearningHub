package swp391.group2.learninghub.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Feature {
    @Id
    private int id;
    private String name;
    private boolean is_Active;
    private String description;

    public Feature() {
    }

    public Feature(int id, String name, boolean is_Active, String description) {
        this.id = id;
        this.name = name;
        this.is_Active = is_Active;
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

    public boolean isIs_Active() {
        return is_Active;
    }

    public void setIs_Active(boolean is_Active) {
        this.is_Active = is_Active;
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
                ", is_Active=" + is_Active +
                ", description='" + description + '\'' +
                '}';
    }
}
