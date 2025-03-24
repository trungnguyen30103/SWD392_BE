package com.blindbox.service;

import com.blindbox.model.BlindBoxItem;
import com.blindbox.model.Blindbox;
import com.blindbox.model.BlindboxImage;
import com.blindbox.request.Create.Blindbox.BlindboxCreateRequest;
import com.blindbox.request.Update.Blindbox.BlindboxImageUpdateRequest;
import com.blindbox.request.Update.Blindbox.BlindboxItemUpdateRequest;
import com.blindbox.request.Update.Blindbox.BlindboxUpdateRequest;
import org.springframework.lang.NonNull;

import java.util.List;

public interface BlindboxService {

    /* Blindbox */
    @NonNull
    Blindbox createBlindbox(@NonNull BlindboxCreateRequest request);

    @NonNull
    Blindbox updateBlindbox(@NonNull Integer id, @NonNull BlindboxUpdateRequest request);

    void deleteBlindbox(@NonNull Integer id);


    @NonNull
    List<Blindbox> getAllBlindboxes();

    Blindbox getBlindboxById(@NonNull Integer id);

    List<Blindbox> getBlindboxByName(String name);

    List<Blindbox> getBlindboxByCategory(Integer categoryID);


    /* Blindbox Image */
    @NonNull
    BlindboxImage updateImage(@NonNull Integer blindboxID, @NonNull Integer imageID, @NonNull BlindboxImageUpdateRequest request);

    void deleteImage(@NonNull Integer blindboxID, @NonNull Integer imageID);


    /* Blindbox Item */
    @NonNull
    BlindBoxItem updateItem(@NonNull Integer blindboxID, @NonNull Integer itemID, @NonNull BlindboxItemUpdateRequest request);

    void deleteItem(@NonNull Integer blindboxID, @NonNull Integer itemID);
}
