package backend.backendweb.week_03.yh.jung.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSimpleResponse {

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

    public static UserSimpleResponse fromEntity() {
        return null;
    }

}
