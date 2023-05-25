package swp391.group2.learninghub.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePass {
    private String oldpass;
    private String verpass;
    private String newpass;
}
