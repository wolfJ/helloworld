package org.wolf.carmanager;

import java.io.Serializable;

/**
 * 分页查询参数对象
 *
 * @author wolf
 * @create_date 2014-07-30
 */
public class PageQueryParam<T> implements Serializable {

    public PageQueryParam() {
    }

    public PageQueryParam(T param) {
        this.param = param;
    }

    public PageQueryParam(T param, int iDisplayStart, int iDisplayLength) {
        this.param = param;
        this.iDisplayStart = iDisplayStart;
        this.iDisplayLength = iDisplayLength;
    }

    private T param;

    /**
     * 开始索引
     */
    private int iDisplayStart;

    /**
     * 要显示的数量
     */
    private int iDisplayLength;

    /**
     * 排序信息,使用set方法注入
     */
    private String sortInfo;

    /**
     * 是否需要进行分页查询
     * @return
     */
    public boolean needQueryPage(){
        return iDisplayStart>=0 && iDisplayLength>0;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public String getSortInfo() {
        return sortInfo;
    }

    public void setSortInfo(String sortInfo) {
        this.sortInfo = sortInfo;
    }

    @Override
    public String toString() {
        return "PageQueryParam{" +
                "param=" + param +
                ", iDisplayStart=" + iDisplayStart +
                ", iDisplayLength=" + iDisplayLength +
                ", sortInfo='" + sortInfo + '\'' +
                '}';
    }
}
