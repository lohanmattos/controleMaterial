package com.material.controleMaterial.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.material.controleMaterial.model.Produto;


@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, String> {
		
	
	@Query(value = "SELECT * FROM tb_produto",  nativeQuery = true)
	List<Produto> findAllProdutoListado();

	
	@Query(value = "SELECT * FROM tb_produto", countQuery = "select count(p) from tb_produto p",  nativeQuery = true)
	Page<Produto> findAllProdutoListadoPaginado(Pageable pageable);
	
}
