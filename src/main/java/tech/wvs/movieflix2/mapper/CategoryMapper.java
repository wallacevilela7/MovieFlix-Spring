package tech.wvs.movieflix2.mapper;

import tech.wvs.movieflix2.controller.dto.request.CategoryRequest;
import tech.wvs.movieflix2.controller.dto.response.CategoryResponse;
import tech.wvs.movieflix2.domain.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryRequest request) {
        var entity = new Category();
        entity.setName(request.name());
        return entity;
    }

    public static CategoryResponse toResponse(Category entity) {
        return new CategoryResponse(entity.getId(), entity.getName());
    }
}
