package swp391.group2.learninghub.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391.group2.learninghub.Model.Feature;

public interface FeatureDAO extends JpaRepository<Feature,Integer> {
}
