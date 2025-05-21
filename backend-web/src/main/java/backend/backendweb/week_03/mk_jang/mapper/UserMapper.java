package backend.backendweb.week_03.mk_jang.mapper;

import backend.backendweb.week_03._problem.entity.Team;
import backend.backendweb.week_03._problem.entity.User;
import backend.backendweb.week_03.mk_jang.dto.request.UserCreateRequest;
import backend.backendweb.week_03.mk_jang.dto.response.UserResponse;
import backend.backendweb.week_03.mk_jang.dto.response.UserSimpleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", uses = TeamMapper.class)
public interface UserMapper {

    // UserCreateRequest -> User 엔티티
    @Mapping(target = "id", ignore = true) // id는 자동 생성되므로 매핑 무시
    @Mapping(target = "team", source = "teamEntity") // UserCreateRequest에는 teamId만 있으므로, 서비스에서 조회한 Team 엔티티를 직접 받아서 매핑
//    @Mapping(target = "registrationDate", expression = "java(java.time.LocalDateTime.now())") // 등록일은 현재 시간으로 설정
    @Mapping(target = "active", constant = "true") // 새로운 사용자는 기본적으로 활성 상태
    @Mapping(target = "loginAttempts", constant = "0") // 초기 로그인 시도 횟수는 0
    @Mapping(source = "request.birthDate", target = "birthDate", qualifiedByName = "stringToLocalDate") // 문자열 생년월일을 LocalDate로 변환
    User toEntity(UserCreateRequest request, Team teamEntity); // teamId 대신 Team 객체를 받음

    // User -> UserResponse
    // fullName은 User의 firstName과 lastName을 조합해야 하므로 expression 사용
    @Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    @Mapping(source = "registrationDate", target = "registrationDateFormatted", qualifiedByName = "localDateTimeToString")
    @Mapping(source = "birthDate", target = "birthDateFormatted", qualifiedByName = "localDateToString")
    @Mapping(source = "team.teamName", target = "teamName") // User 엔티티의 team 객체의 teamName 필드를 UserResponse의 teamName으로 매핑
    @Mapping(source = "loginAttempts", target = "currentLoginAttempts") // 필드명 변경 매핑
    @Mapping(source = "isActive", target = "active") // User.isActive() -> UserResponse.active (boolean 필드는 getter가 isXxx 형태일 수 있음)
    UserResponse toUserResponse(User user);

    // User -> UserSimpleResponse (필드명이 대부분 일치하므로 간단)
    UserSimpleResponse toUserSimpleResponse(User user);

    List<UserSimpleResponse> toUserSimpleResponseList(List<User> users);

    // UserCreateRequest 정보를 기존 User 엔티티에 업데이트
    @Mapping(target = "id", ignore = true) // ID는 업데이트 대상 아님
    @Mapping(target = "team", ignore = true) // 팀 변경은 별도 로직으로 처리하거나, 필요시 명시적 매핑 추가
    @Mapping(target = "registrationDate", ignore = true) // 등록일은 보통 업데이트하지 않음
    @Mapping(source = "request.birthDate", target = "birthDate", qualifiedByName = "stringToLocalDate")
    // 비밀번호는 암호화 등 특별한 처리가 필요할 수 있으므로 서비스 레이어에서 별도 처리 권장
    // 여기서는 MapStruct가 다른 필드를 업데이트하도록 함
    void updateUserFromDto(UserCreateRequest request, @MappingTarget User user);

    @Named("localDateTimeToString")
    default String localDateTimeToString(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Named("localDateToString")
    default String localDateToString(LocalDate date) {
        if (date == null) return null;
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) return null;
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
