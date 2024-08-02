
package com.wasl.drooldemo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.List;

@Document(collection = "company_tables")
public class CompanyTable implements Serializable {

    @Id
    private Long id;
    private Integer companyId;
    private List<Integer> content;

    public CompanyTable() {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public List<Integer> getContent() {
        return this.content;
    }

    public void setContent(List<Integer> content) {
        this.content = content;
    }

    public CompanyTable(Long id, Integer companyId, List<Integer> content) {
        this.id = id;
        this.companyId = companyId;
        this.content = content;
    }

    public Integer getvalue(List<Integer> position){
        return 0;
    }


}
