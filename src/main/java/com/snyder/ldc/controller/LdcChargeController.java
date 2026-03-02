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

import com.snyder.ldc.model.LdcChargeModel;
import com.snyder.ldc.service.LdcChargeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/ldc/charges")
@Validated
@CrossOrigin(origins = "*")
public class LdcChargeController {

	@Autowired
	private LdcChargeService chargeService;

	// ================ Create a new charge =================
	@PostMapping
	public ResponseEntity<LdcChargeModel> createCharge(@Valid @RequestBody LdcChargeModel request) {
		LdcChargeModel response = chargeService.createCharge(request);

		// Add HATEOAS links
		response.add(linkTo(methodOn(LdcChargeController.class).getChargeById(response.getId())).withSelfRel());
		response.add(linkTo(methodOn(LdcChargeController.class).getAllCharges()).withRel("all-charges"));
		response.add(linkTo(methodOn(LdcChargeController.class).updateCharge(response.getId(), request))
				.withRel("update-charge"));

		return ResponseEntity.status(201).body(response);
	}

	// ================ Get a charge by ID =================
	@GetMapping("/{id}")
	public ResponseEntity<LdcChargeModel> getChargeById(@PathVariable Long id) {
		LdcChargeModel response = chargeService.getChargeById(id);

		// Add HATEOAS links
		response.add(linkTo(methodOn(LdcChargeController.class).getChargeById(id)).withSelfRel());
		response.add(linkTo(methodOn(LdcChargeController.class).getAllCharges()).withRel("all-charges"));
		response.add(linkTo(methodOn(LdcChargeController.class).updateCharge(id, null)).withRel("update-charge"));

		return ResponseEntity.ok(response);
	}

	// ================ Get all charges =================
	@GetMapping
	public ResponseEntity<CollectionModel<LdcChargeModel>> getAllCharges() {
		List<LdcChargeModel> responses = chargeService.getAllCharges();

		// Add self link for each charge
		for (LdcChargeModel resp : responses) {
			resp.add(linkTo(methodOn(LdcChargeController.class).getChargeById(resp.getId())).withSelfRel());
		}

		// Wrap in CollectionModel and add collection-level links
		CollectionModel<LdcChargeModel> collectionModel = CollectionModel.of(responses);
		collectionModel.add(linkTo(methodOn(LdcChargeController.class).getAllCharges()).withSelfRel());
		collectionModel.add(linkTo(methodOn(LdcChargeController.class).createCharge(null)).withRel("create-charge"));

		return ResponseEntity.ok(collectionModel);
	}

	// ================ Get charges by service area =================
	@GetMapping("/service-area/{serviceAreaId}")
	public ResponseEntity<CollectionModel<LdcChargeModel>> getChargesByServiceArea(@PathVariable Long serviceAreaId) {
		List<LdcChargeModel> responses = chargeService.getChargesByServiceArea(serviceAreaId);

		// Add self link for each charge
		for (LdcChargeModel resp : responses) {
			resp.add(linkTo(methodOn(LdcChargeController.class).getChargeById(resp.getId())).withSelfRel());
		}

		// Wrap in CollectionModel and add collection-level links
		CollectionModel<LdcChargeModel> collectionModel = CollectionModel.of(responses);
		collectionModel
				.add(linkTo(methodOn(LdcChargeController.class).getChargesByServiceArea(serviceAreaId)).withSelfRel());
		collectionModel.add(linkTo(methodOn(LdcChargeController.class).createCharge(null)).withRel("create-charge"));

		return ResponseEntity.ok(collectionModel);
	}

	// ================ Update a charge =================
	@PutMapping("/{id}")
	public ResponseEntity<LdcChargeModel> updateCharge(@PathVariable Long id,
			@Valid @RequestBody LdcChargeModel request) {
		LdcChargeModel response = chargeService.updateCharge(id, request);

		// Add HATEOAS links
		response.add(linkTo(methodOn(LdcChargeController.class).getChargeById(id)).withSelfRel());
		response.add(linkTo(methodOn(LdcChargeController.class).getAllCharges()).withRel("all-charges"));

		return ResponseEntity.ok(response);
	}

	// ================ Delete a charge =================
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCharge(@PathVariable Long id) {
		chargeService.deleteCharge(id);

		// Return 204 No Content for clean RESTful deletion
		return ResponseEntity.noContent().build();
	}
}