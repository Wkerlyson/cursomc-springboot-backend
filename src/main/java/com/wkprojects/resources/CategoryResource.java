package com.wkprojects.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wkprojects.domain.Category;
import com.wkprojects.dto.CategoryDTO;
import com.wkprojects.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> categories = service.findAll();
		List<CategoryDTO> listDto = categories.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> find(@PathVariable Integer id) {
		Category obj = service.find(id);
		return ResponseEntity.ok(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Category category) {
		category = service.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Category category, @PathVariable Integer id) {
		service.update(category, id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
