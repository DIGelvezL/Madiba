package entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Conciliador.class)
public abstract class Conciliador_ {

	public static volatile SingularAttribute<Conciliador, String> apellidos;
	public static volatile SingularAttribute<Conciliador, String> tipoId;
	public static volatile SingularAttribute<Conciliador, String> egresado;
	public static volatile SingularAttribute<Conciliador, String> identificacion;
	public static volatile ListAttribute<Conciliador, Reparto> repartos;
	public static volatile SingularAttribute<Conciliador, String> nombres;
	public static volatile SingularAttribute<Conciliador, Long> idConciliador;
	public static volatile ListAttribute<Conciliador, Conciliador_Especialidad> conciliadorEspecialidads;
	public static volatile SingularAttribute<Conciliador, String> foto;
	public static volatile ListAttribute<Conciliador, Designacion> designacions;
	public static volatile SingularAttribute<Conciliador, String> correo;
	public static volatile SingularAttribute<Conciliador, Integer> experiencia;
	public static volatile SingularAttribute<Conciliador, String> telefono;

}

