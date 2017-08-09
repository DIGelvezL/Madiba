package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Solicitud.class)
public abstract class Solicitud_ {

	public static volatile SingularAttribute<Solicitud, String> estado;
	public static volatile ListAttribute<Solicitud, Anexo> anexos;
	public static volatile ListAttribute<Solicitud, Parte> partes;
	public static volatile SingularAttribute<Solicitud, Long> idSolicitud;
	public static volatile SingularAttribute<Solicitud, Boolean> conciliable;
	public static volatile ListAttribute<Solicitud, Pago> pagos;
	public static volatile ListAttribute<Solicitud, Audiencia> audiencias;
	public static volatile SingularAttribute<Solicitud, Date> fecha;
	public static volatile SingularAttribute<Solicitud, String> nroRadicado;
	public static volatile ListAttribute<Solicitud, Designacion> designacions;
	public static volatile SingularAttribute<Solicitud, String> asunto;
	public static volatile SingularAttribute<Solicitud, Double> cuantia;
	public static volatile ListAttribute<Solicitud, Actas_Conciliacione> actasConciliaciones;
	public static volatile SingularAttribute<Solicitud, Double> valorPagar;
	public static volatile ListAttribute<Solicitud, Devolucione> devoluciones;

}

