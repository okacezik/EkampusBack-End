package myproject.ekampus.business.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreatePostRequest {
    @NotNull
    @NotBlank
    private int studentId;

    @NotNull
    @NotBlank
    private String comment;

    /*@NotNull
    @NotBlank
    private String postPhotoPath;*/
    private LocalDateTime loadDate = LocalDateTime.now();
    
}
