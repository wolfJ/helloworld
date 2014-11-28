package org.wolf.carmanager.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wolf on 2014/11/23.
 */
public class ImportPO implements Serializable {
    private static final long serialVersionUID = -1978589936428946931L;

    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private int id;
    private String importFileName;
    private Date importTime;
    private String importTimeStr;

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

    public String getImportTimeStr() {
        if (importTime != null)
            return format.format(importTime);
        else
            return null;
    }

    public void setImportTimeStr(String importTimeStr) {
        this.importTimeStr = importTimeStr;
    }
}
