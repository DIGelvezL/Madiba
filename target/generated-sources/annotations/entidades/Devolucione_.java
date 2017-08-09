package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Devolucione.class)
public abstract class Devolucione_ {

	public static volatile SingularAttribute<Devolucione, Date> fecha;
	public static volatile SingularAttribute<Devolucione, Boolean> devolucion;
	public static volatile SingularAttribute<Devolucione, Solicitud> solicitud;
	public static volatile SingularAttribute<Devolucione, Double> valor;
	public static volatile SingularAttribute<Devolucione, Long> idDevolucion;

}

