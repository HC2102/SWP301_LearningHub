package swp.group2.learninghub.model;

import jakarta.persistence.*;
import lombok.*;


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

}
