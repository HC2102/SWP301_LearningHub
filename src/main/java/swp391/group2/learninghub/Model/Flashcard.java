package swp391.group2.learninghub.Model;

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
