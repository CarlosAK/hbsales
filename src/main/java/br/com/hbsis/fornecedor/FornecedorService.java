package br.com.hbsis.fornecedor;


import br.com.hbsis.categoria.Categoria;
import br.com.hbsis.categoria.CategoriaDTO;
import br.com.hbsis.categoria.CategoriaService;
import br.com.hbsis.categoria.ICategoriaRepository;
import com.microsoft.sqlserver.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FornecedorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FornecedorService.class);

    private final IFornecedorRepository iFornecedorRepository;
    private final CategoriaService categoriaService;
    private final ICategoriaRepository iCategoriaRepository;

    @Autowired
    public FornecedorService(IFornecedorRepository iFornecedorRepository, CategoriaService categoriaService, ICategoriaRepository iCategoriaRepository) {
        this.iFornecedorRepository = iFornecedorRepository;
        this.categoriaService = categoriaService;
        this.iCategoriaRepository = iCategoriaRepository;
    }

    public FornecedorDTO save(FornecedorDTO fornecedorDTO) {
        this.validate(fornecedorDTO);
        LOGGER.info("Salvando fornecedor");
        LOGGER.debug("Fornecedor: {}", fornecedorDTO);

        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setRazaoSocial(fornecedorDTO.getRazaoSocial());
        fornecedor.setCnpj(fornecedorDTO.getCnpj());
        fornecedor.setNome(fornecedorDTO.getNome());
        fornecedor.setEndereco(fornecedorDTO.getEndereco());
        fornecedor.setTelefone(fornecedorDTO.getTelefone());
        fornecedor.setEmail(fornecedorDTO.getEmail());

        fornecedor = this.iFornecedorRepository.save(fornecedor);
        return FornecedorDTO.of(fornecedor);
    }

    private void validate(FornecedorDTO fornecedorDTO) {
        LOGGER.info("Validando Fornecedor");

        if (fornecedorDTO == null) {
            throw new IllegalArgumentException("FornecedorDTO não deve ser nulo/vazio");

        }

        if (StringUtils.isEmpty(fornecedorDTO.getRazaoSocial())) {
            throw new IllegalArgumentException("Razão Social não deve ser nula/vazia");

        }

        if (StringUtils.isEmpty(fornecedorDTO.getCnpj())) {
            throw new IllegalArgumentException("CNPJ não deve ser nulo/vazio");

        }

        if (fornecedorDTO.getCnpj().length() != 14) {
            throw new IllegalArgumentException("CNPJ deve conter 14 números");

        }

        if (StringUtils.isEmpty(fornecedorDTO.getNome())) {
            throw new IllegalArgumentException("Nome fantasia não deve ser nulo/vazio");
        }

        if (StringUtils.isEmpty(fornecedorDTO.getEndereco())) {
            throw new IllegalArgumentException("Endereço não deve ser nulo/vazio");

        }

        if (StringUtils.isEmpty(fornecedorDTO.getTelefone())) {
            throw new IllegalArgumentException("Telefone não deve ser nulo/vazio");

        }

        if (fornecedorDTO.getTelefone().length() > 14 || fornecedorDTO.getTelefone().length() < 13) {
            throw new IllegalArgumentException("Telefone deve conter entre 13 e 14 números");

        }

        if (StringUtils.isEmpty(fornecedorDTO.getEmail())) {
            throw new IllegalArgumentException("Email não deve ser nulo/vazio");

        }

    }

    public FornecedorDTO findById(Long id) {
        Optional<Fornecedor> fornecedorOptional = this.iFornecedorRepository.findById(id);

        if (fornecedorOptional.isPresent()) {
            return FornecedorDTO.of(fornecedorOptional.get());

        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));

    }

    public Fornecedor findByFornecedorId(Long id) {
        Optional<Fornecedor> fornecedorOptional = this.iFornecedorRepository.findById(id);

        if (fornecedorOptional.isPresent()) {
            return fornecedorOptional.get();

        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));

    }

    public Fornecedor findByFornecedorCnpj(String cnpj) {
        Optional<Fornecedor> fornecedorOptional = this.iFornecedorRepository.findByCnpj(cnpj);

        if (fornecedorOptional.isPresent()) {
            return fornecedorOptional.get();

        }

        throw new IllegalArgumentException(String.format("Cnpj %s não existe", cnpj));

    }

    public FornecedorDTO update(FornecedorDTO fornecedorDTO, Long id) {
        Optional<Fornecedor> fornecedorExistenteOptional = this.iFornecedorRepository.findById(id);
        this.validate(fornecedorDTO);

        if (fornecedorExistenteOptional.isPresent()) {

            Fornecedor fornecedorExistente = fornecedorExistenteOptional.get();
            Fornecedor fornecedor = new Fornecedor();
            List<Categoria> categorias = new ArrayList<>();
            fornecedor = findByFornecedorId(id);

            categorias = iCategoriaRepository.findByFornecedor(fornecedor);
            LOGGER.info("Atualizando fornecedor... id: [{}]", fornecedorExistente.getId());
            LOGGER.debug("Payload: {}", fornecedorDTO);
            LOGGER.debug("Fornecedor Existente: {}", fornecedorExistente);

            fornecedorExistente.setRazaoSocial(fornecedorDTO.getRazaoSocial());
            fornecedorExistente.setCnpj(fornecedorDTO.getCnpj());
            fornecedorExistente.setNome(fornecedorDTO.getNome());
            fornecedorExistente.setEndereco(fornecedorDTO.getEndereco());
            fornecedorExistente.setTelefone(fornecedorDTO.getTelefone());
            fornecedorExistente.setEmail(fornecedorDTO.getEmail());

            fornecedorExistente = this.iFornecedorRepository.save(fornecedorExistente);

            for (Categoria categoria : categorias) {
                categoria.setCodCategoria(categoria.getCodCategoria().substring(7, 10));
                categoria.setFornecedor(fornecedorExistente);

                categoriaService.update(CategoriaDTO.of(categoria), categoria.getId());

            }

            return FornecedorDTO.of(fornecedorExistente);

        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));

    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para fornecedor de ID: [{}]", id);

        this.iFornecedorRepository.deleteById(id);

    }


}