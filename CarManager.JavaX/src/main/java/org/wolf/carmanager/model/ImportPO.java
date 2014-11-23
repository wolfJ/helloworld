package org.wolf.carmanager.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wolf on 2014/11/23.
 */
public class ImportPO implements Serializable {
    private static final long serialVersionUID = -1978589936428946931L;

    private int id;
    private String importFileName;
    private Date importTime;

    public Date getImportTime() {
        return importTime;
    }

    public void setImportTime(Date importTime) {
        this.importTime = importTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImportFileName() {
        return importFileName;
    }

    public void setImportFileName(String importFileName) {
        this.importFileName = importFileName;
    }
}
