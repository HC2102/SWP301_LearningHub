package swp391.group2.learninghub.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.group2.learninghub.Model.User;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User,String> {

    List<User> findByEmail(String name);
}
