package swp391.group2.learninghub.Service;

import swp391.group2.learninghub.DAO.FeatureDAO;
import swp391.group2.learninghub.Model.Feature;

import java.util.List;

public interface FeatureService {
    public List<Feature> showAll();
    public void setActive(int id, String mess) throws Exception;
}
