package swp391.group2.learninghub.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.group2.learninghub.DAO.UserAuthorizationRepository;
import swp391.group2.learninghub.Model.UserAuthorization;

@Service
public class UserAuthorizationService {

    private final UserAuthorizationRepository userAuthorizationRepository;

    @Autowired
    public UserAuthorizationService(UserAuthorizationRepository userAuthorizationRepository) {
        this.userAuthorizationRepository = userAuthorizationRepository;
    }

    public UserAuthorization getUserAuthorizationByEmail(String email) {
        return userAuthorizationRepository.findByEmail(email);
    }

    // Add other methods for user authorization-related operations

}
