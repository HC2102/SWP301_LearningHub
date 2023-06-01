package swp391.group2.learninghub.exception;

public class NotFoundException extends Exception {

    // Add any custom logic or properties for the exception
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Additional methods

    public String getCustomMessage() {
        return "Not found: " + getMessage();
    }
}
