package nl.hrmanagement.usermanagement;

import nl.hrmanagement.usermanagement.dto.*;
import nl.hrmanagement.usermanagement.service.impl.UserManagementServiceProvider;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author Rick Spanjers
 * @since 21/10/2019
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserManagementApplicationTests {

	@InjectMocks
	private UserManagementServiceProvider userManagementServiceProvider;

	@Before
	public void setUp() {
		ClassLoader classLoader = getClass().getClassLoader();
		MockitoAnnotations.initMocks(this);
	}

	public UserManagementApplicationTests() throws ServiceException {

	}

	@Test
	public void setupContext(){

	}


	private static final Logger logger = LoggerFactory.getLogger(UserManagementApplicationTests.class);


	@Test(expected = NullPointerException.class)
	public void testUpdateProfile(){
		UpdateProfileDTO request = new UpdateProfileDTO(null, "test", "test", "test","test", "test", "test","test");
		GenericResponseDTO actualResult = userManagementServiceProvider.updateProfile(request);
		assertTrue(actualResult.getSuccess());
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateProfile_NoUserId(){
		UpdateProfileDTO request = new UpdateProfileDTO(null, "test", "test", "test","test", "test", "test","test");
		GenericResponseDTO actualResult = userManagementServiceProvider.updateProfile(request);
		assertFalse(actualResult.getSuccess());
	}

	@Test(expected = NullPointerException.class)
	public void testChangePassword(){
		ChangePasswordDTO request = new ChangePasswordDTO(null, "test", "test", "test");
		GenericResponseDTO actualResult = userManagementServiceProvider.changePassword(request);
		assertTrue(actualResult.getSuccess());
	}

	@Test
	public void testChangePassword_NoConfirmation(){
		ChangePasswordDTO request = new ChangePasswordDTO(null, "test", "test", null);
		GenericResponseDTO actualResult = userManagementServiceProvider.changePassword(request);
		assertFalse(actualResult.getSuccess());
	}

	@Test(expected = NullPointerException.class)
	public void testAddProfileElement(){
		AddProfileElementDTO request = new AddProfileElementDTO(null, "test", "test", "test", "EDUCATION");
		GenericResponseDTO actualResult = userManagementServiceProvider.addProfileElement(request);
		assertTrue(actualResult.getSuccess());
	}

	@Test(expected = NullPointerException.class)
	public void testAddProfileElement_NoName(){
		AddProfileElementDTO request = new AddProfileElementDTO(null, null, "test", "test", "EDUCATION");
		GenericResponseDTO actualResult = userManagementServiceProvider.addProfileElement(request);
		assertTrue(actualResult.getSuccess());
	}

	@Test(expected = NullPointerException.class)
	public void testDeleteProfileElement(){
		RemoveProfileElementDTO request = new RemoveProfileElementDTO(null, 1, "EDUCATION");
		GenericResponseDTO actualResult = userManagementServiceProvider.removeProfileElement(request);
		assertTrue(actualResult.getSuccess());
	}

	@Test
	public void testDeleteProfileElement_NoId(){
		RemoveProfileElementDTO request = new RemoveProfileElementDTO(null, 0, "EDUCATION");
		GenericResponseDTO actualResult = userManagementServiceProvider.removeProfileElement(request);
		assertFalse(actualResult.getSuccess());
	}

	@Test(expected = NullPointerException.class)
	public void testRetrieveUsersFromCompany_NoId(){
		GenericResponseDTO actualResult = userManagementServiceProvider.retrieveUsersFromCompany(UUID.randomUUID());
		assertFalse(actualResult.getSuccess());
	}

	@Test(expected = NullPointerException.class)
	public void testRetrieveUsersFromCompanyArchived_NoId(){
		GenericResponseDTO actualResult = userManagementServiceProvider.retrieveUsersFromCompanyArchived(UUID.randomUUID());
		assertFalse(actualResult.getSuccess());
	}

	@Test(expected = NullPointerException.class)
	public void testAddAccount_NoModerator(){
		AddAccountDTO account = new AddAccountDTO();
		account.setModeratorId(UUID.randomUUID());
		GenericResponseDTO actualResult = userManagementServiceProvider.addAccount(account);
		assertFalse(actualResult.getSuccess());
	}

	@Test(expected = NullPointerException.class)
	public void testArchive_NoId(){
		ArchiveOrRemoveAccountDTO dto = new ArchiveOrRemoveAccountDTO();
		dto.setAccountId(UUID.randomUUID());
		dto.setAction(null);
		dto.setModeratorId(UUID.randomUUID());
		GenericResponseDTO actualResult = userManagementServiceProvider.archiveOrDeleteAccount(dto);
		assertFalse(actualResult.getSuccess());
	}
}
