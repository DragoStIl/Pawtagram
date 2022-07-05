package project.pawtagram.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.pawtagram.entities.Role;
import project.pawtagram.entities.TypeEntity;
import project.pawtagram.entities.enums.AnimalType;
import project.pawtagram.entities.enums.RoleCategory;
import project.pawtagram.repositories.RoleRepository;
import project.pawtagram.repositories.TypeRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class AnimalTypeSeeder implements CommandLineRunner {

    private final TypeRepository typeRepository;

    public AnimalTypeSeeder(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.typeRepository.count() == 0){
            List<TypeEntity> typeEntities = Arrays.stream(AnimalType.values())
                    .map(TypeEntity::new).collect(Collectors.toList());

            this.typeRepository.saveAll(typeEntities);
        }



    }
}
