package swp.group2.learninghub.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;
import swp.group2.learninghub.dao.CardAttachmentDAO;
import swp.group2.learninghub.model.clientModel.CompositeModel;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class CardAttachment {
    @Id
    private int id;
    private int cardId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedDate;
    private  String filename;
    private String url;
}
