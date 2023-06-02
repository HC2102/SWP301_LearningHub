package swp391.group2.learninghub.model;

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
    private boolean isLearned;

    public Flashcard() {
    }

    public Flashcard(int id, int setId, String term, String definition, int position, boolean isLearned) {
        this.id = id;
        this.setId = setId;
        this.term = term;
        this.definition = definition;
        this.position = position;
        this.isLearned = isLearned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isLearned() {
        return isLearned;
    }

    public void setLearned(boolean learned) {
        isLearned = learned;
    }

    @Override
    public String toString() {
        return "Flashcard{" +
                "id=" + id +
                ", setId=" + setId +
                ", term='" + term + '\'' +
                ", definition='" + definition + '\'' +
                ", position=" + position +
                ", isLearn=" + isLearned +
                '}';
    }

}
