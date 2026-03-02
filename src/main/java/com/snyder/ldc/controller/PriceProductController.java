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

import com.snyder.ldc.model.PriceProductModel;
import com.snyder.ldc.service.PriceProductService;

import jakarta.validation.Valid;

//========== PRICE PRODUCT CONTROLLER ==========
@RestController
@RequestMapping("/api/v1/ldc/price-products")
@Validated
@CrossOrigin(origins = "*")
public class PriceProductController {

	@Autowired
	private PriceProductService priceProductService;

	// ================ Create a new Price Product =================
	@PostMapping
	public ResponseEntity<PriceProductModel> createPriceProduct(@Valid @RequestBody PriceProductModel request) {
		PriceProductModel response = priceProductService.createPriceProduct(request);

		// HATEOAS links
		response.add(
				linkTo(methodOn(PriceProductController.class).getPriceProductById(response.getId())).withSelfRel());
		response.add(
				linkTo(methodOn(PriceProductController.class).getAllPriceProducts()).withRel("all-price-products"));
		response.add(linkTo(methodOn(PriceProductController.class).updatePriceProduct(response.getId(), request))
				.withRel("update-price-product"));

		return ResponseEntity.status(201).body(response);
	}

	// ================ Get Price Product by ID =================
	@GetMapping("/{id}")
	public ResponseEntity<PriceProductModel> getPriceProductById(@PathVariable Long id) {
		PriceProductModel response = priceProductService.getPriceProductById(id);

		// HATEOAS links
		response.add(linkTo(methodOn(PriceProductController.class).getPriceProductById(id)).withSelfRel());
		response.add(
				linkTo(methodOn(PriceProductController.class).getAllPriceProducts()).withRel("all-price-products"));
		response.add(linkTo(methodOn(PriceProductController.class).updatePriceProduct(id, null))
				.withRel("update-price-product"));

		return ResponseEntity.ok(response);
	}

	// ================ Get all Price Products =================
	@GetMapping
	public ResponseEntity<CollectionModel<PriceProductModel>> getAllPriceProducts() {
		List<PriceProductModel> responses = priceProductService.getAllPriceProducts();

		for (PriceProductModel resp : responses) {
			resp.add(linkTo(methodOn(PriceProductController.class).getPriceProductById(resp.getId())).withSelfRel());
		}

		CollectionModel<PriceProductModel> collectionModel = CollectionModel.of(responses);
		collectionModel.add(linkTo(methodOn(PriceProductController.class).getAllPriceProducts()).withSelfRel());
		collectionModel.add(linkTo(methodOn(PriceProductController.class).createPriceProduct(null))
				.withRel("create-price-product"));

		return ResponseEntity.ok(collectionModel);
	}

	// ================ Get Price Products by Pooling Point =================
	@GetMapping("/pooling-point/{poolingPointId}")
	public ResponseEntity<CollectionModel<PriceProductModel>> getPriceProductsByPoolingPoint(
			@PathVariable Long poolingPointId) {
		List<PriceProductModel> responses = priceProductService.getPriceProductsByPoolingPoint(poolingPointId);

		for (PriceProductModel resp : responses) {
			resp.add(linkTo(methodOn(PriceProductController.class).getPriceProductById(resp.getId())).withSelfRel());
		}

		CollectionModel<PriceProductModel> collectionModel = CollectionModel.of(responses);
		collectionModel
				.add(linkTo(methodOn(PriceProductController.class).getPriceProductsByPoolingPoint(poolingPointId))
						.withSelfRel());
		collectionModel.add(
				linkTo(methodOn(PriceProductController.class).getAllPriceProducts()).withRel("all-price-products"));
		collectionModel.add(linkTo(methodOn(PriceProductController.class).createPriceProduct(null))
				.withRel("create-price-product"));

		return ResponseEntity.ok(collectionModel);
	}

	// ================ Update a Price Product =================
	@PutMapping("/{id}")
	public ResponseEntity<PriceProductModel> updatePriceProduct(@PathVariable Long id,
			@Valid @RequestBody PriceProductModel request) {
		PriceProductModel response = priceProductService.updatePriceProduct(id, request);

		// HATEOAS links
		response.add(linkTo(methodOn(PriceProductController.class).getPriceProductById(id)).withSelfRel());
		response.add(
				linkTo(methodOn(PriceProductController.class).getAllPriceProducts()).withRel("all-price-products"));

		return ResponseEntity.ok(response);
	}

	// ================ Delete a Price Product =================
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePriceProduct(@PathVariable Long id) {
		priceProductService.deletePriceProduct(id);
		return ResponseEntity.noContent().build();
	}
}