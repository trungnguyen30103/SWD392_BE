package com.blindbox.service;

import com.blindbox.model.BlindBoxItem;
import com.blindbox.model.Blindbox;
import com.blindbox.request.Create.Blindbox.BlindboxCreateRequest;
import com.blindbox.request.Update.Blindbox.BlindboxUpdateRequest;
import org.springframework.lang.NonNull;

import java.util.List;

public interface BlindboxService {

    /* Blindbox */

    Blindbox createBlindbox(BlindboxCreateRequest request);

    @NonNull
    Blindbox updateBlindbox(@NonNull Integer id, @NonNull BlindboxUpdateRequest request);

    void deleteBlindbox(@NonNull Integer id);


    @NonNull
    List<Blindbox> getAllBlindboxes();

    Blindbox getBlindboxById(@NonNull Integer id);

    List<Blindbox> getBlindboxByName(String name);

    List<Blindbox> getBlindboxByCategory(Integer categoryID);

    List<Blindbox> getActiveBlindbox();

    List<Blindbox> getDisableBlindbox();

    List<Blindbox> getOutOfStockBlindbox();


    /* Blindbox Image */

    void deleteImage(@NonNull Integer blindboxID, @NonNull Integer imageID);


    /* Blindbox Item */

    void deleteItem(@NonNull Integer blindboxID, @NonNull Integer itemID);

    List<BlindBoxItem> getActiveBlindboxItem();

    List<BlindBoxItem> getDisableBlindboxItem();

    List<BlindBoxItem> getOutOfStockBlindboxItem();

    List<Blindbox> getActiveBlindboxByCategoryID(Integer categoryID);

}
