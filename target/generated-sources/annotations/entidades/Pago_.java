package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pago.class)
public abstract class Pago_ {

	public static volatile SingularAttribute<Pago, Date> fecha;
	public static volatile SingularAttribute<Pago, String> estado;
	public static volatile SingularAttribute<Pago, Solicitud> solicitud;
	public static volatile SingularAttribute<Pago, Long> idPago;
	public static volatile SingularAttribute<Pago, Double> valor;
	public static volatile SingularAttribute<Pago, Integer> banco;
	public static volatile SingularAttribute<Pago, Integer> formaPago;
	public static volatile SingularAttribute<Pago, Integer> referencia;

}

