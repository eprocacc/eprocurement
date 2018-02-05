package com.dxc.app.web.rest;

import com.dxc.app.SupplierRegisterationApp;

import com.dxc.app.domain.Supplier;
import com.dxc.app.repository.SupplierRepository;
import com.dxc.app.service.SupplierService;
import com.dxc.app.service.dto.SupplierDTO;
import com.dxc.app.service.mapper.SupplierMapper;
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

import com.dxc.app.domain.enumeration.SocialStatus;
import com.dxc.app.domain.enumeration.SupplierStatus;
/**
 * Test class for the SupplierResource REST controller.
 *
 * @see SupplierResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SupplierRegisterationApp.class)
public class SupplierResourceIntTest {

    private static final String DEFAULT_APPLICANT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_APPLICANT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final SocialStatus DEFAULT_SOCIAL_STATUS = SocialStatus.HUF;
    private static final SocialStatus UPDATED_SOCIAL_STATUS = SocialStatus.SC;

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_REGN_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REGN_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PAN = "AAAAAAAAAA";
    private static final String UPDATED_PAN = "BBBBBBBBBB";

    private static final String DEFAULT_PAN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAN_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_GROSS_TURN_OVER = 1D;
    private static final Double UPDATED_GROSS_TURN_OVER = 2D;

    private static final SupplierStatus DEFAULT_SUPPLIER_STATUS = SupplierStatus.DRAFT;
    private static final SupplierStatus UPDATED_SUPPLIER_STATUS = SupplierStatus.RECEIVED;

    private static final Boolean DEFAULT_SSI_YN = false;
    private static final Boolean UPDATED_SSI_YN = true;

    private static final String DEFAULT_SSI_REGN_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SSI_REGN_NUMBER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PAN_STATUS = false;
    private static final Boolean UPDATED_PAN_STATUS = true;

    private static final Boolean DEFAULT_MSME_YN = false;
    private static final Boolean UPDATED_MSME_YN = true;

    private static final String DEFAULT_MSME_REGN_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MSME_REGN_NUMBER = "BBBBBBBBBB";

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSupplierMockMvc;

    private Supplier supplier;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SupplierResource supplierResource = new SupplierResource(supplierService);
        this.restSupplierMockMvc = MockMvcBuilders.standaloneSetup(supplierResource)
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
    public static Supplier createEntity(EntityManager em) {
        Supplier supplier = new Supplier()
            .applicantType(DEFAULT_APPLICANT_TYPE)
            .type(DEFAULT_TYPE)
            .name(DEFAULT_NAME)
            .firstName(DEFAULT_FIRST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .socialStatus(DEFAULT_SOCIAL_STATUS)
            .designation(DEFAULT_DESIGNATION)
            .regnNumber(DEFAULT_REGN_NUMBER)
            .pan(DEFAULT_PAN)
            .panName(DEFAULT_PAN_NAME)
            .grossTurnOver(DEFAULT_GROSS_TURN_OVER)
            .supplierStatus(DEFAULT_SUPPLIER_STATUS)
            .ssiYn(DEFAULT_SSI_YN)
            .ssiRegnNumber(DEFAULT_SSI_REGN_NUMBER)
            .panStatus(DEFAULT_PAN_STATUS)
            .msmeYn(DEFAULT_MSME_YN)
            .msmeRegnNumber(DEFAULT_MSME_REGN_NUMBER);
        return supplier;
    }

    @Before
    public void initTest() {
        supplier = createEntity(em);
    }

    @Test
    @Transactional
    public void createSupplier() throws Exception {
        int databaseSizeBeforeCreate = supplierRepository.findAll().size();

        // Create the Supplier
        SupplierDTO supplierDTO = supplierMapper.toDto(supplier);
        restSupplierMockMvc.perform(post("/api/suppliers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supplierDTO)))
            .andExpect(status().isCreated());

        // Validate the Supplier in the database
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeCreate + 1);
        Supplier testSupplier = supplierList.get(supplierList.size() - 1);
        assertThat(testSupplier.getApplicantType()).isEqualTo(DEFAULT_APPLICANT_TYPE);
        assertThat(testSupplier.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testSupplier.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSupplier.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testSupplier.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testSupplier.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testSupplier.getSocialStatus()).isEqualTo(DEFAULT_SOCIAL_STATUS);
        assertThat(testSupplier.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testSupplier.getRegnNumber()).isEqualTo(DEFAULT_REGN_NUMBER);
        assertThat(testSupplier.getPan()).isEqualTo(DEFAULT_PAN);
        assertThat(testSupplier.getPanName()).isEqualTo(DEFAULT_PAN_NAME);
        assertThat(testSupplier.getGrossTurnOver()).isEqualTo(DEFAULT_GROSS_TURN_OVER);
        assertThat(testSupplier.getSupplierStatus()).isEqualTo(DEFAULT_SUPPLIER_STATUS);
        assertThat(testSupplier.isSsiYn()).isEqualTo(DEFAULT_SSI_YN);
        assertThat(testSupplier.getSsiRegnNumber()).isEqualTo(DEFAULT_SSI_REGN_NUMBER);
        assertThat(testSupplier.isPanStatus()).isEqualTo(DEFAULT_PAN_STATUS);
        assertThat(testSupplier.isMsmeYn()).isEqualTo(DEFAULT_MSME_YN);
        assertThat(testSupplier.getMsmeRegnNumber()).isEqualTo(DEFAULT_MSME_REGN_NUMBER);
    }

    @Test
    @Transactional
    public void createSupplierWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = supplierRepository.findAll().size();

        // Create the Supplier with an existing ID
        supplier.setId(1L);
        SupplierDTO supplierDTO = supplierMapper.toDto(supplier);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSupplierMockMvc.perform(post("/api/suppliers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supplierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Supplier in the database
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSuppliers() throws Exception {
        // Initialize the database
        supplierRepository.saveAndFlush(supplier);

        // Get all the supplierList
        restSupplierMockMvc.perform(get("/api/suppliers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(supplier.getId().intValue())))
            .andExpect(jsonPath("$.[*].applicantType").value(hasItem(DEFAULT_APPLICANT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].socialStatus").value(hasItem(DEFAULT_SOCIAL_STATUS.toString())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].regnNumber").value(hasItem(DEFAULT_REGN_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].pan").value(hasItem(DEFAULT_PAN.toString())))
            .andExpect(jsonPath("$.[*].panName").value(hasItem(DEFAULT_PAN_NAME.toString())))
            .andExpect(jsonPath("$.[*].grossTurnOver").value(hasItem(DEFAULT_GROSS_TURN_OVER.doubleValue())))
            .andExpect(jsonPath("$.[*].supplierStatus").value(hasItem(DEFAULT_SUPPLIER_STATUS.toString())))
            .andExpect(jsonPath("$.[*].ssiYn").value(hasItem(DEFAULT_SSI_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].ssiRegnNumber").value(hasItem(DEFAULT_SSI_REGN_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].panStatus").value(hasItem(DEFAULT_PAN_STATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].msmeYn").value(hasItem(DEFAULT_MSME_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].msmeRegnNumber").value(hasItem(DEFAULT_MSME_REGN_NUMBER.toString())));
    }

    @Test
    @Transactional
    public void getSupplier() throws Exception {
        // Initialize the database
        supplierRepository.saveAndFlush(supplier);

        // Get the supplier
        restSupplierMockMvc.perform(get("/api/suppliers/{id}", supplier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(supplier.getId().intValue()))
            .andExpect(jsonPath("$.applicantType").value(DEFAULT_APPLICANT_TYPE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.socialStatus").value(DEFAULT_SOCIAL_STATUS.toString()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()))
            .andExpect(jsonPath("$.regnNumber").value(DEFAULT_REGN_NUMBER.toString()))
            .andExpect(jsonPath("$.pan").value(DEFAULT_PAN.toString()))
            .andExpect(jsonPath("$.panName").value(DEFAULT_PAN_NAME.toString()))
            .andExpect(jsonPath("$.grossTurnOver").value(DEFAULT_GROSS_TURN_OVER.doubleValue()))
            .andExpect(jsonPath("$.supplierStatus").value(DEFAULT_SUPPLIER_STATUS.toString()))
            .andExpect(jsonPath("$.ssiYn").value(DEFAULT_SSI_YN.booleanValue()))
            .andExpect(jsonPath("$.ssiRegnNumber").value(DEFAULT_SSI_REGN_NUMBER.toString()))
            .andExpect(jsonPath("$.panStatus").value(DEFAULT_PAN_STATUS.booleanValue()))
            .andExpect(jsonPath("$.msmeYn").value(DEFAULT_MSME_YN.booleanValue()))
            .andExpect(jsonPath("$.msmeRegnNumber").value(DEFAULT_MSME_REGN_NUMBER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSupplier() throws Exception {
        // Get the supplier
        restSupplierMockMvc.perform(get("/api/suppliers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSupplier() throws Exception {
        // Initialize the database
        supplierRepository.saveAndFlush(supplier);
        int databaseSizeBeforeUpdate = supplierRepository.findAll().size();

        // Update the supplier
        Supplier updatedSupplier = supplierRepository.findOne(supplier.getId());
        // Disconnect from session so that the updates on updatedSupplier are not directly saved in db
        em.detach(updatedSupplier);
        updatedSupplier
            .applicantType(UPDATED_APPLICANT_TYPE)
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .socialStatus(UPDATED_SOCIAL_STATUS)
            .designation(UPDATED_DESIGNATION)
            .regnNumber(UPDATED_REGN_NUMBER)
            .pan(UPDATED_PAN)
            .panName(UPDATED_PAN_NAME)
            .grossTurnOver(UPDATED_GROSS_TURN_OVER)
            .supplierStatus(UPDATED_SUPPLIER_STATUS)
            .ssiYn(UPDATED_SSI_YN)
            .ssiRegnNumber(UPDATED_SSI_REGN_NUMBER)
            .panStatus(UPDATED_PAN_STATUS)
            .msmeYn(UPDATED_MSME_YN)
            .msmeRegnNumber(UPDATED_MSME_REGN_NUMBER);
        SupplierDTO supplierDTO = supplierMapper.toDto(updatedSupplier);

        restSupplierMockMvc.perform(put("/api/suppliers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supplierDTO)))
            .andExpect(status().isOk());

        // Validate the Supplier in the database
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeUpdate);
        Supplier testSupplier = supplierList.get(supplierList.size() - 1);
        assertThat(testSupplier.getApplicantType()).isEqualTo(UPDATED_APPLICANT_TYPE);
        assertThat(testSupplier.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSupplier.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSupplier.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testSupplier.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testSupplier.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testSupplier.getSocialStatus()).isEqualTo(UPDATED_SOCIAL_STATUS);
        assertThat(testSupplier.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testSupplier.getRegnNumber()).isEqualTo(UPDATED_REGN_NUMBER);
        assertThat(testSupplier.getPan()).isEqualTo(UPDATED_PAN);
        assertThat(testSupplier.getPanName()).isEqualTo(UPDATED_PAN_NAME);
        assertThat(testSupplier.getGrossTurnOver()).isEqualTo(UPDATED_GROSS_TURN_OVER);
        assertThat(testSupplier.getSupplierStatus()).isEqualTo(UPDATED_SUPPLIER_STATUS);
        assertThat(testSupplier.isSsiYn()).isEqualTo(UPDATED_SSI_YN);
        assertThat(testSupplier.getSsiRegnNumber()).isEqualTo(UPDATED_SSI_REGN_NUMBER);
        assertThat(testSupplier.isPanStatus()).isEqualTo(UPDATED_PAN_STATUS);
        assertThat(testSupplier.isMsmeYn()).isEqualTo(UPDATED_MSME_YN);
        assertThat(testSupplier.getMsmeRegnNumber()).isEqualTo(UPDATED_MSME_REGN_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingSupplier() throws Exception {
        int databaseSizeBeforeUpdate = supplierRepository.findAll().size();

        // Create the Supplier
        SupplierDTO supplierDTO = supplierMapper.toDto(supplier);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSupplierMockMvc.perform(put("/api/suppliers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supplierDTO)))
            .andExpect(status().isCreated());

        // Validate the Supplier in the database
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSupplier() throws Exception {
        // Initialize the database
        supplierRepository.saveAndFlush(supplier);
        int databaseSizeBeforeDelete = supplierRepository.findAll().size();

        // Get the supplier
        restSupplierMockMvc.perform(delete("/api/suppliers/{id}", supplier.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Supplier.class);
        Supplier supplier1 = new Supplier();
        supplier1.setId(1L);
        Supplier supplier2 = new Supplier();
        supplier2.setId(supplier1.getId());
        assertThat(supplier1).isEqualTo(supplier2);
        supplier2.setId(2L);
        assertThat(supplier1).isNotEqualTo(supplier2);
        supplier1.setId(null);
        assertThat(supplier1).isNotEqualTo(supplier2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupplierDTO.class);
        SupplierDTO supplierDTO1 = new SupplierDTO();
        supplierDTO1.setId(1L);
        SupplierDTO supplierDTO2 = new SupplierDTO();
        assertThat(supplierDTO1).isNotEqualTo(supplierDTO2);
        supplierDTO2.setId(supplierDTO1.getId());
        assertThat(supplierDTO1).isEqualTo(supplierDTO2);
        supplierDTO2.setId(2L);
        assertThat(supplierDTO1).isNotEqualTo(supplierDTO2);
        supplierDTO1.setId(null);
        assertThat(supplierDTO1).isNotEqualTo(supplierDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(supplierMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(supplierMapper.fromId(null)).isNull();
    }
}
