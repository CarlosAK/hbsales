package br.com.hbsis.periodoVenda;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class PeriodoVendaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodoVendaService.class);
    private final IPeriodoVendaRepository iPeriodoVendaRepository;

    public PeriodoVendaService(IPeriodoVendaRepository iPeriodoVendaRepository) {
        this.iPeriodoVendaRepository = iPeriodoVendaRepository;

    }

    public PeriodoVendaDTO save(PeriodoVendaDTO periodoVendaDTO) {
        this.validate(periodoVendaDTO);

        LOGGER.info("Salvando venda");
        LOGGER.debug("PeriodoVenda: {}", periodoVendaDTO);

        PeriodoVenda periodoVenda = new PeriodoVenda();

        periodoVenda.setId(periodoVendaDTO.getId());
        periodoVenda.setDataInicio(periodoVendaDTO.getDataInicio());
        periodoVenda.setDataFinal(periodoVendaDTO.getDataFinal());
        periodoVenda.setDataRetirada(periodoVendaDTO.getDataRetirada());
        periodoVenda.setDescricao(periodoVendaDTO.getDescricao());

        periodoVenda = this.iPeriodoVendaRepository.save(periodoVenda);
        return PeriodoVendaDTO.of(periodoVenda);

    }

    public void validate(PeriodoVendaDTO periodoVendaDTO) {
        LOGGER.info("Validando Venda");

        if (periodoVendaDTO == null) {
            throw new IllegalArgumentException("Periodo Venda não pode ser nulo/vazio.");

        }

        if (StringUtils.isEmpty(String.valueOf(periodoVendaDTO.getDataInicio()))) {
            throw new IllegalArgumentException("Data inicio não pode ser nulo/vazio");

        }

        if (StringUtils.isEmpty(String.valueOf(periodoVendaDTO.getDataFinal()))) {
            throw new IllegalArgumentException("Data final não pode ser nulo/vazio");

        }

        if (StringUtils.isEmpty(String.valueOf(periodoVendaDTO.getDataRetirada()))) {
            throw new IllegalArgumentException("Data de retirada não deve ser nulo/vazio");

        }

        if (StringUtils.isEmpty(periodoVendaDTO.getDescricao())) {
            throw new IllegalArgumentException("Descrição não deve ser nulo/vazio");

        }

        if (StringUtils.isEmpty(String.valueOf(periodoVendaDTO.getFornecedor()))) {
            throw new IllegalArgumentException("Fornecedor não deve ser nulo/vazio");

        }

        if (periodoVendaDTO.getDataInicio().isBefore(LocalDate.now()) || periodoVendaDTO.getDataFinal().isBefore(LocalDate.now()) || periodoVendaDTO.getDataRetirada().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("As datas não podem ser inferior a de hoje");

        }

        if (iPeriodoVendaRepository.existDateAberta(periodoVendaDTO.getDataInicio(), periodoVendaDTO.getFornecedor()) >= 1) {
            throw new IllegalArgumentException("O fornecedor não pode ter dois periodos de venda ao mesmo tempo");

        }

        if (periodoVendaDTO.getDataFinal().isBefore(periodoVendaDTO.getDataInicio())) {
            throw new IllegalArgumentException("Data final não pode ser anterior que a dara de inicio");

        }

        if (periodoVendaDTO.getDataRetirada().isBefore(periodoVendaDTO.getDataFinal())) {
            throw new IllegalArgumentException("A data  de retirada não pode ser anterior a de final");

        }

    }

    public PeriodoVendaDTO findById(Long id) {
        Optional<PeriodoVenda> periodoVendaOptional = this.iPeriodoVendaRepository.findById(id);

        if (periodoVendaOptional.isPresent()) {
            return PeriodoVendaDTO.of(periodoVendaOptional.get());

        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));

    }

    public PeriodoVenda findByPeriodoVendaId(Long id) {
        Optional<PeriodoVenda> periodoVendaOptional = this.iPeriodoVendaRepository.findById(id);

        if (periodoVendaOptional.isPresent()) {
            return periodoVendaOptional.get();

        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));

    }

    public PeriodoVendaDTO update(PeriodoVendaDTO periodoVendaDTO, Long id) {
        Optional<PeriodoVenda> periodoVendaExistenteOptional = this.iPeriodoVendaRepository.findById(id);

        if (periodoVendaExistenteOptional.isPresent()) {
            PeriodoVenda periodoVendaExistente = periodoVendaExistenteOptional.get();

            LOGGER.info("Atualizando periodo de venda... id: [{}]", periodoVendaExistente.getId());
            LOGGER.debug("Payload: {}", periodoVendaDTO);
            LOGGER.debug("Usuario Existente: {}", periodoVendaExistente);

            periodoVendaExistente.setDataInicio(periodoVendaDTO.getDataInicio());
            periodoVendaExistente.setDataFinal(periodoVendaDTO.getDataFinal());
            periodoVendaExistente.setDataRetirada(periodoVendaDTO.getDataRetirada());

            periodoVendaExistente = this.iPeriodoVendaRepository.save(periodoVendaExistente);
            return PeriodoVendaDTO.of(periodoVendaExistente);

        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));

    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para o periodo de venda: {}", id);

        this.iPeriodoVendaRepository.deleteById(id);

    }

}


