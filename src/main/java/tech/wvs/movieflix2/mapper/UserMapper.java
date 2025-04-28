package tech.wvs.movieflix2.mapper;

import tech.wvs.movieflix2.controller.dto.request.UserRequest;
import tech.wvs.movieflix2.controller.dto.response.UserResponse;
import tech.wvs.movieflix2.domain.User;

public class UserMapper {

    public static User toEntity(UserRequest request) {
        return new User(null,
                request.name(),
                request.email(),
                request.password());
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail());
    }
}
