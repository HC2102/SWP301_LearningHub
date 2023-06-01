package swp391.group2.learninghub.API;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import swp391.group2.learninghub.exception.NotFoundException;
import swp391.group2.learninghub.exception.InternalServerErrorException;



@Controller
@RequestMapping("/api/v1/error")
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public String handleError(Model model) {
        // Add your custom error handling logic here
        Exception exception = (Exception) model.getAttribute("jakarta.servlet.error.exception");
        int statusCode = (int) model.getAttribute("jakarta.servlet.error.status_code");

        String errorMessage;
        if (exception != null) {
            // Handle specific exceptions and set custom error message
            if (exception instanceof NotFoundException) {
                errorMessage = "Page not found";
            } else if (exception instanceof InternalServerErrorException) {
                errorMessage = "Internal server error";
            } else {
                errorMessage = "An error occurred";
            }
        } else {
            // Handle different status codes and set custom error message
            if (statusCode == 404) {
                errorMessage = "Page not found";
            } else if (statusCode == 500) {
                errorMessage = "Internal server error";
            } else {
                errorMessage = "An error occurred";
            }
        }

        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

}

