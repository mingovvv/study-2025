package backend.backendweb.week_03.yh.jung.service;

import backend.backendweb.week_03.yh.jung.dto.request.UserCreateRequest;
import backend.backendweb.week_03.yh.jung.dto.response.UserResponse;
import backend.backendweb.week_03.yh.jung.dto.response.UserSimpleResponse;
import backend.backendweb.week_03._problem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceYh03 {

    private final UserRepository userRepository;

    public UserResponse createUser(UserCreateRequest request) {
        System.out.println("UserService.createUser called with: " + request);
        return null;
    }

    public UserResponse getUserById(Long id) {
        System.out.println("UserService.getUserById called with id: " + id);
        return null;
    }

    public List<UserSimpleResponse> getAllUsers() {
        System.out.println("UserService.getAllUsers called");
        return List.of();
    }

    public UserResponse updateUser(Long id, UserCreateRequest request) {
        System.out.println("UserService.updateUser called for id: " + id + " with data: " + request);
        return null;
    }

}
