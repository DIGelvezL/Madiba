package presentacion;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import entidades.Parte;
import entidades.Solicitud;
import negocio.iSolicitudBean;
import vo.ParteVO;
import vo.SolicitudResponseVO;

@ManagedBean 
public class SendMail
{
	@Resource(mappedName="java:jboss/mail/Gmail")
    private Session mailSession;
 
    @EJB
    private iSolicitudBean solicitudBean;
    
    public void enviarCitacion(List<Solicitud> auxSolicitud) throws IOException{
    	ControllerDocumento contDocumento = new ControllerDocumento();
    	
    	for (Solicitud solicitud : auxSolicitud) {
    		//Long long1 = soli.getIdSolicitud();
			//Solicitud solicitud = this.solicitudBean.findSolicitud(long1);
			
			Date fechaActual = new Date();
	    	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	    	String cadenaFecha = formato.format(fechaActual);
	    	
			int ultimaAudiencia =solicitud.getAudiencias().size()-1;
			int ultimaAgenda = solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().size()-1;
			String fechaAudiencia = solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().get(ultimaAgenda).getFecha().toString();
			
			
			if(fechaAudiencia.equals(cadenaFecha)){
				this.solicitudBean.actualizarEstadoSolicitud(solicitud.getIdSolicitud(), "AUDIENCIA-ENCURSO");
			}else{
				this.solicitudBean.actualizarEstadoSolicitud(solicitud.getIdSolicitud(), "AUDIENCIA-PENDIENTE");
			}
			
			//Envio de citacion al Conciliador
			String identificacionConciliador = solicitud.getDesignacions().get(solicitud.getDesignacions().size()-1).getConciliador().getIdentificacion();
			//String rutaCitacion="C:/Conalbos-Madiba/docs/Conciliador"+identificacionConciliador+".docx";
			//contDocumento.citaciones(rutaCitacion, "res", "bras", "");
			//contDocumento.citaciones(rutaCitacion, nombreConvocado, nombresConvocantes, asunto, direccionCitado, radicado, fechaAudiencia, horaAudiencia);
    		this.emailCitacionConciliador(solicitud.getDesignacions().get(solicitud.getDesignacions().size()-1).getConciliador().getCorreo(), "Conciliador", identificacionConciliador);

    		List<Parte> parte = solicitud.getPartes();
    		String nombresConvocantes = "";
    		for (Parte parte3 : parte) {
    			if(parte3.getTipoParte().equalsIgnoreCase("Convocante")){
    				nombresConvocantes+=parte3.getNombres()+" "+parte3.getApellidos()+", ";
    			}    			
    		}
			for (Parte parte2 : parte) {
				String rutaCitacion="C:/Conalbos-Madiba/docs/"+parte2.getIdentificacion()+".docx";
				String nombreConvocado = parte2.getNombres()+" "+parte2.getApellidos();
				
				String asunto = solicitud.getAsunto();
				String direccionCitado = parte2.getDireccion();
				String radicado = solicitud.getNroRadicado();
			
				ControllerSolicitud contSolicitud = new ControllerSolicitud();
				String horaAudiencia = contSolicitud.MascaraHora(solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().get(ultimaAgenda).getHoraInicial());
				
				contDocumento.citaciones(rutaCitacion, parte2.getTipoParte(), nombreConvocado, nombresConvocantes, asunto, direccionCitado, radicado, fechaAudiencia, horaAudiencia);
				this.emailCitacion(parte2.getCorreo(), parte2.getTipoParte(), parte2.getIdentificacion());
			}
		}
    	
    }
    
    /**
     * 
     * @param emailParte El email
     * @param rol El rol al que se le envia convocante, Convocado
     */
    public void emailCitacion(String emailParte, String rol, String identificacion) {
        {
 
            try    {
            	BodyPart texto = new MimeBodyPart();
            	texto.setText("Señor "+rol);
            	if(rol.equalsIgnoreCase("Conciliador")){
            		texto.setText("Señor conciliador usted se encuentra citado");
            	}
            	BodyPart adjunto = new MimeBodyPart();
            	//adjunto.setDataHandler(new DataHandler(new FileDataSource("C:/Users/Brayan Restrepo/Pictures/Prologa.doc")));
            	//adjunto.setFileName("Prologa.doc");
            	
        		//File file = new File("C:/Conalbos-Madiba/conalbos.png");
            	File file = new File("C:/Conalbos-Madiba/docs/"+identificacion+".docx");
        	    DataSource source = new FileDataSource(file);
        	    
            	adjunto.setDataHandler(new DataHandler(source));
            	adjunto.setFileName(identificacion+".docx");
            	
            	MimeMultipart multiParte = new MimeMultipart();

            	multiParte.addBodyPart(texto);
            	multiParte.addBodyPart(adjunto);
            	
            	MimeMessage m = new MimeMessage(mailSession);
                Address from = new InternetAddress("conalbos.madiba@gmail.com");
                Address[] to = new InternetAddress[] {new InternetAddress(emailParte) };
 
                m.setFrom(from);
                m.setRecipients(Message.RecipientType.TO, to);
                m.setSubject("Citacion de Audiencia - Conalbos");
               /* m.setSentDate(new java.util.Date());
                m.setContent("Mail sent from JBoss AS 7","text/plain");
                */
                m.setContent(multiParte);
                Transport.send(m);
            }
            catch (javax.mail.MessagingException e)
            {
                e.printStackTrace();
                System.out.println("Error in Sending Mail: "+e);
            }
        }
    }
    
    /**
     * 
     * @param emailParte El email
     * @param rol El rol al que se le envia Conciliador
     */
    public void emailCitacionConciliador(String emailParte, String rol, String identificacion) {
        {
 
            try    {
            	BodyPart texto = new MimeBodyPart();
            	texto.setText("Señor "+rol);
            	if(rol.equalsIgnoreCase("Conciliador")){
            		texto.setText("Señor conciliador usted se encuentra citado");
            	}
            	BodyPart adjunto = new MimeBodyPart();
            	//adjunto.setDataHandler(new DataHandler(new FileDataSource("C:/Users/Brayan Restrepo/Pictures/Prologa.doc")));
            	//adjunto.setFileName("Prologa.doc");
            	
            	MimeMultipart multiParte = new MimeMultipart();

            	multiParte.addBodyPart(texto);
            	
            	MimeMessage m = new MimeMessage(mailSession);
                Address from = new InternetAddress("conalbos.madiba@gmail.com");
                Address[] to = new InternetAddress[] {new InternetAddress(emailParte) };
 
                m.setFrom(from);
                m.setRecipients(Message.RecipientType.TO, to);
                m.setSubject("Citacion de Audiencia - Conalbos");
               /* m.setSentDate(new java.util.Date());
                m.setContent("Mail sent from JBoss AS 7","text/plain");
                */
                m.setContent(multiParte);
                Transport.send(m);
            }
            catch (javax.mail.MessagingException e)
            {
                e.printStackTrace();
                System.out.println("Error in Sending Mail: "+e);
            }
        }
    }
    
    public void emailSolicitudAceptada(SolicitudResponseVO solicitudResponseVO, Session sessionMail) {
    	 
        try    {
        	for(ParteVO parteVO: solicitudResponseVO.getParteVOList()){
        		if("Convocante".equals(parteVO.getTipoParte())){
        			String mailConvocante = parteVO.getCorreo();
        			
        			BodyPart texto = new MimeBodyPart();
                	texto.setText("Señor "+parteVO.getNombres() + " " + parteVO.getApellidos());
                	
                	BodyPart texto2 = new MimeBodyPart();
                	texto2.setText("La solicitud N. "+solicitudResponseVO.getSolicitudVO().getIdSolicitud() + " fue aceptada y debe pagar: " + solicitudResponseVO.getSolicitudVO().getValorPagar());
                	
                	BodyPart texto3 = new MimeBodyPart();
                	texto3.setText("lleve la confirmación del pago a la oficina para radicar su solicitud.");
                	
                	MimeMultipart multiParte = new MimeMultipart();
                	multiParte.addBodyPart(texto);
                	multiParte.addBodyPart(texto2);
                	multiParte.addBodyPart(texto3);
                	
                	MimeMessage m = new MimeMessage(sessionMail);
                    Address from = new InternetAddress("conalbos.madiba@gmail.com");
                    Address[] to = new InternetAddress[] {new InternetAddress(mailConvocante) };
         
                    m.setFrom(from);
                    m.setRecipients(Message.RecipientType.TO, to);
                    m.setSubject("Solicitud de conciliación aceptada - Conalbos");
                    m.setContent(multiParte);
                    
                    Transport.send(m);
        		}
        		
        	}
        }
        catch (javax.mail.MessagingException e)
        {
            e.printStackTrace();
            System.out.println("Error in Sending Mail: "+e);
        }
    }
    
    public void emailSolicitudNegada(SolicitudResponseVO solicitudResponseVO, Session sessionMail) {
 
        try    {
        	for(ParteVO parteVO: solicitudResponseVO.getParteVOList()){
        		if("Convocante".equals(parteVO.getTipoParte())){
        			String mailConvocante = parteVO.getCorreo();
        			
        			BodyPart texto = new MimeBodyPart();
                	texto.setText("Señor "+parteVO.getNombres() + " " + parteVO.getApellidos());
                	
                	BodyPart texto2 = new MimeBodyPart();
                	texto2.setText("La solicitud N. "+solicitudResponseVO.getSolicitudVO().getIdSolicitud() + " fue negada por el siguiente motivo: " + solicitudResponseVO.getSolicitudVO().getMotivo());
                	
                	MimeMultipart multiParte = new MimeMultipart();
                	multiParte.addBodyPart(texto);
                	multiParte.addBodyPart(texto2);
                	
                	MimeMessage m = new MimeMessage(sessionMail);
                    Address from = new InternetAddress("conalbos.madiba@gmail.com");
                    Address[] to = new InternetAddress[] {new InternetAddress(mailConvocante) };
         
                    m.setFrom(from);
                    m.setRecipients(Message.RecipientType.TO, to);
                    m.setSubject("Solicitud de conciliación negada - Conalbos");
                    m.setContent(multiParte);
                    
                    Transport.send(m);
        		}
        		
        	}
        }
        catch (javax.mail.MessagingException e)
        {
            e.printStackTrace();
            System.out.println("Error in Sending Mail: "+e);
        }
    }
}
