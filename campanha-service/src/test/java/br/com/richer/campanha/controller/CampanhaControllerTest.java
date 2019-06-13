package br.com.richer.campanha.controller;

import br.com.richer.campanha.CampanhaServiceApplication;
import br.com.richer.campanha.domain.Campanha;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CampanhaServiceApplication.class)
public class CampanhaControllerTest {

    @Autowired
    private CampanhaController campanhaController;

    private MockMvc mockMvc;

    @Before
    public void before() {
        FixtureFactoryLoader.loadTemplates("br.com.richer.campanha.template");
        mockMvc = MockMvcBuilders.standaloneSetup(campanhaController).build();
    }

    @Test
    public void validaCriacaoCampanha() throws Exception {

        final Campanha campanha = Fixture.from(Campanha.class).gimme("valida");

        ResultActions response = mockMvc.perform(put("/campanha")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson(campanha)));

        response.andExpect(status().isCreated());
    }

    private String createJson(Object anObject) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(anObject);
    }

}
