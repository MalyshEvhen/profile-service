package ua.malysh.service;

import ua.malysh.domain.UserProfile;

public interface UserProfileService {

    Long save(UserProfile profile);

    UserProfile findByUserId(Long userId);

}
