package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.RolMenu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RolMenuDao extends CrudRepository<RolMenu, Integer> {

    RolMenu findByRolIdAndAndNodoMenuIdAndEstatusId(int rolId, int menuId, int estatusId);

    List<RolMenu> findAllByRolIdAndEstatusId(int rolId, int estatusId);
}
