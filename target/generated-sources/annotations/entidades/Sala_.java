package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sala.class)
public abstract class Sala_ {

	public static volatile SingularAttribute<Sala, String> estado;
	public static volatile ListAttribute<Sala, Agenda> agendas;
	public static volatile SingularAttribute<Sala, Integer> idSala;
	public static volatile SingularAttribute<Sala, String> nombre;

}

