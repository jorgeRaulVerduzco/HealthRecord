/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthrecord.service;

/**
 *
 * @author JORGE
 */
import com.mycompany.healthrecord.entities.User;
import com.mycompany.healthrecord.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que maneja la lógica de negocio relacionada con los usuarios. Actúa
 * como intermediario entre los controladores y la capa de acceso a datos.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo,
            PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public Optional<User> getUserByCurp(String curp) {
        return userRepo.findByCurp(curp);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User updateUser(Long id, User updated) {
        return userRepo.findById(id)
                .map(existing -> {
                    existing.setName(updated.getName());
                    existing.setLastName(updated.getLastName());
                    if (!existing.getPassword().equals(updated.getPassword())) {
                        existing.setPassword(passwordEncoder.encode(updated.getPassword()));
                    }
                    existing.setAge(updated.getAge());
                    // ...otros campos si aplica
                    return userRepo.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public Optional<User> authenticateUser(String curp, String rawPassword) {
        return userRepo.findByCurp(curp)
                .filter(u -> passwordEncoder.matches(rawPassword, u.getPassword()));
    }

    public Optional<User> authenticateWithBiometrics(String biometricData) {
        return userRepo.findByBiometricData(biometricData);
    }
}
