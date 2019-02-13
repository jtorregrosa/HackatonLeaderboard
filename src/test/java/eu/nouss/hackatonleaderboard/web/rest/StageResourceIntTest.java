package eu.nouss.hackatonleaderboard.web.rest;

import eu.nouss.hackatonleaderboard.HackatonLeaderboardApp;

import eu.nouss.hackatonleaderboard.domain.Stage;
import eu.nouss.hackatonleaderboard.domain.Score;
import eu.nouss.hackatonleaderboard.repository.StageRepository;
import eu.nouss.hackatonleaderboard.service.StageService;
import eu.nouss.hackatonleaderboard.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static eu.nouss.hackatonleaderboard.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the StageResource REST controller.
 *
 * @see StageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HackatonLeaderboardApp.class)
public class StageResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private StageService stageService;

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

    private MockMvc restStageMockMvc;

    private Stage stage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StageResource stageResource = new StageResource(stageService);
        this.restStageMockMvc = MockMvcBuilders.standaloneSetup(stageResource)
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
    public static Stage createEntity(EntityManager em) {
        Stage stage = new Stage()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        // Add required entity
        Score score = ScoreResourceIntTest.createEntity(em);
        em.persist(score);
        em.flush();
        stage.getScores().add(score);
        return stage;
    }

    @Before
    public void initTest() {
        stage = createEntity(em);
    }

    @Test
    @Transactional
    public void createStage() throws Exception {
        int databaseSizeBeforeCreate = stageRepository.findAll().size();

        // Create the Stage
        restStageMockMvc.perform(post("/api/stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stage)))
            .andExpect(status().isCreated());

        // Validate the Stage in the database
        List<Stage> stageList = stageRepository.findAll();
        assertThat(stageList).hasSize(databaseSizeBeforeCreate + 1);
        Stage testStage = stageList.get(stageList.size() - 1);
        assertThat(testStage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStage.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createStageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stageRepository.findAll().size();

        // Create the Stage with an existing ID
        stage.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStageMockMvc.perform(post("/api/stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stage)))
            .andExpect(status().isBadRequest());

        // Validate the Stage in the database
        List<Stage> stageList = stageRepository.findAll();
        assertThat(stageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = stageRepository.findAll().size();
        // set the field null
        stage.setName(null);

        // Create the Stage, which fails.

        restStageMockMvc.perform(post("/api/stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stage)))
            .andExpect(status().isBadRequest());

        List<Stage> stageList = stageRepository.findAll();
        assertThat(stageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStages() throws Exception {
        // Initialize the database
        stageRepository.saveAndFlush(stage);

        // Get all the stageList
        restStageMockMvc.perform(get("/api/stages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stage.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getStage() throws Exception {
        // Initialize the database
        stageRepository.saveAndFlush(stage);

        // Get the stage
        restStageMockMvc.perform(get("/api/stages/{id}", stage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(stage.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStage() throws Exception {
        // Get the stage
        restStageMockMvc.perform(get("/api/stages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStage() throws Exception {
        // Initialize the database
        stageService.save(stage);

        int databaseSizeBeforeUpdate = stageRepository.findAll().size();

        // Update the stage
        Stage updatedStage = stageRepository.findById(stage.getId()).get();
        // Disconnect from session so that the updates on updatedStage are not directly saved in db
        em.detach(updatedStage);
        updatedStage
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);

        restStageMockMvc.perform(put("/api/stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStage)))
            .andExpect(status().isOk());

        // Validate the Stage in the database
        List<Stage> stageList = stageRepository.findAll();
        assertThat(stageList).hasSize(databaseSizeBeforeUpdate);
        Stage testStage = stageList.get(stageList.size() - 1);
        assertThat(testStage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStage.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingStage() throws Exception {
        int databaseSizeBeforeUpdate = stageRepository.findAll().size();

        // Create the Stage

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStageMockMvc.perform(put("/api/stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stage)))
            .andExpect(status().isBadRequest());

        // Validate the Stage in the database
        List<Stage> stageList = stageRepository.findAll();
        assertThat(stageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStage() throws Exception {
        // Initialize the database
        stageService.save(stage);

        int databaseSizeBeforeDelete = stageRepository.findAll().size();

        // Get the stage
        restStageMockMvc.perform(delete("/api/stages/{id}", stage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Stage> stageList = stageRepository.findAll();
        assertThat(stageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Stage.class);
        Stage stage1 = new Stage();
        stage1.setId(1L);
        Stage stage2 = new Stage();
        stage2.setId(stage1.getId());
        assertThat(stage1).isEqualTo(stage2);
        stage2.setId(2L);
        assertThat(stage1).isNotEqualTo(stage2);
        stage1.setId(null);
        assertThat(stage1).isNotEqualTo(stage2);
    }
}
