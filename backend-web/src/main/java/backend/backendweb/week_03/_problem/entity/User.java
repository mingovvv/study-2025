package backend.backendweb.week_03._problem.entity;

import backend.backendweb.week_03._problem.enums.UserRole;
import backend.backendweb.week_03.mk_jang.dto.request.UserCreateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "USERS")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private boolean isActive;
    private int loginAttempts;

    @Builder.Default
    private LocalDateTime registrationDate = LocalDateTime.now();
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public static User toEntity(UserCreateRequest request, Team team) {
        return User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(request.getRole())
                .birthDate(LocalDate.parse(request.getBirthDate()))
                .team(team)
                .isActive(true)
                .loginAttempts(0)
                .build();
    }


    public void update(UserCreateRequest request) {

        if (request.getUsername() != null) {
            this.username = request.getUsername();
        }
        if (request.getPassword() != null) {
            this.password = request.getPassword();
        }
        if (request.getEmail() != null) {
            this.email = request.getEmail();
        }
        if (request.getFirstName() != null) {
            this.firstName = request.getFirstName();
        }
        if (request.getLastName() != null) {
            this.lastName = request.getLastName();
        }
        if (request.getRole() != null) {
            this.role = request.getRole();
        }
        if (request.getBirthDate() != null) {
            this.birthDate = LocalDate.parse(request.getBirthDate());
        }

    }

}
