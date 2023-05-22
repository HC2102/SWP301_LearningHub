package swp391.group2.learninghub.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.group2.learninghub.Model.User;

@Repository
public interface UserDAO extends JpaRepository<User,String> {
}
