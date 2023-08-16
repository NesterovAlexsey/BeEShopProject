package com.onlineshop.controller.api;

import com.onlineshop.dto.CategoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/admin/category")
@Tags(value = {
        @Tag(name = "Categories")
})
public interface AdminCategoryControllerAPI {

    @Operation(summary = "Get all products category", description = "Applicable only for administrator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All categories was shown to client",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDTO.class))
                })
    })
    @GetMapping("/all")
    List<CategoryDTO> findAllCategory();
}
