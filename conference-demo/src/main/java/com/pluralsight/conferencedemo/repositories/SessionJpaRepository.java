package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.models.projections.SessionDetailView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//Annotation to change the automatically generated URL and the collection Type
@RepositoryRestResource(
        path="conference_sessions",
        collectionResourceRel = "conference_sessions",
        excerptProjection = SessionDetailView.class
)
public interface SessionJpaRepository extends JpaRepository<Session, Long>, SessionCustomJpaRepository {
    List<Session> findBySessionNameContains(String name);
    List<Session> findBySessionNameNotLike(String name);
    List<Session> findBySessionLengthNot(Integer sessionLength);
    List<Session> findBySessionLengthLessThan(Integer sessionLength);

    @Query("select s from Session s where s.sessionName like %:name")
    Page<Session> getSessionsWithName(@Param("name") String name, Pageable pageable);
}
