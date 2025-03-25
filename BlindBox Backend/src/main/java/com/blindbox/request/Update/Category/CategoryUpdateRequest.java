package com.blindbox.request.Update.Category;

import com.blindbox.request.Update.Blindbox.BlindboxUpdateRequest;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryUpdateRequest {

    String name;

    String description;

    List<BlindboxUpdateRequest> blindboxes;

}
