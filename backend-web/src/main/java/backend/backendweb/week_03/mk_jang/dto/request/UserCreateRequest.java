package backend.backendweb.week_03.mk_jang.dto.request;

import backend.backendweb.week_03._problem.entity.User;
import backend.backendweb.week_03._problem.enums.UserRole;
import lombok.Getter;

@Getter
public class UserCreateRequest {

    /**
     * 사용자 아이디
     * - 매핑 대상: User.username (String)
     * - 변환: 직접 매핑
     */
    private String username;

    /**
     * 사용자 비밀번호
     * - 매핑 대상: User.password (String)
     * - 변환: 직접 매핑 (서비스 레이어에서 암호화 처리 필요)
     */
    private String password;

    /**
     * 사용자 이메일
     * - 매핑 대상: User.email (String)
     * - 변환: 직접 매핑
     */
    private String email;

    /**
     * 사용자 이름 (First Name)
     * - 매핑 대상: User.firstName (String)
     * - 변환: 직접 매핑
     */
    private String firstName;

    /**
     * 사용자 성 (Last Name)
     * - 매핑 대상: User.lastName (String)
     * - 변환: 직접 매핑
     */
    private String lastName;

    /**
     * 사용자 역할
     * - 매핑 대상: User.role (UserRole Enum)
     * - 변환: 직접 매핑 (String으로 받을 경우 Enum으로 변환 필요)
     */
    private UserRole role;

    /**
     * 사용자 생년월일 (문자열 형식: "yyyy-MM-dd")
     * - 매핑 대상: User.birthDate (LocalDate)
     * - 변환: String("yyyy-MM-dd") -> LocalDate 객체로 변환
     */
    private String birthDate;

    /**
     * 사용자가 속한 팀의 ID
     * - 매핑 대상: User.team (Team Entity)
     * - 변환: Long teamId -> 서비스 레이어에서 Team 엔티티 조회 후 User.team에 설정
     */
    private Long teamId;

    public static User toEntity() {
        return null;
    }

}