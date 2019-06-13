package br.com.richer.torcedor.template;

import br.com.richer.torcedor.domain.Campanha;
import br.com.richer.torcedor.entity.CampanhaEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.time.LocalDate;

public class CampanhaTemplate implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Campanha.class).addTemplate("valida", new Rule(){{
            add("nomeCampanha", "Campanha válida");
            add("idTimeCoracao", random(Long.class, range(1L, 200L)));
            add("dataVigencia", LocalDate.of(2020, 05, 01));
        }});

        Fixture.of(CampanhaEntity.class).addTemplate("entity", new Rule(){{
            add("nomeCampanha", "Campanha válida");
            add("idTimeCoracao", random(Long.class, range(1L, 200L)));
            add("dataVigencia", LocalDate.of(2020, 05, 01));
        }});
    }
}
