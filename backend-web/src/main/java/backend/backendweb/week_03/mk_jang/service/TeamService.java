package backend.backendweb.week_03.mk_jang.service;

import backend.backendweb.week_03._problem.entity.Team;
import backend.backendweb.week_03._problem.repository.TeamRepository;
import backend.backendweb.week_03.mk_jang.dto.request.TeamCreateRequest;
import backend.backendweb.week_03.mk_jang.dto.response.TeamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("min-teamService-v1")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public TeamResponse createTeam(TeamCreateRequest request) {
        System.out.println("TeamService.createTeam called with: " + request);
        return TeamResponse.from(teamRepository.save(Team.toEntity(request)));
    }

    public TeamResponse getTeamById(Long id) {
        System.out.println("TeamService.getTeamById called with id: " + id);
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));
        return TeamResponse.from(team);
    }

    public List<TeamResponse> getAllTeams() {
        System.out.println("TeamService.getAllTeams called");
        List<Team> teams = teamRepository.findAll();
        return TeamResponse.fromList(teams);
    }

    @Transactional
    public TeamResponse updateTeam(Long id, TeamCreateRequest request) {
        System.out.println("TeamService.updateTeam called for id: " + id + " with data: " + request);
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));
        team.update(request);
        return TeamResponse.from(team);
    }

}
