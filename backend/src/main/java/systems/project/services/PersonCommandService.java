package systems.project.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import systems.project.models.Person;
import systems.project.repositories.LocationRepository;
import systems.project.repositories.PersonRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@Slf4j
public class PersonCommandService {

    private final PersonRepository personRepository;
    private final LocationRepository locationRepository;

    public PersonCommandService(PersonRepository personRepository,
                                LocationRepository locationRepository) {
        this.personRepository = personRepository;
        this.locationRepository = locationRepository;
    }

    public Map<String, List<Person>> getPersons() {
        try {
            List<Person> people = personRepository.findAllBy();
            return Map.of("persons", people);
        } catch (Exception e) {
            return Collections.singletonMap("persons", (List<Person>) null);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Map<String, Boolean> addPerson(Person person) {
        try {
            log.info("enterd to create person");
            if (person == null || person.getPassportID() == null) {
                log.info(person.toString());
                return Map.of("status", false, "passportId", true);
            }
            if (personRepository.existsPersonByPassportID(person.getPassportID())) {
                return Map.of("status", false, "passportId", true);
            }
            var savedLocation = locationRepository.save(person.getLocation());
            person.setLocation(savedLocation);
            personRepository.save(person);
            return Map.of("status", true);
        } catch (Exception e) {
            return Map.of("status", false);
        }
    }
}
