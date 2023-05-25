package swp391.group2.learninghub.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import swp391.group2.learninghub.Model.User;
import swp391.group2.learninghub.Model.UserAuthorization;
import swp391.group2.learninghub.Service.UserAuthorizationService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api")
public class AuthController {

    private final UserAuthorizationService userAuthorizationService;

    @Autowired
    public AuthController(UserAuthorizationService userAuthorizationService) {
        this.userAuthorizationService = userAuthorizationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        // Perform authentication logic
        // ...

        // Set session attributes
        session.setAttribute("email", email);
        session.setAttribute("role", "user");

        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("/changepassword")
    public ResponseEntity<String> changePassword(@RequestParam String currentPassword, @RequestParam String newPassword, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            // Perform change password logic
            // ...

            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }
    }

    @GetMapping("/userprofile")
    public ResponseEntity<String> getUserProfile(HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            // Retrieve user profile information
            // ...

            return ResponseEntity.ok("User profile information");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }
    }

    @GetMapping("/disablefunctions")
    public ResponseEntity<String> disableFunctions(HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (role != null && role.equals("admin")) {
            // Disable functions in the app
            // ...

            return ResponseEntity.ok("Functions disabled");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
    }

    // Add other API methods for user-related operations

}
