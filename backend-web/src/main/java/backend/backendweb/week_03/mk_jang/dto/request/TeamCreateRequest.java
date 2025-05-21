package backend.backendweb.week_03.mk_jang.dto.request;

import backend.backendweb.week_03._problem.entity.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamCreateRequest {

    /**
     * 팀 이름
     * - 매핑 대상: Team.teamName (String)
     * - 변환: 직접 매핑
     */
    private String teamName;

    /**
     * 프로젝트 코드 접두사 (예: "PJ")
     * - 매핑 대상: Team.projectCode (String) 의 일부
     * - 변환: projectNumber와 조합하여 Team.projectCode 생성 (예: "PJ-001")
     */
    private String projectPrefix;

    /**
     * 프로젝트 코드 번호 (예: "001")
     * - 매핑 대상: Team.projectCode (String) 의 일부
     * - 변환: projectPrefix와 조합하여 Team.projectCode 생성 (예: "PJ-001")
     */
    private String projectNumber;

    /**
     * 팀 설명
     * - 매핑 대상: Team.description (String)
     * - 변환: 직접 매핑
     */
    private String description;

    /**
     * 팀 창립일 (문자열 형식: "yyyy-MM-dd")
     * - 매핑 대상: Team.foundationDate (LocalDateTime)
     * - 변환: String("yyyy-MM-dd") -> LocalDate -> LocalDateTime (자정으로) 변환
     */
    private String foundationDate; // "yyyy-MM-dd"

    public static Team toEntity() {
        return null;
    }

}
