package br.com.hbsis.cadastro;

import br.com.hbsis.fornecedor.FornecedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/produtos")

public class ProdutoRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoRest.class);

    private final ProdutoService produtoService;
    private final FornecedorService fornecedorService;

    @Autowired
    public ProdutoRest(ProdutoService produtoService, FornecedorService fornecedorService) {
        this.produtoService = produtoService;
        this.fornecedorService = fornecedorService;
    }

    @PostMapping
    public ProdutoDTO save(@RequestBody ProdutoDTO produtoDTO) {
        LOGGER.info("Recebendo solicitação de persistência de produtos...");
        LOGGER.debug("Payload: {}", produtoDTO);

        return this.produtoService.save(produtoDTO);

    }

    @GetMapping("/{id}")
    private ProdutoDTO find(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo find by ID... id:[{}]", id);

        return this.produtoService.finById(id);

    }

    @PutMapping("/{id}")
    public ProdutoDTO update(@PathVariable("id") Long id, @RequestBody ProdutoDTO produtoDTO) {
        LOGGER.info("Recebendo Update para produto de ID: {}", id);
        LOGGER.debug("payload: {}", produtoDTO);

        return this.produtoService.update(produtoDTO, id);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo delete para Produto de ID: {} ", id);

        this.produtoService.delete(id);

    }

    @GetMapping("/exporta-produtos")
    public void findAll(HttpServletResponse response) throws Exception {
        produtoService.csvToProdutoExport(response);

    }

    @GetMapping("/exporta-produto-fornecedor")
    public void findAllFornecedor(HttpServletResponse response) throws Exception {
        produtoService.csvToProdutoFornecedorExport(response);

    }

    @PostMapping("/importa-produtos")
    public void importCSV(@RequestParam("file") MultipartFile file) throws Exception {
        produtoService.importFromCsvProduto(file);

    }

    @PostMapping("/importa-produtos-fornecedor/{id}")
    public void importCSVFornecedor(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws Exception {
        produtoService.csvToProdutoFornecedor(id, file);

    }

}



