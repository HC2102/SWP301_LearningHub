package swp391.group2.learninghub.exception;

public class InternalServerErrorException extends Exception {

    // Add any custom logic or properties for the exception
    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    // Additional methods

    public String getCustomMessage() {
        return "Internal server error: " + getMessage();
    }
}
