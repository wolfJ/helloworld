package org.wolf.carmanager.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wolf on 2014/11/23.
 */
public class CarPO implements Serializable {
    private static final long serialVersionUID = -4867214823493085140L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChePai() {
        return chePai;
    }

    public void setChePai(String chePai) {
        this.chePai = chePai;
    }

    public String getCheZhu() {
        return cheZhu;
    }

    public void setCheZhu(String cheZhu) {
        this.cheZhu = cheZhu;
    }

    public String getDianHua() {
        return dianHua;
    }

    public void setDianHua(String dianHua) {
        this.dianHua = dianHua;
    }

    public String getChePingPai() {
        return chePingPai;
    }

    public void setChePingPai(String chePingPai) {
        this.chePingPai = chePingPai;
    }

    public String getCheXinHao() {
        return cheXinHao;
    }

    public void setCheXinHao(String cheXinHao) {
        this.cheXinHao = cheXinHao;
    }

    public String getFaDongJi() {
        return faDongJi;
    }

    public void setFaDongJi(String faDongJi) {
        this.faDongJi = faDongJi;
    }

    public String getCheJiaHao() {
        return cheJiaHao;
    }

    public void setCheJiaHao(String cheJiaHao) {
        this.cheJiaHao = cheJiaHao;
    }

    public Date getDengJiRQ() {
        return dengJiRQ;
    }

    public void setDengJiRQ(Date dengJiRQ) {
        this.dengJiRQ = dengJiRQ;
    }

    public Date getBaoXianRQ() {
        return baoXianRQ;
    }

    public void setBaoXianRQ(Date baoXianRQ) {
        this.baoXianRQ = baoXianRQ;
    }

    public String getShenFengZheng() {
        return shenFengZheng;
    }

    public void setShenFengZheng(String shenFengZheng) {
        this.shenFengZheng = shenFengZheng;
    }

    public String getDiZhi() {
        return diZhi;
    }

    public void setDiZhi(String diZhi) {
        this.diZhi = diZhi;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private int id;
    private String  chePai;
    private String  cheZhu;
    private String  dianHua;
    private String  chePingPai;
    private String  cheXinHao;
    private String  faDongJi;
    private String  cheJiaHao;
    private Date  dengJiRQ;
    private Date  baoXianRQ;
    private String  shenFengZheng;
    private String  diZhi;
    private String  remark;
//    private Double  bakA;
//    private String  bakB;


}
