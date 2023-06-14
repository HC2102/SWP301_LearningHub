package swp.group2.learninghub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class BoardLabel {
    @Id
    private int id;
    @ManyToMany(mappedBy = "labels")
    private ArrayList<Card> cards;
    private int boardId;
    private String name;
    private String color;

    public void addCard(Card card) {
        if (cards == null) {
            cards = new ArrayList<>();
        }
        cards.add(card);
        card.addLabel(this); // Add the label to the card's list of labels
    }

    public void removeCard(Card card) {
        if (cards != null) {
            cards.remove(card);
            card.removeLabel(this); // Remove the label from the card's list of labels
        }
    }

    public void addLabel(BoardLabel label) {
    }

    public void removeLabel(BoardLabel label) {
    }
}
