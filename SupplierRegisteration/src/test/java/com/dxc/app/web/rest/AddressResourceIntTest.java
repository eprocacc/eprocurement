package com.dxc.app.web.rest;

import com.dxc.app.SupplierRegisterationApp;

import com.dxc.app.domain.Address;
import com.dxc.app.repository.AddressRepository;
import com.dxc.app.service.AddressService;
import com.dxc.app.service.dto.AddressDTO;
import com.dxc.app.service.mapper.AddressMapper;
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
 * Test class for the AddressResource REST controller.
 *
 * @see AddressResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SupplierRegisterationApp.class)
public class AddressResourceIntTest {

    private static final String DEFAULT_BLOCK_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_STREET = "AAAAAAAAAA";
    private static final String UPDATED_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_PIN = "AAAAAAAAAA";
    private static final String UPDATED_PIN = "BBBBBBBBBB";

    private static final Integer DEFAULT_COUNTRY_ID = 1;
    private static final Integer UPDATED_COUNTRY_ID = 2;

    private static final Integer DEFAULT_STATE_ID = 1;
    private static final Integer UPDATED_STATE_ID = 2;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressService addressService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAddressMockMvc;

    private Address address;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AddressResource addressResource = new AddressResource(addressService);
        this.restAddressMockMvc = MockMvcBuilders.standaloneSetup(addressResource)
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
    public static Address createEntity(EntityManager em) {
        Address address = new Address()
            .blockNumber(DEFAULT_BLOCK_NUMBER)
            .street(DEFAULT_STREET)
            .city(DEFAULT_CITY)
            .otherState(DEFAULT_OTHER_STATE)
            .pin(DEFAULT_PIN)
            .countryId(DEFAULT_COUNTRY_ID)
            .stateId(DEFAULT_STATE_ID);
        return address;
    }

    @Before
    public void initTest() {
        address = createEntity(em);
    }

    @Test
    @Transactional
    public void createAddress() throws Exception {
        int databaseSizeBeforeCreate = addressRepository.findAll().size();

        // Create the Address
        AddressDTO addressDTO = addressMapper.toDto(address);
        restAddressMockMvc.perform(post("/api/addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isCreated());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeCreate + 1);
        Address testAddress = addressList.get(addressList.size() - 1);
        assertThat(testAddress.getBlockNumber()).isEqualTo(DEFAULT_BLOCK_NUMBER);
        assertThat(testAddress.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testAddress.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testAddress.getOtherState()).isEqualTo(DEFAULT_OTHER_STATE);
        assertThat(testAddress.getPin()).isEqualTo(DEFAULT_PIN);
        assertThat(testAddress.getCountryId()).isEqualTo(DEFAULT_COUNTRY_ID);
        assertThat(testAddress.getStateId()).isEqualTo(DEFAULT_STATE_ID);
    }

    @Test
    @Transactional
    public void createAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = addressRepository.findAll().size();

        // Create the Address with an existing ID
        address.setId(1L);
        AddressDTO addressDTO = addressMapper.toDto(address);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAddressMockMvc.perform(post("/api/addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAddresses() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);

        // Get all the addressList
        restAddressMockMvc.perform(get("/api/addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(address.getId().intValue())))
            .andExpect(jsonPath("$.[*].blockNumber").value(hasItem(DEFAULT_BLOCK_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].otherState").value(hasItem(DEFAULT_OTHER_STATE.toString())))
            .andExpect(jsonPath("$.[*].pin").value(hasItem(DEFAULT_PIN.toString())))
            .andExpect(jsonPath("$.[*].countryId").value(hasItem(DEFAULT_COUNTRY_ID)))
            .andExpect(jsonPath("$.[*].stateId").value(hasItem(DEFAULT_STATE_ID)));
    }

    @Test
    @Transactional
    public void getAddress() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);

        // Get the address
        restAddressMockMvc.perform(get("/api/addresses/{id}", address.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(address.getId().intValue()))
            .andExpect(jsonPath("$.blockNumber").value(DEFAULT_BLOCK_NUMBER.toString()))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.otherState").value(DEFAULT_OTHER_STATE.toString()))
            .andExpect(jsonPath("$.pin").value(DEFAULT_PIN.toString()))
            .andExpect(jsonPath("$.countryId").value(DEFAULT_COUNTRY_ID))
            .andExpect(jsonPath("$.stateId").value(DEFAULT_STATE_ID));
    }

    @Test
    @Transactional
    public void getNonExistingAddress() throws Exception {
        // Get the address
        restAddressMockMvc.perform(get("/api/addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAddress() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);
        int databaseSizeBeforeUpdate = addressRepository.findAll().size();

        // Update the address
        Address updatedAddress = addressRepository.findOne(address.getId());
        // Disconnect from session so that the updates on updatedAddress are not directly saved in db
        em.detach(updatedAddress);
        updatedAddress
            .blockNumber(UPDATED_BLOCK_NUMBER)
            .street(UPDATED_STREET)
            .city(UPDATED_CITY)
            .otherState(UPDATED_OTHER_STATE)
            .pin(UPDATED_PIN)
            .countryId(UPDATED_COUNTRY_ID)
            .stateId(UPDATED_STATE_ID);
        AddressDTO addressDTO = addressMapper.toDto(updatedAddress);

        restAddressMockMvc.perform(put("/api/addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isOk());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeUpdate);
        Address testAddress = addressList.get(addressList.size() - 1);
        assertThat(testAddress.getBlockNumber()).isEqualTo(UPDATED_BLOCK_NUMBER);
        assertThat(testAddress.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testAddress.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testAddress.getOtherState()).isEqualTo(UPDATED_OTHER_STATE);
        assertThat(testAddress.getPin()).isEqualTo(UPDATED_PIN);
        assertThat(testAddress.getCountryId()).isEqualTo(UPDATED_COUNTRY_ID);
        assertThat(testAddress.getStateId()).isEqualTo(UPDATED_STATE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAddress() throws Exception {
        int databaseSizeBeforeUpdate = addressRepository.findAll().size();

        // Create the Address
        AddressDTO addressDTO = addressMapper.toDto(address);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAddressMockMvc.perform(put("/api/addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isCreated());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAddress() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);
        int databaseSizeBeforeDelete = addressRepository.findAll().size();

        // Get the address
        restAddressMockMvc.perform(delete("/api/addresses/{id}", address.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Address.class);
        Address address1 = new Address();
        address1.setId(1L);
        Address address2 = new Address();
        address2.setId(address1.getId());
        assertThat(address1).isEqualTo(address2);
        address2.setId(2L);
        assertThat(address1).isNotEqualTo(address2);
        address1.setId(null);
        assertThat(address1).isNotEqualTo(address2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddressDTO.class);
        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1.setId(1L);
        AddressDTO addressDTO2 = new AddressDTO();
        assertThat(addressDTO1).isNotEqualTo(addressDTO2);
        addressDTO2.setId(addressDTO1.getId());
        assertThat(addressDTO1).isEqualTo(addressDTO2);
        addressDTO2.setId(2L);
        assertThat(addressDTO1).isNotEqualTo(addressDTO2);
        addressDTO1.setId(null);
        assertThat(addressDTO1).isNotEqualTo(addressDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(addressMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(addressMapper.fromId(null)).isNull();
    }
}
