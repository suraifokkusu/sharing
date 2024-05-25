package com.thewhite.blank.repository;

import com.thewhite.blank.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface EntityRepository<EntityT extends BaseEntity> extends JpaRepository<EntityT, UUID>,
                                                                      QuerydslPredicateExecutor<EntityT> {}
