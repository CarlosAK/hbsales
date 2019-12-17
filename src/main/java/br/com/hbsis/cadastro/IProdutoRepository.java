package br.com.hbsis.cadastro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProdutoRepository extends JpaRepository<Produto, Long> {

    boolean existsByCodProduto(String codProduto);

    Optional<Produto> findByCodProduto(String codProduto);

    boolean existsById(Long id);

    Optional<Produto> findById(Long id);

}
