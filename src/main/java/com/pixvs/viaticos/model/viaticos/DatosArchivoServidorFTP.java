package com.pixvs.viaticos.model.viaticos;

public class DatosArchivoServidorFTP {

    private String archivoOriginal;
    private String rutaDestino;
    private String archivoDestino;

    public DatosArchivoServidorFTP(String archivoOriginal, String rutaDestino, String archivoDestino) {
        this.archivoOriginal = archivoOriginal;
        this.rutaDestino = rutaDestino;
        this.archivoDestino = archivoDestino;
    }

    public String getArchivoOriginal() {
        return archivoOriginal;
    }

    public void setArchivoOriginal(String archivoOriginal) {
        this.archivoOriginal = archivoOriginal;
    }

    public String getRutaDestino() {
        return rutaDestino;
    }

    public void setRutaDestino(String rutaDestino) {
        this.rutaDestino = rutaDestino;
    }

    public String getArchivoDestino() {
        return archivoDestino;
    }

    public void setArchivoDestino(String archivoDestino) {
        this.archivoDestino = archivoDestino;
    }
}