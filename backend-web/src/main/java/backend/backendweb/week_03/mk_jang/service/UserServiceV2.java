package backend.backendweb.week_03.mk_jang.service;

import backend.backendweb.week_03._problem.entity.Team;
import backend.backendweb.week_03._problem.entity.User;
import backend.backendweb.week_03._problem.repository.TeamRepository;
import backend.backendweb.week_03._problem.repository.UserRepository;
import backend.backendweb.week_03.mk_jang.dto.request.UserCreateRequest;
import backend.backendweb.week_03.mk_jang.dto.response.UserResponse;
import backend.backendweb.week_03.mk_jang.dto.response.UserSimpleResponse;
import backend.backendweb.week_03.mk_jang.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("min-userService-v2")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceV2 {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        System.out.println("UserService.createUser called with: " + request);
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

//        User savedUser = userRepository.save(userMapper.toEntity(request, team));
//        return userMapper.toUserResponse(savedUser);
        return null;
    }

    public UserResponse getUserById(Long id) {
        System.out.println("UserService.getUserById called with id: " + id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return userMapper.toUserResponse(user);
    }

    public List<UserSimpleResponse> getAllUsers() {
        System.out.println("UserService.getAllUsers called");
        List<User> users = userRepository.findAll();
        return userMapper.toUserSimpleResponseList(users);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserCreateRequest request) {
        System.out.println("UserService.updateUser called for id: " + id + " with data: " + request);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userMapper.updateUserFromDto(request, user);
        return userMapper.toUserResponse(user);
    }

}
