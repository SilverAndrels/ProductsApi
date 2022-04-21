package com.example.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.produtos.model.ProdutoModel;

@Repository
public interface ProdutoRespository extends JpaRepository<ProdutoModel, Long> {

}
