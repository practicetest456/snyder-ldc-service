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

import com.snyder.ldc.model.LdcTariffModel;
import com.snyder.ldc.service.LdcTariffService;

import jakarta.validation.Valid;

//========== LDC TARIFF CONTROLLER ==========
@RestController
@RequestMapping("/api/v1/ldc/tariffs")
@Validated
@CrossOrigin(origins = "*")
public class LdcTariffController {

	@Autowired
	private LdcTariffService tariffService;

	// ================ Create a new tariff =================
	@PostMapping
	public ResponseEntity<LdcTariffModel> createTariff(@Valid @RequestBody LdcTariffModel request) {
		LdcTariffModel response = tariffService.createTariff(request);

		// HATEOAS links
		response.add(linkTo(methodOn(LdcTariffController.class).getTariffById(response.getId())).withSelfRel());
		response.add(linkTo(methodOn(LdcTariffController.class).getAllTariffs()).withRel("all-tariffs"));
		response.add(linkTo(methodOn(LdcTariffController.class).updateTariff(response.getId(), request))
				.withRel("update-tariff"));

		return ResponseEntity.status(201).body(response);
	}

	// ================ Get tariff by ID =================
	@GetMapping("/{id}")
	public ResponseEntity<LdcTariffModel> getTariffById(@PathVariable Long id) {
		LdcTariffModel response = tariffService.getTariffById(id);

		// HATEOAS links
		response.add(linkTo(methodOn(LdcTariffController.class).getTariffById(id)).withSelfRel());
		response.add(linkTo(methodOn(LdcTariffController.class).getAllTariffs()).withRel("all-tariffs"));
		response.add(linkTo(methodOn(LdcTariffController.class).updateTariff(id, null)).withRel("update-tariff"));

		return ResponseEntity.ok(response);
	}

	// ================ Get all tariffs =================
	@GetMapping
	public ResponseEntity<CollectionModel<LdcTariffModel>> getAllTariffs() {
		List<LdcTariffModel> responses = tariffService.getAllTariffs();

		// Add self link for each tariff
		for (LdcTariffModel resp : responses) {
			resp.add(linkTo(methodOn(LdcTariffController.class).getTariffById(resp.getId())).withSelfRel());
		}

		// Wrap in CollectionModel and add collection-level links
		CollectionModel<LdcTariffModel> collectionModel = CollectionModel.of(responses);
		collectionModel.add(linkTo(methodOn(LdcTariffController.class).getAllTariffs()).withSelfRel());
		collectionModel.add(linkTo(methodOn(LdcTariffController.class).createTariff(null)).withRel("create-tariff"));

		return ResponseEntity.ok(collectionModel);
	}

	// ================ Get tariffs by meter type =================
	@GetMapping("/meter-type/{meterTypeId}")
	public ResponseEntity<CollectionModel<LdcTariffModel>> getTariffsByMeterType(@PathVariable Long meterTypeId) {
		List<LdcTariffModel> responses = tariffService.getTariffsByMeterType(meterTypeId);

		for (LdcTariffModel resp : responses) {
			resp.add(linkTo(methodOn(LdcTariffController.class).getTariffById(resp.getId())).withSelfRel());
		}

		CollectionModel<LdcTariffModel> collectionModel = CollectionModel.of(responses);
		collectionModel
				.add(linkTo(methodOn(LdcTariffController.class).getTariffsByMeterType(meterTypeId)).withSelfRel());
		collectionModel.add(linkTo(methodOn(LdcTariffController.class).getAllTariffs()).withRel("all-tariffs"));
		collectionModel.add(linkTo(methodOn(LdcTariffController.class).createTariff(null)).withRel("create-tariff"));

		return ResponseEntity.ok(collectionModel);
	}

	// ================ Update a tariff =================
	@PutMapping("/{id}")
	public ResponseEntity<LdcTariffModel> updateTariff(@PathVariable Long id,
			@Valid @RequestBody LdcTariffModel request) {
		LdcTariffModel response = tariffService.updateTariff(id, request);

		// HATEOAS links
		response.add(linkTo(methodOn(LdcTariffController.class).getTariffById(id)).withSelfRel());
		response.add(linkTo(methodOn(LdcTariffController.class).getAllTariffs()).withRel("all-tariffs"));

		return ResponseEntity.ok(response);
	}

	// ================ Delete a tariff =================
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTariff(@PathVariable Long id) {
		tariffService.deleteTariff(id);
		return ResponseEntity.noContent().build();
	}
}