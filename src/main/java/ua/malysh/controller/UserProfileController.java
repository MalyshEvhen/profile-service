package ua.malysh.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ua.malysh.domain.UserProfile;
import ua.malysh.service.UserProfileService;

@RestController
@RequestMapping("/api/v1/user-profiles")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService service;

    public ResponseEntity<Long> save(UserProfile profile) {
        return new ResponseEntity<>(service.save(profile), HttpStatus.CREATED);
    }

    @GetMapping("/by-user")
    public ResponseEntity<UserProfile> findByUserId(@RequestParam Long userId) {
        return new ResponseEntity<>(service.findByUserId(userId), HttpStatus.OK);
    }
}
