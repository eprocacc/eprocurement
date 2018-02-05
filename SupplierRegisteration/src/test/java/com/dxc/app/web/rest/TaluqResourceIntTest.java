package com.dxc.app.web.rest;

import com.dxc.app.SupplierRegisterationApp;

import com.dxc.app.domain.Taluq;
import com.dxc.app.repository.TaluqRepository;
import com.dxc.app.service.TaluqService;
import com.dxc.app.service.dto.TaluqDTO;
import com.dxc.app.service.mapper.TaluqMapper;
import com.dxc.app.web.rest.errors.ExceptionTranslator;

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

import javax.persistence.EntityManager;
import java.util.List;

import static com.dxc.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TaluqResource REST controller.
 *
 * @see TaluqResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SupplierRegisterationApp.class)
public class TaluqResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private TaluqRepository taluqRepository;

    @Autowired
    private TaluqMapper taluqMapper;

    @Autowired
    private TaluqService taluqService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTaluqMockMvc;

    private Taluq taluq;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TaluqResource taluqResource = new TaluqResource(taluqService);
        this.restTaluqMockMvc = MockMvcBuilders.standaloneSetup(taluqResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Taluq createEntity(EntityManager em) {
        Taluq taluq = new Taluq()
            .name(DEFAULT_NAME);
        return taluq;
    }

    @Before
    public void initTest() {
        taluq = createEntity(em);
    }

    @Test
    @Transactional
    public void createTaluq() throws Exception {
        int databaseSizeBeforeCreate = taluqRepository.findAll().size();

        // Create the Taluq
        TaluqDTO taluqDTO = taluqMapper.toDto(taluq);
        restTaluqMockMvc.perform(post("/api/taluqs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taluqDTO)))
            .andExpect(status().isCreated());

        // Validate the Taluq in the database
        List<Taluq> taluqList = taluqRepository.findAll();
        assertThat(taluqList).hasSize(databaseSizeBeforeCreate + 1);
        Taluq testTaluq = taluqList.get(taluqList.size() - 1);
        assertThat(testTaluq.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createTaluqWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taluqRepository.findAll().size();

        // Create the Taluq with an existing ID
        taluq.setId(1L);
        TaluqDTO taluqDTO = taluqMapper.toDto(taluq);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaluqMockMvc.perform(post("/api/taluqs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taluqDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Taluq in the database
        List<Taluq> taluqList = taluqRepository.findAll();
        assertThat(taluqList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTaluqs() throws Exception {
        // Initialize the database
        taluqRepository.saveAndFlush(taluq);

        // Get all the taluqList
        restTaluqMockMvc.perform(get("/api/taluqs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taluq.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getTaluq() throws Exception {
        // Initialize the database
        taluqRepository.saveAndFlush(taluq);

        // Get the taluq
        restTaluqMockMvc.perform(get("/api/taluqs/{id}", taluq.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(taluq.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTaluq() throws Exception {
        // Get the taluq
        restTaluqMockMvc.perform(get("/api/taluqs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTaluq() throws Exception {
        // Initialize the database
        taluqRepository.saveAndFlush(taluq);
        int databaseSizeBeforeUpdate = taluqRepository.findAll().size();

        // Update the taluq
        Taluq updatedTaluq = taluqRepository.findOne(taluq.getId());
        // Disconnect from session so that the updates on updatedTaluq are not directly saved in db
        em.detach(updatedTaluq);
        updatedTaluq
            .name(UPDATED_NAME);
        TaluqDTO taluqDTO = taluqMapper.toDto(updatedTaluq);

        restTaluqMockMvc.perform(put("/api/taluqs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taluqDTO)))
            .andExpect(status().isOk());

        // Validate the Taluq in the database
        List<Taluq> taluqList = taluqRepository.findAll();
        assertThat(taluqList).hasSize(databaseSizeBeforeUpdate);
        Taluq testTaluq = taluqList.get(taluqList.size() - 1);
        assertThat(testTaluq.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingTaluq() throws Exception {
        int databaseSizeBeforeUpdate = taluqRepository.findAll().size();

        // Create the Taluq
        TaluqDTO taluqDTO = taluqMapper.toDto(taluq);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTaluqMockMvc.perform(put("/api/taluqs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taluqDTO)))
            .andExpect(status().isCreated());

        // Validate the Taluq in the database
        List<Taluq> taluqList = taluqRepository.findAll();
        assertThat(taluqList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTaluq() throws Exception {
        // Initialize the database
        taluqRepository.saveAndFlush(taluq);
        int databaseSizeBeforeDelete = taluqRepository.findAll().size();

        // Get the taluq
        restTaluqMockMvc.perform(delete("/api/taluqs/{id}", taluq.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Taluq> taluqList = taluqRepository.findAll();
        assertThat(taluqList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Taluq.class);
        Taluq taluq1 = new Taluq();
        taluq1.setId(1L);
        Taluq taluq2 = new Taluq();
        taluq2.setId(taluq1.getId());
        assertThat(taluq1).isEqualTo(taluq2);
        taluq2.setId(2L);
        assertThat(taluq1).isNotEqualTo(taluq2);
        taluq1.setId(null);
        assertThat(taluq1).isNotEqualTo(taluq2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaluqDTO.class);
        TaluqDTO taluqDTO1 = new TaluqDTO();
        taluqDTO1.setId(1L);
        TaluqDTO taluqDTO2 = new TaluqDTO();
        assertThat(taluqDTO1).isNotEqualTo(taluqDTO2);
        taluqDTO2.setId(taluqDTO1.getId());
        assertThat(taluqDTO1).isEqualTo(taluqDTO2);
        taluqDTO2.setId(2L);
        assertThat(taluqDTO1).isNotEqualTo(taluqDTO2);
        taluqDTO1.setId(null);
        assertThat(taluqDTO1).isNotEqualTo(taluqDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(taluqMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(taluqMapper.fromId(null)).isNull();
    }
}
