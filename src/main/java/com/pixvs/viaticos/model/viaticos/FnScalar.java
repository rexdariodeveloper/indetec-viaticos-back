package com.pixvs.viaticos.model.viaticos;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "sp_AutonumericoArcSigIncr",
                procedureName = "sp_AutonumericoArcSigIncr",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.INOUT, name = "valor", type = String.class)
                }),
        @NamedStoredProcedureQuery(
                name = "iniciarAlerta",
                procedureName = "sp_IniciarAlerta",
                parameters = {
                    @StoredProcedureParameter(name = "accionId", type = Integer.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "alertaDefinicionId", type = Integer.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "referenciaProcesoId", type = Integer.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "codigoTramite", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "textoRepresentativo", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "creadoPorId", type = Integer.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "valorSalida", type = String.class, mode = ParameterMode.OUT),
                }),
        @NamedStoredProcedureQuery(
                name = "autorizarAlerta",
                procedureName = "sp_AutorizarAlerta",
                parameters = {
                        @StoredProcedureParameter(name = "accionId", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "creadoPorId", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "alertaId", type = Long.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "valorSalida", type = String.class, mode = ParameterMode.OUT)
                }),
        @NamedStoredProcedureQuery(
                name = "revisionRechazarAlerta",
                procedureName = "sp_RevisionRechazarAlerta",
                parameters = {
                        @StoredProcedureParameter(name = "accionId", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "creadoPorId", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "alertaId", type = Long.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "motivo", type = String.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "valorSalida", type = String.class, mode = ParameterMode.OUT)
                }),
        @NamedStoredProcedureQuery(
                name = "ocultarAlertas",
                procedureName = "sp_OcultarAlertas",
                parameters = {
                        @StoredProcedureParameter(name = "accionId", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "creadoPorId", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "alertasId", type = String.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "valorSalida", type = String.class, mode = ParameterMode.OUT)
                }),
        @NamedStoredProcedureQuery(
                name = "cancelarAlertas",
                procedureName = "sp_CancelarAlertas",
                parameters = {
                        @StoredProcedureParameter(name = "accionId", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "alertaDefinicionId", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "referenciaProcesoId", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "valorSalida", type = String.class, mode = ParameterMode.OUT)
                }),
        @NamedStoredProcedureQuery(
                name = "siguienteAutonumerico",
                procedureName = "sp_AutonumericoSigIncr",
                parameters = {
                        @StoredProcedureParameter(name = "nombre", type = String.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "ejercicio", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "valorSalida", type = String.class, mode = ParameterMode.OUT)
                }),
        @NamedStoredProcedureQuery(
                name = "permiteEliminarRegistro",
                procedureName = "sp_PermiteEliminarRegistro",
                parameters = {
                        @StoredProcedureParameter(name = "RegistroId", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "NombreTabla", type = String.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "PermiteEliminar", type = Boolean.class, mode = ParameterMode.OUT)
                }),
})
public class FnScalar implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "valor")
    private String valor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
