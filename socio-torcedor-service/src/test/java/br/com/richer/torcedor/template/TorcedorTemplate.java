package br.com.richer.torcedor.template;

import br.com.richer.torcedor.domain.Torcedor;
import br.com.richer.torcedor.entity.TimeCoracaoEntity;
import br.com.richer.torcedor.entity.TorcedorEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.time.LocalDate;

public class TorcedorTemplate implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Torcedor.class).addTemplate("valido", new Rule(){{
            add("nomeCompleto", "Fulano Santos");
            add("email", "fulano@gmail.com");
            add("dataNascimento", LocalDate.of(2020, 05, 01));
            add("timeCoracao", random("Botafogo", "Vitória", "Palmeiras", "Inter"));
        }});

        Fixture.of(TorcedorEntity.class).addTemplate("entity", new Rule(){{
            add("nomeCompleto", "Fulano Santos");
            add("email", "fulano@gmail.com");
            add("dataNascimento", LocalDate.of(2020, 05, 01));
            add("timeCoracaoEntity", new TimeCoracaoEntity("Vitória"));
        }});
    }
}
