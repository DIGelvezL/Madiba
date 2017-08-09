package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Designacion.class)
public abstract class Designacion_ {

	public static volatile SingularAttribute<Designacion, Boolean> aceptada;
	public static volatile SingularAttribute<Designacion, Solicitud> solicitud;
	public static volatile SingularAttribute<Designacion, String> tipoDesignacion;
	public static volatile SingularAttribute<Designacion, Long> idDesignacion;
	public static volatile SingularAttribute<Designacion, String> observacion;
	public static volatile SingularAttribute<Designacion, Conciliador> conciliador;

}

