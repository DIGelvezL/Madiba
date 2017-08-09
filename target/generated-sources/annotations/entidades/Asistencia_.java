package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Asistencia.class)
public abstract class Asistencia_ {

	public static volatile SingularAttribute<Asistencia, String> excusa;
	public static volatile SingularAttribute<Asistencia, Long> idAsistencia;
	public static volatile SingularAttribute<Asistencia, Boolean> asistio;
	public static volatile SingularAttribute<Asistencia, Parte> parte;
	public static volatile SingularAttribute<Asistencia, Audiencia> audiencia;

}

