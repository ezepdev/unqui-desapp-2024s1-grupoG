package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;

public class UserNotAuthorized extends Error {
    public UserNotAuthorized() {
        super("Unexpected user id","The user is not authorized to perform this action","USER_NOT_AUTHORIZED");
    }
}
