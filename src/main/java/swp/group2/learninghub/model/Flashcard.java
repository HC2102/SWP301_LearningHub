package swp.group2.learninghub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Flashcard {
    @Id
    private int id;
    private int setId;
    private String term;
    private String definition;
    private int position;

    public Flashcard() {
    }

    public Flashcard(int id, int setId, String term, String definition, int position) {
        this.id = id;
        this.setId = setId;
        this.term = term;
        this.definition = definition;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public int getSetId() {
        return setId;
    }

    @Override
    public String toString() {
        return "Flashcard{" +
                "id=" + id +
                ", setId=" + setId +
                ", term='" + term + '\'' +
                ", definition='" + definition + '\'' +
                ", position=" + position +
                '}';
    }

}
