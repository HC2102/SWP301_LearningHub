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
    @ManyToMany
    @JoinTable(name="CardLabel",
    joinColumns = @JoinColumn(name = "card_id"),
    inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private List<Card> cards = new ArrayList<>();
}
