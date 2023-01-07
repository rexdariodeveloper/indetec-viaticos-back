package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.GeneralScalarDao;
import com.pixvs.viaticos.dao.viaticos.RolDao;
import com.pixvs.viaticos.model.viaticos.Rol;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.Rol.RolEditarProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SistemaService {

    @Autowired
    private GeneralScalarDao generalScalarDao;

    public boolean permiteEliminarRegistro(int registroId, String nombreTabla){
        return generalScalarDao.permiteEliminarRegistro(registroId, nombreTabla);
    }
}
