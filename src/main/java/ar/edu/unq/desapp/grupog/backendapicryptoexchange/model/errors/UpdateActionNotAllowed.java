package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;

public class UpdateActionNotAllowed extends Error {
    public UpdateActionNotAllowed(String action) {
        super("Unexpected value " + action,"The action is not allowed. Check action and user id","UPDATE_ACTION_NOT_ALLOWED");
    }
}
