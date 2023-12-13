package com.inova.project_manager_api.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inova.project_manager_api.enums.DocumentResourceType;
import com.inova.project_manager_api.enums.FileType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentUploadRequestDto {
    @JsonProperty("project_id")
    @NotNull(message = "Project id cannot be null")
    private Integer projectId;

    @JsonProperty("description")
    @NotNull(message = "Description cannot be null")
    private String description;

    @JsonProperty("file_base64_string")
    @NotNull(message = "File string cannot be null")
    private String fileString;

    @JsonProperty("resource")
    @NotNull(message = "Resource type cannot be null")
    @Pattern(regexp = "RFP|OFI", message = "Resource must be either RFP or OFI")
    private DocumentResourceType resource;

    @JsonProperty("type")
    @NotNull(message = " type cannot be null")
    @Pattern(regexp = "IMAGE|PDF", message = "Resource must be either IMAGE or PDF")
    private FileType fileType;
}
