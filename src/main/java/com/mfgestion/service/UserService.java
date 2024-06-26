package com.mfgestion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mfgestion.model.User;
import com.mfgestion.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        if (user.getId() == null) { // Nouvelle inscription
            user.setMdp(passwordEncoder.encode(user.getMdp()));
        } else { // Mise à jour
            Optional<User> existingUser = userRepository.findById(user.getId());
            if (existingUser.isPresent() && !existingUser.get().getMdp().equals(user.getMdp())) {
                // Si le mot de passe a changé, on l'encode
                user.setMdp(passwordEncoder.encode(user.getMdp()));
            }
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findByRole(User.Role role) {
        return userRepository.findByRole(role);
    }
}
