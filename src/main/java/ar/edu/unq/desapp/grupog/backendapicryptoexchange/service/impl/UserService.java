package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        Spliterator<User> allUsers = userRepository.findAll().spliterator();
        return StreamSupport.stream(allUsers, false).toList();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}
