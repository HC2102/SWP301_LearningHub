package swp.group2.learninghub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import swp.group2.learninghub.model.User;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User,String> {
    List<User> findByEmail(String name);

}
