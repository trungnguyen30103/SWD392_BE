package com.blindbox.request.Update.Blindbox;

import com.blindbox.request.Create.Blindbox.BlindboxImageCreateRequest;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlindboxUpdateRequest {

    private String blindboxName;

    private String description;

    private Double price;

    private Integer stock;

    private Integer categoryID;

    private List<BlindboxImageCreateRequest> blindboxImages;

}
