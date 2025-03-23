package com.blindbox.service.impl;

import com.blindbox.model.Blindbox;
import com.blindbox.model.BlindboxImage;
import com.blindbox.model.Category;
import com.blindbox.repository.BlindboxImageRepository;
import com.blindbox.repository.BlindboxRepository;
import com.blindbox.repository.CategoryRepository;
import com.blindbox.request.Create.Blindbox.BlindboxImageCreateRequest;
import com.blindbox.request.Create.Blindbox.BlindboxCreateRequest;
import com.blindbox.request.Update.Blindbox.BlindboxImageUpdateRequest;
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

    public BlindboxServiceImpl(BlindboxRepository blindboxRepository, CategoryRepository categoryRepository, BlindboxImageRepository blindboxImageRepository) {
        this.blindboxRepository = blindboxRepository;
        this.categoryRepository = categoryRepository;
        this.blindboxImageRepository = blindboxImageRepository;
    }

    /* Blindbox */


    // Create a new blindbox
    @Override
    @NonNull
    public Blindbox createBlindbox(@NonNull BlindboxCreateRequest request) {
        Blindbox blindbox = new Blindbox();

        // Set
        blindbox.setBlindboxName(request.getBlindboxName());
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
                blindboxImageRepository.save(image);
                images.add(image);
            }
        }

        blindbox.setBlindboxImages(images);

        // Save
        return blindboxRepository.save(blindbox);
    }

    // Update a blindbox
    @Override
    @NonNull
    public Blindbox updateBlindbox(@NonNull Integer id, @NonNull BlindboxUpdateRequest request) {
        Blindbox existingBlindbox = blindboxRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blindbox not found"));

        // Update
        if (request.getBlindboxName() != null) existingBlindbox.setBlindboxName(request.getBlindboxName());
        if (request.getPrice() != null) existingBlindbox.setPrice(request.getPrice());
        if (request.getDescription() != null) existingBlindbox.setDescription(request.getDescription());
        if (request.getStock() != null) existingBlindbox.setStock(request.getStock());

        if (request.getCategoryID() != null) {
            Category category = categoryRepository.findById(request.getCategoryID())
                    .orElseThrow(() -> new RuntimeException("Blindbox not found"));
            existingBlindbox.setCategory(category);
        }

        // Only add more
        if (request.getBlindboxImages() != null) {
            List<BlindboxImage> updateImages = new ArrayList<>();
            for (BlindboxImageCreateRequest imgReq : request.getBlindboxImages()) {
                BlindboxImage image = new BlindboxImage();
                image.setImageUrl(imgReq.getImageUrl());
                image.setBlindbox(existingBlindbox); // ✅ Required to set back-reference

                // Save new image
                blindboxImageRepository.save(image);

                // Add image to the list
                updateImages.add(image);
            }
            existingBlindbox.setBlindboxImages(updateImages);
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
        String regex = ".*" + name + ".*";
        return blindboxRepository.findByBlindboxNameRegexIgnoreCase(regex);
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

        if (request.getImageUrl() != null) image.setImageUrl(request.getImageUrl());

        return blindboxImageRepository.save(image);
    }

    @Override
    public void deleteImage(@NonNull Integer blindboxID, @NonNull Integer imageID) {
        blindboxImageRepository.deleteByBlindbox_BlindboxIDAndBlindboxImageID(blindboxID, imageID);
    }
}
