package swp391.group2.learninghub.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.group2.learninghub.DAO.FeatureDAO;
import swp391.group2.learninghub.Model.Feature;

import java.util.List;

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
            if(feature.isIs_Active()){
                feature.setIs_Active(false);
            }else{
                feature.setIs_Active(true);
            }
            featureDAO.save(feature);
        }
    }
}
