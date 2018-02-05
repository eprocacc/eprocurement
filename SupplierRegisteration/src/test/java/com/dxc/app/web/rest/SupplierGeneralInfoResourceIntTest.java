package com.dxc.app.web.rest;

import com.dxc.app.SupplierRegisterationApp;

import com.dxc.app.domain.SupplierGeneralInfo;
import com.dxc.app.repository.SupplierGeneralInfoRepository;
import com.dxc.app.service.SupplierGeneralInfoService;
import com.dxc.app.service.dto.SupplierGeneralInfoDTO;
import com.dxc.app.service.mapper.SupplierGeneralInfoMapper;
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

import com.dxc.app.domain.enumeration.SupplierStatus;
import com.dxc.app.domain.enumeration.SupplierUserType;
import com.dxc.app.domain.enumeration.SupplierStatus;
/**
 * Test class for the SupplierGeneralInfoResource REST controller.
 *
 * @see SupplierGeneralInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SupplierRegisterationApp.class)
public class SupplierGeneralInfoResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final SupplierStatus DEFAULT_SUPPLIER_STATUS = SupplierStatus.DRAFT;
    private static final SupplierStatus UPDATED_SUPPLIER_STATUS = SupplierStatus.RECEIVED;

    private static final String DEFAULT_PRIMARY_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_EXPIRY_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_EXPIRY_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_APPLICATION_REF_NO = "AAAAAAAAAA";
    private static final String UPDATED_APPLICATION_REF_NO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TYPE_GOODS_YN = false;
    private static final Boolean UPDATED_TYPE_GOODS_YN = true;

    private static final Boolean DEFAULT_TYPE_SERVICES_YN = false;
    private static final Boolean UPDATED_TYPE_SERVICES_YN = true;

    private static final Boolean DEFAULT_TYPE_WORKS_YN = false;
    private static final Boolean UPDATED_TYPE_WORKS_YN = true;

    private static final SupplierUserType DEFAULT_USER_TYPE = SupplierUserType.AUTHORIZED_SIGNATORY;
    private static final SupplierUserType UPDATED_USER_TYPE = SupplierUserType.NOMINATED_USER;

    private static final ZonedDateTime DEFAULT_RENEWED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_RENEWED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_REG_VALIDITY = 1;
    private static final Integer UPDATED_REG_VALIDITY = 2;

    private static final ZonedDateTime DEFAULT_SUBMISSION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SUBMISSION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_RENEWAL_NO = 1;
    private static final Integer UPDATED_RENEWAL_NO = 2;

    private static final ZonedDateTime DEFAULT_APPROVALDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_APPROVALDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_REGISTRATION_BILLED_YN = false;
    private static final Boolean UPDATED_REGISTRATION_BILLED_YN = true;

    private static final Boolean DEFAULT_RENEWAL_BILLED_YN = false;
    private static final Boolean UPDATED_RENEWAL_BILLED_YN = true;

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_REMARKS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_BLACKLISTED_YN = false;
    private static final Boolean UPDATED_BLACKLISTED_YN = true;

    private static final Boolean DEFAULT_REG_MAIL_NOTIFICATION_YN = false;
    private static final Boolean UPDATED_REG_MAIL_NOTIFICATION_YN = true;

    private static final Boolean DEFAULT_APPLICATION_VER_YN = false;
    private static final Boolean UPDATED_APPLICATION_VER_YN = true;

    private static final SupplierStatus DEFAULT_APPROVER_DECISION = SupplierStatus.DRAFT;
    private static final SupplierStatus UPDATED_APPROVER_DECISION = SupplierStatus.RECEIVED;

    private static final Boolean DEFAULT_SEND_PUBLISHED_MAIL = false;
    private static final Boolean UPDATED_SEND_PUBLISHED_MAIL = true;

    private static final Boolean DEFAULT_CERTIFICATE_CHECK_YN = false;
    private static final Boolean UPDATED_CERTIFICATE_CHECK_YN = true;

    private static final Boolean DEFAULT_MOBILE_VERIFIED = false;
    private static final Boolean UPDATED_MOBILE_VERIFIED = true;

    private static final Boolean DEFAULT_SMS_ENABLED = false;
    private static final Boolean UPDATED_SMS_ENABLED = true;

    private static final Boolean DEFAULT_EMAIL_VERIFIED = false;
    private static final Boolean UPDATED_EMAIL_VERIFIED = true;

    private static final String DEFAULT_EMAIL_OTP = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_OTP = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_EMAIL_OTP_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_EMAIL_OTP_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_EMAIL_OTP_EXPIRED = false;
    private static final Boolean UPDATED_EMAIL_OTP_EXPIRED = true;

    @Autowired
    private SupplierGeneralInfoRepository supplierGeneralInfoRepository;

    @Autowired
    private SupplierGeneralInfoMapper supplierGeneralInfoMapper;

    @Autowired
    private SupplierGeneralInfoService supplierGeneralInfoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSupplierGeneralInfoMockMvc;

    private SupplierGeneralInfo supplierGeneralInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SupplierGeneralInfoResource supplierGeneralInfoResource = new SupplierGeneralInfoResource(supplierGeneralInfoService);
        this.restSupplierGeneralInfoMockMvc = MockMvcBuilders.standaloneSetup(supplierGeneralInfoResource)
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
    public static SupplierGeneralInfo createEntity(EntityManager em) {
        SupplierGeneralInfo supplierGeneralInfo = new SupplierGeneralInfo()
            .name(DEFAULT_NAME)
            .firstName(DEFAULT_FIRST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .supplierStatus(DEFAULT_SUPPLIER_STATUS)
            .primaryEmail(DEFAULT_PRIMARY_EMAIL)
            .secondaryEmail(DEFAULT_SECONDARY_EMAIL)
            .website(DEFAULT_WEBSITE)
            .expiryDate(DEFAULT_EXPIRY_DATE)
            .applicationRefNo(DEFAULT_APPLICATION_REF_NO)
            .typeGoodsYn(DEFAULT_TYPE_GOODS_YN)
            .typeServicesYn(DEFAULT_TYPE_SERVICES_YN)
            .typeWorksYn(DEFAULT_TYPE_WORKS_YN)
            .userType(DEFAULT_USER_TYPE)
            .renewedDate(DEFAULT_RENEWED_DATE)
            .regValidity(DEFAULT_REG_VALIDITY)
            .submissionDate(DEFAULT_SUBMISSION_DATE)
            .renewalNo(DEFAULT_RENEWAL_NO)
            .approvaldate(DEFAULT_APPROVALDATE)
            .registrationBilledYn(DEFAULT_REGISTRATION_BILLED_YN)
            .renewalBilledYn(DEFAULT_RENEWAL_BILLED_YN)
            .comments(DEFAULT_COMMENTS)
            .transactionRemarks(DEFAULT_TRANSACTION_REMARKS)
            .blacklistedYn(DEFAULT_BLACKLISTED_YN)
            .regMailNotificationYn(DEFAULT_REG_MAIL_NOTIFICATION_YN)
            .applicationVerYn(DEFAULT_APPLICATION_VER_YN)
            .approverDecision(DEFAULT_APPROVER_DECISION)
            .sendPublishedMail(DEFAULT_SEND_PUBLISHED_MAIL)
            .certificateCheckYn(DEFAULT_CERTIFICATE_CHECK_YN)
            .mobileVerified(DEFAULT_MOBILE_VERIFIED)
            .smsEnabled(DEFAULT_SMS_ENABLED)
            .emailVerified(DEFAULT_EMAIL_VERIFIED)
            .emailOTP(DEFAULT_EMAIL_OTP)
            .emailOTPDate(DEFAULT_EMAIL_OTP_DATE)
            .emailOTPExpired(DEFAULT_EMAIL_OTP_EXPIRED);
        return supplierGeneralInfo;
    }

    @Before
    public void initTest() {
        supplierGeneralInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createSupplierGeneralInfo() throws Exception {
        int databaseSizeBeforeCreate = supplierGeneralInfoRepository.findAll().size();

        // Create the SupplierGeneralInfo
        SupplierGeneralInfoDTO supplierGeneralInfoDTO = supplierGeneralInfoMapper.toDto(supplierGeneralInfo);
        restSupplierGeneralInfoMockMvc.perform(post("/api/supplier-general-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supplierGeneralInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the SupplierGeneralInfo in the database
        List<SupplierGeneralInfo> supplierGeneralInfoList = supplierGeneralInfoRepository.findAll();
        assertThat(supplierGeneralInfoList).hasSize(databaseSizeBeforeCreate + 1);
        SupplierGeneralInfo testSupplierGeneralInfo = supplierGeneralInfoList.get(supplierGeneralInfoList.size() - 1);
        assertThat(testSupplierGeneralInfo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSupplierGeneralInfo.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testSupplierGeneralInfo.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testSupplierGeneralInfo.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testSupplierGeneralInfo.getSupplierStatus()).isEqualTo(DEFAULT_SUPPLIER_STATUS);
        assertThat(testSupplierGeneralInfo.getPrimaryEmail()).isEqualTo(DEFAULT_PRIMARY_EMAIL);
        assertThat(testSupplierGeneralInfo.getSecondaryEmail()).isEqualTo(DEFAULT_SECONDARY_EMAIL);
        assertThat(testSupplierGeneralInfo.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testSupplierGeneralInfo.getExpiryDate()).isEqualTo(DEFAULT_EXPIRY_DATE);
        assertThat(testSupplierGeneralInfo.getApplicationRefNo()).isEqualTo(DEFAULT_APPLICATION_REF_NO);
        assertThat(testSupplierGeneralInfo.isTypeGoodsYn()).isEqualTo(DEFAULT_TYPE_GOODS_YN);
        assertThat(testSupplierGeneralInfo.isTypeServicesYn()).isEqualTo(DEFAULT_TYPE_SERVICES_YN);
        assertThat(testSupplierGeneralInfo.isTypeWorksYn()).isEqualTo(DEFAULT_TYPE_WORKS_YN);
        assertThat(testSupplierGeneralInfo.getUserType()).isEqualTo(DEFAULT_USER_TYPE);
        assertThat(testSupplierGeneralInfo.getRenewedDate()).isEqualTo(DEFAULT_RENEWED_DATE);
        assertThat(testSupplierGeneralInfo.getRegValidity()).isEqualTo(DEFAULT_REG_VALIDITY);
        assertThat(testSupplierGeneralInfo.getSubmissionDate()).isEqualTo(DEFAULT_SUBMISSION_DATE);
        assertThat(testSupplierGeneralInfo.getRenewalNo()).isEqualTo(DEFAULT_RENEWAL_NO);
        assertThat(testSupplierGeneralInfo.getApprovaldate()).isEqualTo(DEFAULT_APPROVALDATE);
        assertThat(testSupplierGeneralInfo.isRegistrationBilledYn()).isEqualTo(DEFAULT_REGISTRATION_BILLED_YN);
        assertThat(testSupplierGeneralInfo.isRenewalBilledYn()).isEqualTo(DEFAULT_RENEWAL_BILLED_YN);
        assertThat(testSupplierGeneralInfo.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testSupplierGeneralInfo.getTransactionRemarks()).isEqualTo(DEFAULT_TRANSACTION_REMARKS);
        assertThat(testSupplierGeneralInfo.isBlacklistedYn()).isEqualTo(DEFAULT_BLACKLISTED_YN);
        assertThat(testSupplierGeneralInfo.isRegMailNotificationYn()).isEqualTo(DEFAULT_REG_MAIL_NOTIFICATION_YN);
        assertThat(testSupplierGeneralInfo.isApplicationVerYn()).isEqualTo(DEFAULT_APPLICATION_VER_YN);
        assertThat(testSupplierGeneralInfo.getApproverDecision()).isEqualTo(DEFAULT_APPROVER_DECISION);
        assertThat(testSupplierGeneralInfo.isSendPublishedMail()).isEqualTo(DEFAULT_SEND_PUBLISHED_MAIL);
        assertThat(testSupplierGeneralInfo.isCertificateCheckYn()).isEqualTo(DEFAULT_CERTIFICATE_CHECK_YN);
        assertThat(testSupplierGeneralInfo.isMobileVerified()).isEqualTo(DEFAULT_MOBILE_VERIFIED);
        assertThat(testSupplierGeneralInfo.isSmsEnabled()).isEqualTo(DEFAULT_SMS_ENABLED);
        assertThat(testSupplierGeneralInfo.isEmailVerified()).isEqualTo(DEFAULT_EMAIL_VERIFIED);
        assertThat(testSupplierGeneralInfo.getEmailOTP()).isEqualTo(DEFAULT_EMAIL_OTP);
        assertThat(testSupplierGeneralInfo.getEmailOTPDate()).isEqualTo(DEFAULT_EMAIL_OTP_DATE);
        assertThat(testSupplierGeneralInfo.isEmailOTPExpired()).isEqualTo(DEFAULT_EMAIL_OTP_EXPIRED);
    }

    @Test
    @Transactional
    public void createSupplierGeneralInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = supplierGeneralInfoRepository.findAll().size();

        // Create the SupplierGeneralInfo with an existing ID
        supplierGeneralInfo.setId(1L);
        SupplierGeneralInfoDTO supplierGeneralInfoDTO = supplierGeneralInfoMapper.toDto(supplierGeneralInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSupplierGeneralInfoMockMvc.perform(post("/api/supplier-general-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supplierGeneralInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SupplierGeneralInfo in the database
        List<SupplierGeneralInfo> supplierGeneralInfoList = supplierGeneralInfoRepository.findAll();
        assertThat(supplierGeneralInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSupplierGeneralInfos() throws Exception {
        // Initialize the database
        supplierGeneralInfoRepository.saveAndFlush(supplierGeneralInfo);

        // Get all the supplierGeneralInfoList
        restSupplierGeneralInfoMockMvc.perform(get("/api/supplier-general-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(supplierGeneralInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].supplierStatus").value(hasItem(DEFAULT_SUPPLIER_STATUS.toString())))
            .andExpect(jsonPath("$.[*].primaryEmail").value(hasItem(DEFAULT_PRIMARY_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].secondaryEmail").value(hasItem(DEFAULT_SECONDARY_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].expiryDate").value(hasItem(sameInstant(DEFAULT_EXPIRY_DATE))))
            .andExpect(jsonPath("$.[*].applicationRefNo").value(hasItem(DEFAULT_APPLICATION_REF_NO.toString())))
            .andExpect(jsonPath("$.[*].typeGoodsYn").value(hasItem(DEFAULT_TYPE_GOODS_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].typeServicesYn").value(hasItem(DEFAULT_TYPE_SERVICES_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].typeWorksYn").value(hasItem(DEFAULT_TYPE_WORKS_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].userType").value(hasItem(DEFAULT_USER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].renewedDate").value(hasItem(sameInstant(DEFAULT_RENEWED_DATE))))
            .andExpect(jsonPath("$.[*].regValidity").value(hasItem(DEFAULT_REG_VALIDITY)))
            .andExpect(jsonPath("$.[*].submissionDate").value(hasItem(sameInstant(DEFAULT_SUBMISSION_DATE))))
            .andExpect(jsonPath("$.[*].renewalNo").value(hasItem(DEFAULT_RENEWAL_NO)))
            .andExpect(jsonPath("$.[*].approvaldate").value(hasItem(sameInstant(DEFAULT_APPROVALDATE))))
            .andExpect(jsonPath("$.[*].registrationBilledYn").value(hasItem(DEFAULT_REGISTRATION_BILLED_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].renewalBilledYn").value(hasItem(DEFAULT_RENEWAL_BILLED_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].transactionRemarks").value(hasItem(DEFAULT_TRANSACTION_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].blacklistedYn").value(hasItem(DEFAULT_BLACKLISTED_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].regMailNotificationYn").value(hasItem(DEFAULT_REG_MAIL_NOTIFICATION_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].applicationVerYn").value(hasItem(DEFAULT_APPLICATION_VER_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].approverDecision").value(hasItem(DEFAULT_APPROVER_DECISION.toString())))
            .andExpect(jsonPath("$.[*].sendPublishedMail").value(hasItem(DEFAULT_SEND_PUBLISHED_MAIL.booleanValue())))
            .andExpect(jsonPath("$.[*].certificateCheckYn").value(hasItem(DEFAULT_CERTIFICATE_CHECK_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].mobileVerified").value(hasItem(DEFAULT_MOBILE_VERIFIED.booleanValue())))
            .andExpect(jsonPath("$.[*].smsEnabled").value(hasItem(DEFAULT_SMS_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].emailVerified").value(hasItem(DEFAULT_EMAIL_VERIFIED.booleanValue())))
            .andExpect(jsonPath("$.[*].emailOTP").value(hasItem(DEFAULT_EMAIL_OTP.toString())))
            .andExpect(jsonPath("$.[*].emailOTPDate").value(hasItem(sameInstant(DEFAULT_EMAIL_OTP_DATE))))
            .andExpect(jsonPath("$.[*].emailOTPExpired").value(hasItem(DEFAULT_EMAIL_OTP_EXPIRED.booleanValue())));
    }

    @Test
    @Transactional
    public void getSupplierGeneralInfo() throws Exception {
        // Initialize the database
        supplierGeneralInfoRepository.saveAndFlush(supplierGeneralInfo);

        // Get the supplierGeneralInfo
        restSupplierGeneralInfoMockMvc.perform(get("/api/supplier-general-infos/{id}", supplierGeneralInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(supplierGeneralInfo.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.supplierStatus").value(DEFAULT_SUPPLIER_STATUS.toString()))
            .andExpect(jsonPath("$.primaryEmail").value(DEFAULT_PRIMARY_EMAIL.toString()))
            .andExpect(jsonPath("$.secondaryEmail").value(DEFAULT_SECONDARY_EMAIL.toString()))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE.toString()))
            .andExpect(jsonPath("$.expiryDate").value(sameInstant(DEFAULT_EXPIRY_DATE)))
            .andExpect(jsonPath("$.applicationRefNo").value(DEFAULT_APPLICATION_REF_NO.toString()))
            .andExpect(jsonPath("$.typeGoodsYn").value(DEFAULT_TYPE_GOODS_YN.booleanValue()))
            .andExpect(jsonPath("$.typeServicesYn").value(DEFAULT_TYPE_SERVICES_YN.booleanValue()))
            .andExpect(jsonPath("$.typeWorksYn").value(DEFAULT_TYPE_WORKS_YN.booleanValue()))
            .andExpect(jsonPath("$.userType").value(DEFAULT_USER_TYPE.toString()))
            .andExpect(jsonPath("$.renewedDate").value(sameInstant(DEFAULT_RENEWED_DATE)))
            .andExpect(jsonPath("$.regValidity").value(DEFAULT_REG_VALIDITY))
            .andExpect(jsonPath("$.submissionDate").value(sameInstant(DEFAULT_SUBMISSION_DATE)))
            .andExpect(jsonPath("$.renewalNo").value(DEFAULT_RENEWAL_NO))
            .andExpect(jsonPath("$.approvaldate").value(sameInstant(DEFAULT_APPROVALDATE)))
            .andExpect(jsonPath("$.registrationBilledYn").value(DEFAULT_REGISTRATION_BILLED_YN.booleanValue()))
            .andExpect(jsonPath("$.renewalBilledYn").value(DEFAULT_RENEWAL_BILLED_YN.booleanValue()))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()))
            .andExpect(jsonPath("$.transactionRemarks").value(DEFAULT_TRANSACTION_REMARKS.toString()))
            .andExpect(jsonPath("$.blacklistedYn").value(DEFAULT_BLACKLISTED_YN.booleanValue()))
            .andExpect(jsonPath("$.regMailNotificationYn").value(DEFAULT_REG_MAIL_NOTIFICATION_YN.booleanValue()))
            .andExpect(jsonPath("$.applicationVerYn").value(DEFAULT_APPLICATION_VER_YN.booleanValue()))
            .andExpect(jsonPath("$.approverDecision").value(DEFAULT_APPROVER_DECISION.toString()))
            .andExpect(jsonPath("$.sendPublishedMail").value(DEFAULT_SEND_PUBLISHED_MAIL.booleanValue()))
            .andExpect(jsonPath("$.certificateCheckYn").value(DEFAULT_CERTIFICATE_CHECK_YN.booleanValue()))
            .andExpect(jsonPath("$.mobileVerified").value(DEFAULT_MOBILE_VERIFIED.booleanValue()))
            .andExpect(jsonPath("$.smsEnabled").value(DEFAULT_SMS_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.emailVerified").value(DEFAULT_EMAIL_VERIFIED.booleanValue()))
            .andExpect(jsonPath("$.emailOTP").value(DEFAULT_EMAIL_OTP.toString()))
            .andExpect(jsonPath("$.emailOTPDate").value(sameInstant(DEFAULT_EMAIL_OTP_DATE)))
            .andExpect(jsonPath("$.emailOTPExpired").value(DEFAULT_EMAIL_OTP_EXPIRED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSupplierGeneralInfo() throws Exception {
        // Get the supplierGeneralInfo
        restSupplierGeneralInfoMockMvc.perform(get("/api/supplier-general-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSupplierGeneralInfo() throws Exception {
        // Initialize the database
        supplierGeneralInfoRepository.saveAndFlush(supplierGeneralInfo);
        int databaseSizeBeforeUpdate = supplierGeneralInfoRepository.findAll().size();

        // Update the supplierGeneralInfo
        SupplierGeneralInfo updatedSupplierGeneralInfo = supplierGeneralInfoRepository.findOne(supplierGeneralInfo.getId());
        // Disconnect from session so that the updates on updatedSupplierGeneralInfo are not directly saved in db
        em.detach(updatedSupplierGeneralInfo);
        updatedSupplierGeneralInfo
            .name(UPDATED_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .supplierStatus(UPDATED_SUPPLIER_STATUS)
            .primaryEmail(UPDATED_PRIMARY_EMAIL)
            .secondaryEmail(UPDATED_SECONDARY_EMAIL)
            .website(UPDATED_WEBSITE)
            .expiryDate(UPDATED_EXPIRY_DATE)
            .applicationRefNo(UPDATED_APPLICATION_REF_NO)
            .typeGoodsYn(UPDATED_TYPE_GOODS_YN)
            .typeServicesYn(UPDATED_TYPE_SERVICES_YN)
            .typeWorksYn(UPDATED_TYPE_WORKS_YN)
            .userType(UPDATED_USER_TYPE)
            .renewedDate(UPDATED_RENEWED_DATE)
            .regValidity(UPDATED_REG_VALIDITY)
            .submissionDate(UPDATED_SUBMISSION_DATE)
            .renewalNo(UPDATED_RENEWAL_NO)
            .approvaldate(UPDATED_APPROVALDATE)
            .registrationBilledYn(UPDATED_REGISTRATION_BILLED_YN)
            .renewalBilledYn(UPDATED_RENEWAL_BILLED_YN)
            .comments(UPDATED_COMMENTS)
            .transactionRemarks(UPDATED_TRANSACTION_REMARKS)
            .blacklistedYn(UPDATED_BLACKLISTED_YN)
            .regMailNotificationYn(UPDATED_REG_MAIL_NOTIFICATION_YN)
            .applicationVerYn(UPDATED_APPLICATION_VER_YN)
            .approverDecision(UPDATED_APPROVER_DECISION)
            .sendPublishedMail(UPDATED_SEND_PUBLISHED_MAIL)
            .certificateCheckYn(UPDATED_CERTIFICATE_CHECK_YN)
            .mobileVerified(UPDATED_MOBILE_VERIFIED)
            .smsEnabled(UPDATED_SMS_ENABLED)
            .emailVerified(UPDATED_EMAIL_VERIFIED)
            .emailOTP(UPDATED_EMAIL_OTP)
            .emailOTPDate(UPDATED_EMAIL_OTP_DATE)
            .emailOTPExpired(UPDATED_EMAIL_OTP_EXPIRED);
        SupplierGeneralInfoDTO supplierGeneralInfoDTO = supplierGeneralInfoMapper.toDto(updatedSupplierGeneralInfo);

        restSupplierGeneralInfoMockMvc.perform(put("/api/supplier-general-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supplierGeneralInfoDTO)))
            .andExpect(status().isOk());

        // Validate the SupplierGeneralInfo in the database
        List<SupplierGeneralInfo> supplierGeneralInfoList = supplierGeneralInfoRepository.findAll();
        assertThat(supplierGeneralInfoList).hasSize(databaseSizeBeforeUpdate);
        SupplierGeneralInfo testSupplierGeneralInfo = supplierGeneralInfoList.get(supplierGeneralInfoList.size() - 1);
        assertThat(testSupplierGeneralInfo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSupplierGeneralInfo.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testSupplierGeneralInfo.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testSupplierGeneralInfo.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testSupplierGeneralInfo.getSupplierStatus()).isEqualTo(UPDATED_SUPPLIER_STATUS);
        assertThat(testSupplierGeneralInfo.getPrimaryEmail()).isEqualTo(UPDATED_PRIMARY_EMAIL);
        assertThat(testSupplierGeneralInfo.getSecondaryEmail()).isEqualTo(UPDATED_SECONDARY_EMAIL);
        assertThat(testSupplierGeneralInfo.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testSupplierGeneralInfo.getExpiryDate()).isEqualTo(UPDATED_EXPIRY_DATE);
        assertThat(testSupplierGeneralInfo.getApplicationRefNo()).isEqualTo(UPDATED_APPLICATION_REF_NO);
        assertThat(testSupplierGeneralInfo.isTypeGoodsYn()).isEqualTo(UPDATED_TYPE_GOODS_YN);
        assertThat(testSupplierGeneralInfo.isTypeServicesYn()).isEqualTo(UPDATED_TYPE_SERVICES_YN);
        assertThat(testSupplierGeneralInfo.isTypeWorksYn()).isEqualTo(UPDATED_TYPE_WORKS_YN);
        assertThat(testSupplierGeneralInfo.getUserType()).isEqualTo(UPDATED_USER_TYPE);
        assertThat(testSupplierGeneralInfo.getRenewedDate()).isEqualTo(UPDATED_RENEWED_DATE);
        assertThat(testSupplierGeneralInfo.getRegValidity()).isEqualTo(UPDATED_REG_VALIDITY);
        assertThat(testSupplierGeneralInfo.getSubmissionDate()).isEqualTo(UPDATED_SUBMISSION_DATE);
        assertThat(testSupplierGeneralInfo.getRenewalNo()).isEqualTo(UPDATED_RENEWAL_NO);
        assertThat(testSupplierGeneralInfo.getApprovaldate()).isEqualTo(UPDATED_APPROVALDATE);
        assertThat(testSupplierGeneralInfo.isRegistrationBilledYn()).isEqualTo(UPDATED_REGISTRATION_BILLED_YN);
        assertThat(testSupplierGeneralInfo.isRenewalBilledYn()).isEqualTo(UPDATED_RENEWAL_BILLED_YN);
        assertThat(testSupplierGeneralInfo.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testSupplierGeneralInfo.getTransactionRemarks()).isEqualTo(UPDATED_TRANSACTION_REMARKS);
        assertThat(testSupplierGeneralInfo.isBlacklistedYn()).isEqualTo(UPDATED_BLACKLISTED_YN);
        assertThat(testSupplierGeneralInfo.isRegMailNotificationYn()).isEqualTo(UPDATED_REG_MAIL_NOTIFICATION_YN);
        assertThat(testSupplierGeneralInfo.isApplicationVerYn()).isEqualTo(UPDATED_APPLICATION_VER_YN);
        assertThat(testSupplierGeneralInfo.getApproverDecision()).isEqualTo(UPDATED_APPROVER_DECISION);
        assertThat(testSupplierGeneralInfo.isSendPublishedMail()).isEqualTo(UPDATED_SEND_PUBLISHED_MAIL);
        assertThat(testSupplierGeneralInfo.isCertificateCheckYn()).isEqualTo(UPDATED_CERTIFICATE_CHECK_YN);
        assertThat(testSupplierGeneralInfo.isMobileVerified()).isEqualTo(UPDATED_MOBILE_VERIFIED);
        assertThat(testSupplierGeneralInfo.isSmsEnabled()).isEqualTo(UPDATED_SMS_ENABLED);
        assertThat(testSupplierGeneralInfo.isEmailVerified()).isEqualTo(UPDATED_EMAIL_VERIFIED);
        assertThat(testSupplierGeneralInfo.getEmailOTP()).isEqualTo(UPDATED_EMAIL_OTP);
        assertThat(testSupplierGeneralInfo.getEmailOTPDate()).isEqualTo(UPDATED_EMAIL_OTP_DATE);
        assertThat(testSupplierGeneralInfo.isEmailOTPExpired()).isEqualTo(UPDATED_EMAIL_OTP_EXPIRED);
    }

    @Test
    @Transactional
    public void updateNonExistingSupplierGeneralInfo() throws Exception {
        int databaseSizeBeforeUpdate = supplierGeneralInfoRepository.findAll().size();

        // Create the SupplierGeneralInfo
        SupplierGeneralInfoDTO supplierGeneralInfoDTO = supplierGeneralInfoMapper.toDto(supplierGeneralInfo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSupplierGeneralInfoMockMvc.perform(put("/api/supplier-general-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supplierGeneralInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the SupplierGeneralInfo in the database
        List<SupplierGeneralInfo> supplierGeneralInfoList = supplierGeneralInfoRepository.findAll();
        assertThat(supplierGeneralInfoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSupplierGeneralInfo() throws Exception {
        // Initialize the database
        supplierGeneralInfoRepository.saveAndFlush(supplierGeneralInfo);
        int databaseSizeBeforeDelete = supplierGeneralInfoRepository.findAll().size();

        // Get the supplierGeneralInfo
        restSupplierGeneralInfoMockMvc.perform(delete("/api/supplier-general-infos/{id}", supplierGeneralInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SupplierGeneralInfo> supplierGeneralInfoList = supplierGeneralInfoRepository.findAll();
        assertThat(supplierGeneralInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupplierGeneralInfo.class);
        SupplierGeneralInfo supplierGeneralInfo1 = new SupplierGeneralInfo();
        supplierGeneralInfo1.setId(1L);
        SupplierGeneralInfo supplierGeneralInfo2 = new SupplierGeneralInfo();
        supplierGeneralInfo2.setId(supplierGeneralInfo1.getId());
        assertThat(supplierGeneralInfo1).isEqualTo(supplierGeneralInfo2);
        supplierGeneralInfo2.setId(2L);
        assertThat(supplierGeneralInfo1).isNotEqualTo(supplierGeneralInfo2);
        supplierGeneralInfo1.setId(null);
        assertThat(supplierGeneralInfo1).isNotEqualTo(supplierGeneralInfo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupplierGeneralInfoDTO.class);
        SupplierGeneralInfoDTO supplierGeneralInfoDTO1 = new SupplierGeneralInfoDTO();
        supplierGeneralInfoDTO1.setId(1L);
        SupplierGeneralInfoDTO supplierGeneralInfoDTO2 = new SupplierGeneralInfoDTO();
        assertThat(supplierGeneralInfoDTO1).isNotEqualTo(supplierGeneralInfoDTO2);
        supplierGeneralInfoDTO2.setId(supplierGeneralInfoDTO1.getId());
        assertThat(supplierGeneralInfoDTO1).isEqualTo(supplierGeneralInfoDTO2);
        supplierGeneralInfoDTO2.setId(2L);
        assertThat(supplierGeneralInfoDTO1).isNotEqualTo(supplierGeneralInfoDTO2);
        supplierGeneralInfoDTO1.setId(null);
        assertThat(supplierGeneralInfoDTO1).isNotEqualTo(supplierGeneralInfoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(supplierGeneralInfoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(supplierGeneralInfoMapper.fromId(null)).isNull();
    }
}
