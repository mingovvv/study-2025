package backend.backendweb.week_03.yh.jung.dto.response;

import backend.backendweb.week_03._problem.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    /**
     * 사용자 고유 ID
     * - 매핑 대상: User.id (Long)
     * - 변환: 직접 매핑
     */
    private Long id;

    /**
     * 사용자 아이디
     * - 매핑 대상: User.username (String)
     * - 변환: 직접 매핑
     */
    private String username;

    /**
     * 사용자 이메일
     * - 매핑 대상: User.email (String)
     * - 변환: 직접 매핑
     */
    private String email;

    /**
     * 사용자 전체 이름 (이름 + " " + 성)
     * - 매핑 대상: User.firstName, User.lastName (String)
     * - 변환: user.getFirstName() + " " + user.getLastName()
     */
    private String fullName;

    /**
     * 사용자 역할
     * - 매핑 대상: User.role (UserRole Enum)
     * - 변환: 직접 매핑
     */
    private UserRole role;

    /**
     * 사용자 활성 상태 (User 엔티티의 isActive 필드)
     * - 매핑 대상: User.isActive (boolean)
     * - 변환: 필드명 변경 (isActive -> active), 값은 직접 매핑
     */
    private boolean active;

    /**
     * 사용자 등록일 (문자열 형식: "yyyy-MM-dd HH:mm:ss")
     * - 매핑 대상: User.registrationDate (LocalDateTime)
     * - 변환: LocalDateTime -> String("yyyy-MM-dd HH:mm:ss") 포맷팅
     */
    private String registrationDateFormatted;

    /**
     * 사용자 생년월일 (문자열 형식: "yyyy-MM-dd")
     * - 매핑 대상: User.birthDate (LocalDate)
     * - 변환: LocalDate -> String("yyyy-MM-dd") 포맷팅
     */
    private String birthDateFormatted;

    /**
     * 사용자가 속한 팀의 이름
     * - 매핑 대상: User.team.teamName (String, 중첩 객체 필드)
     * - 변환: user.getTeam() != null ? user.getTeam().getTeamName() : null
     */
    private String teamName;

    /**
     * 현재 로그인 시도 횟수 (User 엔티티의 loginAttempts 필드)
     * - 매핑 대상: User.loginAttempts (int)
     * - 변환: 필드명 변경 (loginAttempts -> currentLoginAttempts), 값은 직접 매핑
     */
    private int currentLoginAttempts;

    public static UserResponse fromEntity() {
        return null;
    }

}
