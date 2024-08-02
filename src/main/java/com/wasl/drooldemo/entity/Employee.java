
package com.wasl.drooldemo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.YearMonth;

@Document(collection = "employees")
public class Employee implements Serializable {

    @Id
    private Long id;
    private Integer tahsilat;
    private Integer rotbeh;
    private Integer tabageh;
    private Integer hozeh;
    private Integer semat;
    private Boolean valid;
    private BigInteger hogog;
    private String companyId;
    private YearMonth month;

    public Employee() {}

    public Employee(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTahsilat() {
        return this.tahsilat;
    }

    public void setTahsilat(Integer tahsilat) {
        this.tahsilat = tahsilat;
    }

    public Integer getRotbeh() {
        return this.rotbeh;
    }

    public void setRotbeh(Integer rotbeh) {
        this.rotbeh = rotbeh;
    }

    public Integer getTabageh() {
        return this.tabageh;
    }

    public void setTabageh(Integer tabageh) {
        this.tabageh = tabageh;
    }

    public Integer getHozeh() {
        return this.hozeh;
    }

    public void setHozeh(Integer hozeh) {
        this.hozeh = hozeh;
    }

    public Integer getSemat() {
        return this.semat;
    }

    public void setSemat(Integer semat) {
        this.semat = semat;
    }

    public Boolean getValid() {
        return this.valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public BigInteger getHogog() {
        return this.hogog;
    }

    public void setHogog(BigInteger hogog) {
        this.hogog = hogog;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public Employee(Integer tahsilat, Integer rotbeh, Integer tabageh, Integer hozeh, Integer semat, Boolean valid, BigInteger hogog) {
        this.tahsilat = tahsilat;
        this.rotbeh = rotbeh;
        this.tabageh = tabageh;
        this.hozeh = hozeh;
        this.semat = semat;
        this.valid = valid;
        this.hogog = hogog;
    }
}
