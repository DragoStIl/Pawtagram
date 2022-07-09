package project.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.entities.enums.RoleCategory;
import project.entities.Role;
import project.repositories.RoleRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleSeeder implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.roleRepository.count() == 0){
            List<Role> roles = Arrays.stream(RoleCategory.values())
                    .map(Role::new).collect(Collectors.toList());


            this.roleRepository.saveAll(roles);


        }
    }
}
