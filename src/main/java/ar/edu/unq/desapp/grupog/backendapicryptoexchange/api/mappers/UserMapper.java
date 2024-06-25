package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.mappers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.authentication.UserResponse;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class UserMapper {

    public static List<UserResponse> mapToUserResponses(List<User> users) {
        return users.stream().map(UserMapper::mapToUserResponse).toList();
    }

    public static UserResponse mapToUserResponse(User user) {
        return new UserResponse(user.getId(),
                user.getName() + " " + user.getLastname(),
                user.getEmail(),
                user.getAddress(),
                user.getCvu(),
                user.getWalletAddress()
        );
    }
}
