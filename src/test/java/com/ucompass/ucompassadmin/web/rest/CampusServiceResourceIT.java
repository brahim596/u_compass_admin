package com.ucompass.ucompassadmin.web.rest;

import com.ucompass.ucompassadmin.UCompassAdminApp;
import com.ucompass.ucompassadmin.domain.CampusService;
import com.ucompass.ucompassadmin.repository.CampusServiceRepository;
import com.ucompass.ucompassadmin.service.CampusServiceService;
import com.ucompass.ucompassadmin.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.ucompass.ucompassadmin.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ucompass.ucompassadmin.domain.enumeration.Affluence;
import com.ucompass.ucompassadmin.domain.enumeration.Type;
/**
 * Integration tests for the {@link CampusServiceResource} REST controller.
 */
@SpringBootTest(classes = UCompassAdminApp.class)
public class CampusServiceResourceIT {

    private static final String DEFAULT_IDD = "AAAAAAAAAA";
    private static final String UPDATED_IDD = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_OPEN = false;
    private static final Boolean UPDATED_OPEN = true;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final Duration DEFAULT_ESTIMATED_WAITING_TIME = Duration.ofHours(6);
    private static final Duration UPDATED_ESTIMATED_WAITING_TIME = Duration.ofHours(12);

    private static final Integer DEFAULT_ATTENTE = 1;
    private static final Integer UPDATED_ATTENTE = 2;

    private static final Instant DEFAULT_OPENING_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OPENING_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CLOSURE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CLOSURE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Affluence DEFAULT_AFFLUENCE = Affluence.LOW;
    private static final Affluence UPDATED_AFFLUENCE = Affluence.MEDIUM;

    private static final Type DEFAULT_TYPE = Type.RESTAURATION;
    private static final Type UPDATED_TYPE = Type.ADMINISTRATION;

    @Autowired
    private CampusServiceRepository campusServiceRepository;

    @Autowired
    private CampusServiceService campusServiceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCampusServiceMockMvc;

    private CampusService campusService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CampusServiceResource campusServiceResource = new CampusServiceResource(campusServiceService);
        this.restCampusServiceMockMvc = MockMvcBuilders.standaloneSetup(campusServiceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampusService createEntity(EntityManager em) {
        CampusService campusService = new CampusService()
            .idd(DEFAULT_IDD)
            .name(DEFAULT_NAME)
            .open(DEFAULT_OPEN)
            .description(DEFAULT_DESCRIPTION)
            .longitude(DEFAULT_LONGITUDE)
            .latitude(DEFAULT_LATITUDE)
            .estimatedWaitingTime(DEFAULT_ESTIMATED_WAITING_TIME)
            .attente(DEFAULT_ATTENTE)
            .openingTime(DEFAULT_OPENING_TIME)
            .closureTime(DEFAULT_CLOSURE_TIME)
            .affluence(DEFAULT_AFFLUENCE)
            .type(DEFAULT_TYPE);
        return campusService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampusService createUpdatedEntity(EntityManager em) {
        CampusService campusService = new CampusService()
            .idd(UPDATED_IDD)
            .name(UPDATED_NAME)
            .open(UPDATED_OPEN)
            .description(UPDATED_DESCRIPTION)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .estimatedWaitingTime(UPDATED_ESTIMATED_WAITING_TIME)
            .attente(UPDATED_ATTENTE)
            .openingTime(UPDATED_OPENING_TIME)
            .closureTime(UPDATED_CLOSURE_TIME)
            .affluence(UPDATED_AFFLUENCE)
            .type(UPDATED_TYPE);
        return campusService;
    }

    @BeforeEach
    public void initTest() {
        campusService = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampusService() throws Exception {
        int databaseSizeBeforeCreate = campusServiceRepository.findAll().size();

        // Create the CampusService
        restCampusServiceMockMvc.perform(post("/api/campus-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campusService)))
            .andExpect(status().isCreated());

        // Validate the CampusService in the database
        List<CampusService> campusServiceList = campusServiceRepository.findAll();
        assertThat(campusServiceList).hasSize(databaseSizeBeforeCreate + 1);
        CampusService testCampusService = campusServiceList.get(campusServiceList.size() - 1);
        assertThat(testCampusService.getIdd()).isEqualTo(DEFAULT_IDD);
        assertThat(testCampusService.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCampusService.isOpen()).isEqualTo(DEFAULT_OPEN);
        assertThat(testCampusService.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCampusService.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testCampusService.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testCampusService.getEstimatedWaitingTime()).isEqualTo(DEFAULT_ESTIMATED_WAITING_TIME);
        assertThat(testCampusService.getAttente()).isEqualTo(DEFAULT_ATTENTE);
        assertThat(testCampusService.getOpeningTime()).isEqualTo(DEFAULT_OPENING_TIME);
        assertThat(testCampusService.getClosureTime()).isEqualTo(DEFAULT_CLOSURE_TIME);
        assertThat(testCampusService.getAffluence()).isEqualTo(DEFAULT_AFFLUENCE);
        assertThat(testCampusService.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createCampusServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campusServiceRepository.findAll().size();

        // Create the CampusService with an existing ID
        campusService.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampusServiceMockMvc.perform(post("/api/campus-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campusService)))
            .andExpect(status().isBadRequest());

        // Validate the CampusService in the database
        List<CampusService> campusServiceList = campusServiceRepository.findAll();
        assertThat(campusServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCampusServices() throws Exception {
        // Initialize the database
        campusServiceRepository.saveAndFlush(campusService);

        // Get all the campusServiceList
        restCampusServiceMockMvc.perform(get("/api/campus-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campusService.getId().intValue())))
            .andExpect(jsonPath("$.[*].idd").value(hasItem(DEFAULT_IDD)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].open").value(hasItem(DEFAULT_OPEN.booleanValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].estimatedWaitingTime").value(hasItem(DEFAULT_ESTIMATED_WAITING_TIME.toString())))
            .andExpect(jsonPath("$.[*].attente").value(hasItem(DEFAULT_ATTENTE)))
            .andExpect(jsonPath("$.[*].openingTime").value(hasItem(DEFAULT_OPENING_TIME.toString())))
            .andExpect(jsonPath("$.[*].closureTime").value(hasItem(DEFAULT_CLOSURE_TIME.toString())))
            .andExpect(jsonPath("$.[*].affluence").value(hasItem(DEFAULT_AFFLUENCE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getCampusService() throws Exception {
        // Initialize the database
        campusServiceRepository.saveAndFlush(campusService);

        // Get the campusService
        restCampusServiceMockMvc.perform(get("/api/campus-services/{id}", campusService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(campusService.getId().intValue()))
            .andExpect(jsonPath("$.idd").value(DEFAULT_IDD))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.open").value(DEFAULT_OPEN.booleanValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.estimatedWaitingTime").value(DEFAULT_ESTIMATED_WAITING_TIME.toString()))
            .andExpect(jsonPath("$.attente").value(DEFAULT_ATTENTE))
            .andExpect(jsonPath("$.openingTime").value(DEFAULT_OPENING_TIME.toString()))
            .andExpect(jsonPath("$.closureTime").value(DEFAULT_CLOSURE_TIME.toString()))
            .andExpect(jsonPath("$.affluence").value(DEFAULT_AFFLUENCE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCampusService() throws Exception {
        // Get the campusService
        restCampusServiceMockMvc.perform(get("/api/campus-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampusService() throws Exception {
        // Initialize the database
        campusServiceService.save(campusService);

        int databaseSizeBeforeUpdate = campusServiceRepository.findAll().size();

        // Update the campusService
        CampusService updatedCampusService = campusServiceRepository.findById(campusService.getId()).get();
        // Disconnect from session so that the updates on updatedCampusService are not directly saved in db
        em.detach(updatedCampusService);
        updatedCampusService
            .idd(UPDATED_IDD)
            .name(UPDATED_NAME)
            .open(UPDATED_OPEN)
            .description(UPDATED_DESCRIPTION)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .estimatedWaitingTime(UPDATED_ESTIMATED_WAITING_TIME)
            .attente(UPDATED_ATTENTE)
            .openingTime(UPDATED_OPENING_TIME)
            .closureTime(UPDATED_CLOSURE_TIME)
            .affluence(UPDATED_AFFLUENCE)
            .type(UPDATED_TYPE);

        restCampusServiceMockMvc.perform(put("/api/campus-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCampusService)))
            .andExpect(status().isOk());

        // Validate the CampusService in the database
        List<CampusService> campusServiceList = campusServiceRepository.findAll();
        assertThat(campusServiceList).hasSize(databaseSizeBeforeUpdate);
        CampusService testCampusService = campusServiceList.get(campusServiceList.size() - 1);
        assertThat(testCampusService.getIdd()).isEqualTo(UPDATED_IDD);
        assertThat(testCampusService.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCampusService.isOpen()).isEqualTo(UPDATED_OPEN);
        assertThat(testCampusService.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCampusService.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testCampusService.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testCampusService.getEstimatedWaitingTime()).isEqualTo(UPDATED_ESTIMATED_WAITING_TIME);
        assertThat(testCampusService.getAttente()).isEqualTo(UPDATED_ATTENTE);
        assertThat(testCampusService.getOpeningTime()).isEqualTo(UPDATED_OPENING_TIME);
        assertThat(testCampusService.getClosureTime()).isEqualTo(UPDATED_CLOSURE_TIME);
        assertThat(testCampusService.getAffluence()).isEqualTo(UPDATED_AFFLUENCE);
        assertThat(testCampusService.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCampusService() throws Exception {
        int databaseSizeBeforeUpdate = campusServiceRepository.findAll().size();

        // Create the CampusService

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampusServiceMockMvc.perform(put("/api/campus-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campusService)))
            .andExpect(status().isBadRequest());

        // Validate the CampusService in the database
        List<CampusService> campusServiceList = campusServiceRepository.findAll();
        assertThat(campusServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampusService() throws Exception {
        // Initialize the database
        campusServiceService.save(campusService);

        int databaseSizeBeforeDelete = campusServiceRepository.findAll().size();

        // Delete the campusService
        restCampusServiceMockMvc.perform(delete("/api/campus-services/{id}", campusService.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CampusService> campusServiceList = campusServiceRepository.findAll();
        assertThat(campusServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
