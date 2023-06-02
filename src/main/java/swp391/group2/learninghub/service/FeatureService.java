package swp391.group2.learninghub.service;

import swp391.group2.learninghub.model.Feature;

import java.util.List;

public interface FeatureService {
    public List<Feature> showAll();
    public void setActive(int id, String mess) throws Exception;
    public Feature findFeatureById(int id);
}
