package br.com.richer.torcedor.service;

import br.com.richer.torcedor.TorcedorServiceApplication;
import br.com.richer.torcedor.controller.exception.TorcedorCadastradoException;
import br.com.richer.torcedor.domain.Campanha;
import br.com.richer.torcedor.domain.Torcedor;
import br.com.richer.torcedor.entity.CampanhaEntity;
import br.com.richer.torcedor.entity.TorcedorEntity;
import br.com.richer.torcedor.repository.CampanhaRepository;
import br.com.richer.torcedor.repository.TorcedorRepository;
import br.com.richer.torcedor.service.impl.TorcedorServiceImpl;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TorcedorServiceApplication.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.ANY, connection= EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TorcedorServiceTest {

    @Autowired
    private TorcedorRepository torcedorRepository;

    @Autowired
    private ConversorService conversorService;

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Mock
    private CampanhaClientService campanhaClientService;

    private TorcedorService torcedorService;

    @Before
    public void before() {

        MockitoAnnotations.initMocks(this);

        FixtureFactoryLoader.loadTemplates("br.com.richer.torcedor.template");

        this.torcedorService = new TorcedorServiceImpl(torcedorRepository, conversorService, campanhaClientService);

    }

    @Test
    public void validaTorcedorNovoAssociandoCampanhas() {

        List<Campanha> campanhaList = new ArrayList<>();
        final Torcedor torcedor = Fixture.from(Torcedor.class).gimme("valido");
        final Campanha campanha = Fixture.from(Campanha.class).gimme("valida");

        campanhaList.add(campanha);
        Mockito.when(campanhaClientService.listaCampanhasAtivasPorTimeCoracao(Mockito.anyLong())).thenReturn(campanhaList);

        this.torcedorService.criarTorcedor(torcedor);

        final Optional<TorcedorEntity> OptionalTorcedorEntity = torcedorRepository.findAllByEmail(torcedor.getEmail());
        final TorcedorEntity torcedorEntity = OptionalTorcedorEntity.get();

        Assert.assertNotNull(torcedorEntity);
        Assert.assertEquals(torcedorEntity.getCampanhas().size(), 1);


    }

    @Test
    public void validaTorcedorCadastradaSemCampanhaAssociada() {

        final Torcedor torcedor = Fixture.from(Torcedor.class).gimme("valido");

        List<Campanha> campanhaList = new ArrayList<>();
        final TorcedorEntity torcedorEntity = Fixture.from(TorcedorEntity.class).gimme("entity");
        final Campanha campanha = Fixture.from(Campanha.class).gimme("valida");

        campanhaList.add(campanha);
        Mockito.when(campanhaClientService.listaCampanhasAtivasPorTimeCoracao(Mockito.anyLong())).thenReturn(campanhaList);

        torcedorRepository.save(torcedorEntity);

        this.torcedorService.criarTorcedor(torcedor);

        final Optional<TorcedorEntity> optionalTorcedorEntity = torcedorRepository.findAllByEmail(torcedor.getEmail());
        final TorcedorEntity torcedorEntityAlterado = optionalTorcedorEntity.get();

        Assert.assertNotNull(torcedorEntityAlterado);
        Assert.assertEquals(torcedorEntityAlterado.getCampanhas().size(), 1);

    }

    @Test(expected = TorcedorCadastradoException.class)
    public void validaTorcedorCadastradaComAssociacao() {
        final Torcedor torcedor = Fixture.from(Torcedor.class).gimme("valido");

        List<CampanhaEntity> campanhaListEntity = new ArrayList<>();
        final TorcedorEntity torcedorEntity = Fixture.from(TorcedorEntity.class).gimme("entity");
        final CampanhaEntity campanhaEntity = Fixture.from(CampanhaEntity.class).gimme("entity");

        campanhaListEntity.add(campanhaEntity);
        torcedorEntity.setCampanhas(campanhaListEntity);

        torcedorRepository.save(torcedorEntity);

        this.torcedorService.criarTorcedor(torcedor);

    }

}
