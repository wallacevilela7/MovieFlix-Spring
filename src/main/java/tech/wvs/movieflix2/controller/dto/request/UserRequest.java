package tech.wvs.movieflix2.controller.dto.request;

public record UserRequest(
        String name,
        String email,
        String password) {
}
