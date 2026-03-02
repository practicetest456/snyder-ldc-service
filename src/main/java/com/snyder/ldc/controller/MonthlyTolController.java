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

import com.snyder.ldc.model.MonthlyTolModel;
import com.snyder.ldc.service.MonthlyTolService;

import jakarta.validation.Valid;

//========== MONTHLY TOL CONTROLLER ==========
@RestController
@RequestMapping("/api/v1/ldc/monthly-tol")
@Validated
@CrossOrigin(origins = "*")
public class MonthlyTolController {

	@Autowired
	private MonthlyTolService monthlyTolService;

	// ================ Create a new Monthly TOL =================
	@PostMapping
	public ResponseEntity<MonthlyTolModel> createMonthlyTol(@Valid @RequestBody MonthlyTolModel request) {
		MonthlyTolModel response = monthlyTolService.createMonthlyTol(request);

		// HATEOAS links
		response.add(linkTo(methodOn(MonthlyTolController.class).getMonthlyTolById(response.getId())).withSelfRel());
		response.add(linkTo(methodOn(MonthlyTolController.class).getAllMonthlyTols()).withRel("all-monthly-tols"));
		response.add(linkTo(methodOn(MonthlyTolController.class).updateMonthlyTol(response.getId(), request))
				.withRel("update-monthly-tol"));

		return ResponseEntity.status(201).body(response);
	}

	// ================ Get Monthly TOL by ID =================
	@GetMapping("/{id}")
	public ResponseEntity<MonthlyTolModel> getMonthlyTolById(@PathVariable Long id) {
		MonthlyTolModel response = monthlyTolService.getMonthlyTolById(id);

		// HATEOAS links
		response.add(linkTo(methodOn(MonthlyTolController.class).getMonthlyTolById(id)).withSelfRel());
		response.add(linkTo(methodOn(MonthlyTolController.class).getAllMonthlyTols()).withRel("all-monthly-tols"));
		response.add(
				linkTo(methodOn(MonthlyTolController.class).updateMonthlyTol(id, null)).withRel("update-monthly-tol"));

		return ResponseEntity.ok(response);
	}

	// ================ Get all Monthly TOLs =================
	@GetMapping
	public ResponseEntity<CollectionModel<MonthlyTolModel>> getAllMonthlyTols() {
		List<MonthlyTolModel> responses = monthlyTolService.getAllMonthlyTols();

		// Add self link for each record
		for (MonthlyTolModel resp : responses) {
			resp.add(linkTo(methodOn(MonthlyTolController.class).getMonthlyTolById(resp.getId())).withSelfRel());
		}

		CollectionModel<MonthlyTolModel> collectionModel = CollectionModel.of(responses);
		collectionModel.add(linkTo(methodOn(MonthlyTolController.class).getAllMonthlyTols()).withSelfRel());
		collectionModel
				.add(linkTo(methodOn(MonthlyTolController.class).createMonthlyTol(null)).withRel("create-monthly-tol"));

		return ResponseEntity.ok(collectionModel);
	}

	// ================ Get Monthly TOLs by Meter ID =================
	@GetMapping("/meter/{meterId}")
	public ResponseEntity<CollectionModel<MonthlyTolModel>> getMonthlyTolsByMeterId(@PathVariable Long meterId) {
		List<MonthlyTolModel> responses = monthlyTolService.getMonthlyTolsByMeterId(meterId);

		for (MonthlyTolModel resp : responses) {
			resp.add(linkTo(methodOn(MonthlyTolController.class).getMonthlyTolById(resp.getId())).withSelfRel());
		}

		CollectionModel<MonthlyTolModel> collectionModel = CollectionModel.of(responses);
		collectionModel
				.add(linkTo(methodOn(MonthlyTolController.class).getMonthlyTolsByMeterId(meterId)).withSelfRel());
		collectionModel
				.add(linkTo(methodOn(MonthlyTolController.class).getAllMonthlyTols()).withRel("all-monthly-tols"));
		collectionModel
				.add(linkTo(methodOn(MonthlyTolController.class).createMonthlyTol(null)).withRel("create-monthly-tol"));

		return ResponseEntity.ok(collectionModel);
	}

	// ================ Update a Monthly TOL =================
	@PutMapping("/{id}")
	public ResponseEntity<MonthlyTolModel> updateMonthlyTol(@PathVariable Long id,
			@Valid @RequestBody MonthlyTolModel request) {
		MonthlyTolModel response = monthlyTolService.updateMonthlyTol(id, request);

		// HATEOAS links
		response.add(linkTo(methodOn(MonthlyTolController.class).getMonthlyTolById(id)).withSelfRel());
		response.add(linkTo(methodOn(MonthlyTolController.class).getAllMonthlyTols()).withRel("all-monthly-tols"));

		return ResponseEntity.ok(response);
	}

	// ================ Delete a Monthly TOL =================
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMonthlyTol(@PathVariable Long id) {
		monthlyTolService.deleteMonthlyTol(id);
		return ResponseEntity.noContent().build();
	}
}