package com.blindbox.request.Update.Blindbox;

import com.blindbox.request.Create.Blindbox.BlindboxImageCreateRequest;
import com.blindbox.request.Create.Blindbox.BlindboxItemCreateRequest;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlindboxUpdateRequest {

    String blindboxName;

    String description;

    Double price;

    Integer categoryID;

    Set<BlindboxImageUpdateRequest> blindboxImageUpdateRequests;

    Set<BlindboxImageCreateRequest> blindboxImageCreateRequests;

    Set<BlindboxItemUpdateRequest> blindboxItemUpdateRequests;

    Set<BlindboxItemCreateRequest> blindboxItemCreateRequests;

}
