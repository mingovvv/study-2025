package backend.backendweb.week_03.mk_jang.mapper;

import backend.backendweb.week_03._problem.entity.Team;
import backend.backendweb.week_03._problem.entity.User;
import backend.backendweb.week_03.mk_jang.dto.request.TeamCreateRequest;
import backend.backendweb.week_03.mk_jang.dto.response.TeamResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class) // List<UserSimpleResponse> 매핑을 위해 UserMapper 사용
public interface TeamMapper {

    // TeamCreateRequest -> Team 엔티티
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "members", ignore = true) // 멤버는 팀 생성 후 별도로 추가/관리
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())") // 생성 시간은 현재 시간
    @Mapping(target = "projectCode", source = "request", qualifiedByName = "buildProjectCode") // projectPrefix와 projectNumber 조합
    @Mapping(source = "request.foundationDate", target = "foundationDate", qualifiedByName = "stringToLocalDateTimeAtStartOfDay") // "yyyy-MM-dd" -> LocalDateTime (자정)
    Team toEntity(TeamCreateRequest request);

    // Team -> TeamResponse
    @Mapping(source = "description", target = "descriptionOrDefault", qualifiedByName = "mapDescriptionOrDefault") // null일 경우 기본값 처리
    @Mapping(source = "createdAt", target = "createdAtFormatted", qualifiedByName = "localDateTimeToFormattedStringForTeam") // 날짜 포맷팅
    @Mapping(source = "foundationDate", target = "foundationDateFormatted", qualifiedByName = "localDateFromLocalDateTimeToFormattedString") // foundationDate는 LocalDateTime -> "yyyy-MM-dd"
    @Mapping(source = "members", target = "memberCount", qualifiedByName = "mapMemberCount") // 멤버 수 계산
    @Mapping(source = "members", target = "members") // List<User> -> List<UserSimpleResponse> (UserMapper가 처리)
    TeamResponse toTeamResponse(Team team);

    List<TeamResponse> toTeamResponseList(List<Team> teams);

    // TeamCreateRequest 정보를 기존 Team 엔티티에 업데이트
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "members", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "projectCode", source = "request", qualifiedByName = "buildProjectCode")
    @Mapping(source = "request.foundationDate", target = "foundationDate", qualifiedByName = "stringToLocalDateTimeAtStartOfDay")
    void updateTeamFromDto(TeamCreateRequest request, @MappingTarget Team team);


    // --- QualifiedByName으로 호출될 커스텀 매핑 메소드들 ---

    @Named("buildProjectCode")
    default String buildProjectCode(TeamCreateRequest request) {
        if (request.getProjectPrefix() == null || request.getProjectNumber() == null) {
            return null;
        }
        return request.getProjectPrefix() + "-" + request.getProjectNumber();
    }

    @Named("stringToLocalDateTimeAtStartOfDay")
    default LocalDateTime stringToLocalDateTimeAtStartOfDay(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        // "yyyy-MM-dd" 문자열을 LocalDate로 파싱 후, 자정(00:00:00) 시간으로 LocalDateTime 생성
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
    }

    @Named("mapDescriptionOrDefault")
    default String mapDescriptionOrDefault(String description) {
        return description != null ? description : "No description provided.";
    }

    @Named("localDateTimeToFormattedStringForTeam") // TeamResponse용 createdAt 포맷터
    default String localDateTimeToFormattedStringForTeam(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Named("localDateFromLocalDateTimeToFormattedString") // Team.foundationDate (LocalDateTime) -> String ("yyyy-MM-dd")
    default String localDateFromLocalDateTimeToFormattedString(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Named("mapMemberCount")
    default int mapMemberCount(List<User> members) {
        return members != null ? members.size() : 0;
    }

}
