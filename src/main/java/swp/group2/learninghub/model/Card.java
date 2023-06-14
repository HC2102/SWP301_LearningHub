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

}
