package swp391.group2.learninghub.DAO;

import swp391.group2.learninghub.Model.UserAuthorization;

public interface UserAuthorizationRepository {
    UserAuthorization findByEmail(String email);

    // Add other custom methods for user authorization-related operations
}