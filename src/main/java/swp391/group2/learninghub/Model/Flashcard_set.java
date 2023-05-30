package swp391.group2.learninghub.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Flashcard_set {
    @Id
    @
    private int id;
    private String user_id;
    private String title;
    private String description;
    private Date created_date;
    private boolean isActive;
    private boolean isLearned;




}
