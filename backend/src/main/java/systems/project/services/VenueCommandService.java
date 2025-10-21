package systems.project.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import systems.project.models.Venue;
import systems.project.repositories.VenueRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class VenueCommandService {

    private final VenueRepository venueRepository;

    public VenueCommandService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public Map<String, List<Venue>> getVenues() {
        try {
            List<Venue> venues = venueRepository.findAllBy();
            return Map.of("venues", venues);
        } catch (Exception e) {
            return Collections.singletonMap("venues", (List<Venue>) null);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Map<String, Boolean> addVenue(Venue venue) {
        try {
            venueRepository.save(venue);
            return Map.of("status", true);
        } catch (Exception e) {
            return Map.of("status", false);
        }
    }
}
