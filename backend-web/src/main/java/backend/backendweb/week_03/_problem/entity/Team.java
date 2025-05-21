package backend.backendweb.week_03._problem.entity;

import backend.backendweb.week_03.mk_jang.dto.request.TeamCreateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "TEAM")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private String projectCode; // 예: "PJ-001", 고급 매핑용 (분리 또는 조합)
    private String description;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime foundationDate; // 팀 창립일 (Date <-> String 변환용)

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<User> members = new ArrayList<>();

    public static Team toEntity(TeamCreateRequest dto) {
        return Team.builder()
                .teamName(dto.getTeamName())
                .projectCode(dto.getProjectPrefix() + "-" + dto.getProjectNumber())
                .description(dto.getDescription())
                .foundationDate(LocalDate.parse(dto.getFoundationDate()).atStartOfDay())
                .build();
    }

    public void update(TeamCreateRequest request) {
        if (request.getTeamName() != null) {
            this.teamName = request.getTeamName();
        }
        if (request.getProjectPrefix() != null && request.getProjectNumber() != null) {
            this.projectCode = request.getProjectPrefix() + "-" + request.getProjectNumber();
        }
        if (request.getDescription() != null) {
            this.description = request.getDescription();
        }
        if (request.getFoundationDate() != null) {
            this.foundationDate = LocalDate.parse(request.getFoundationDate()).atStartOfDay();
        }
    }

}
