package nl.novi.backendhuiswerktechiteasy.exceptions;

public class NameNotApproved extends RuntimeException {
    public NameNotApproved() {
        super();
    }

    public NameNotApproved(String message) {
        super(message);
    }
}
