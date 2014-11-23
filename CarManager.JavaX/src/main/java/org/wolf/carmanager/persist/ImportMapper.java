package org.wolf.carmanager.persist;

import org.wolf.carmanager.model.ImportPO;

import java.util.List;

/**
 * Created by wolf on 2014/11/23.
 */
public interface ImportMapper {

    List<ImportPO> selectAll();

    void insert(ImportPO ipo);
}
