package com.example.produtos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.produtos.model.ProdutoModel;
import com.example.produtos.repository.ProdutoRespository;

@Service
public class ProdutoService {

	final ProdutoRespository produtoRespository;

	public ProdutoService(ProdutoRespository produtoRespository) {
		this.produtoRespository = produtoRespository;
	}

	public List<ProdutoModel> findAll() {
		return produtoRespository.findAll();
	}

	public ProdutoModel save(ProdutoModel produto) {
		return produtoRespository.save(produto);
	}

	public Optional<ProdutoModel> findOne(Long id) {
		return produtoRespository.findById(id);
	}

	public void delete(ProdutoModel produtoModel) {
		produtoRespository.delete(produtoModel);
	}
}
