package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Copia.class)
public abstract class Copia_ {

	public static volatile SingularAttribute<Copia, Long> idCopia;
	public static volatile SingularAttribute<Copia, Date> fechaGeneracion;
	public static volatile SingularAttribute<Copia, Integer> numCopia;
	public static volatile SingularAttribute<Copia, Actas_Conciliacione> actasConciliacione;

}

