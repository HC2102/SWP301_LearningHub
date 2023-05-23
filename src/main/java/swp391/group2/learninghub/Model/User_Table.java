package swp391.group2.learninghub.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="User")
public class User_Table  implements Serializable {
    @Id
    private String email;
   @Column(nullable = false)
    private String real_name;

    private String phone_num;
    @Column(nullable = false)
    private String role_id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean is_Active;
    @Column(nullable = false)
    private String signup_date;
}

