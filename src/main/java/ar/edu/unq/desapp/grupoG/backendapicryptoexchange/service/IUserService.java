package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    UserDetailsService userDetailsService();
}
