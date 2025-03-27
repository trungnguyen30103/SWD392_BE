package com.blindbox.request.Update.Blindbox;

import com.blindbox.request.Create.Blindbox.BlindboxImageCreateRequest;
import com.blindbox.request.Create.Blindbox.BlindboxItemCreateRequest;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlindboxUpdateRequest {

    String blindboxName;

    String description;

    Double price;

    Integer categoryID;

    List<BlindboxImageUpdateRequest> blindboxImageUpdateRequests;

    List<BlindboxImageCreateRequest> blindboxImageCreateRequests;

    List<BlindboxItemUpdateRequest> blindboxItemUpdateRequests;

    List<BlindboxItemCreateRequest> blindboxItemCreateRequests;

}
