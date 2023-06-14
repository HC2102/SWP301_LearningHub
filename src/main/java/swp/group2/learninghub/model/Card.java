package swp.group2.learninghub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Card {
    @Id
    private int id;
    private int columnId;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateEnd;
    private boolean isActive;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    //to resolved many to many
    public void addLabel(BoardLabel label) {
        if (labels == null) {
            labels = new ArrayList<>();
        }
        labels.add(label);
        label.addCard(this); // Add the card to the label's list of cards
    }

    public void removeLabel(BoardLabel label) {
        if (labels != null) {
            labels.remove(label);
            label.removeCard(this); // Remove the card from the label's list of cards
        }
    }
}
