
package com.rinesarusinovci.online_quizzes_vue_back.controllers;


import com.rinesarusinovci.online_quizzes_vue_back.dto.*;
import com.rinesarusinovci.online_quizzes_vue_back.entities.User;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.UserRepository;
import com.rinesarusinovci.online_quizzes_vue_back.security.AppUserDetails;
import com.rinesarusinovci.online_quizzes_vue_back.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto request) {
        var user = authenticationService.login(request.getEmail(), request.getPassword());
        var token = authenticationService.generateToken(user);

        var authResponse = new AuthResponse(token, 86400L);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody  RegisterUserDto request) {
        authenticationService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }


    @PutMapping("/update")
    public ResponseEntity<AuthResponse> updateProfile(@RequestBody @Valid UpdateUserDto dto,
                                                      @AuthenticationPrincipal AppUserDetails userDetails) {
        User updatedUser = authenticationService.updateProfile(dto, userDetails);
        UserDetails updatedDetails = userDetailsService.loadUserByUsername(updatedUser.getEmail());

        String newToken = authenticationService.generateToken(updatedDetails);
        return ResponseEntity.ok(new AuthResponse(newToken, 86400L));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(@AuthenticationPrincipal AppUserDetails userDetails) {
        authenticationService.deleteUser(userDetails.getUser().getId());
        return ResponseEntity.ok("Account deleted successfully");
    }
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordDto dto,
                                                 @AuthenticationPrincipal AppUserDetails userDetails) {
        authenticationService.changePassword(dto, userDetails);
        return ResponseEntity.ok("Password changed successfully");
    }
    @PostMapping("/upload-profile-picture")
    public ResponseEntity<String> uploadProfilePicture(@RequestParam("image") MultipartFile image,
                                                       @AuthenticationPrincipal AppUserDetails userDetails) throws IOException {
        User user = userDetails.getUser();
        String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path path = Paths.get("uploads/profile-images");

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        Path imagePath = path.resolve(filename);
        Files.write(imagePath, image.getBytes());

        user.setImagePath("/images/profile/" + filename);
        userRepository.save(user);

        return ResponseEntity.ok(user.getImagePath());
    }
    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal AppUserDetails userDetails) {
        User user = userDetails.getUser();
        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getSurname(), user.getBirthdate(), user.getUsername(), user.getEmail(), user.getRole(), user.getImagePath());
        return ResponseEntity.ok(userDto);
    }

}
