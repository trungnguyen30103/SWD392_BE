package com.blindbox.request.Create.Category;

import com.blindbox.request.Create.Blindbox.BlindboxCreateRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryCreateRequest {

    @NotBlank
    String name;

    String description;

    List<BlindboxCreateRequest> blindboxes;

}
