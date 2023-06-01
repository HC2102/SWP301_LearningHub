package swp391.group2.learninghub.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.group2.learninghub.DAO.FeatureDAO;
import swp391.group2.learninghub.Model.Feature;

import java.util.List;
import java.util.Optional;

@Service
public class FeatureServiceImpl implements FeatureService{
    @Autowired
    private final FeatureDAO featureDAO;

    @Autowired
    public FeatureServiceImpl(FeatureDAO featureDAO) {
        this.featureDAO = featureDAO;
    }

    @Override
    public List<Feature> showAll() {
        return featureDAO.findAll();
    }

    @Override
    public void setActive(int id,String mess) throws Exception {
        Feature feature = (Feature)featureDAO.findById(id).get();
        feature.setDescription(mess);
        System.out.println(feature.toString());
        if(feature == null){
            throw new Exception("item is null");
        }else{
            if(feature.isActive()){
                feature.setActive(false);
            }else{
                feature.setActive(true);
            }
            featureDAO.save(feature);
        }
    }

    @Override
    public Feature findFeatureById(int id) {
        Optional<Feature> f=featureDAO.findById(id);
        Optional<Feature> optionalFeature = featureDAO.findById(id);

        if (optionalFeature.isPresent()) {
            Feature feature = optionalFeature.get();
            return feature;
        } else {
            return null;
        }
    }
}
