package backend.backendweb.week_03.mk_jang.dto.response;

import backend.backendweb.week_03._problem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
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

    public static UserSimpleResponse from(User entity) {
        return UserSimpleResponse.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .build();
    }

    public static List<UserSimpleResponse> fromList(List<User> entities) {
        return entities.stream()
                .map(UserSimpleResponse::from)
                .toList();
    }

}
