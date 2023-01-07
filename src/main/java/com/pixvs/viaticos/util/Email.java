package com.pixvs.viaticos.util;

import com.pixvs.viaticos.dao.viaticos.GeneralScalarDao;
import com.pixvs.viaticos.exceptions.EmailException;
import com.pixvs.viaticos.exceptions.GenericException;
import com.pixvs.viaticos.model.viaticos.Alerta;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte.CorreosConfiguracionEnteProjection;
import com.pixvs.viaticos.service.AlertaService;
import com.pixvs.viaticos.service.ConfiguracionEnteService;
import org.simplejavamail.api.email.AttachmentResource;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by PixvsChevy on 3/30/2017.
 */
@Service
public class Email {

    @Autowired
    private Environment environment;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    ConfiguracionEnteService configuracionEnteService;

    @Autowired
    private GeneralScalarDao generalScalarDao;

    @Autowired
    private AlertaService alertaService;

    private String user;
    private String email;
    private String pass;
    private String host;
    private Integer port;

    public Properties getProperties() throws EmailException {

        CorreosConfiguracionEnteProjection configuracion = configuracionEnteService.getDatosServidorCorreos();

        user = configuracion.getNombreUsuario();
        email = configuracion.getEmail();
        pass = JSEncriptador.aesDecrypt(configuracion.getContrasenia());
        host = configuracion.getHost();
        port = configuracion.getPuerto();
        Integer protocolo = configuracion.getProtocolo();

        if (user == null || email == null || pass == null || host == null || port == null || protocolo == null) {
            throw new EmailException(EmailException.STATUS_DATOS_INCOMPLETOS);
        }

        /*
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", true);

        if (protocolo == ListadoCMM.ProtocoloSeguridadEmail.TLS) {
            // Propiedad solo para TLS
            properties.put("mail.smtp.starttls.enable", true);
        } else if (protocolo == ListadoCMM.ProtocoloSeguridadEmail.SSL) {
            // Propiedades solo para SSL
            properties.put("mail.smtp.socketFactory.port", port);
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.socketFactory.fallback", false);
        }
        */

        Properties properties = new Properties();
        properties.put("host", host);
        properties.put("port", port);
        properties.put("username", user);
        properties.put("password", pass);
        properties.put("protocol", protocolo);
        properties.put("from", email);

        return properties;
    }

    public void enviaCorreo(String alertaId) throws Exception {
        enviaCorreo(alertaId, null);
    }

    public void enviaCorreo(String alertaId, String archivoAdjunto) throws Exception {
        if (alertaId != null && !alertaId.isEmpty()) {
            List<List> datos = generalScalarDao.getDatosEnviarCorreo(Integer.valueOf(alertaId));

            for (int i = 0; i < datos.size(); i++) {
                String correos = (String) datos.get(i).get(0);
                String asunto = (String) datos.get(i).get(1);
                String cuerpo = (String) datos.get(i).get(2);

                JsonResponse email = null;

                if (correos != null && !correos.isEmpty()) {
                    if (archivoAdjunto == null) {
                        email = sendHtmlEmail(correos, asunto, cuerpo);
                    } else {
                        email = sendHtmlEmail(correos, asunto, cuerpo, new ArrayList<String>(){{ add(archivoAdjunto); }});
                    }
                }

                if (email != null && !Boolean.valueOf(email.getData().toString())) {
                    throw new GenericException(email.getMessage(), 5000);
                }
            }
        }
    }

    @Async
    public void sendSpringEmail(String correoDestino, String asunto, String mensaje) throws EmailException {

        getProperties();

        System.out.println("Sleeping now...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sending email...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(correoDestino);
        mail.setFrom(email);
        mail.setSubject(asunto);
        mail.setText(mensaje);
        javaMailSender.send(mail);

        System.out.println("Email Sent!");
    }

    public JsonResponse sendHtmlEmail(String correoDestino, String asunto, String mensaje) throws Exception {
        return sendEmail(correoDestino, asunto, mensaje, true, null);
    }

    public JsonResponse sendHtmlEmail(String correoDestino, String asunto, String mensaje, List<String> archivosNombres) throws Exception {
        return sendEmail(correoDestino, asunto, mensaje, true, archivosNombres);
    }

    public JsonResponse sendEmail(String correoDestino, String asunto, String mensaje) throws Exception {
        return sendEmail(correoDestino, asunto, mensaje, false, null);
    }

    public JsonResponse sendEmail(String correoDestino, String asunto, String mensaje, List<String> archivosNombres) throws Exception {
        return sendEmail(correoDestino, asunto, mensaje, false, archivosNombres);
    }

    /*
    public JsonResponse sendEmail(String correoDestino, String asunto, String mensaje, boolean tipoHtml, List<String> archivosNombres) throws Exception {
        Session session = Session.getInstance(getProperties(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, pass);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email, user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino));
            message.setSubject(asunto);

            if (archivosNombres != null) {
                Multipart multipart = new MimeMultipart();

                BodyPart messageBodyPart = new MimeBodyPart();
                if (tipoHtml) {
                    messageBodyPart.setContent(mensaje, "text/html; charset=utf-8");
                } else {
                    messageBodyPart.setText(mensaje);
                }
                multipart.addBodyPart(messageBodyPart);

                for (String nombreArchivo : archivosNombres) {
                    String pathArchivo = nombreArchivo.replace("/", File.separator).replace("\\", File.separator);

                    messageBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(pathArchivo);
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(pathArchivo.substring(pathArchivo.lastIndexOf(File.separator) + 1));
                    multipart.addBodyPart(messageBodyPart);
                }

                message.setContent(multipart);
            } else {
                if (tipoHtml) {
                    message.setContent(mensaje, "text/html; charset=utf-8");
                } else {
                    message.setText(mensaje);
                }
            }

            Transport.send(message);
        } catch (AuthenticationFailedException e) {
            return new JsonResponse(false, "Error al enviar el correo:\nEl correo electrónico o la contraseña son incorrectos. Revise la Configuración del Ente.");
        } catch (SendFailedException e) {
            return new JsonResponse(false, "Error al enviar el correo:\nEl correo no se pudo enviar, favor de volver a intentar.");
        } catch (MessagingException e) {
            return new JsonResponse(false, "Error al enviar el correo:\nNo se pudo conectar al SMTP Host: " + host + ", Puerto: " + port + ". Revise la Configuración del Ente.");
        }

        return new JsonResponse(true, "Correo Enviado.");
    }   */

    public JsonResponse sendEmail(String correoDestino, String asunto, String mensaje, boolean tipoHtml, List<String> archivosNombres) throws Exception {
        try {
            Mailer mailer = null;
            org.simplejavamail.api.email.Email email = null;
            Properties properties = getProperties();

            if((Integer)properties.get("protocol") == ListadoCMM.ProtocoloSeguridadEmail.TLS){
                mailer = MailerBuilder
                        .withSMTPServer((String)properties.get("host"), (Integer)properties.get("port"), (String)properties.get("username"), (String)properties.get("password"))
                        .buildMailer();
            }
            else if((Integer)properties.get("protocol") == ListadoCMM.ProtocoloSeguridadEmail.SSL){
                mailer = MailerBuilder
                        .withSMTPServer((String)properties.get("host"), (Integer)properties.get("port"), (String)properties.get("username"), (String)properties.get("password"))
                        .withTransportStrategy(TransportStrategy.SMTPS)
                        .buildMailer();
            }

            if(archivosNombres == null) {
                email = EmailBuilder.startingBlank()
                        .to(correoDestino)
                        .withSubject(asunto)
                        .withHTMLText(mensaje)
                        .from((String) properties.get("from"))
                        .buildEmail();
            }
            else{
                List<AttachmentResource> attachments = new ArrayList<AttachmentResource>();
                for (String nombreArchivo : archivosNombres) {
                    String pathArchivo = nombreArchivo.replace("/", File.separator).replace("\\", File.separator);
                    DataSource source = new FileDataSource(pathArchivo);
                    AttachmentResource attachmentResource = new AttachmentResource(((FileDataSource) source).getFile().getName(), source);
                    attachments.add(attachmentResource);
                }

                email = EmailBuilder.startingBlank()
                        .to(correoDestino)
                        .withSubject(asunto)
                        .withHTMLText(mensaje)
                        .withAttachments(attachments)
                        .from((String) properties.get("from"))
                        .buildEmail();
            }

            mailer.sendMail(email);

            /*
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email, user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino));
            message.setSubject(asunto);

            if (archivosNombres != null) {
                Multipart multipart = new MimeMultipart();

                BodyPart messageBodyPart = new MimeBodyPart();
                if (tipoHtml) {
                    messageBodyPart.setContent(mensaje, "text/html; charset=utf-8");
                } else {
                    messageBodyPart.setText(mensaje);
                }
                multipart.addBodyPart(messageBodyPart);

                for (String nombreArchivo : archivosNombres) {
                    String pathArchivo = nombreArchivo.replace("/", File.separator).replace("\\", File.separator);

                    messageBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(pathArchivo);
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(pathArchivo.substring(pathArchivo.lastIndexOf(File.separator) + 1));
                    multipart.addBodyPart(messageBodyPart);
                }

                message.setContent(multipart);
            } else {
                if (tipoHtml) {
                    message.setContent(mensaje, "text/html; charset=utf-8");
                } else {
                    message.setText(mensaje);
                }
            }

            Transport.send(message);
            */
        } catch (Exception e) {
            return new JsonResponse(false, "ERROR AL ENVIAR EL CORREO\nRevisar configuración del servidor SMTP: " + host
                    + ", Puerto: " + port + "\nUsuario: "+user+"\nRevisar correos destinatario(s): "+correoDestino.replace(',','|'));
        }
        return new JsonResponse(true, "Correo Enviado.");
    }
}
