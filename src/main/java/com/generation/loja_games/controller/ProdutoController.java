package com.generation.loja_games.controller;

import com.generation.loja_games.model.Produto;
import com.generation.loja_games.repository.CategoriaRepository;
import com.generation.loja_games.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins="*", allowedHeaders="*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity <List<Produto>> getAll(){
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id){
        return produtoRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(produtoRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping
    public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto){
        if(produtoRepository.existsById(produto.getCategoria().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(produtoRepository.save(produto));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping
    public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto){
        if(produtoRepository.existsById(produto.getId())) {

            if(categoriaRepository.existsById(produto.getCategoria().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(produtoRepository.save(produto));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        produtoRepository.deleteById(id);
    }

}
