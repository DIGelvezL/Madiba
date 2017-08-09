package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Audconcil.class)
public abstract class Audconcil_ {

	public static volatile SingularAttribute<Audconcil, String> radicado;
	public static volatile SingularAttribute<Audconcil, Long> idconciliador;
	public static volatile SingularAttribute<Audconcil, String> tipoparte;
	public static volatile SingularAttribute<Audconcil, Date> fechaaudiencia;
	public static volatile SingularAttribute<Audconcil, String> parte;
	public static volatile SingularAttribute<Audconcil, Integer> horafinaudiencia;
	public static volatile SingularAttribute<Audconcil, Integer> horainiaudiencia;
	public static volatile SingularAttribute<Audconcil, Integer> pretensiones;
	public static volatile SingularAttribute<Audconcil, Date> fechasolicitud;
	public static volatile SingularAttribute<Audconcil, String> estadoaudiencia;
	public static volatile SingularAttribute<Audconcil, String> sala;
	public static volatile SingularAttribute<Audconcil, String> tipodesignacion;
	public static volatile SingularAttribute<Audconcil, String> asunto;
	public static volatile SingularAttribute<Audconcil, Integer> numeroaudiencia;
	public static volatile SingularAttribute<Audconcil, Long> id;
	public static volatile SingularAttribute<Audconcil, String> conciliador;

}

