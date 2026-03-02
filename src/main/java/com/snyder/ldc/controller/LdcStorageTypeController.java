package com.snyder.ldc.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snyder.ldc.model.LdcStorageTypeModel;
import com.snyder.ldc.service.LdcStorageTypeService;

import jakarta.validation.Valid;

//========== LDC STORAGE TYPE CONTROLLER ==========
@RestController
@RequestMapping("/api/v1/ldc/storage-types")
@Validated
@CrossOrigin(origins = "*")
public class LdcStorageTypeController {

	@Autowired
	private LdcStorageTypeService storageTypeService;

	// ================ Create a new storage type =================
	@PostMapping
	public ResponseEntity<LdcStorageTypeModel> createStorageType(@Valid @RequestBody LdcStorageTypeModel request) {
		LdcStorageTypeModel response = storageTypeService.createStorageType(request);

		// Add HATEOAS links
		response.add(
				linkTo(methodOn(LdcStorageTypeController.class).getStorageTypeById(response.getId())).withSelfRel());
		response.add(
				linkTo(methodOn(LdcStorageTypeController.class).getAllStorageTypes()).withRel("all-storage-types"));
		response.add(linkTo(methodOn(LdcStorageTypeController.class).updateStorageType(response.getId(), request))
				.withRel("update-storage-type"));

		return ResponseEntity.status(201).body(response);
	}

	// ================ Get storage type by ID =================
	@GetMapping("/{id}")
	public ResponseEntity<LdcStorageTypeModel> getStorageTypeById(@PathVariable Long id) {
		LdcStorageTypeModel response = storageTypeService.getStorageTypeById(id);

		// Add HATEOAS links
		response.add(linkTo(methodOn(LdcStorageTypeController.class).getStorageTypeById(id)).withSelfRel());
		response.add(
				linkTo(methodOn(LdcStorageTypeController.class).getAllStorageTypes()).withRel("all-storage-types"));
		response.add(linkTo(methodOn(LdcStorageTypeController.class).updateStorageType(id, null))
				.withRel("update-storage-type"));

		return ResponseEntity.ok(response);
	}

	// ================ Get all storage types =================
	@GetMapping
	public ResponseEntity<CollectionModel<LdcStorageTypeModel>> getAllStorageTypes() {
		List<LdcStorageTypeModel> responses = storageTypeService.getAllStorageTypes();

		// Add self link for each storage type
		for (LdcStorageTypeModel resp : responses) {
			resp.add(linkTo(methodOn(LdcStorageTypeController.class).getStorageTypeById(resp.getId())).withSelfRel());
		}

		// Wrap in CollectionModel and add collection-level links
		CollectionModel<LdcStorageTypeModel> collectionModel = CollectionModel.of(responses);
		collectionModel.add(linkTo(methodOn(LdcStorageTypeController.class).getAllStorageTypes()).withSelfRel());
		collectionModel.add(linkTo(methodOn(LdcStorageTypeController.class).createStorageType(null))
				.withRel("create-storage-type"));

		return ResponseEntity.ok(collectionModel);
	}

	// ================ Update a storage type =================
	@PutMapping("/{id}")
	public ResponseEntity<LdcStorageTypeModel> updateStorageType(@PathVariable Long id,
			@Valid @RequestBody LdcStorageTypeModel request) {
		LdcStorageTypeModel response = storageTypeService.updateStorageType(id, request);

		// Add HATEOAS links
		response.add(linkTo(methodOn(LdcStorageTypeController.class).getStorageTypeById(id)).withSelfRel());
		response.add(
				linkTo(methodOn(LdcStorageTypeController.class).getAllStorageTypes()).withRel("all-storage-types"));

		return ResponseEntity.ok(response);
	}

	// ================ Delete a storage type =================
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStorageType(@PathVariable Long id) {
		storageTypeService.deleteStorageType(id);
		return ResponseEntity.noContent().build();
	}
}