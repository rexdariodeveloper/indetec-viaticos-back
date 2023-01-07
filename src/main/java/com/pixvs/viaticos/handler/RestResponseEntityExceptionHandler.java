package com.pixvs.viaticos.handler;

import com.pixvs.viaticos.exceptions.EmailException;
import com.pixvs.viaticos.exceptions.GenericException;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.JsonResponseError;
import com.pixvs.viaticos.util.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by PixvsChevy on 3/30/2017.
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private Environment environment;

    @Autowired
    private Email email;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, new JsonResponse(null, ex.getMessage(), JsonResponseError.STATUS_ERROR), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, ServletWebRequest request) throws EmailException {
        JsonResponseError jsonResponseError = new JsonResponseError();
        Boolean enviarCorreo = false;

        try {

            //GenericException
            if(ex != null && ex.getClass() == GenericException.class){
                GenericException exg = (GenericException) ex;
                jsonResponseError = new JsonResponseError(exg.getMessage(), exg.getData(), request.getRequest().getRequestURI(), exg.getStatus());
                enviarCorreo = exg.isEnviarCorreo();
            }

            //Clases que extienden de GenericException
            else if(ex != null && ex.getClass().getSuperclass() != null && ex.getClass().getSuperclass() == GenericException.class){
                GenericException exg = (GenericException) ex;
                jsonResponseError = new JsonResponseError(exg.getMessage(), exg.getData(), request.getRequest().getRequestURI(), exg.getStatus());
                enviarCorreo = exg.isEnviarCorreo();
            }

            //SQLGrammarException
            else if (ex != null && ex.getCause() != null && ex.getCause().getClass() == org.hibernate.exception.SQLGrammarException.class) {
                jsonResponseError.setStatus(JsonResponseError.STATUS_ERROR_SQL);
                jsonResponseError.setMessage(org.hibernate.exception.SQLGrammarException.class.getSimpleName() + ", " + ex.getCause().getCause().getMessage());
                jsonResponseError.setPath(request.getRequest().getRequestURI());
            }

            //NullPointerException
            else if (ex != null && ex.getClass() == NullPointerException.class) {
                jsonResponseError.setStatus(JsonResponseError.STATUS_ERROR_NULL_POINTER);
                jsonResponseError.setMessage("Valor Nulo no permitido");
                jsonResponseError.setPath(request.getRequest().getRequestURI());
            }

            else if(ex != null && ex.getCause() != null && ex.getCause().getCause() != null && ex.getCause().getCause().getMessage() != null){
                jsonResponseError.setStatus(JsonResponseError.STATUS_ERROR);
                jsonResponseError.setMessage(ex.getCause().getClass().getSimpleName() + ", " + ex.getCause().getCause().getMessage());
                jsonResponseError.setPath(request.getRequest().getRequestURI());
            }

            else if(ex != null && ex.getMessage() != null){
                jsonResponseError.setStatus(JsonResponseError.STATUS_ERROR);
                jsonResponseError.setMessage(ex.getClass().getSimpleName() + ", " + ex.getMessage());
                jsonResponseError.setPath(request.getRequest().getRequestURI());
            }

            else{
                jsonResponseError.setStatus(JsonResponseError.STATUS_ERROR);
                jsonResponseError.setMessage("Error, Error Desconocido");
                jsonResponseError.setPath(request.getRequest().getRequestURI());
            }
        }
        catch(Exception e){
            jsonResponseError.setStatus(JsonResponseError.STATUS_ERROR);
            jsonResponseError.setMessage("Problemas de conexión\n" + e.getCause().getClass().getSimpleName() + ", " + e.getCause().getCause().getMessage());
            jsonResponseError.setPath(request.getRequest().getRequestURI());
        }
        finally {

            String stackTrace = "";
            if(ex != null && ex.getStackTrace()!= null){
                for (StackTraceElement stack : ex.getStackTrace()){
                    stackTrace += stack+"\n";
                }
            }

            //Enviar correo si esta en Producción
            if ("production".equals(environment.getActiveProfiles()[0])) {
                if(enviarCorreo){
                    email.sendSpringEmail(environment.getProperty("enviroment.mail.correo_errores"), environment.getProperty("environments.pixvs.empresa")+" (" + environment.getActiveProfiles()[0] + ") Error 1500", jsonResponseError.getMessage() + "\n\nStack:\n" + stackTrace);
                }
            }
            else {
                ex.printStackTrace();
            }

            return handleExceptionInternal(ex, jsonResponseError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }

}
