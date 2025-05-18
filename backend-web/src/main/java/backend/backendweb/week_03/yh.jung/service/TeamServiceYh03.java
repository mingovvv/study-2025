package backend.backendweb.week_03.yh.jung.service;

import backend.backendweb.week_03.yh.jung.dto.request.TeamCreateRequest;
import backend.backendweb.week_03.yh.jung.dto.response.TeamResponse;
import backend.backendweb.week_03._problem.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceYh03 {

    private final TeamRepository teamRepository;

    public TeamResponse createTeam(TeamCreateRequest request) {
        System.out.println("TeamService.createTeam called with: " + request);
        return null;
    }

    public TeamResponse getTeamById(Long id) {
        System.out.println("TeamService.getTeamById called with id: " + id);
        return null;
    }

    public List<TeamResponse> getAllTeams() {
        System.out.println("TeamService.getAllTeams called");
        return List.of();
    }

    public TeamResponse updateTeam(Long id, TeamCreateRequest request) {
        System.out.println("TeamService.updateTeam called for id: " + id + " with data: " + request);
        return null;
    }

}
