package br.com.hbsis.categoria;

import br.com.hbsis.fornecedor.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByCodigoCategoria(String codigoCategoria);

    Optional<Categoria> findByCodigoCategoria(String codigoCategoria);

    List<Categoria> findByFornecedor(Fornecedor fornecedor);

}