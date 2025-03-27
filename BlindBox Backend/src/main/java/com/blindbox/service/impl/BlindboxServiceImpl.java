package com.blindbox.service.impl;

import com.blindbox.enums.Blindbox.BlindboxItemStatus;
import com.blindbox.enums.Blindbox.BlindboxStatus;
import com.blindbox.model.BlindBoxItem;
import com.blindbox.model.Blindbox;
import com.blindbox.model.BlindboxImage;
import com.blindbox.model.Category;
import com.blindbox.repository.BlindBoxItemRepository;
import com.blindbox.repository.BlindboxImageRepository;
import com.blindbox.repository.BlindboxRepository;
import com.blindbox.repository.CategoryRepository;
import com.blindbox.request.Create.Blindbox.BlindboxCreateRequest;
import com.blindbox.request.Create.Blindbox.BlindboxImageCreateRequest;
import com.blindbox.request.Create.Blindbox.BlindboxItemCreateRequest;
import com.blindbox.request.Update.Blindbox.BlindboxImageUpdateRequest;
import com.blindbox.request.Update.Blindbox.BlindboxItemUpdateRequest;
import com.blindbox.request.Update.Blindbox.BlindboxUpdateRequest;
import com.blindbox.service.BlindboxService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BlindboxServiceImpl implements BlindboxService {

    private final BlindboxRepository blindboxRepository;
    private final CategoryRepository categoryRepository;
    private final BlindboxImageRepository blindboxImageRepository;
    private final BlindBoxItemRepository blindBoxItemRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public BlindboxServiceImpl(BlindboxRepository blindboxRepository, CategoryRepository categoryRepository, BlindboxImageRepository blindboxImageRepository, BlindBoxItemRepository blindBoxItemRepository) {
        this.blindboxRepository = blindboxRepository;
        this.categoryRepository = categoryRepository;
        this.blindboxImageRepository = blindboxImageRepository;
        this.blindBoxItemRepository = blindBoxItemRepository;
    }


    /* Blindbox
    * */

    // Create a new blindbox
    @Override
    @Transactional
    public Blindbox createBlindbox(BlindboxCreateRequest request) {
        Blindbox blindbox = new Blindbox();

        // Set
        blindbox.setName(request.getName());
        blindbox.setDescription(request.getDescription());
        blindbox.setPrice(request.getPrice());
        blindbox.setTotalStock(0);
        blindbox.setStatus(BlindboxStatus.OUT_OF_STOCK);

        // Set categoryID into category
        Category category = categoryRepository.findById(request.getCategoryID())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        blindbox.setCategory(category);

        // Save first
        blindbox = blindboxRepository.save(blindbox);

        // Set images
        Set<BlindboxImage> images = new HashSet<>();
        if (request.getBlindboxImages() != null) {
            for (BlindboxImageCreateRequest imgReq : request.getBlindboxImages()) {
                BlindboxImage image = new BlindboxImage();

                image.setImageUrl(imgReq.getImageUrl());

                image.setBlindbox(blindbox);
                image.setAltText(imgReq.getAltText());
                images.add(image);
            }
            blindboxImageRepository.saveAll(images);
        }

        // Set items and stock
        Set<BlindBoxItem> items = new HashSet<>();
        if (request.getBlindboxItem() != null) {
            int totalStock = 0;
            for (BlindboxItemCreateRequest itemCreateRequest : request.getBlindboxItem()) {
                BlindBoxItem item = new BlindBoxItem();
                item.setBlindbox(blindbox);
                item.setName(itemCreateRequest.getName());
                item.setRarity(itemCreateRequest.getRarity());

                item.setImageUrl(itemCreateRequest.getImageUrl());

                if (itemCreateRequest.getStock() != 0) {
                    item.setStock(itemCreateRequest.getStock());
                    item.setStatus(BlindboxItemStatus.ACTIVE);
                    totalStock += itemCreateRequest.getStock();
                } else {
                    item.setStock(0);
                    item.setStatus(BlindboxItemStatus.OUT_OF_STOCK);
                }
                items.add(item);
            }
            blindBoxItemRepository.saveAll(items);
            blindbox.setTotalStock(totalStock);
            blindbox.setStatus(totalStock == 0 ? BlindboxStatus.OUT_OF_STOCK : BlindboxStatus.ACTIVE);
        }


        blindbox.setBlindboxImages(images);

        blindbox.setBlindBoxItems(items);

        // Save
        return blindboxRepository.save(blindbox);
    }

    // Update a blindbox
    @Override
    @NonNull
    @Transactional
    public Blindbox updateBlindbox(@NonNull Integer blindboxID, @NonNull BlindboxUpdateRequest request) {
        Blindbox existingBlindbox = blindboxRepository.findById(blindboxID)
                .orElseThrow(() -> new RuntimeException("Blindbox not found"));

        // Update
        if (request.getBlindboxName() != null) existingBlindbox.setName(request.getBlindboxName());
        if (request.getPrice() != null) existingBlindbox.setPrice(request.getPrice());
        if (request.getDescription() != null) existingBlindbox.setDescription(request.getDescription());

        if (request.getCategoryID() != null) {
            Category category = categoryRepository.findById(request.getCategoryID())
                    .orElseThrow(() -> new RuntimeException("Blindbox not found"));
            existingBlindbox.setCategory(category);
        }

        // Force flush before updating child entities
        blindboxRepository.saveAndFlush(existingBlindbox);

        // Update image

        // Update existing image(s)
        if (request.getBlindboxImageUpdateRequests() != null) {
            for (BlindboxImageUpdateRequest imageUpdateRequest : request.getBlindboxImageUpdateRequests()) {
                BlindboxImage image = blindboxImageRepository.findByBlindbox_BlindboxIDAndBlindboxImageID(blindboxID, imageUpdateRequest.getBlindboxImageID())
                        .orElseThrow(() -> new RuntimeException("Blindbox Image not found"));
                if (imageUpdateRequest.getImageUrl() != null) image.setImageUrl(imageUpdateRequest.getImageUrl());
                if (imageUpdateRequest.getAltText() != null) image.setAltText(imageUpdateRequest.getAltText());
            }
        }

        // Add new image(s)
        if (request.getBlindboxImageCreateRequests() != null) {
            for (BlindboxImageCreateRequest imageCreateRequest : request.getBlindboxImageCreateRequests()) {
                BlindboxImage newImage = new BlindboxImage();
                newImage.setImageUrl(imageCreateRequest.getImageUrl());
                newImage.setBlindbox(existingBlindbox);
                newImage.setAltText(imageCreateRequest.getAltText());

                // Save
                BlindboxImage saveImage = blindboxImageRepository.save(newImage);
                existingBlindbox.getBlindboxImages().add(saveImage);
            }
        }

        // Update item
        int totalStock = existingBlindbox.getTotalStock();

        // Update exiting item(s)
        if (request.getBlindboxItemUpdateRequests() != null) {
            for (BlindboxItemUpdateRequest itemUpdateRequest : request.getBlindboxItemUpdateRequests()) {
                BlindBoxItem item = blindBoxItemRepository.findByBlindbox_BlindboxIDAndBlindboxItemID(blindboxID, itemUpdateRequest.getBlindboxItemID())
                        .orElseThrow(() -> new RuntimeException("Blindbox Item not found"));

                if (itemUpdateRequest.getName() != null) item.setName(itemUpdateRequest.getName());
                if (itemUpdateRequest.getRarity() != null) item.setRarity(itemUpdateRequest.getRarity());
                if (itemUpdateRequest.getImageUrl() != null) item.setImageUrl(itemUpdateRequest.getImageUrl());

                if (itemUpdateRequest.getStock() != null) {
                    totalStock += itemUpdateRequest.getStock() - item.getStock();
                    item.setStock(itemUpdateRequest.getStock());
                    item.setStatus(item.getStock() == 0 ? BlindboxItemStatus.OUT_OF_STOCK : BlindboxItemStatus.ACTIVE);
                }
            }
        }

        // Create new item(s)
        if (request.getBlindboxItemCreateRequests() != null) {
            for (BlindboxItemCreateRequest itemCreateRequest : request.getBlindboxItemCreateRequests()) {
                BlindBoxItem newItem = getBlindBoxItem(itemCreateRequest, existingBlindbox);

                // Save
                BlindBoxItem savedItem = blindBoxItemRepository.save(newItem);
                existingBlindbox.getBlindBoxItems().add(savedItem);
            }
        }

        // Update totalStock
        existingBlindbox.setTotalStock(totalStock);
        existingBlindbox.setStatus(totalStock == 0 ? BlindboxStatus.OUT_OF_STOCK : BlindboxStatus.ACTIVE);

        // Save
        return blindboxRepository.save(existingBlindbox);
    }

    private BlindBoxItem getBlindBoxItem(BlindboxItemCreateRequest itemCreateRequest, Blindbox existingBlindbox) {
        BlindBoxItem newItem = new BlindBoxItem();
        newItem.setBlindbox(existingBlindbox);
        newItem.setName(itemCreateRequest.getName());
        newItem.setRarity(itemCreateRequest.getRarity());
        newItem.setImageUrl(itemCreateRequest.getImageUrl());
        newItem.setStock(itemCreateRequest.getStock() != null ? itemCreateRequest.getStock() : 0);
        newItem.setStatus(newItem.getStock() == 0 ? BlindboxItemStatus.OUT_OF_STOCK : BlindboxItemStatus.ACTIVE);
        return newItem;
    }

    // Disable a blindbox with its items
    @Override
    @NonNull
    public void deleteBlindbox(@NonNull Integer id) {
        Blindbox blindbox = blindboxRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blindbox not found"));

        // Update status to DISABLE
        blindbox.setStatus(BlindboxStatus.DISABLE);

        // Update status of all items to DISABLE
        for (BlindBoxItem item : blindbox.getBlindBoxItems()) {
            item.setStatus(BlindboxItemStatus.DISABLE);
        }

        // Save changes
        blindboxRepository.save(blindbox);
        blindBoxItemRepository.saveAll(blindbox.getBlindBoxItems());
    }


    // Get all blindboxes
    @Override
    @NonNull
    public List<Blindbox> getAllBlindboxes() {
        entityManager.clear(); // Clear Hibernate cache
        List<Blindbox> blindboxes = blindboxRepository.findAll();
        System.out.println("Total fetched blindboxes: " + blindboxes.size());
        return blindboxes;
    }







    // Get blindbox by ID
    @Override
    @NonNull
    @Transactional(readOnly = true)
    public Blindbox getBlindboxById(@NonNull Integer id) {
        return blindboxRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blindbox not found"));
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

    // Get ACTIVE blindboxes
    @Override
    public List<Blindbox> getActiveBlindbox() {
        return blindboxRepository.findByStatus(BlindboxStatus.ACTIVE);
    }

    // Get DISABLE blindboxes
    @Override
    public List<Blindbox> getDisableBlindbox() {
        return blindboxRepository.findByStatus(BlindboxStatus.DISABLE);
    }

    // Get OUT_OF_STOCK blindboxes
    @Override
    public List<Blindbox> getOutOfStockBlindbox() {
        return blindboxRepository.findByStatus(BlindboxStatus.OUT_OF_STOCK);
    }

    // Get ACTIVE blindboxes by categoryID
    @Override
    public List<Blindbox> getActiveBlindboxByCategoryID(Integer categoryID) {
        return blindboxRepository.findByStatusAndCategory_CategoryID(BlindboxStatus.ACTIVE, categoryID);
    }


    /* Blindbox image
    * */

    @Override
    public void deleteImage(@NonNull Integer blindboxID, @NonNull Integer imageID) {
        blindboxImageRepository.deleteByBlindbox_BlindboxIDAndBlindboxImageID(blindboxID, imageID);
    }


    /* Blindbox Item
    * */

    // Disable an item
    @Override
    public void deleteItem(@NonNull Integer blindboxID, @NonNull Integer itemID) {
        BlindBoxItem item = blindBoxItemRepository.findByBlindbox_BlindboxIDAndBlindboxItemID(blindboxID, itemID)
                .orElseThrow(() -> new RuntimeException("BlindboxItem not found"));
        item.setStatus(BlindboxItemStatus.DISABLE);
        blindBoxItemRepository.save(item);
    }

    // Get ACTIVE blindbox items
    @Override
    public List<BlindBoxItem> getActiveBlindboxItem() {
        return blindBoxItemRepository.findByStatus(BlindboxItemStatus.ACTIVE);
    }

    // Get DISABLE blindbox items
    @Override
    public List<BlindBoxItem> getDisableBlindboxItem() {
        return blindBoxItemRepository.findByStatus(BlindboxItemStatus.DISABLE);
    }

    // Get OUT_OF_STOCK blindbox items
    @Override
    public List<BlindBoxItem> getOutOfStockBlindboxItem() {
        return blindBoxItemRepository.findByStatus(BlindboxItemStatus.OUT_OF_STOCK);
    }
}
