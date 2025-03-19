package com.caasa.comexsap.exportaciones.model.handler;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;

import oracle.jdbc.OracleDatabaseException;


@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
    protected final Logger logger = LogManager.getLogger(getClass());


    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> dataAccessException(DataAccessException e) {
        String errorMessage = "";
        Throwable rootCause = e.getRootCause();
        if(rootCause instanceof OracleDatabaseException) {
        	OracleDatabaseException sqle = (OracleDatabaseException)e.getRootCause();
        	logger.error("##### DataAccessException ");
            logger.error("#### error code [" + sqle.getOracleErrorNumber() +"]", e);
            logger.error("#### message [" + sqle.getMessage() +"]");
            errorMessage = "Ocurrio un problema durante el procesamiento.";
        } else {
        	SQLException sqle = (SQLException)e.getRootCause();
        	logger.error("##### DataAccessException ");
            logger.error("#### error code [" + sqle.getErrorCode() +"]", e);
            logger.error("#### message [" + sqle.getMessage() +"]");
            errorMessage = "Ocurrio un problema durante el procesamiento.";
        }

        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> exception(Exception e) {
        String errorMessage = e.getMessage();
        e.printStackTrace();
        logger.error("##### Exception #####");
        logger.error("#### message [" + errorMessage + "]");

        errorMessage = "Ocurrio un problema durante el procesamiento.";

        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MultipartException.class)
    public ResponseEntity<?> multipartException(MultipartException e) {
        String errorMessage = e.getMessage();
        e.printStackTrace();
        logger.error("##### MultipartException #####");

        if (e.getRootCause() instanceof SizeLimitExceededException) {
            SizeLimitExceededException me = (SizeLimitExceededException) e.getRootCause();
            logger.error("#### message [" + me.getMessage() + "]");
            logger.error("#### MaxUploadSize [" + me.getPermittedSize() + "]");
            errorMessage = "Se excede el tamanio del archivo descargable. \n";
            errorMessage += "Tamanio del archivo  [ " + (me.getPermittedSize() / 1024 ) +"KB ] ";

            return new ResponseEntity<>(errorMessage,HttpStatus.PAYLOAD_TOO_LARGE);
        } else {
            logger.error("#### message [" + errorMessage + "]");
            errorMessage = "Hubo un problema al cargar el archivo.";

            return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException(Exception e) {
        String errorMessage = e.getMessage();
        e.printStackTrace();
        logger.error("##### Exception #####");
        logger.error("#### message [" + errorMessage + "]");

        return new ResponseEntity<>(errorMessage,HttpStatus.FORBIDDEN);
    }
}
