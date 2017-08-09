package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Agenda.class)
public abstract class Agenda_ {

	public static volatile SingularAttribute<Agenda, Date> fecha;
	public static volatile SingularAttribute<Agenda, Integer> horaInicial;
	public static volatile SingularAttribute<Agenda, Sala> sala;
	public static volatile SingularAttribute<Agenda, Long> idAgenda;
	public static volatile SingularAttribute<Agenda, Integer> horaFinal;
	public static volatile SingularAttribute<Agenda, Audiencia> audiencia;

}

