package ua.malysh.service.impl;

import java.util.function.Supplier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ua.malysh.domain.UserProfile;
import ua.malysh.repository.UserProfileRepository;
import ua.malysh.service.DietCalculatorService;
import ua.malysh.service.UserProfileService;
import ua.malysh.service.exceptions.ProfileAlreadyExistsException;
import ua.malysh.service.exceptions.ProfileNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository repository;
    private final DietCalculatorService dietCalculatorService;

    @Override
    public Long save(UserProfile profile) {
        if (isUserHasProfile(profile)) {
            throw new ProfileAlreadyExistsException("Profile for this user is already exists.");
        }

        var diet = dietCalculatorService.getDiet(profile.getPhysique(), profile.getDiet().getType());
        profile.setDiet(diet);

        var savedProfile = repository.save(profile);

        return savedProfile.getId();
    }

    @Override
    public UserProfile findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(notFundExceptionSupplier());
    }

    private boolean isUserHasProfile(UserProfile profile) {
        return repository.existsByUsername(profile.getUsername());
    }

    private Supplier<? extends ProfileNotFoundException> notFundExceptionSupplier() {
        return () -> new ProfileNotFoundException("Profile with this ID not found.");
    }
}
