package systems.project.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import systems.project.models.Event;
import systems.project.repositories.EventRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class EventCommandService {

    private final EventRepository eventRepository;

    public EventCommandService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Map<String, List<Event>> getEvents() {
        try {
            List<Event> events = eventRepository.findAllBy();
            return Map.of("events", events);
        } catch (Exception e) {
            return Collections.singletonMap("events", (List<Event>) null);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Map<String, Boolean> addEvent(Event event) {
        try {
            eventRepository.save(event);
            return Map.of("status", true);
        } catch (Exception e) {
            return Map.of("status", false);
        }
    }
}
