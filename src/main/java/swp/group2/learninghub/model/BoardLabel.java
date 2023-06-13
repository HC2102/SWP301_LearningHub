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
}
