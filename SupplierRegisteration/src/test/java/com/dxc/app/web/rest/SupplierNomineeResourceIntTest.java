package com.dxc.app.web.rest;

import com.dxc.app.SupplierRegisterationApp;

import com.dxc.app.domain.SupplierNominee;
import com.dxc.app.repository.SupplierNomineeRepository;
import com.dxc.app.service.SupplierNomineeService;
import com.dxc.app.service.dto.SupplierNomineeDTO;
import com.dxc.app.service.mapper.SupplierNomineeMapper;
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
 * Test class for the SupplierNomineeResource REST controller.
 *
 * @see SupplierNomineeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SupplierRegisterationApp.class)
public class SupplierNomineeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_REGISTERED = false;
    private static final Boolean UPDATED_REGISTERED = true;

    private static final String DEFAULT_CERT_CHAIN = "AAAAAAAAAA";
    private static final String UPDATED_CERT_CHAIN = "BBBBBBBBBB";

    @Autowired
    private SupplierNomineeRepository supplierNomineeRepository;

    @Autowired
    private SupplierNomineeMapper supplierNomineeMapper;

    @Autowired
    private SupplierNomineeService supplierNomineeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSupplierNomineeMockMvc;

    private SupplierNominee supplierNominee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SupplierNomineeResource supplierNomineeResource = new SupplierNomineeResource(supplierNomineeService);
        this.restSupplierNomineeMockMvc = MockMvcBuilders.standaloneSetup(supplierNomineeResource)
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
    public static SupplierNominee createEntity(EntityManager em) {
        SupplierNominee supplierNominee = new SupplierNominee()
            .name(DEFAULT_NAME)
            .registered(DEFAULT_REGISTERED)
            .certChain(DEFAULT_CERT_CHAIN);
        return supplierNominee;
    }

    @Before
    public void initTest() {
        supplierNominee = createEntity(em);
    }

    @Test
    @Transactional
    public void createSupplierNominee() throws Exception {
        int databaseSizeBeforeCreate = supplierNomineeRepository.findAll().size();

        // Create the SupplierNominee
        SupplierNomineeDTO supplierNomineeDTO = supplierNomineeMapper.toDto(supplierNominee);
        restSupplierNomineeMockMvc.perform(post("/api/supplier-nominees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supplierNomineeDTO)))
            .andExpect(status().isCreated());

        // Validate the SupplierNominee in the database
        List<SupplierNominee> supplierNomineeList = supplierNomineeRepository.findAll();
        assertThat(supplierNomineeList).hasSize(databaseSizeBeforeCreate + 1);
        SupplierNominee testSupplierNominee = supplierNomineeList.get(supplierNomineeList.size() - 1);
        assertThat(testSupplierNominee.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSupplierNominee.isRegistered()).isEqualTo(DEFAULT_REGISTERED);
        assertThat(testSupplierNominee.getCertChain()).isEqualTo(DEFAULT_CERT_CHAIN);
    }

    @Test
    @Transactional
    public void createSupplierNomineeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = supplierNomineeRepository.findAll().size();

        // Create the SupplierNominee with an existing ID
        supplierNominee.setId(1L);
        SupplierNomineeDTO supplierNomineeDTO = supplierNomineeMapper.toDto(supplierNominee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSupplierNomineeMockMvc.perform(post("/api/supplier-nominees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supplierNomineeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SupplierNominee in the database
        List<SupplierNominee> supplierNomineeList = supplierNomineeRepository.findAll();
        assertThat(supplierNomineeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSupplierNominees() throws Exception {
        // Initialize the database
        supplierNomineeRepository.saveAndFlush(supplierNominee);

        // Get all the supplierNomineeList
        restSupplierNomineeMockMvc.perform(get("/api/supplier-nominees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(supplierNominee.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].registered").value(hasItem(DEFAULT_REGISTERED.booleanValue())))
            .andExpect(jsonPath("$.[*].certChain").value(hasItem(DEFAULT_CERT_CHAIN.toString())));
    }

    @Test
    @Transactional
    public void getSupplierNominee() throws Exception {
        // Initialize the database
        supplierNomineeRepository.saveAndFlush(supplierNominee);

        // Get the supplierNominee
        restSupplierNomineeMockMvc.perform(get("/api/supplier-nominees/{id}", supplierNominee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(supplierNominee.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.registered").value(DEFAULT_REGISTERED.booleanValue()))
            .andExpect(jsonPath("$.certChain").value(DEFAULT_CERT_CHAIN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSupplierNominee() throws Exception {
        // Get the supplierNominee
        restSupplierNomineeMockMvc.perform(get("/api/supplier-nominees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSupplierNominee() throws Exception {
        // Initialize the database
        supplierNomineeRepository.saveAndFlush(supplierNominee);
        int databaseSizeBeforeUpdate = supplierNomineeRepository.findAll().size();

        // Update the supplierNominee
        SupplierNominee updatedSupplierNominee = supplierNomineeRepository.findOne(supplierNominee.getId());
        // Disconnect from session so that the updates on updatedSupplierNominee are not directly saved in db
        em.detach(updatedSupplierNominee);
        updatedSupplierNominee
            .name(UPDATED_NAME)
            .registered(UPDATED_REGISTERED)
            .certChain(UPDATED_CERT_CHAIN);
        SupplierNomineeDTO supplierNomineeDTO = supplierNomineeMapper.toDto(updatedSupplierNominee);

        restSupplierNomineeMockMvc.perform(put("/api/supplier-nominees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supplierNomineeDTO)))
            .andExpect(status().isOk());

        // Validate the SupplierNominee in the database
        List<SupplierNominee> supplierNomineeList = supplierNomineeRepository.findAll();
        assertThat(supplierNomineeList).hasSize(databaseSizeBeforeUpdate);
        SupplierNominee testSupplierNominee = supplierNomineeList.get(supplierNomineeList.size() - 1);
        assertThat(testSupplierNominee.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSupplierNominee.isRegistered()).isEqualTo(UPDATED_REGISTERED);
        assertThat(testSupplierNominee.getCertChain()).isEqualTo(UPDATED_CERT_CHAIN);
    }

    @Test
    @Transactional
    public void updateNonExistingSupplierNominee() throws Exception {
        int databaseSizeBeforeUpdate = supplierNomineeRepository.findAll().size();

        // Create the SupplierNominee
        SupplierNomineeDTO supplierNomineeDTO = supplierNomineeMapper.toDto(supplierNominee);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSupplierNomineeMockMvc.perform(put("/api/supplier-nominees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supplierNomineeDTO)))
            .andExpect(status().isCreated());

        // Validate the SupplierNominee in the database
        List<SupplierNominee> supplierNomineeList = supplierNomineeRepository.findAll();
        assertThat(supplierNomineeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSupplierNominee() throws Exception {
        // Initialize the database
        supplierNomineeRepository.saveAndFlush(supplierNominee);
        int databaseSizeBeforeDelete = supplierNomineeRepository.findAll().size();

        // Get the supplierNominee
        restSupplierNomineeMockMvc.perform(delete("/api/supplier-nominees/{id}", supplierNominee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SupplierNominee> supplierNomineeList = supplierNomineeRepository.findAll();
        assertThat(supplierNomineeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupplierNominee.class);
        SupplierNominee supplierNominee1 = new SupplierNominee();
        supplierNominee1.setId(1L);
        SupplierNominee supplierNominee2 = new SupplierNominee();
        supplierNominee2.setId(supplierNominee1.getId());
        assertThat(supplierNominee1).isEqualTo(supplierNominee2);
        supplierNominee2.setId(2L);
        assertThat(supplierNominee1).isNotEqualTo(supplierNominee2);
        supplierNominee1.setId(null);
        assertThat(supplierNominee1).isNotEqualTo(supplierNominee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupplierNomineeDTO.class);
        SupplierNomineeDTO supplierNomineeDTO1 = new SupplierNomineeDTO();
        supplierNomineeDTO1.setId(1L);
        SupplierNomineeDTO supplierNomineeDTO2 = new SupplierNomineeDTO();
        assertThat(supplierNomineeDTO1).isNotEqualTo(supplierNomineeDTO2);
        supplierNomineeDTO2.setId(supplierNomineeDTO1.getId());
        assertThat(supplierNomineeDTO1).isEqualTo(supplierNomineeDTO2);
        supplierNomineeDTO2.setId(2L);
        assertThat(supplierNomineeDTO1).isNotEqualTo(supplierNomineeDTO2);
        supplierNomineeDTO1.setId(null);
        assertThat(supplierNomineeDTO1).isNotEqualTo(supplierNomineeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(supplierNomineeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(supplierNomineeMapper.fromId(null)).isNull();
    }
}
