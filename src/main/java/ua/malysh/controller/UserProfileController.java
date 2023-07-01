package ua.malysh.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import ua.malysh.domain.UserProfile;
import ua.malysh.service.UserProfileService;

@RestController
@RequestMapping("/api/v1/user-profiles")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService service;

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody UserProfile profile) {
        return new ResponseEntity<>(service.save(profile), HttpStatus.CREATED);
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<UserProfile> findByUserId(@PathVariable String username) {
        return new ResponseEntity<>(service.findByUsername(username), HttpStatus.OK);
    }
}
