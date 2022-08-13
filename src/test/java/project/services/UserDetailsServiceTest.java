package project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project.entities.Role;
import project.entities.User;
import project.entities.enums.RoleCategory;
import project.repositories.UserRepository;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceTest {

    private AppUserDetailsService mockService;

    @Mock
    private UserRepository mockRepo;

    @BeforeEach
    void setUp(){
        mockService = new AppUserDetailsService(
            mockRepo
        );
    }

    @Test
    void testLoadUserByUsername_existing(){
        User testUser = new User();
        testUser.setUsername("testUser");
        testUser.setEmail("test@mail.com");
        testUser.setPassword("1234");
        testUser.setFullName("user userov");
        testUser.setAge(10);
        testUser.setRole(new Role(RoleCategory.ADMIN));

        when(mockRepo.findByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));

        UserDetails userDetails = mockService.loadUserByUsername(testUser.getUsername());

        Assertions.assertEquals(testUser.getUsername(), userDetails.getUsername());


    }

    @Test
    void testLoadUserByUsername_nonExistent(){

        boolean thrown = false;
        when(mockRepo.findByUsername("fakeUser")).thenThrow(new UsernameNotFoundException("on such user"));


        try {
            UserDetails details = mockService.loadUserByUsername("fakeUser");
        } catch (UsernameNotFoundException e) {
            thrown = true;
        }
        Assertions.assertTrue(thrown);


    }
}
