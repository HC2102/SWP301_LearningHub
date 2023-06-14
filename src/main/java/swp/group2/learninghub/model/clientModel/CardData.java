package swp.group2.learninghub.model.clientModel;

import lombok.*;
import swp.group2.learninghub.model.BoardLabel;
import swp.group2.learninghub.model.Card;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CardData {
    public Card card;
    public List<BoardLabel> labels;
}
