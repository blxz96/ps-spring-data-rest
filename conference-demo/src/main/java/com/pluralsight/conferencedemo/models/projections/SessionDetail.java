package com.pluralsight.conferencedemo.models.projections;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.models.Speaker;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;
//Projection allows us to customise our payload.
//Without projection, we need 2 API calls to get sessions and speakers info.
//With projection, we can do it with a single API call.
//E.g http://localhost:8080/api/conference_sessions?projection=sessionDetail
//E.g http://localhost:8080/api/conference_sessions (if projection excerpt is set in SessionJpaRepository, however it only works with collection and not individual resources))
//E.g http://localhost:8080/api/conference_sessions/1?projection=sessionDetail
@Projection(name="sessionDetail", types = {Session.class})
public interface SessionDetail {
    String getSessionName();
    String getSessionDescription();
    Integer getSessionLength();
    List<Speaker> getSpeakers();
}
