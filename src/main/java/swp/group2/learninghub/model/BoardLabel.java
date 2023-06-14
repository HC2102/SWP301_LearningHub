package swp.group2.learninghub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
