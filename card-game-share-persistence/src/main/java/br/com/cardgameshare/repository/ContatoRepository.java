package br.com.cardgameshare.repository;

import br.com.cardgameshare.dto.ContatoDTO;
import br.com.cardgameshare.entity.Contato;
import br.com.cardgameshare.entity.TipoContato;
import br.com.cardgameshare.orika.converter.StringConverter;
import br.com.cardgameshare.orika.factory.TipoContatoFactory;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;

public class ContatoRepository extends Repository {

    public ContatoRepository(EntityManager em) {
        super.em = em;
    }

    public TipoContato find(Long id) {
        return super.em.find(TipoContato.class, id);
    }

    public void salvar(ContatoDTO dto) {

        super.em.getTransaction().begin();

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new StringConverter());
        mapperFactory.registerObjectFactory(new TipoContatoFactory(this), TipoContato.class);
        mapperFactory.classMap(ContatoDTO.class, Contato.class)
                .field("mensagem", "mensagem")
                .field("nome", "nome")
                .field("email", "email")
                .fieldAToB("motivo", "tipoContato")
                .register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        Contato contato = mapperFacade.map(dto, Contato.class);

        super.em.merge(contato);

        super.em.getTransaction().commit();
        super.em.close();

    }

    public List<TipoContato> listarTiposContato() {
        Session session = super.em.unwrap(Session.class);
        Criteria criteriaTipoContato = session.createCriteria(TipoContato.class);
        List<TipoContato> listaTipoContato = criteriaTipoContato.list();
        super.em.close();
        return listaTipoContato;
    }

}
