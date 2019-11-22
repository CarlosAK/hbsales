package br.com.hbsis.fornecedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {
    private IFornecedorRepository iFornecedorRepository;

    @Autowired
    public FornecedorService(IFornecedorRepository iFornecedorRepository){
        this.iFornecedorRepository = iFornecedorRepository;
    }

    public FornecedorDTO save(FornecedorDTO fornecedorDTO){
        Fornecedor fornecedor = new Fornecedor(
                fornecedorDTO.getRazaoSocial(),
                fornecedorDTO.getCNPJ(),
                fornecedorDTO.getNomeFantasia(),
                fornecedorDTO.getEndereco(),
                fornecedorDTO.getTelefone(),
                fornecedorDTO.getEmail()
        );

        fornecedor = this.iFornecedorRepository.save(fornecedor);
       return Fornecedor.of(fornecedor);

    }
}
