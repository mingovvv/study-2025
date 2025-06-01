//package backend.backendweb.week_03.yh.jung.service;
//
//import backend.backendweb.week_03._problem.entity.Team;
//import backend.backendweb.week_03._problem.entity.User;
//import backend.backendweb.week_03._problem.repository.TeamRepository;
//import backend.backendweb.week_03.yh.jung.dto.request.UserCreateRequest;
//import backend.backendweb.week_03.yh.jung.dto.response.UserResponse;
//import backend.backendweb.week_03.yh.jung.dto.response.UserSimpleResponse;
//import backend.backendweb.week_03._problem.repository.UserRepository;
//import backend.backendweb.week_03.yh.jung.mapper.UserMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceYh03 {
//    private final TeamRepository teamRepository;
//
//    private final UserRepository userRepository;
//    private final UserMapper userMapper;
//
//    public UserResponse createUser(UserCreateRequest request) {
//        System.out.println("UserService.createUser called with: " + request);
//
//        Team team = teamRepository.findById(request.getTeamId()).orElse(null);
//        if(team == null) return null;
//
//        /*User user = User.builder()
//                .username(request.getUsername())
//                .password(request.getPassword())
//                .email(request.getEmail())
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .role(request.getRole())
//                .isActive(true)
//                .loginAttempts(0)
//                .birthDate(LocalDate.parse(request.getBirthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
//                .team(team)
//                .build();*/
//
//        User user = userMapper.toUser(request);
//        user.setTeam(team); // mapper에서 넣을 수 없음
//
//        User savedUser = userRepository.save(user);
//
//        return UserResponse.fromEntity(savedUser);
//    }
//
//    public UserResponse getUserById(Long id) {
//        System.out.println("UserService.getUserById called with id: " + id);
//
//        User user = userRepository.findById(id).orElse(null);
//        if(user == null) return null;
//
////        return UserResponse.fromEntity(user);
//        return userMapper.toUserResponse(user);
//    }
//
//    public List<UserSimpleResponse> getAllUsers() {
//        System.out.println("UserService.getAllUsers called");
//
//        List<User> users = userRepository.findAll();
//
///*
//        return users.stream()
//                .map(UserSimpleResponse::fromEntity)
//                .collect(Collectors.toList());
//*/
//        return userMapper.toUserSimpleResponses(users);
//    }
//
//    public UserResponse updateUser(Long id, UserCreateRequest request) {
//        System.out.println("UserService.updateUser called for id: " + id + " with data: " + request);
//
//        User user = userRepository.findById(id).orElse(null);
//        if(user == null) return null;
//
//        Team team = teamRepository.findById(request.getTeamId()).orElse(null);
//        if(team == null) return null;
//
///*
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setEmail(request.getEmail());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setRole(request.getRole());
//        user.setBirthDate(LocalDate.parse(request.getBirthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        user.setTeam(team);
//
//        return UserResponse.fromEntity(user);
//*/
//
//        return userMapper.toUserResponse(user);
//    }
//
//}
