package backend.backendweb.week_03.yh.jung.mapper;

import backend.backendweb.week_03._problem.entity.Team;
import backend.backendweb.week_03._problem.entity.User;
import backend.backendweb.week_03.yh.jung.dto.request.UserCreateRequest;
import backend.backendweb.week_03.yh.jung.dto.response.UserResponse;
import backend.backendweb.week_03.yh.jung.dto.response.UserSimpleResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //User -> UserSimpleResponse
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "loginAttempts", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "birthDate", ignore = true)
    @Mapping(target = "team", ignore = true)
    UserSimpleResponse toUserSimpleResponse(User user);

    //List<User> -> List<UserSimpleResponse>
    List<UserSimpleResponse> toUserSimpleResponses(List<User> users);

    //UserCreateRequest -> User
    //@Mapping(target = "role", source = "request.role", qualifiedByName = "cmm_userRoleToEnum") request의 role이 String일 경우만 가능(enum 자체이면 에러남)
    @Mapping(target = "birthDate", source = "birthDate", qualifiedByName = "cmm_stringToDate")

    User toUser(UserCreateRequest request);

    //UserCreateRequest -> User 매핑 후 호출(파람에 일치하지 않는 타입이면 호출 X)

    //User -> UserResponse
    @Mapping(target = "fullName", source = "user", qualifiedByName = "generateFullName")
    @Mapping(target = "active", source = "isActive")
    @Mapping(target = "registrationDateFormatted", source = "registrationDate", qualifiedByName = "cmm_dateTimeToDateTimeString")
    @Mapping(target = "birthDateFormatted", source = "birthDate", qualifiedByName = "cmm_dateToDateString")
    @Mapping(target = "teamName", source = "team", qualifiedByName = "teamNameOrDefault")
    @Mapping(target = "currentLoginAttempts", source = "loginAttempts")
    UserResponse toUserResponse(User user);

    @Mapping(target = "birthDate", source = "birthDate", qualifiedByName = "cmm_stringToDate")
    void updateUserFromRequest(UserCreateRequest request, @MappingTarget User user);


    @AfterMapping
    static void defaultUserValue(UserCreateRequest request, @MappingTarget User user) {
        user.setActive(true);
        user.setLoginAttempts(0);
    }

    @Named("generateFullName")
    static String generateFullName(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }

    @Named("teamNameOrDefault")
    static String teamNameOrDefault(Team team) {
        return team != null ? team.getTeamName() : null;
    }


}
