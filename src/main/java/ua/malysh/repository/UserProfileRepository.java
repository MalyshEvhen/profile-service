package ua.malysh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.malysh.domain.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    @Query("select (count(u) > 0) from UserProfile u where u.username = ?1")
    boolean existsByUsername(String username);

    @Query("select u from UserProfile u where u.username = ?1")
    Optional<UserProfile> findByUsername(String username);
}
