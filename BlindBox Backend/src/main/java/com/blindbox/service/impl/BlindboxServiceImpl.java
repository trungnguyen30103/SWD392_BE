package com.blindbox.service.impl;

import com.blindbox.model.BlindBoxItem;
import com.blindbox.model.Blindbox;
import com.blindbox.model.BlindboxImage;
import com.blindbox.model.Category;
import com.blindbox.repository.BlindBoxItemRepository;
import com.blindbox.repository.BlindboxImageRepository;
import com.blindbox.repository.BlindboxRepository;
import com.blindbox.repository.CategoryRepository;
import com.blindbox.request.Create.Blindbox.BlindboxImageCreateRequest;
import com.blindbox.request.Create.Blindbox.BlindboxCreateRequest;
import com.blindbox.request.Create.Blindbox.BlindboxItemCreateRequest;
import com.blindbox.request.Update.Blindbox.BlindboxImageUpdateRequest;
import com.blindbox.request.Update.Blindbox.BlindboxItemUpdateRequest;
import com.blindbox.request.Update.Blindbox.BlindboxUpdateRequest;
import com.blindbox.service.BlindboxService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlindboxServiceImpl implements BlindboxService {

    private final BlindboxRepository blindboxRepository;

    private final CategoryRepository categoryRepository;
    private final BlindboxImageRepository blindboxImageRepository;
    private final BlindBoxItemRepository blindBoxItemRepository;

    public BlindboxServiceImpl(BlindboxRepository blindboxRepository, CategoryRepository categoryRepository, BlindboxImageRepository blindboxImageRepository, BlindBoxItemRepository blindBoxItemRepository) {
        this.blindboxRepository = blindboxRepository;
        this.categoryRepository = categoryRepository;
        this.blindboxImageRepository = blindboxImageRepository;
        this.blindBoxItemRepository = blindBoxItemRepository;
    }

    /* Blindbox */


    // Create a new blindbox
    @Override
    @NonNull
    public Blindbox createBlindbox(@NonNull BlindboxCreateRequest request) {
        Blindbox blindbox = new Blindbox();

        // Set
        blindbox.setName(request.getName());
        blindbox.setDescription(request.getDescription());
        blindbox.setPrice(request.getPrice());
        blindbox.setStock(request.getStock());

        // Set categoryID into category
        Category category = categoryRepository.findById(request.getCategoryID())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        blindbox.setCategory(category);

        // Set images
        List<BlindboxImage> images = new ArrayList<>();
        if (request.getBlindboxImages() != null) {
            for (BlindboxImageCreateRequest imgReq : request.getBlindboxImages()) {
                BlindboxImage image = new BlindboxImage();
                image.setImageUrl(imgReq.getImageUrl());
                image.setBlindbox(blindbox); // ✅ Required to set back-reference
                image.setAltText(imgReq.getAltText());
                blindboxImageRepository.save(image);
                images.add(image);
            }
        }

        blindbox.setBlindboxImages(images);

        // Set items
        List<BlindBoxItem> items = new ArrayList<>();
        if (request.getBlindboxItem() != null) {
            for (BlindboxItemCreateRequest itemCreateRequest : request.getBlindboxItem()) {
                BlindBoxItem item = new BlindBoxItem();
                item.setBlindbox(blindbox);
                item.setName(itemCreateRequest.getName());
                item.setRarity(itemCreateRequest.getRarity());
                blindBoxItemRepository.save(item);
                items.add(item);
            }
        }

        blindbox.setBlindBoxItems(items);

        // Save
        return blindboxRepository.save(blindbox);
    }

    // Update a blindbox
    @Override
    @NonNull
    public Blindbox updateBlindbox(@NonNull Integer blindboxID, @NonNull BlindboxUpdateRequest request) {
        Blindbox existingBlindbox = blindboxRepository.findById(blindboxID)
                .orElseThrow(() -> new RuntimeException("Blindbox not found"));

        // Update
        if (request.getBlindboxName() != null) existingBlindbox.setName(request.getBlindboxName());
        if (request.getPrice() != null) existingBlindbox.setPrice(request.getPrice());
        if (request.getDescription() != null) existingBlindbox.setDescription(request.getDescription());
        if (request.getStock() != null) existingBlindbox.setStock(request.getStock());

        if (request.getCategoryID() != null) {
            Category category = categoryRepository.findById(request.getCategoryID())
                    .orElseThrow(() -> new RuntimeException("Blindbox not found"));
            existingBlindbox.setCategory(category);
        }

        // Update image
        if (request.getBlindboxImages() != null) {
            List<BlindboxImage> updateImages = new ArrayList<>();
            for (BlindboxImageUpdateRequest imgReq : request.getBlindboxImages()) {
                BlindboxImage image = blindboxImageRepository.findByBlindbox_BlindboxIDAndBlindboxImageID(blindboxID, imgReq.getBlindboxImageID())
                        .orElseThrow(() -> new RuntimeException("Blindbox Image not found"));
                if (imgReq.getImageUrl() != null) image.setImageUrl(imgReq.getImageUrl());
                if (imgReq.getAltText() != null) image.setAltText(imgReq.getAltText());

                // Save new image
                blindboxImageRepository.save(image);

                // Add image to the list
                updateImages.add(image);
            }
            existingBlindbox.setBlindboxImages(updateImages);
        }

        // Update item
        if (request.getBlindboxItem() != null) {
            List<BlindBoxItem> updateItems = new ArrayList<>();
            for (BlindboxItemUpdateRequest itemUpdateRequest : request.getBlindboxItem()) {
                BlindBoxItem item = blindBoxItemRepository.findByBlindbox_BlindboxIDAndAndBlindboxItemID(blindboxID, itemUpdateRequest.getBlindboxItemID())
                        .orElseThrow(() -> new RuntimeException("Blindbox Item not found"));
                item.setName(itemUpdateRequest.getName());
                item.setRarity(itemUpdateRequest.getRarity());

                // Save new image
                blindBoxItemRepository.save(item);

                // Add image to the list
                updateItems.add(item);
            }
            existingBlindbox.setBlindBoxItems(updateItems);
        }

        // Save
        return blindboxRepository.save(existingBlindbox);
    }

    // Delete a blindbox
    @Override
    @NonNull
    public void deleteBlindbox(@NonNull Integer id) {
        blindboxRepository.deleteById(id);
        blindboxImageRepository.deleteAllByBlindbox_BlindboxID(id);
        blindBoxItemRepository.deleteAllByBlindbox_BlindboxID(id);
    }


    // Get all blindboxes
    @Override
    @NonNull
    public List<Blindbox> getAllBlindboxes() {
        return blindboxRepository.findAll();
    }

    // Get blindbox by ID
    @Override
    @NonNull
    public Blindbox getBlindboxById(@NonNull Integer id) {
        Optional<Blindbox> blindbox = blindboxRepository.findById(id);
        return blindbox.orElseThrow(() -> new RuntimeException("Blindbox not found"));
    }

    // Get blindbox by name
    @Override
    public List<Blindbox> getBlindboxByName(String name) {
        return blindboxRepository.findByBlindboxNameContainingIgnoreCase(name);
    }

    // Get blindbox by category
    @Override
    public List<Blindbox> getBlindboxByCategory(Integer categoryID) {
        return blindboxRepository.findByCategory_CategoryID(categoryID);
    }


    /* Blindbox image */

    @Override
    @NonNull
    public BlindboxImage updateImage(@NonNull Integer blindboxID, @NonNull Integer imageID, @NonNull BlindboxImageUpdateRequest request) {
        BlindboxImage image = blindboxImageRepository.findByBlindbox_BlindboxIDAndBlindboxImageID(blindboxID, imageID)
                .orElseThrow(() -> new RuntimeException("Blindbox Image not found"));

        if (request.getBlindboxID() != null) {
            Blindbox blindbox = blindboxRepository.findById(request.getBlindboxID())
                    .orElseThrow(() -> new RuntimeException("Blindbox not found"));
            image.setBlindbox(blindbox);
        }

        if (request.getAltText() != null) image.setAltText(request.getAltText());

        if (request.getImageUrl() != null) image.setImageUrl(request.getImageUrl());

        return blindboxImageRepository.save(image);
    }

    @Override
    public void deleteImage(@NonNull Integer blindboxID, @NonNull Integer imageID) {
        blindboxImageRepository.deleteByBlindbox_BlindboxIDAndBlindboxImageID(blindboxID, imageID);
    }


    /* Blindbox Item */

    @Override
    @NonNull
    public BlindBoxItem updateItem(@NonNull Integer blindboxID, @NonNull Integer itemID, @NonNull BlindboxItemUpdateRequest request) {
        BlindBoxItem item = blindBoxItemRepository.findByBlindbox_BlindboxIDAndAndBlindboxItemID(blindboxID, itemID)
                .orElseThrow(() -> new RuntimeException("Blindbox Item not found"));

        if (request.getBlindboxID() != null) {
            Blindbox blindbox = blindboxRepository.findById(request.getBlindboxID())
                    .orElseThrow(() -> new RuntimeException("Blindbox not found"));
            item.setBlindbox(blindbox);
        }

        if (request.getName() != null) item.setName(request.getName());
        if (request.getRarity() != null) item.setRarity(request.getRarity());

        return blindBoxItemRepository.save(item);
    }

    @Override
    public void deleteItem(@NonNull Integer blindboxID, @NonNull Integer itemID) {
        blindBoxItemRepository.deleteByBlindbox_BlindboxIDAndBlindboxItemID(blindboxID, itemID);
    }
}
