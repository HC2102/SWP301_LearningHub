package swp.group2.learninghub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Board {
    @Id
    private int id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
    private int noteId;
    private boolean isActive;
}
