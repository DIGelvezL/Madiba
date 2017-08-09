package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Actas_Conciliacione.class)
public abstract class Actas_Conciliacione_ {

	public static volatile SingularAttribute<Actas_Conciliacione, Date> fechaExpedicion;
	public static volatile SingularAttribute<Actas_Conciliacione, String> tipo;
	public static volatile SingularAttribute<Actas_Conciliacione, String> ruta;
	public static volatile SingularAttribute<Actas_Conciliacione, Long> idActaConcil;
	public static volatile SingularAttribute<Actas_Conciliacione, Solicitud> solicitud;
	public static volatile ListAttribute<Actas_Conciliacione, Copia> copias;
	public static volatile SingularAttribute<Actas_Conciliacione, Integer> limiteCopias;

}

