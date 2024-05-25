package com.thewhite.blank.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

