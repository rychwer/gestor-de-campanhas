package br.com.richer.torcedor.controller;

import br.com.richer.torcedor.TorcedorServiceApplication;
import br.com.richer.torcedor.domain.Campanha;
import br.com.richer.torcedor.domain.Torcedor;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TorcedorServiceApplication.class)
public class TorcedorControllerTest {

    @Autowired
    private TorcedorController torcedorController;

    private MockMvc mockMvc;

    @Before
    public void before() {
        FixtureFactoryLoader.loadTemplates("br.com.richer.torcedor.template");
        mockMvc = MockMvcBuilders.standaloneSetup(torcedorController).build();
    }

    @Test
    public void validaCriacaoTorcedor() throws Exception {

        final Torcedor torcedor = Fixture.from(Torcedor.class).gimme("valido");

        MvcResult response = mockMvc.perform(post("/torcedor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson(torcedor)))
                .andExpect(status().isOk())
                .andReturn();

        final String content = response.getResponse().getContentAsString();

        Assert.assertFalse(content.isEmpty());
    }

    private String createJson(Object anObject) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(anObject);
    }
}
