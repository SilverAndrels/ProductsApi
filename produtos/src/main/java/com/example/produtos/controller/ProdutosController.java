package com.example.produtos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.produtos.model.ProdutoModel;
import com.example.produtos.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

	ProdutoService produtoService;

	public ProdutosController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping
	public ResponseEntity<List<ProdutoModel>> getAllProducts() {
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") Long id) {
		Optional<ProdutoModel> produtoModel = produtoService.findOne(id);
		if (!produtoModel.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(produtoModel.get());
	}

	@PostMapping
	public ProdutoModel saveProducts(@RequestBody ProdutoModel produto) {
		return produtoService.save(produto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateProduto(@PathVariable(value = "id") Long id,
			@RequestBody ProdutoModel produto) {
		Optional<ProdutoModel> produtoModelOptional = produtoService.findOne(id);
		if (!produtoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
		}
		ProdutoModel produtoModel = produtoModelOptional.get();
		produtoModel.setNome(produto.getNome());
		produtoModel.setMarca(produto.getMarca());
		produtoModel.setCor(produto.getCor());
		produtoModel.setPreco(produto.getPreco());
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produtoModel));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> updateProduto(@PathVariable(value = "id") Long id) {
		Optional<ProdutoModel> produtoModelOptional = produtoService.findOne(id);
		if (!produtoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
		}
		produtoService.delete(produtoModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Produto excluído!");
	}
}
