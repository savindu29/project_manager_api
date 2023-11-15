package com.inova.project_manager_api.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageUploadRequestDto {
    @JsonProperty("project_id")
    @NotNull(message = "Project id cannot be null")
    private Integer projectId;
    @JsonProperty("description")
    @NotNull(message = "Description cannot be null")
    private String description;

    @JsonProperty("image_base64_string")
    @NotNull(message = "Image string cannot be null")
    private String imageString;
}
