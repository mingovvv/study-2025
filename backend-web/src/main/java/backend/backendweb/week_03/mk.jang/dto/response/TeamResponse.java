package backend.backendweb.week_03.mk.jang.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamResponse {

    /**
     * 팀 고유 ID
     * - 매핑 대상: Team.id (Long)
     * - 변환: 직접 매핑
     */
    private Long id;

    /**
     * 팀 이름
     * - 매핑 대상: Team.teamName (String)
     * - 변환: 직접 매핑
     */
    private String teamName;

    /**
     * 프로젝트 코드
     * - 매핑 대상: Team.projectCode (String)
     * - 변환: 직접 매핑 (엔티티의 projectCode 값을 그대로 사용)
     */
    private String projectCode;

    /**
     * 팀 설명 (null일 경우 기본값 "No description provided." 사용)
     * - 매핑 대상: Team.description (String)
     * - 변환: team.getDescription() != null ? team.getDescription() : "No description provided."
     */
    private String descriptionOrDefault;

    /**
     * 팀 생성일 (문자열 형식: "yyyy-MM-dd HH:mm:ss")
     * - 매핑 대상: Team.createdAt (LocalDateTime)
     * - 변환: LocalDateTime -> String("yyyy-MM-dd HH:mm:ss") 포맷팅
     */
    private String createdAtFormatted;

    /**
     * 팀 창립일 (문자열 형식: "yyyy-MM-dd")
     * - 매핑 대상: Team.foundationDate (LocalDateTime)
     * - 변환: LocalDateTime -> String("yyyy-MM-dd") 포맷팅 (날짜 부분만)
     */
    private String foundationDateFormatted;

    /**
     * 팀 멤버 수
     * - 매핑 대상: Team.members (List<User>)
     * - 변환: team.getMembers() != null ? team.getMembers().size() : 0
     */
    private int memberCount;

    /**
     * 팀 멤버 목록 (간략 정보)
     * - 매핑 대상: Team.members (List<User>)
     * - 변환: List<User> -> List<UserSimpleResponse> (각 User 객체를 UserSimpleResponse로 변환)
     */
    private List<UserSimpleResponse> members;

    public static TeamResponse fromEntity() {
        return null;
    }

}
