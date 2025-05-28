package backend.backendweb.week_03.yh.jung.mapper;

import backend.backendweb.week_03._problem.entity.Team;
import backend.backendweb.week_03._problem.entity.User;
import backend.backendweb.week_03.yh.jung.dto.request.TeamCreateRequest;
import backend.backendweb.week_03.yh.jung.dto.response.TeamResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

//componentModel : bean 주입
@Mapper(componentModel = "spring", uses = {CommonMapper.class, UserMapper.class})
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    //TeamCreateRequest -> Team
    //@Mapping(target = "projectCode", expression = "java(request.getProjectPrefix() + \"-\" + request.getProjectNumber())")
    //source는 단 1개의 파람만 가능
    @Mapping(target = "projectCode", source = "request", qualifiedByName = "generateProjectCode")
    @Mapping(target = "foundationDate", source = "request.foundationDate", qualifiedByName = "cmm_stringToDateTime")
    @Mapping(target = "members", ignore = true)
    Team toTeam(TeamCreateRequest request);

    //Team -> TeamResponse
    @Mapping(target = "descriptionOrDefault", source = "team.description", qualifiedByName = "defaultDescription")
    @Mapping(target = "createdAtFormatted", source = "createdAt", qualifiedByName = "cmm_dateTimeToDateTimeString")
    @Mapping(target = "foundationDateFormatted", source = "foundationDate", qualifiedByName = "cmm_dateTimeToDateString")
    @Mapping(target = "memberCount", source = "team.users", qualifiedByName = "defaultMemberCount")
    @Mapping(target = "members", source = "members") // User → UserSimpleResponse 매핑 필요(uses 설정 추가)
    TeamResponse toTeamResponse(Team team);

    //List<Team> -> List<TeamResponse>
    //위 toTeamResponse()의 @Mapping 설정 공유
    List<TeamResponse> toTeamResponses(List<Team> teams);
    /* 자동 구현
    @Override
    public List<TeamResponse> toTeamResponses(List<Team> teams) {
        if (teams == null) {
            return null;
        }

        List<TeamResponse> list = new ArrayList<>(teams.size());
        for (Team team : teams) {
            list.add(toTeamResponse(team));
        }
        return list;
    }
    */

    @Mapping(target = "projectCode", source = "request", qualifiedByName = "generateProjectCode")
    @Mapping(target = "foundationDate", source = "request.foundationDate", qualifiedByName = "cmm_stringToDateTime")
    @Mapping(target = "members", ignore = true)
    void updateTeamFromRequest(TeamCreateRequest request, @MappingTarget Team team);


    /*
        default 메서드는 매퍼 인스턴스와 연관된 상태나 다른 인스턴스 메서드를 활용할 때 쓰는 게 좋고,
        static은 순수 변환 유틸리티 함수일 때 더 권장됩니다.

        // 매퍼 내에서 공유하는 상수나 상태 (예: 접두사)
        String PREFIX = "PJ-";

        // 다른 인스턴스 메서드를 호출하는 default 메서드
        default String generateProjectCode(TeamCreateRequest request) {
            // default 메서드 내에서 또 다른 인스턴스 메서드 호출 가능
            return PREFIX + "-" + formatNumber(request.getProjectNumber());
        }

        // 포맷팅을 담당하는 또 다른 인스턴스 메서드
        default String formatNumber(int number) {
            return String.format("%03d", number); // 예: 1 -> "001"
        }
    */
    @Named("defaultDescription")
    static String defaultDescription(String description) {
        return description != null ? description : "No description provided.";
    }

    @Named("defaultMemberCount")
    static int defaultMemberCount(List<User> users) {
        return users != null ? users.size() : 0;
    }

    @Named("generateProjectCode")
    static String generateProjectCode(TeamCreateRequest request) {
        return request.getProjectPrefix() + "-" + request.getProjectNumber();
    }



}
