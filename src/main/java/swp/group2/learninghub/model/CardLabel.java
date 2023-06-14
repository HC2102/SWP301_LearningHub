package swp.group2.learninghub.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class CardLabel {
    @Id
    private int id;
    @ManyToOne
    private BoardLabel boardLabel;

    @ManyToOne
    private Card card;
}
