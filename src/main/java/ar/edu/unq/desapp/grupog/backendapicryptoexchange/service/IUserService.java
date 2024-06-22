package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    UserDetailsService userDetailsService();
}
