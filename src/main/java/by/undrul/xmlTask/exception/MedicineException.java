package by.undrul.xmlTask.exception;

public class MedicineException extends Exception {
    public MedicineException() {
        super();
    }

    public MedicineException(String message) {
        super(message);
    }

    public MedicineException(String message, Throwable cause) {
        super(message, cause);
    }

    public MedicineException(Throwable cause) {
        super(cause);
    }
}
