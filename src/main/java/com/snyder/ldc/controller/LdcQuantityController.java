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

import com.snyder.ldc.model.LdcQuantityModel;
import com.snyder.ldc.service.LdcQuantityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/ldc/quantities")
@Validated
@CrossOrigin(origins = "*")
public class LdcQuantityController {

	@Autowired
	private LdcQuantityService quantityService;

	// ================ Record a new quantity =================
	@PostMapping
	public ResponseEntity<LdcQuantityModel> recordQuantity(@Valid @RequestBody LdcQuantityModel request) {
		LdcQuantityModel response = quantityService.recordQuantity(request);

		// Add HATEOAS links
		response.add(linkTo(methodOn(LdcQuantityController.class).getQuantityById(response.getId())).withSelfRel());
		response.add(linkTo(methodOn(LdcQuantityController.class).getAllQuantities()).withRel("all-quantities"));
		response.add(linkTo(methodOn(LdcQuantityController.class).updateQuantity(response.getId(), request))
				.withRel("update-quantity"));

		return ResponseEntity.status(201).body(response);
	}

	// ================ Get quantity by ID =================
	@GetMapping("/{id}")
	public ResponseEntity<LdcQuantityModel> getQuantityById(@PathVariable Long id) {
		LdcQuantityModel response = quantityService.getQuantityById(id);

		// Add HATEOAS links
		response.add(linkTo(methodOn(LdcQuantityController.class).getQuantityById(id)).withSelfRel());
		response.add(linkTo(methodOn(LdcQuantityController.class).getAllQuantities()).withRel("all-quantities"));
		response.add(linkTo(methodOn(LdcQuantityController.class).updateQuantity(id, null)).withRel("update-quantity"));

		return ResponseEntity.ok(response);
	}

	// ================ Get all quantities =================
	@GetMapping
	public ResponseEntity<CollectionModel<LdcQuantityModel>> getAllQuantities() {
		List<LdcQuantityModel> responses = quantityService.getAllQuantities();

		// Add self link for each quantity
		for (LdcQuantityModel resp : responses) {
			resp.add(linkTo(methodOn(LdcQuantityController.class).getQuantityById(resp.getId())).withSelfRel());
		}

		// Wrap in CollectionModel and add collection-level links
		CollectionModel<LdcQuantityModel> collectionModel = CollectionModel.of(responses);
		collectionModel.add(linkTo(methodOn(LdcQuantityController.class).getAllQuantities()).withSelfRel());
		collectionModel
				.add(linkTo(methodOn(LdcQuantityController.class).recordQuantity(null)).withRel("record-quantity"));

		return ResponseEntity.ok(collectionModel);
	}

	// ================ Get quantities by meter ID =================
	@GetMapping("/meter/{meterId}")
	public ResponseEntity<CollectionModel<LdcQuantityModel>> getQuantitiesByMeterId(@PathVariable Long meterId) {
		List<LdcQuantityModel> responses = quantityService.getQuantitiesByMeterId(meterId);

		// Add self link for each quantity
		for (LdcQuantityModel resp : responses) {
			resp.add(linkTo(methodOn(LdcQuantityController.class).getQuantityById(resp.getId())).withSelfRel());
		}

		// Wrap in CollectionModel and add collection-level links
		CollectionModel<LdcQuantityModel> collectionModel = CollectionModel.of(responses);
		collectionModel
				.add(linkTo(methodOn(LdcQuantityController.class).getQuantitiesByMeterId(meterId)).withSelfRel());
		collectionModel
				.add(linkTo(methodOn(LdcQuantityController.class).recordQuantity(null)).withRel("record-quantity"));

		return ResponseEntity.ok(collectionModel);
	}

	// ================ Update a quantity =================
	@PutMapping("/{id}")
	public ResponseEntity<LdcQuantityModel> updateQuantity(@PathVariable Long id,
			@Valid @RequestBody LdcQuantityModel request) {
		LdcQuantityModel response = quantityService.updateQuantity(id, request);

		// Add HATEOAS links
		response.add(linkTo(methodOn(LdcQuantityController.class).getQuantityById(id)).withSelfRel());
		response.add(linkTo(methodOn(LdcQuantityController.class).getAllQuantities()).withRel("all-quantities"));

		return ResponseEntity.ok(response);
	}

	// ================ Delete a quantity =================
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteQuantity(@PathVariable Long id) {
		quantityService.deleteQuantity(id);
		return ResponseEntity.noContent().build();
	}
}