package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Parte.class)
public abstract class Parte_ {

	public static volatile SingularAttribute<Parte, String> apellidos;
	public static volatile SingularAttribute<Parte, String> tipoId;
	public static volatile SingularAttribute<Parte, Solicitud> solicitud;
	public static volatile SingularAttribute<Parte, String> correo;
	public static volatile SingularAttribute<Parte, String> direccion;
	public static volatile SingularAttribute<Parte, String> tipoParte;
	public static volatile SingularAttribute<Parte, String> identificacion;
	public static volatile SingularAttribute<Parte, String> telefono;
	public static volatile ListAttribute<Parte, Asistencia> asistencias;
	public static volatile SingularAttribute<Parte, Long> idParte;
	public static volatile SingularAttribute<Parte, String> nombres;

}

