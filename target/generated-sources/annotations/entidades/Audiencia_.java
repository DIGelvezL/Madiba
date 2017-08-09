package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Audiencia.class)
public abstract class Audiencia_ {

	public static volatile SingularAttribute<Audiencia, Integer> audienciaNum;
	public static volatile SingularAttribute<Audiencia, String> estado;
	public static volatile ListAttribute<Audiencia, Resultado> resultados;
	public static volatile ListAttribute<Audiencia, Agenda> agendas;
	public static volatile SingularAttribute<Audiencia, Solicitud> solicitud;
	public static volatile SingularAttribute<Audiencia, Long> idAudiencia;
	public static volatile ListAttribute<Audiencia, Asistencia> asistencias;

}

