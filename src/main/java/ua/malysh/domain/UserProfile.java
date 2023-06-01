package ua.malysh.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_profiles",
       indexes = {
            @Index(name = "idx_user_profiles_user_id", columnList = "user_id")})
@NoArgsConstructor
@Setter @Getter
public class UserProfile {

    @Id
    @Column(name = "profile_id",
            nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "user_id",
            nullable = false,
            unique = true)
    private Long userId;

    @Column(name = "profile_name",
            nullable = false)
    private String name;

    @Embedded
    private Physique physique;

    @Embedded
    private Diet diet = Diet.getDefault();

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    public void setDietType(DietType type) {
        this.diet.setType(type);
    }

    public void setActivityLevel(ActivityLevel level) {
        this.physique.setActivityLevel(level);
    }

    public void setWeight(Double weight) {
        this.physique.setWeight(weight);
    }
}
