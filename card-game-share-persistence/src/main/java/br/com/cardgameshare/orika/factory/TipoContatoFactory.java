package br.com.cardgameshare.orika.factory;

import br.com.cardgameshare.entity.TipoContato;
import br.com.cardgameshare.repository.ContatoRepository;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.ObjectFactory;
import ma.glasnost.orika.metadata.Type;

import javax.persistence.EntityManager;

public class TipoContatoFactory implements ObjectFactory<TipoContato> {

    private ContatoRepository contatoRepository;

    public TipoContatoFactory(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public TipoContato create(Object o, MappingContext mappingContext) {
        return this.contatoRepository.find((Long) o);
    }

}
