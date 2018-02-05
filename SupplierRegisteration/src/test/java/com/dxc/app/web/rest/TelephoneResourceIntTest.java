package com.dxc.app.web.rest;

import com.dxc.app.SupplierRegisterationApp;

import com.dxc.app.domain.Telephone;
import com.dxc.app.repository.TelephoneRepository;
import com.dxc.app.service.TelephoneService;
import com.dxc.app.service.dto.TelephoneDTO;
import com.dxc.app.service.mapper.TelephoneMapper;
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

import com.dxc.app.domain.enumeration.PhoneType;
/**
 * Test class for the TelephoneResource REST controller.
 *
 * @see TelephoneResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SupplierRegisterationApp.class)
public class TelephoneResourceIntTest {

    private static final Integer DEFAULT_COUNTRY_CODE = 1;
    private static final Integer UPDATED_COUNTRY_CODE = 2;

    private static final Integer DEFAULT_AREA_CODE = 1;
    private static final Integer UPDATED_AREA_CODE = 2;

    private static final String DEFAULT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER = "BBBBBBBBBB";

    private static final PhoneType DEFAULT_TYPE = PhoneType.HOME;
    private static final PhoneType UPDATED_TYPE = PhoneType.OFFICE;

    private static final String DEFAULT_MOBILE_OTP = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_OTP = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_MOBILE_OTP_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MOBILE_OTP_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_MOBILE_OTP_EXPIRED = false;
    private static final Boolean UPDATED_MOBILE_OTP_EXPIRED = true;

    @Autowired
    private TelephoneRepository telephoneRepository;

    @Autowired
    private TelephoneMapper telephoneMapper;

    @Autowired
    private TelephoneService telephoneService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTelephoneMockMvc;

    private Telephone telephone;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TelephoneResource telephoneResource = new TelephoneResource(telephoneService);
        this.restTelephoneMockMvc = MockMvcBuilders.standaloneSetup(telephoneResource)
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
    public static Telephone createEntity(EntityManager em) {
        Telephone telephone = new Telephone()
            .countryCode(DEFAULT_COUNTRY_CODE)
            .areaCode(DEFAULT_AREA_CODE)
            .number(DEFAULT_NUMBER)
            .type(DEFAULT_TYPE)
            .mobileOTP(DEFAULT_MOBILE_OTP)
            .mobileOTPDate(DEFAULT_MOBILE_OTP_DATE)
            .mobileOTPExpired(DEFAULT_MOBILE_OTP_EXPIRED);
        return telephone;
    }

    @Before
    public void initTest() {
        telephone = createEntity(em);
    }

    @Test
    @Transactional
    public void createTelephone() throws Exception {
        int databaseSizeBeforeCreate = telephoneRepository.findAll().size();

        // Create the Telephone
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);
        restTelephoneMockMvc.perform(post("/api/telephones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(telephoneDTO)))
            .andExpect(status().isCreated());

        // Validate the Telephone in the database
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeCreate + 1);
        Telephone testTelephone = telephoneList.get(telephoneList.size() - 1);
        assertThat(testTelephone.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
        assertThat(testTelephone.getAreaCode()).isEqualTo(DEFAULT_AREA_CODE);
        assertThat(testTelephone.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testTelephone.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testTelephone.getMobileOTP()).isEqualTo(DEFAULT_MOBILE_OTP);
        assertThat(testTelephone.getMobileOTPDate()).isEqualTo(DEFAULT_MOBILE_OTP_DATE);
        assertThat(testTelephone.isMobileOTPExpired()).isEqualTo(DEFAULT_MOBILE_OTP_EXPIRED);
    }

    @Test
    @Transactional
    public void createTelephoneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = telephoneRepository.findAll().size();

        // Create the Telephone with an existing ID
        telephone.setId(1L);
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTelephoneMockMvc.perform(post("/api/telephones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(telephoneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Telephone in the database
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTelephones() throws Exception {
        // Initialize the database
        telephoneRepository.saveAndFlush(telephone);

        // Get all the telephoneList
        restTelephoneMockMvc.perform(get("/api/telephones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(telephone.getId().intValue())))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].areaCode").value(hasItem(DEFAULT_AREA_CODE)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].mobileOTP").value(hasItem(DEFAULT_MOBILE_OTP.toString())))
            .andExpect(jsonPath("$.[*].mobileOTPDate").value(hasItem(sameInstant(DEFAULT_MOBILE_OTP_DATE))))
            .andExpect(jsonPath("$.[*].mobileOTPExpired").value(hasItem(DEFAULT_MOBILE_OTP_EXPIRED.booleanValue())));
    }

    @Test
    @Transactional
    public void getTelephone() throws Exception {
        // Initialize the database
        telephoneRepository.saveAndFlush(telephone);

        // Get the telephone
        restTelephoneMockMvc.perform(get("/api/telephones/{id}", telephone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(telephone.getId().intValue()))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE))
            .andExpect(jsonPath("$.areaCode").value(DEFAULT_AREA_CODE))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.mobileOTP").value(DEFAULT_MOBILE_OTP.toString()))
            .andExpect(jsonPath("$.mobileOTPDate").value(sameInstant(DEFAULT_MOBILE_OTP_DATE)))
            .andExpect(jsonPath("$.mobileOTPExpired").value(DEFAULT_MOBILE_OTP_EXPIRED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTelephone() throws Exception {
        // Get the telephone
        restTelephoneMockMvc.perform(get("/api/telephones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTelephone() throws Exception {
        // Initialize the database
        telephoneRepository.saveAndFlush(telephone);
        int databaseSizeBeforeUpdate = telephoneRepository.findAll().size();

        // Update the telephone
        Telephone updatedTelephone = telephoneRepository.findOne(telephone.getId());
        // Disconnect from session so that the updates on updatedTelephone are not directly saved in db
        em.detach(updatedTelephone);
        updatedTelephone
            .countryCode(UPDATED_COUNTRY_CODE)
            .areaCode(UPDATED_AREA_CODE)
            .number(UPDATED_NUMBER)
            .type(UPDATED_TYPE)
            .mobileOTP(UPDATED_MOBILE_OTP)
            .mobileOTPDate(UPDATED_MOBILE_OTP_DATE)
            .mobileOTPExpired(UPDATED_MOBILE_OTP_EXPIRED);
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(updatedTelephone);

        restTelephoneMockMvc.perform(put("/api/telephones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(telephoneDTO)))
            .andExpect(status().isOk());

        // Validate the Telephone in the database
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeUpdate);
        Telephone testTelephone = telephoneList.get(telephoneList.size() - 1);
        assertThat(testTelephone.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testTelephone.getAreaCode()).isEqualTo(UPDATED_AREA_CODE);
        assertThat(testTelephone.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testTelephone.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTelephone.getMobileOTP()).isEqualTo(UPDATED_MOBILE_OTP);
        assertThat(testTelephone.getMobileOTPDate()).isEqualTo(UPDATED_MOBILE_OTP_DATE);
        assertThat(testTelephone.isMobileOTPExpired()).isEqualTo(UPDATED_MOBILE_OTP_EXPIRED);
    }

    @Test
    @Transactional
    public void updateNonExistingTelephone() throws Exception {
        int databaseSizeBeforeUpdate = telephoneRepository.findAll().size();

        // Create the Telephone
        TelephoneDTO telephoneDTO = telephoneMapper.toDto(telephone);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTelephoneMockMvc.perform(put("/api/telephones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(telephoneDTO)))
            .andExpect(status().isCreated());

        // Validate the Telephone in the database
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTelephone() throws Exception {
        // Initialize the database
        telephoneRepository.saveAndFlush(telephone);
        int databaseSizeBeforeDelete = telephoneRepository.findAll().size();

        // Get the telephone
        restTelephoneMockMvc.perform(delete("/api/telephones/{id}", telephone.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Telephone> telephoneList = telephoneRepository.findAll();
        assertThat(telephoneList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Telephone.class);
        Telephone telephone1 = new Telephone();
        telephone1.setId(1L);
        Telephone telephone2 = new Telephone();
        telephone2.setId(telephone1.getId());
        assertThat(telephone1).isEqualTo(telephone2);
        telephone2.setId(2L);
        assertThat(telephone1).isNotEqualTo(telephone2);
        telephone1.setId(null);
        assertThat(telephone1).isNotEqualTo(telephone2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TelephoneDTO.class);
        TelephoneDTO telephoneDTO1 = new TelephoneDTO();
        telephoneDTO1.setId(1L);
        TelephoneDTO telephoneDTO2 = new TelephoneDTO();
        assertThat(telephoneDTO1).isNotEqualTo(telephoneDTO2);
        telephoneDTO2.setId(telephoneDTO1.getId());
        assertThat(telephoneDTO1).isEqualTo(telephoneDTO2);
        telephoneDTO2.setId(2L);
        assertThat(telephoneDTO1).isNotEqualTo(telephoneDTO2);
        telephoneDTO1.setId(null);
        assertThat(telephoneDTO1).isNotEqualTo(telephoneDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(telephoneMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(telephoneMapper.fromId(null)).isNull();
    }
}
