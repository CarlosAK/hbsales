package br.com.hbsis.cadastro;


import br.com.hbsis.categoria.Categoria;
import br.com.hbsis.categoria.CategoriaService;
import br.com.hbsis.categoria.ICategoriaRepository;
import br.com.hbsis.fornecedor.FornecedorService;
import br.com.hbsis.fornecedor.IFornecedorRepository;
import br.com.hbsis.linhaCategoria.ILinhaCategoriaRepository;
import br.com.hbsis.linhaCategoria.LinhaCategoria;
import br.com.hbsis.linhaCategoria.LinhaCategoriaService;
import com.opencsv.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProdutoService<produto, iProdutoRepository> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoService.class);

    private final IProdutoRepository iProdutoRepository;
    private final ILinhaCategoriaRepository iLinhaCategoriaRepository;
    private final ICategoriaRepository iCategoriaRepository;
    private final IFornecedorRepository iFornecedorRepository;
    private final LinhaCategoriaService linhaCategoriaService;
    private final CategoriaService categoriaService;
    private final FornecedorService fornecedorService;


    @Autowired
    public ProdutoService(IProdutoRepository iProdutoRepository, ILinhaCategoriaRepository iLinhaCategoriaRepository, ICategoriaRepository iCategoriaRepository, IFornecedorRepository iFornecedorRepository, LinhaCategoriaService linhaCategoriaService, CategoriaService categoriaService, FornecedorService fornecedorService) {
        this.iProdutoRepository = iProdutoRepository;
        this.iLinhaCategoriaRepository = iLinhaCategoriaRepository;
        this.iCategoriaRepository = iCategoriaRepository;
        this.iFornecedorRepository = iFornecedorRepository;
        this.linhaCategoriaService = linhaCategoriaService;
        this.categoriaService = categoriaService;
        this.fornecedorService = fornecedorService;
    }

    public List<Produto> saveAll(List<Produto> produto) throws Exception {
        return iProdutoRepository.saveAll(produto);

    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        this.validate(produtoDTO);

        LOGGER.info("Salvando Produto");
        LOGGER.debug("Produto {}", produtoDTO);

        Produto produto = new Produto();

        String codigorecebido = produtoDTO.getCodProduto();
        String codigoComZero = validarCodigo(codigorecebido);
        String codFinal = codigoComZero.toUpperCase();

        produto.setCodProduto(codFinal);
        produto.setNomeProduto(produtoDTO.getNomeProduto());
        produto.setPrecoProduto(produtoDTO.getPrecoProduto());
        produto.setUnidadeCx(produtoDTO.getUnidadeCx());
        produto.setPesoUn(produtoDTO.getPesoUn());
        produto.setValidade(produtoDTO.getValidade());
        produto.setUnidadeMedida(produtoDTO.getUnidadeMedida());
        produto.setLinhaCategoria(linhaCategoriaService.findByLinhaCategoriaId(produtoDTO.getLinhaCategoria()));

        produto = this.iProdutoRepository.save(produto);
        return ProdutoDTO.of(produto);


    }

    public String validarCodigo(String codigo) {
        String codigoProcessador = StringUtils.leftPad(codigo, 10, "0");
        return codigoProcessador;

    }

    public ProdutoDTO finById(Long id) {
        Optional<Produto> produtoOptional = this.iProdutoRepository.findById(id);

        if (((Optional) produtoOptional).isPresent()) {
            return ProdutoDTO.of(produtoOptional.get());

        }

        throw new IllegalArgumentException(String.format("esse %s não existe", id));

    }

    public Produto findByProdutoId(Long id) {
        Optional<Produto> produtoOptional = this.iProdutoRepository.findById(id);

        if (produtoOptional.isPresent()) {
            return produtoOptional.get();

        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));

    }

    public Produto findByCodProduto(String codProduto) {
        Optional<Produto> produtoOptional = this.iProdutoRepository.findByCodProduto(codProduto);

        if (produtoOptional.isPresent()) {
            return produtoOptional.get();

        }

        throw new IllegalArgumentException(String.format("Codigo produto %s não existe", codProduto));

    }

    public ProdutoDTO update(ProdutoDTO produtoDTO, Long id) {
        Optional<Produto> produtoExistenteOptional = this.iProdutoRepository.findById(id);

        this.validate(produtoDTO);

        if (produtoExistenteOptional.isPresent()) {
            Produto produtoExistente = produtoExistenteOptional.get();

            LOGGER.info("Atualizando produto... id:[{}]", produtoExistente.getId());
            LOGGER.debug("Payload: {}", produtoDTO);
            LOGGER.debug("produto Existente:{}", produtoExistente);

            String codigorecebido = produtoDTO.getCodProduto();
            String codigoComZero = validarCodigo(codigorecebido);
            String codFinal = codigoComZero.toUpperCase();

            produtoExistente.setCodProduto(codFinal);
            produtoExistente.setNomeProduto(produtoDTO.getNomeProduto());
            produtoExistente.setPrecoProduto(produtoDTO.getPrecoProduto());
            produtoExistente.setUnidadeCx(produtoDTO.getUnidadeCx());
            produtoExistente.setPesoUn(produtoDTO.getPesoUn());
            produtoExistente.setValidade(produtoDTO.getValidade());
            produtoExistente.setLinhaCategoria(linhaCategoriaService.findByLinhaCategoriaId(produtoDTO.getLinhaCategoria()));

            produtoExistente = iProdutoRepository.save(produtoExistente);
            return ProdutoDTO.of(produtoExistente);

        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));

    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para categoria de ID: [{}]", id);

        iProdutoRepository.deleteById(id);

    }

    public void csvToProdutoExport(HttpServletResponse response) throws Exception {

        String arquivo = " csv_produtos.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo + "\"");
        PrintWriter Writer = response.getWriter();

        ICSVWriter icsvWriter = new CSVWriterBuilder(Writer).withSeparator(';').withEscapeChar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                .withLineEnd(CSVWriter.DEFAULT_LINE_END).build();

        String titulo[] = {"código", "nome", "preço", "quantidade de unidades por caixa", "peso", "validade", " código da linha de categoria", "nome da Linha de Categoria",
                "código da Categoria", " nome da Categoria", "CNPJ", "razão social", ""};

        icsvWriter.writeNext(titulo);

        for (Produto linha : iProdutoRepository.findAll()) {
            icsvWriter.writeNext(new String[]{linha.getCodProduto(), linha.getNomeProduto(), "R$" + (linha.getPrecoProduto()), String.valueOf(linha.getUnidadeCx()), linha.getPesoUn() + linha.getUnidadeMedida(),
                    String.valueOf(linha.getValidade().format(DateTimeFormatter.ofPattern("dd/MM/yyy"))), linha.getLinhaCategoria().getCodLinhaCategoria(), linha.getLinhaCategoria().getNomeLinhaCategoria(), linha.getLinhaCategoria().getCategoria().getCodCategoria(),
                    linha.getLinhaCategoria().getCategoria().getNomeCategoria(), mascaraCnpj(linha.getLinhaCategoria().getCategoria().getFornecedor().getCnpj()), linha.getLinhaCategoria().getCategoria().getFornecedor().getRazaoSocial()

            });

        }

    }

    public void csvToProdutoFornecedorExport(HttpServletResponse response) throws Exception {

        String arquivo = " csv_produtos-fornecedor.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo + "\"");
        PrintWriter Writer = response.getWriter();

        ICSVWriter icsvWriter = new CSVWriterBuilder(Writer).withSeparator(';').withEscapeChar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                .withLineEnd(CSVWriter.DEFAULT_LINE_END).build();

        String titulo[] = {"código", "nome", "preço", "quantidade de unidades por caixa", "peso ", "validade", " código da linha de categoria", "nome da Linha de Categoria",
                "código da Categoria", "nome categoria"};

        icsvWriter.writeNext(titulo);

        for (Produto linha : iProdutoRepository.findAll()) {
            icsvWriter.writeNext(new String[]{linha.getCodProduto(), linha.getNomeProduto(), "R$" + (linha.getPrecoProduto()), String.valueOf(linha.getUnidadeCx()), linha.getPesoUn() + linha.getUnidadeMedida(),
                    String.valueOf(linha.getValidade().format(DateTimeFormatter.ofPattern("dd/MM/yyy"))), linha.getLinhaCategoria().getCodLinhaCategoria(), linha.getLinhaCategoria().getNomeLinhaCategoria(), linha.getLinhaCategoria().getCategoria().getCodCategoria(),
                    linha.getLinhaCategoria().getCategoria().getNomeCategoria()
            });

        }

    }

    public String mascaraCnpj(String CNPJ) {
        String mascara = CNPJ.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        return mascara;

    }

    public List<Produto> csvToProduto(MultipartFile file) throws Exception {
        InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
        CSVReader csvReader = new CSVReaderBuilder(inputStreamReader)
                .withSkipLines(1).build();

        List<String[]> linhaString = csvReader.readAll();
        List<Produto> resultadoLeitura = new ArrayList<>();

        for (String[] linha : linhaString) {

            try {

                String[] dados = linha[0].replaceAll("\"", "").split(";");

                Produto produto = new Produto();

                LinhaCategoria linhaCategoria = new LinhaCategoria();

                String codProduto = dados[0];
                String nomeProduto = dados[1];
                double preco = Double.parseDouble(dados[2].trim().replace(',', '.').replaceAll("[A-Z$]", ""));
                int uniCaixa = Integer.parseInt(dados[3]);
                double pesoUni = Double.parseDouble(dados[4].replaceAll("[A-Za-z]", ""));
                String unidadeMedida = dados[4].replaceAll("[0-9]", "");
                String codLinhaCategoria = dados[6];
                String data = dados[5].toString();
                int dia = Integer.parseInt(data.substring(0, 2));
                int mes = Integer.parseInt(data.substring(3, 5));
                int ano = Integer.parseInt(data.substring(6, 10));
                LocalDate datavalidade = LocalDate.of(ano, mes, dia);

                if (iProdutoRepository.existsByCodProduto(codProduto)) {
                    LOGGER.info("Produto: {}", codProduto + " já existe");

                } else if (!iProdutoRepository.existsByCodProduto(dados[6])) {
                    produto.setCodProduto((codProduto));
                    produto.setNomeProduto(nomeProduto);
                    produto.setPrecoProduto(preco);
                    produto.setUnidadeCx(uniCaixa);
                    produto.setPesoUn(pesoUni);
                    produto.setUnidadeMedida(unidadeMedida);
                    produto.setValidade(datavalidade);

                    linhaCategoria = linhaCategoriaService.findByLinhaCategoriaCodLinhaCategoria(codLinhaCategoria);
                    produto.setLinhaCategoria(linhaCategoria);
                    resultadoLeitura.add(produto);

                }

            } catch (Exception e) {
                e.printStackTrace();

            }

        }

        return iProdutoRepository.saveAll(resultadoLeitura);

    }

    public void importFromCsvProduto(MultipartFile file) throws Exception {
        List<Produto> produtos = this.csvToProduto(file);
        this.saveAll(produtos);

    }

    public void csvToProdutoFornecedor(Long id, MultipartFile file) throws Exception {
        InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
        CSVReader csvReader = new CSVReaderBuilder(inputStreamReader)
                .withSkipLines(1).build();

        List<String[]> linhaString = csvReader.readAll();

        for (String[] linha : linhaString) {

            try {

                String[] resultado = linha[0].replaceAll("\"", "").split(";");

                Produto produto = new Produto();

                LinhaCategoria linhaCategoria = new LinhaCategoria();

                Categoria categoria = new Categoria();

                String codProduto = resultado[0];
                String nomeProduto = resultado[1];
                double preco = Double.parseDouble(resultado[2].trim().replace(',', '.').replaceAll("[A-Z$]", ""));
                int uniCaixa = Integer.parseInt(resultado[3]);
                double peso = Double.parseDouble(resultado[4].replaceAll("[A-Za-z]", ""));
                String unimedida = resultado[4].replaceAll("[0-9.]", "");
                String data = resultado[5].toString();
                int dia = Integer.parseInt(data.substring(0, 2));
                int mes = Integer.parseInt(data.substring(3, 5));
                int ano = Integer.parseInt(data.substring(6, 10));
                LocalDate datavalidade = LocalDate.of(ano, mes, dia);
                String codLinhaCategoria = resultado[6];
                String nomeLinha = resultado[7];
                String codigocategoria = resultado[8];
                String nomeCategoria = resultado[9];

                if (iFornecedorRepository.existsById(id)) {

                    if (!(iCategoriaRepository.existsByCodigoCategoria(codigocategoria))) {
                        categoria.setNomeCategoria(nomeCategoria);
                        categoria.setCodCategoria(codigocategoria);
                        categoria.setFornecedor(fornecedorService.findByFornecedorId(id));

                        iCategoriaRepository.save(categoria);

                    } else if (iCategoriaRepository.existsByCodigoCategoria(codigocategoria)) {
                        categoria = categoriaService.findByCodigoCategoria(codigocategoria);
                        Optional<Categoria> categoriaOptional = this.iCategoriaRepository.findByCodigoCategoria(codigocategoria);

                        if (categoriaOptional.isPresent()) {
                            Categoria categoriaExistente = categoriaOptional.get();
                            LOGGER.info("Alterando Categoria... id:{}", categoria.getId());
                            LOGGER.debug("Payload: {}", categoria);
                            LOGGER.debug("Categoria Existente: {}", categoria);

                            categoriaExistente.setCodCategoria(codigocategoria);
                            categoriaExistente.setNomeCategoria(nomeCategoria);
                            categoriaExistente.setFornecedor(fornecedorService.findByFornecedorId(id));

                            iCategoriaRepository.save(categoria);

                        }

                    }

                    if (!(iLinhaCategoriaRepository.existsByCodLinhaCategoria(codLinhaCategoria))) {
                        linhaCategoria.setCodLinhaCategoria(codLinhaCategoria);
                        linhaCategoria.setNomeLinhaCategoria(nomeLinha);
                        categoria = categoriaService.findByCodigoCategoria(codigocategoria);

                        iLinhaCategoriaRepository.save(linhaCategoria);

                    } else if (iLinhaCategoriaRepository.existsByCodLinhaCategoria(codLinhaCategoria)) {
                        linhaCategoria = linhaCategoriaService.findByLinhaCategoriaCodLinhaCategoria(codLinhaCategoria);

                        Optional<LinhaCategoria> linhaCategoriaOptional = this.iLinhaCategoriaRepository.findByCodLinhaCategoria(codLinhaCategoria);

                        LOGGER.info("Alterando linha... id:{}", linhaCategoria.getIdLinhaCategoria());
                        LOGGER.debug("Payload: {}", linhaCategoria);
                        LOGGER.debug("Linha Categoria Existente: {}", linhaCategoria);

                        if (linhaCategoriaOptional.isPresent()) {
                            LinhaCategoria linhaExistente = linhaCategoriaOptional.get();
                            linhaExistente.setCodLinhaCategoria(codLinhaCategoria);
                            linhaExistente.setNomeLinhaCategoria(nomeLinha);

                            categoria = categoriaService.findByCodigoCategoria(codigocategoria);

                            iLinhaCategoriaRepository.save(linhaCategoria);

                        }

                    }

                    if (iProdutoRepository.existsByCodProduto(codProduto)) {
                        produto = findByCodProduto(codProduto);

                        Optional<Produto> produtoOptional = this.iProdutoRepository.findByCodProduto(codProduto);
                        LOGGER.info("Atualizando produto... id:[{}]", produto.getId());
                        LOGGER.debug("Payload: {}");
                        LOGGER.debug("produto Existente:{}", produto);

                        if (produtoOptional.isPresent()) {
                            Produto produtoExistente = produtoOptional.get();

                            produtoExistente.setCodProduto(codProduto);
                            produtoExistente.setNomeProduto(nomeProduto);
                            produtoExistente.setPrecoProduto(preco);
                            produtoExistente.setUnidadeCx(uniCaixa);
                            produtoExistente.setPesoUn(peso);
                            produtoExistente.setUnidadeMedida(unimedida);
                            produtoExistente.setValidade(datavalidade);

                            linhaCategoria = linhaCategoriaService.findByLinhaCategoriaCodLinhaCategoria(codLinhaCategoria);
                            System.out.println(produto);

                        }

                    } else if (!iProdutoRepository.existsByCodProduto(codProduto)) {

                        produto.setCodProduto(codProduto);
                        produto.setNomeProduto(nomeProduto);
                        produto.setPrecoProduto(preco);
                        produto.setUnidadeCx(uniCaixa);
                        produto.setPesoUn(peso);
                        produto.setUnidadeMedida(unimedida);
                        produto.setValidade(datavalidade);

                        linhaCategoria = linhaCategoriaService.findByLinhaCategoriaCodLinhaCategoria(codLinhaCategoria);
                        produto.setLinhaCategoria(linhaCategoria);

                        iProdutoRepository.save(produto);

                    } else {

                        LOGGER.info("Produto {} Pretence a outro Fornecedor", produto.getId());

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();

            }

        }

    }

    private void validate(ProdutoDTO produtoDTO) {

        if (produtoDTO == null) {
            throw new IllegalArgumentException("ProdutoDTO não deve ser nulo/vazio");

        }

        if (StringUtils.isEmpty(produtoDTO.getCodProduto())) {
            throw new IllegalArgumentException("Código do produto não pode ser nulo/vazio");

        }

        if (StringUtils.isEmpty(produtoDTO.getNomeProduto())) {
            throw new IllegalArgumentException("O nome do produto nao deve ser nulo/vazio");

        }

        if (StringUtils.isEmpty(String.valueOf(produtoDTO.getPesoUn()))) {
            throw new IllegalArgumentException("O peso unitário não deve ser nulo/vazio");

        }

        if (StringUtils.isEmpty(String.valueOf(produtoDTO.getPrecoProduto()))) {
            throw new IllegalArgumentException("O preço não deve ser nulo/vazio");

        }

        if (StringUtils.isEmpty(String.valueOf(produtoDTO.getUnidadeCx()))) {
            throw new IllegalArgumentException("A unidade por caixa não deve ser nulo/vazio");

        }

        if (StringUtils.isEmpty(String.valueOf(produtoDTO.getValidade()))) {
            throw new IllegalArgumentException("A data de validade do produto não deve ser nulo/vazio");

        }

        if (StringUtils.isEmpty(String.valueOf(produtoDTO.getLinhaCategoria()))) {
            throw new IllegalArgumentException("Linha do produto não cadastrada");

        }

        if (StringUtils.isEmpty(produtoDTO.getUnidadeMedida())) {
            throw new IllegalArgumentException("Unidade de medida não pode ser nulo/vazio");

        }

        switch (produtoDTO.getUnidadeMedida()) {
            case "mg":
            case "g":
            case "Kg":
                break;

            default:
                throw new IllegalArgumentException("Unidade de medida não permitida");

        }

    }
}