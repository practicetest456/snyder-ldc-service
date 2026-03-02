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

import com.snyder.ldc.model.PoolingPointModel;
import com.snyder.ldc.service.PoolingPointService;

import jakarta.validation.Valid;

//========== POOLING POINT CONTROLLER ==========
@RestController
@RequestMapping("/api/v1/ldc/pooling-points")
@Validated
@CrossOrigin(origins = "*")
public class PoolingPointController {

	@Autowired
	private PoolingPointService poolingPointService;

	// ================ Create a new Pooling Point =================
	@PostMapping
	public ResponseEntity<PoolingPointModel> createPoolingPoint(@Valid @RequestBody PoolingPointModel request) {
		PoolingPointModel response = poolingPointService.createPoolingPoint(request);

		// HATEOAS links
		response.add(
				linkTo(methodOn(PoolingPointController.class).getPoolingPointById(response.getId())).withSelfRel());
		response.add(
				linkTo(methodOn(PoolingPointController.class).getAllPoolingPoints()).withRel("all-pooling-points"));
		response.add(linkTo(methodOn(PoolingPointController.class).updatePoolingPoint(response.getId(), request))
				.withRel("update-pooling-point"));

		return ResponseEntity.status(201).body(response);
	}

	// ================ Get Pooling Point by ID =================
	@GetMapping("/{id}")
	public ResponseEntity<PoolingPointModel> getPoolingPointById(@PathVariable Long id) {
		PoolingPointModel response = poolingPointService.getPoolingPointById(id);

		// HATEOAS links
		response.add(linkTo(methodOn(PoolingPointController.class).getPoolingPointById(id)).withSelfRel());
		response.add(
				linkTo(methodOn(PoolingPointController.class).getAllPoolingPoints()).withRel("all-pooling-points"));
		response.add(linkTo(methodOn(PoolingPointController.class).updatePoolingPoint(id, null))
				.withRel("update-pooling-point"));

		return ResponseEntity.ok(response);
	}

	// ================ Get all Pooling Points =================
	@GetMapping
	public ResponseEntity<CollectionModel<PoolingPointModel>> getAllPoolingPoints() {
		List<PoolingPointModel> responses = poolingPointService.getAllPoolingPoints();

		for (PoolingPointModel resp : responses) {
			resp.add(linkTo(methodOn(PoolingPointController.class).getPoolingPointById(resp.getId())).withSelfRel());
		}

		CollectionModel<PoolingPointModel> collectionModel = CollectionModel.of(responses);
		collectionModel.add(linkTo(methodOn(PoolingPointController.class).getAllPoolingPoints()).withSelfRel());
		collectionModel.add(linkTo(methodOn(PoolingPointController.class).createPoolingPoint(null))
				.withRel("create-pooling-point"));

		return ResponseEntity.ok(collectionModel);
	}

	// ================ Get Pooling Points by Service Area =================
	@GetMapping("/service-area/{serviceAreaId}")
	public ResponseEntity<CollectionModel<PoolingPointModel>> getPoolingPointsByServiceArea(
			@PathVariable Long serviceAreaId) {
		List<PoolingPointModel> responses = poolingPointService.getPoolingPointsByServiceArea(serviceAreaId);

		for (PoolingPointModel resp : responses) {
			resp.add(linkTo(methodOn(PoolingPointController.class).getPoolingPointById(resp.getId())).withSelfRel());
		}

		CollectionModel<PoolingPointModel> collectionModel = CollectionModel.of(responses);
		collectionModel.add(linkTo(methodOn(PoolingPointController.class).getPoolingPointsByServiceArea(serviceAreaId))
				.withSelfRel());
		collectionModel.add(
				linkTo(methodOn(PoolingPointController.class).getAllPoolingPoints()).withRel("all-pooling-points"));
		collectionModel.add(linkTo(methodOn(PoolingPointController.class).createPoolingPoint(null))
				.withRel("create-pooling-point"));

		return ResponseEntity.ok(collectionModel);
	}

	// ================ Update a Pooling Point =================
	@PutMapping("/{id}")
	public ResponseEntity<PoolingPointModel> updatePoolingPoint(@PathVariable Long id,
			@Valid @RequestBody PoolingPointModel request) {
		PoolingPointModel response = poolingPointService.updatePoolingPoint(id, request);

		// HATEOAS links
		response.add(linkTo(methodOn(PoolingPointController.class).getPoolingPointById(id)).withSelfRel());
		response.add(
				linkTo(methodOn(PoolingPointController.class).getAllPoolingPoints()).withRel("all-pooling-points"));

		return ResponseEntity.ok(response);
	}

	// ================ Delete a Pooling Point =================
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePoolingPoint(@PathVariable Long id) {
		poolingPointService.deletePoolingPoint(id);
		return ResponseEntity.noContent().build();
	}
}