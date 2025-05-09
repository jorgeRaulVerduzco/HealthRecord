/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthrecord.Controller;

import com.mycompany.healthrecord.entities.User;
import com.mycompany.healthrecord.service.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para operaciones relacionadas con usuarios. Proporciona
 * endpoints para crear, leer, actualizar y eliminar usuarios, así como para
 * autenticación.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User created = userService.createUser(user);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.userId")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/curp/{curp}")
    public ResponseEntity<User> getByCurp(@PathVariable String curp) {
        return userService.getUserByCurp(curp)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> listAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.userId")
    public ResponseEntity<User> update(
            @PathVariable Long id,
            @Valid @RequestBody User user) {
        try {
            User updated = userService.updateUser(id, user);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> creds) {
        Optional<ResponseEntity<?>> opt = userService
                .authenticateUser(creds.get("curp"), creds.get("password"))
                .map(u -> {
                    Map<String, Object> resp = new HashMap<>();
                    resp.put("user", u);
                    resp.put("token", "JWT-aquí");
                    return ResponseEntity.ok(resp);           // esto es ResponseEntity<Map<String,Object>>
                });

        return opt.orElseGet(()
                -> // ahora Optional<ResponseEntity<?>>
                ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Credenciales inválidas") // esto es ResponseEntity<String>
        );
    }

    @PostMapping("/login/biometric")
    public ResponseEntity<?> loginBiometric(@RequestBody Map<String, String> body) {
        Optional<ResponseEntity<?>> opt = userService
                .authenticateWithBiometrics(body.get("biometricData"))
                .map(u -> {
                    Map<String, Object> resp = new HashMap<>();
                    resp.put("user", u);
                    resp.put("token", "JWT-aquí");
                    return ResponseEntity.ok(resp);
                });

        return opt.orElseGet(()
                -> ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Datos biométricos inválidos")
        );
    }
}
