package com.material.controleMaterial.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.material.controleMaterial.model.Produto;


@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, String> {


	
}
