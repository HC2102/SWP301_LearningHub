package swp.group2.learninghub.model.clientModel;

import lombok.*;
import swp.group2.learninghub.model.Card;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardData {
    private String title;
    private List<Card> items;
}
