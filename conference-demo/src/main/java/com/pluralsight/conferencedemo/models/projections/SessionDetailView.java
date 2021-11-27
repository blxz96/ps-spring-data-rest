package com.pluralsight.conferencedemo.models.projections;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.models.Speaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

//As can be seen from the other projection (SessionDetail.java), when creating a projection
//all the interface method names and data return types need to match with the entity model.
//What will happen if we change  a property or attribute on our entity?
//This will ultimately change the JSON that is returned in our API
//This is a breaking change in our REST APU and we will need to update our REST client as well
//If we plan ahead and have the REST clients use projections from the start,
//we can prevent this by using a virtual/view projection as illustrated in this class.
//Example: http://localhost:8080/api/conference_sessions?projection=sessionDetailView
//Example: http://localhost:8080/api/conference_sessions (if projection excerpt is set in SessionJpaRepository, however it only works with collection and not individual resources)
//Example: http://localhost:8080/api/conference_sessions/2?projection=sessionDetailView

//Here, we are turning projection into more of a view rather than the subset or superset of our entity.
@Projection(name="sessionDetailView", types = {Session.class})
public interface SessionDetailView {
    //We use the @Value annotation and utilise the Spring Expression Language to populate the return value.
    //target keyword targets the entity and sessionName in this case is the attribute on it.
    //With this, we are free to change the method name to anything we want
    //We can shield the REST clients from any changes to entity models and names
    //The JSON attributes will then always remain the same
    @Value("#{target.sessionName}")
    String getTitle();

    String getSessionDescription();
    Integer getSessionLength();
    List<Speaker> getSpeakers();

    //Creating a new method
    //SpEL is using the target(Session entity) that is calling the speakers association and calling the .size() on it.
    @Value("#{target.speakers.size()}")
    Integer getSpeakerCount();
}
