package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Auditoria.class)
public abstract class Auditoria_ {

	public static volatile SingularAttribute<Auditoria, String> accion;
	public static volatile SingularAttribute<Auditoria, Long> idAuditoria;
	public static volatile SingularAttribute<Auditoria, Date> fecha;
	public static volatile SingularAttribute<Auditoria, String> registroDesp;
	public static volatile SingularAttribute<Auditoria, String> responsable;
	public static volatile SingularAttribute<Auditoria, String> registroAnt;
	public static volatile SingularAttribute<Auditoria, String> tabla;
	public static volatile SingularAttribute<Auditoria, Integer> idRegistro;

}

