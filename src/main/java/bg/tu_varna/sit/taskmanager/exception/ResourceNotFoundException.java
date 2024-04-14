package bg.tu_varna.sit.taskmanager.exception;

public class ResourceNotFoundException extends RuntimeException {
    private String resource;
    private String property;
    private String propertyValue;

    public ResourceNotFoundException(String resource, String property, String propertyValue) {
        super("Not found " + resource + " with " + property + " = " + propertyValue);
        this.resource = resource;
        this.property = property;
        this.propertyValue = propertyValue;
    }
}
