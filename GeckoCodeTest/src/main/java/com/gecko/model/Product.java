package com.gecko.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "products")
public class Product {
    
    private Long id;
    
    @NotNull
    @Size(min=4, max=50)
    private String name;
    
	private String description;
	
	@NotNull
	@Column(name="updatedTime", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedTime;

    public Product() {
    }

    public Product(String name, String description, Date updatedTime) {
        this.name = name;
        this.description = description;
        this.updatedTime = updatedTime;		
    }

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
	
    public Date getUpdatedtime() {
        return updatedTime;
    }

    public void setUpdatedtime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }	

    /*
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        return hash;
    }
    */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.description != other.description) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }		
        if (!Objects.equals(this.updatedTime, other.updatedTime)) {
            return false;
        }		
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        
        StringBuilder builder = new StringBuilder();
        builder.append("Products{id=").append(id)
				.append(", name=").append(name)
				.append(", description=").append(description)
				.append(", updatedTimestamp=").append(updatedTime)
				.append("}");
        
        return builder.toString();
    }
}