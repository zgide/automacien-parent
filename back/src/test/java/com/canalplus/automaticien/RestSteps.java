package com.canalplus.automaticien;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.canalplus.automaticien.fmk.dto.AddressDto;
import com.canalplus.automaticien.fmk.dto.ContractDto;
import com.canalplus.automaticien.fmk.dto.UserAddressPeriodDto;
import com.canalplus.automaticien.fmk.dto.UserDto;
import com.canalplus.automaticien.fmk.security.PermissionConsts;
import com.canalplus.automaticien.repository.dao.IAddressDao;
import com.canalplus.automaticien.repository.dao.IContractDao;
import com.canalplus.automaticien.repository.dao.IUserAddressPeriodDao;
import com.canalplus.automaticien.repository.dao.IUserDao;

import io.cucumber.java.Before;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Lorsqu;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestSteps {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private IAddressDao addressDao;
	
	@Autowired
	private IUserDao userDao;

	@Autowired
	private IContractDao contractDao;

	@Autowired
	private IUserAddressPeriodDao userAddressPeriodDao;

	@Before
	public void init() {
		manageSecurity();
		RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
	}

	@Transactional
	@Etantdonné("^un abonné avec une adresse principale active en France")
	public void insertData() {
		initDB();
	}

	@Lorsqu("^le conseiller connecté à FACE modifie l'adresse de l'abonné sans date d'effet")
	public void modifyAddress() {
		var newAddress = new AddressDto();
		newAddress.setUserId(2L);
		newAddress.setCity("Los");
		newAddress.setStreet("wash");
		newAddress.setHouseNumber(5);
		RestAssuredMockMvc.given().contentType("application/json").body(newAddress).when().post("/address");
	}

	@Alors("^l'adresse de labonné modifiée est enregistrée sur lensemble des contrats de l'abonné")
	public void checkContractRefistration() {
		List<ContractDto> contractsByUserId = contractDao.getContractsByUserId(2L);
		Assert.assertTrue(contractsByUserId.size()==1L);
		Assert.assertTrue(addressDao.findById(contractsByUserId.get(0).getAddressId()).getCity().equals("Los"));
		
		
	}

	private void initDB() {
		var dto = new AddressDto();
		dto.setCity("Paris");
		dto.setStreet("Rome");
		dto.setHouseNumber(4);

		var userDto = new UserDto();
		userDto.setEmail("test@test.com");
		userDto.setId(2L);
		userDto.setUsername("test");
		userDto.setLastName("testLast");

		var userAddressPeriod = new UserAddressPeriodDto();
		userAddressPeriod.setAddressId(addressDao.save(dto));
		userAddressPeriod.setUserId(userDao.save(userDto));
		userAddressPeriod.setStartDateTime(LocalDateTime.now().minusYears(1L));
		userAddressPeriodDao.save(userAddressPeriod);
		
		var contractDto = new ContractDto();
		contractDto.setId(1L);
		contractDto.setUserId(2L);
		contractDto.setAddressId(1L);
		contractDao.save(contractDto);
	}

	private void manageSecurity() {
		final SecurityContext ctx = SecurityContextHolder.createEmptyContext();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(PermissionConsts.role_advisor));
		Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", authorities);
		ctx.setAuthentication(authentication);
		SecurityContextHolder.setContext(ctx);
	}
}
