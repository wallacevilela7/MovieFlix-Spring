package tech.wvs.movieflix2.mapper;

import tech.wvs.movieflix2.controller.dto.request.StreamingRequest;
import tech.wvs.movieflix2.controller.dto.response.StreamingResponse;
import tech.wvs.movieflix2.domain.Streaming;

public class StreamingMapper {

    public static Streaming toEntity(StreamingRequest request) {
        var entity = new Streaming();
        entity.setName(request.name());
        return entity;
    }

    public static StreamingResponse toResponse(Streaming entity) {
        return new StreamingResponse(entity.getId(), entity.getName());
    }
}
