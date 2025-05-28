/*
package backend.backendweb.week_03.yh.jung.mapper;

import backend.backendweb.week_03._problem.entity.Team;
import backend.backendweb.week_03.yh.jung.dto.request.TeamCreateRequest;
import backend.backendweb.week_03.yh.jung.dto.response.TeamResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TeamMapperImpl implements TeamMapper {

    @Override
    public Team toTeam(TeamCreateRequest request, Team team) {
        if(request == null) return null;

        if(isNotEmpty(request.getTeamName())) {
            team.setTeamName(request.getTeamName());
        }

        team.setProjectCode(request.getProjectPrefix() + "-" + request.getProjectNumber());
        team.setDescription(request.getDescription());
        team.setFoundationDate(LocalDateTime.parse(request.getFoundationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return team;
    }

    @Override
    public TeamResponse toTeamResponse(Team team) {
        if(team == null) return null;

        return TeamResponse.fromEntity(team);
    }

    @Override
    public List<TeamResponse> toTeamResponses(List<Team> teams) {


        return List.of();
    }
}
*/
