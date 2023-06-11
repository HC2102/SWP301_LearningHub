package swp391.group2.learninghub.model;

public class ResponseObject {
    private String status;
    private String message;
    private Object data;

    /*for response handle*/
    public ResponseObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
