package com.blindbox.request.Update.Blindbox;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlindboxImageUpdateRequest {

    Integer blindboxImageID;
    String imageUrl;
    String altText;

}
