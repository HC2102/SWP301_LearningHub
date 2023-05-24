package swp391.group2.learninghub.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.group2.learninghub.Service.FeatureService;

@RestController
@RequestMapping("api/v1/feature")
public class FeatureController {
    @Autowired
    private FeatureService service;

}
