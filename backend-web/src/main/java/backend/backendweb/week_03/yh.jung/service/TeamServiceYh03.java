package backend.backendweb.week_03.yh.jung.service;

import backend.backendweb.week_03._problem.entity.Team;
import backend.backendweb.week_03.yh.jung.dto.request.TeamCreateRequest;
import backend.backendweb.week_03.yh.jung.dto.response.TeamResponse;
import backend.backendweb.week_03._problem.repository.TeamRepository;
import backend.backendweb.week_03.yh.jung.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamServiceYh03 {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public TeamResponse createTeam(TeamCreateRequest request) {
        System.out.println("TeamService.createTeam called with: " + request);

/*
        Team team = Team.builder()
                .teamName(request.getTeamName())
                .projectCode(request.getProjectNumber() + "-" + request.getProjectNumber())
                .description(request.getDescription())
                .foundationDate(LocalDateTime.parse(request.getFoundationDate()))
                .build();
*/
        Team team = teamMapper.toTeam(request);

        Team savedTeam = teamRepository.save(team);

        return TeamResponse.fromEntity(savedTeam);
    }

    public TeamResponse getTeamById(Long id) {
        System.out.println("TeamService.getTeamById called with id: " + id);

        Team team = teamRepository.findById(id).orElse(null);
        if(team == null) return null;

/*
        return TeamResponse.fromEntity(team);
*/

        return teamMapper.toTeamResponse(team);
    }

    public List<TeamResponse> getAllTeams() {
        System.out.println("TeamService.getAllTeams called");

        List<Team> teams = teamRepository.findAll();

        return teamMapper.toTeamResponses(teams);
    }

    public TeamResponse updateTeam(Long id, TeamCreateRequest request) {
        System.out.println("TeamService.updateTeam called for id: " + id + " with data: " + request);

        Team team = teamRepository.findById(id).orElse(null);
        if(team == null) return null;
/*
        team.setTeamName(request.getTeamName());
        team.setProjectCode(request.getProjectNumber() + "-" + request.getProjectNumber());
        team.setDescription(request.getDescription());
        team.setFoundationDate(LocalDateTime.parse(request.getFoundationDate()));

        return TeamResponse.fromEntity(team);
*/
        teamMapper.updateTeamFromRequest(request, team);

        return teamMapper.toTeamResponse(team);
    }

}
