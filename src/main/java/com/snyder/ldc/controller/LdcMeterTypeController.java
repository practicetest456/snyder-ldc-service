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

import com.snyder.ldc.model.LdcMeterTypeModel;
import com.snyder.ldc.service.LdcMeterTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/ldc/meter-types")
@Validated
@CrossOrigin(origins = "*")
public class LdcMeterTypeController {

	@Autowired
	private LdcMeterTypeService meterTypeService;

	// CREATE
	@PostMapping
	public ResponseEntity<LdcMeterTypeModel> createMeterType(@Valid @RequestBody LdcMeterTypeModel request) {
		LdcMeterTypeModel response = meterTypeService.createMeterType(request);

		// HATEOAS links
		response.add(linkTo(methodOn(LdcMeterTypeController.class).getMeterTypeById(response.getId())).withSelfRel());
		response.add(linkTo(methodOn(LdcMeterTypeController.class).getAllMeterTypes()).withRel("all-meter-types"));
		response.add(linkTo(methodOn(LdcMeterTypeController.class).updateMeterType(response.getId(), request))
				.withRel("update-meter-type"));

		return ResponseEntity.status(201).body(response);
	}

	// GET BY ID
	@GetMapping("/{id}")
	public ResponseEntity<LdcMeterTypeModel> getMeterTypeById(@PathVariable Long id) {
		LdcMeterTypeModel response = meterTypeService.getMeterTypeById(id);

		response.add(linkTo(methodOn(LdcMeterTypeController.class).getMeterTypeById(id)).withSelfRel());
		response.add(linkTo(methodOn(LdcMeterTypeController.class).getAllMeterTypes()).withRel("all-meter-types"));
		response.add(
				linkTo(methodOn(LdcMeterTypeController.class).updateMeterType(id, null)).withRel("update-meter-type"));

		return ResponseEntity.ok(response);
	}

	// GET ALL
	@GetMapping
	public ResponseEntity<CollectionModel<LdcMeterTypeModel>> getAllMeterTypes() {
		List<LdcMeterTypeModel> responses = meterTypeService.getAllMeterTypes();

		// Add self link for each resource
		for (LdcMeterTypeModel resp : responses) {
			resp.add(linkTo(methodOn(LdcMeterTypeController.class).getMeterTypeById(resp.getId())).withSelfRel());
		}

		CollectionModel<LdcMeterTypeModel> collectionModel = CollectionModel.of(responses);
		collectionModel.add(linkTo(methodOn(LdcMeterTypeController.class).getAllMeterTypes()).withSelfRel());
		collectionModel
				.add(linkTo(methodOn(LdcMeterTypeController.class).createMeterType(null)).withRel("create-meter-type"));

		return ResponseEntity.ok(collectionModel);
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<LdcMeterTypeModel> updateMeterType(@PathVariable Long id,
			@Valid @RequestBody LdcMeterTypeModel request) {
		LdcMeterTypeModel response = meterTypeService.updateMeterType(id, request);

		response.add(linkTo(methodOn(LdcMeterTypeController.class).getMeterTypeById(id)).withSelfRel());
		response.add(linkTo(methodOn(LdcMeterTypeController.class).getAllMeterTypes()).withRel("all-meter-types"));

		return ResponseEntity.ok(response);
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMeterType(@PathVariable Long id) {
		meterTypeService.deleteMeterType(id);
		return ResponseEntity.noContent().build();
	}
}