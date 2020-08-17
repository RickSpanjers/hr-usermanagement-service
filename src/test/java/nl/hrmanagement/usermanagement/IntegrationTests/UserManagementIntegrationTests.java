package nl.hrmanagement.usermanagement.IntegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hrmanagement.usermanagement.dto.LoginDTO;
import nl.hrmanagement.usermanagement.repository.*;
import nl.hrmanagement.usermanagement.security.jwt.JwtTokenUtil;
import nl.hrmanagement.usermanagement.service.IRESTController;
import nl.hrmanagement.usermanagement.service.IServiceProvider;
import nl.hrmanagement.usermanagement.service.impl.UserManagementController;
import nl.hrmanagement.usermanagement.service.impl.UserManagementServiceProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManagerFactory;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc //need this in Spring Boot test
@AutoConfigureTestEntityManager
public class UserManagementIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ExperienceRepository expRepository;

    @MockBean
    private SkillRepository skillRepository;

    @MockBean
    private EducationRepository eduRepository;

    @MockBean
    private LanguageRepository langRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private IRESTController controller;

    @MockBean
    private EntityManagerFactory entityManagerFactory;

    @MockBean
    private IServiceProvider userManagementServiceProvider;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void retrieveProfileThroughAllLayers_noJWT() throws Exception {
        mockMvc.perform(get("/user/profile/"))
                .andExpect(status().is(401));
    }

    @Test
    public void retrieveProfileThroughAllLayers_withJWT() throws Exception {
        mockMvc.perform(get("/user/profile/"))
                .andExpect(status().is(401));
    }

    @Test
    public void retrieveEmployeesThroughAllLayers_noJWT() throws Exception {
        mockMvc.perform(get("/user/fromCompany/"))
                .andExpect(status().is(401));
    }

    @Test
    public void retrieveEmployeesThroughAllLayers_withJWT() throws Exception {
        mockMvc.perform(get("/user/fromCompany/"))
                .andExpect(status().is(401));
    }

}
