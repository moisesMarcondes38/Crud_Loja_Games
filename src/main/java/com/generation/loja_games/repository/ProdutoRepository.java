package com.generation.loja_games.repository;

import com.generation.loja_games.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public List<Produto> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}
