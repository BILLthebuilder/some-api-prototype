
package events.api;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import events.api.model.user.Role;
import events.api.model.user.User;
import events.api.model.user.UserRoles;
import events.api.repositories.*;


/**
 * Adds seed data to the database for testing purposes
 */
@Transactional
@Component
@AllArgsConstructor
public class SeedData
        implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // ################### ROLES ########################
        // Admin
        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        adminRole = roleRepository.save(adminRole);

        // Organizer
        Role organizerRole = new Role();
        organizerRole.setRoleName("ORGANIZER");
        organizerRole = roleRepository.save(organizerRole);

        // Attendee
        Role attendeeRole = new Role();
        attendeeRole.setRoleName("ATTENDEE");
        attendeeRole = roleRepository.save(attendeeRole);

        // ####### USERS ###########
        // ## Admins
        User admin1 = new User();
        admin1.setFirstName("Bill");
        admin1.setLastName("Kariri");
        admin1.setEmail("bill@mail.com");
        admin1.setUsername("bill@mail.com");
        admin1.setPhoneNo("0720000000");
        admin1.setPassword(passwordEncoder.encode("password"));
        admin1.setStatus(true);
        admin1.getRoles().add(new UserRoles(admin1, adminRole));
        userRepository.save(admin1);

        User admin2 = new User();
        admin2.setFirstName("Bill");
        admin2.setLastName("Kariri");
        admin2.setEmail("bill2@mail.com");
        admin2.setUsername("bill2@mail.com");
        admin2.setPhoneNo("0720000001");
        admin2.setPassword(passwordEncoder.encode("password"));
        admin2.setStatus(true);
        admin2.getRoles().add(new UserRoles(admin2, adminRole));
        userRepository.save(admin2);

        // ## Organizers
        User organizer1 = new User();
        organizer1.setFirstName("Organizer");
        organizer1.setLastName("Organizer");
        organizer1.setEmail("organizer@events.com");
        organizer1.setUsername("organizer@events.com");
        organizer1.setPhoneNo("0714385056");
        organizer1.setPassword(passwordEncoder.encode("password"));
        organizer1.setStatus(true);
        organizer1.getRoles().add(new UserRoles(organizer1, organizerRole));
        userRepository.save(organizer1);

        User organizer2 = new User();
        organizer2.setFirstName("Organizer");
        organizer2.setLastName("Organizer");
        organizer2.setEmail("organizer2@events.com");
        organizer2.setUsername("organizer2@events.com");
        organizer2.setPhoneNo("0714385057");
        organizer2.setPassword(passwordEncoder.encode("password"));
        organizer2.setStatus(true);
        organizer2.getRoles().add(new UserRoles(organizer2, organizerRole));
        userRepository.save(organizer2);

        // Attendees
        User attendee1 = new User();
        attendee1.setFirstName("Attendee");
        attendee1.setLastName("Attendee");
        attendee1.setEmail("attendee@mail.com");
        attendee1.setUsername("attendee@mail.com");
        attendee1.setPhoneNo("0720942928");
        attendee1.setPassword(passwordEncoder.encode("password"));
        attendee1.setStatus(true);
        attendee1.getRoles().add(new UserRoles(attendee1, attendeeRole));
        userRepository.save(attendee1);

        User attendee2 = new User();
        attendee2.setFirstName("Attendee");
        attendee2.setLastName("Attendee");
        attendee2.setEmail("attendee2@mail.com");
        attendee2.setUsername("attendee2@mail.com");
        attendee2.setPhoneNo("0110942927");
        attendee2.setPassword(passwordEncoder.encode("password"));
        attendee2.setStatus(true);
        attendee2.getRoles().add(new UserRoles(attendee2, attendeeRole));
        userRepository.save(attendee2);

    }

}

