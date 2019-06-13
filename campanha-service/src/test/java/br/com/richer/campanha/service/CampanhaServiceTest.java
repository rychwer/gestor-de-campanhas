package br.com.richer.campanha.service;

import br.com.richer.campanha.CampanhaServiceApplication;
import br.com.richer.campanha.controller.exception.CampanhaDataInformadaInvalidaException;
import br.com.richer.campanha.domain.Campanha;
import br.com.richer.campanha.entity.CampanhaEntity;
import br.com.richer.campanha.queue.CampanhaQueueSender;
import br.com.richer.campanha.repository.CampanhaRepository;
import br.com.richer.campanha.service.impl.CampanhaServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CampanhaServiceApplication.class)
public class CampanhaServiceTest {

    private CampanhaService campanhaService;

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private ConversorService conversorService;

    @Autowired
    private CampanhaQueueSender campanhaQueueSender;

    @Before
    public void before() {

        CampanhaEntity entity1 = new CampanhaEntity();

        entity1.setDataVigencia(LocalDate.of(2019, 12, 03));
        entity1.setIdTimeCoracao(1L);
        entity1.setNomeCampanha("campanha1");

        CampanhaEntity entity2 = new CampanhaEntity();

        entity2.setDataVigencia(LocalDate.of(2019, 12, 02));
        entity2.setIdTimeCoracao(2L);
        entity2.setNomeCampanha("campanha2");

        campanhaRepository.save(entity1);
        campanhaRepository.save(entity2);

        this.campanhaService = new CampanhaServiceImpl(campanhaRepository, conversorService, campanhaQueueSender);
    }

    @Test
    public void validaRegraDataVigencia() {

        Campanha campanha = new Campanha();
        campanha.setIdTimeCoracao(3L);
        campanha.setDataVigencia(LocalDate.of(2019, 12, 03));
        campanha.setNomeCampanha("Campanha 3");

        this.campanhaService.criarCampanha(campanha);

        final Optional<CampanhaEntity> campanha1 = campanhaRepository.findById(1L);
        final Optional<CampanhaEntity> campanha2 = campanhaRepository.findById(2L);
        final Optional<CampanhaEntity> campanha3 = campanhaRepository.findById(3L);

        Assert.assertEquals(campanha1.get().getDataVigencia().getDayOfMonth(), 5);
        Assert.assertEquals(campanha2.get().getDataVigencia().getDayOfMonth(), 4);
        Assert.assertEquals(campanha3.get().getDataVigencia().getDayOfMonth(), 3);
    }

    @Test(expected = CampanhaDataInformadaInvalidaException.class)
    public void validaDataMenorQueDiaAtual() {

        Campanha campanha = new Campanha();
        campanha.setIdTimeCoracao(3L);
        campanha.setDataVigencia(LocalDate.of(2019, 01, 01));
        campanha.setNomeCampanha("Campanha 3");

        this.campanhaService.criarCampanha(campanha);

    }

}
