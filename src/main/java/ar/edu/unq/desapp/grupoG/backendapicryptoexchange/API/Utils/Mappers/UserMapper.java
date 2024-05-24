package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.UserResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;

import java.util.List;

public class UserMapper extends Mapper<User, UserResponse> {

    public List<UserResponse> mapToUserResponses(List<User> users) {
        return mapTo(users, UserMapper::mapToUserResponse);
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
