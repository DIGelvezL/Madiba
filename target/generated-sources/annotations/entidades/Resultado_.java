package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Resultado.class)
public abstract class Resultado_ {

	public static volatile SingularAttribute<Resultado, String> conclusion;
	public static volatile SingularAttribute<Resultado, Double> nuevaCuantia;
	public static volatile SingularAttribute<Resultado, String> tipoResultado;
	public static volatile SingularAttribute<Resultado, Long> idResultado;
	public static volatile SingularAttribute<Resultado, Audiencia> audiencia;

}

