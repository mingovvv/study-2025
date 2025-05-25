package backend.backendweb.week_03.yh.jung.dto.response;

import backend.backendweb.week_03._problem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
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

    public static UserSimpleResponse fromEntity(User user) {
        return new UserSimpleResponse(user.getId(), user.getUsername(), user.getEmail());
    }

}
