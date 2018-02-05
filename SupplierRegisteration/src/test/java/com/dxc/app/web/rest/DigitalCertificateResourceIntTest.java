package com.dxc.app.web.rest;

import com.dxc.app.SupplierRegisterationApp;

import com.dxc.app.domain.DigitalCertificate;
import com.dxc.app.repository.DigitalCertificateRepository;
import com.dxc.app.service.DigitalCertificateService;
import com.dxc.app.service.dto.DigitalCertificateDTO;
import com.dxc.app.service.mapper.DigitalCertificateMapper;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.dxc.app.web.rest.TestUtil.sameInstant;
import static com.dxc.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DigitalCertificateResource REST controller.
 *
 * @see DigitalCertificateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SupplierRegisterationApp.class)
public class DigitalCertificateResourceIntTest {

    private static final String DEFAULT_CERT_CHAIN = "AAAAAAAAAA";
    private static final String UPDATED_CERT_CHAIN = "BBBBBBBBBB";

    private static final String DEFAULT_CERTIFICATE_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_CERTIFICATE_SERIAL = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TERM_START_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TERM_START_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TERM_END_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TERM_END_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_SIGNATURE = false;
    private static final Boolean UPDATED_SIGNATURE = true;

    private static final Boolean DEFAULT_ENCRYPTION = false;
    private static final Boolean UPDATED_ENCRYPTION = true;

    private static final Boolean DEFAULT_ACTIVE_YN = false;
    private static final Boolean UPDATED_ACTIVE_YN = true;

    @Autowired
    private DigitalCertificateRepository digitalCertificateRepository;

    @Autowired
    private DigitalCertificateMapper digitalCertificateMapper;

    @Autowired
    private DigitalCertificateService digitalCertificateService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDigitalCertificateMockMvc;

    private DigitalCertificate digitalCertificate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DigitalCertificateResource digitalCertificateResource = new DigitalCertificateResource(digitalCertificateService);
        this.restDigitalCertificateMockMvc = MockMvcBuilders.standaloneSetup(digitalCertificateResource)
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
    public static DigitalCertificate createEntity(EntityManager em) {
        DigitalCertificate digitalCertificate = new DigitalCertificate()
            .certChain(DEFAULT_CERT_CHAIN)
            .certificateSerial(DEFAULT_CERTIFICATE_SERIAL)
            .termStartDate(DEFAULT_TERM_START_DATE)
            .termEndDate(DEFAULT_TERM_END_DATE)
            .signature(DEFAULT_SIGNATURE)
            .encryption(DEFAULT_ENCRYPTION)
            .activeYn(DEFAULT_ACTIVE_YN);
        return digitalCertificate;
    }

    @Before
    public void initTest() {
        digitalCertificate = createEntity(em);
    }

    @Test
    @Transactional
    public void createDigitalCertificate() throws Exception {
        int databaseSizeBeforeCreate = digitalCertificateRepository.findAll().size();

        // Create the DigitalCertificate
        DigitalCertificateDTO digitalCertificateDTO = digitalCertificateMapper.toDto(digitalCertificate);
        restDigitalCertificateMockMvc.perform(post("/api/digital-certificates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(digitalCertificateDTO)))
            .andExpect(status().isCreated());

        // Validate the DigitalCertificate in the database
        List<DigitalCertificate> digitalCertificateList = digitalCertificateRepository.findAll();
        assertThat(digitalCertificateList).hasSize(databaseSizeBeforeCreate + 1);
        DigitalCertificate testDigitalCertificate = digitalCertificateList.get(digitalCertificateList.size() - 1);
        assertThat(testDigitalCertificate.getCertChain()).isEqualTo(DEFAULT_CERT_CHAIN);
        assertThat(testDigitalCertificate.getCertificateSerial()).isEqualTo(DEFAULT_CERTIFICATE_SERIAL);
        assertThat(testDigitalCertificate.getTermStartDate()).isEqualTo(DEFAULT_TERM_START_DATE);
        assertThat(testDigitalCertificate.getTermEndDate()).isEqualTo(DEFAULT_TERM_END_DATE);
        assertThat(testDigitalCertificate.isSignature()).isEqualTo(DEFAULT_SIGNATURE);
        assertThat(testDigitalCertificate.isEncryption()).isEqualTo(DEFAULT_ENCRYPTION);
        assertThat(testDigitalCertificate.isActiveYn()).isEqualTo(DEFAULT_ACTIVE_YN);
    }

    @Test
    @Transactional
    public void createDigitalCertificateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = digitalCertificateRepository.findAll().size();

        // Create the DigitalCertificate with an existing ID
        digitalCertificate.setId(1L);
        DigitalCertificateDTO digitalCertificateDTO = digitalCertificateMapper.toDto(digitalCertificate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDigitalCertificateMockMvc.perform(post("/api/digital-certificates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(digitalCertificateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DigitalCertificate in the database
        List<DigitalCertificate> digitalCertificateList = digitalCertificateRepository.findAll();
        assertThat(digitalCertificateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDigitalCertificates() throws Exception {
        // Initialize the database
        digitalCertificateRepository.saveAndFlush(digitalCertificate);

        // Get all the digitalCertificateList
        restDigitalCertificateMockMvc.perform(get("/api/digital-certificates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(digitalCertificate.getId().intValue())))
            .andExpect(jsonPath("$.[*].certChain").value(hasItem(DEFAULT_CERT_CHAIN.toString())))
            .andExpect(jsonPath("$.[*].certificateSerial").value(hasItem(DEFAULT_CERTIFICATE_SERIAL.toString())))
            .andExpect(jsonPath("$.[*].termStartDate").value(hasItem(sameInstant(DEFAULT_TERM_START_DATE))))
            .andExpect(jsonPath("$.[*].termEndDate").value(hasItem(sameInstant(DEFAULT_TERM_END_DATE))))
            .andExpect(jsonPath("$.[*].signature").value(hasItem(DEFAULT_SIGNATURE.booleanValue())))
            .andExpect(jsonPath("$.[*].encryption").value(hasItem(DEFAULT_ENCRYPTION.booleanValue())))
            .andExpect(jsonPath("$.[*].activeYn").value(hasItem(DEFAULT_ACTIVE_YN.booleanValue())));
    }

    @Test
    @Transactional
    public void getDigitalCertificate() throws Exception {
        // Initialize the database
        digitalCertificateRepository.saveAndFlush(digitalCertificate);

        // Get the digitalCertificate
        restDigitalCertificateMockMvc.perform(get("/api/digital-certificates/{id}", digitalCertificate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(digitalCertificate.getId().intValue()))
            .andExpect(jsonPath("$.certChain").value(DEFAULT_CERT_CHAIN.toString()))
            .andExpect(jsonPath("$.certificateSerial").value(DEFAULT_CERTIFICATE_SERIAL.toString()))
            .andExpect(jsonPath("$.termStartDate").value(sameInstant(DEFAULT_TERM_START_DATE)))
            .andExpect(jsonPath("$.termEndDate").value(sameInstant(DEFAULT_TERM_END_DATE)))
            .andExpect(jsonPath("$.signature").value(DEFAULT_SIGNATURE.booleanValue()))
            .andExpect(jsonPath("$.encryption").value(DEFAULT_ENCRYPTION.booleanValue()))
            .andExpect(jsonPath("$.activeYn").value(DEFAULT_ACTIVE_YN.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDigitalCertificate() throws Exception {
        // Get the digitalCertificate
        restDigitalCertificateMockMvc.perform(get("/api/digital-certificates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDigitalCertificate() throws Exception {
        // Initialize the database
        digitalCertificateRepository.saveAndFlush(digitalCertificate);
        int databaseSizeBeforeUpdate = digitalCertificateRepository.findAll().size();

        // Update the digitalCertificate
        DigitalCertificate updatedDigitalCertificate = digitalCertificateRepository.findOne(digitalCertificate.getId());
        // Disconnect from session so that the updates on updatedDigitalCertificate are not directly saved in db
        em.detach(updatedDigitalCertificate);
        updatedDigitalCertificate
            .certChain(UPDATED_CERT_CHAIN)
            .certificateSerial(UPDATED_CERTIFICATE_SERIAL)
            .termStartDate(UPDATED_TERM_START_DATE)
            .termEndDate(UPDATED_TERM_END_DATE)
            .signature(UPDATED_SIGNATURE)
            .encryption(UPDATED_ENCRYPTION)
            .activeYn(UPDATED_ACTIVE_YN);
        DigitalCertificateDTO digitalCertificateDTO = digitalCertificateMapper.toDto(updatedDigitalCertificate);

        restDigitalCertificateMockMvc.perform(put("/api/digital-certificates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(digitalCertificateDTO)))
            .andExpect(status().isOk());

        // Validate the DigitalCertificate in the database
        List<DigitalCertificate> digitalCertificateList = digitalCertificateRepository.findAll();
        assertThat(digitalCertificateList).hasSize(databaseSizeBeforeUpdate);
        DigitalCertificate testDigitalCertificate = digitalCertificateList.get(digitalCertificateList.size() - 1);
        assertThat(testDigitalCertificate.getCertChain()).isEqualTo(UPDATED_CERT_CHAIN);
        assertThat(testDigitalCertificate.getCertificateSerial()).isEqualTo(UPDATED_CERTIFICATE_SERIAL);
        assertThat(testDigitalCertificate.getTermStartDate()).isEqualTo(UPDATED_TERM_START_DATE);
        assertThat(testDigitalCertificate.getTermEndDate()).isEqualTo(UPDATED_TERM_END_DATE);
        assertThat(testDigitalCertificate.isSignature()).isEqualTo(UPDATED_SIGNATURE);
        assertThat(testDigitalCertificate.isEncryption()).isEqualTo(UPDATED_ENCRYPTION);
        assertThat(testDigitalCertificate.isActiveYn()).isEqualTo(UPDATED_ACTIVE_YN);
    }

    @Test
    @Transactional
    public void updateNonExistingDigitalCertificate() throws Exception {
        int databaseSizeBeforeUpdate = digitalCertificateRepository.findAll().size();

        // Create the DigitalCertificate
        DigitalCertificateDTO digitalCertificateDTO = digitalCertificateMapper.toDto(digitalCertificate);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDigitalCertificateMockMvc.perform(put("/api/digital-certificates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(digitalCertificateDTO)))
            .andExpect(status().isCreated());

        // Validate the DigitalCertificate in the database
        List<DigitalCertificate> digitalCertificateList = digitalCertificateRepository.findAll();
        assertThat(digitalCertificateList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDigitalCertificate() throws Exception {
        // Initialize the database
        digitalCertificateRepository.saveAndFlush(digitalCertificate);
        int databaseSizeBeforeDelete = digitalCertificateRepository.findAll().size();

        // Get the digitalCertificate
        restDigitalCertificateMockMvc.perform(delete("/api/digital-certificates/{id}", digitalCertificate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DigitalCertificate> digitalCertificateList = digitalCertificateRepository.findAll();
        assertThat(digitalCertificateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DigitalCertificate.class);
        DigitalCertificate digitalCertificate1 = new DigitalCertificate();
        digitalCertificate1.setId(1L);
        DigitalCertificate digitalCertificate2 = new DigitalCertificate();
        digitalCertificate2.setId(digitalCertificate1.getId());
        assertThat(digitalCertificate1).isEqualTo(digitalCertificate2);
        digitalCertificate2.setId(2L);
        assertThat(digitalCertificate1).isNotEqualTo(digitalCertificate2);
        digitalCertificate1.setId(null);
        assertThat(digitalCertificate1).isNotEqualTo(digitalCertificate2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DigitalCertificateDTO.class);
        DigitalCertificateDTO digitalCertificateDTO1 = new DigitalCertificateDTO();
        digitalCertificateDTO1.setId(1L);
        DigitalCertificateDTO digitalCertificateDTO2 = new DigitalCertificateDTO();
        assertThat(digitalCertificateDTO1).isNotEqualTo(digitalCertificateDTO2);
        digitalCertificateDTO2.setId(digitalCertificateDTO1.getId());
        assertThat(digitalCertificateDTO1).isEqualTo(digitalCertificateDTO2);
        digitalCertificateDTO2.setId(2L);
        assertThat(digitalCertificateDTO1).isNotEqualTo(digitalCertificateDTO2);
        digitalCertificateDTO1.setId(null);
        assertThat(digitalCertificateDTO1).isNotEqualTo(digitalCertificateDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(digitalCertificateMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(digitalCertificateMapper.fromId(null)).isNull();
    }
}
