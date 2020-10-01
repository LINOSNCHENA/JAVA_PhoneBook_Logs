package comTests;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.apiRepos.contactDaoImplemented;
import com.model.Contact;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

@RunWith(MockitoJUnitRunner.class)
public class contactDaoImplementedTests {

    @Mock
    private contactDaoImplemented contactDao;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private List<Contact> getCurrentContacts() {

        List<Contact> myContacts = new ArrayList<>();

        Contact friend1 = new Contact(1, "MARVIN", 20201984, 20202009);
        Contact friend2 = new Contact(2, "NIKOLAS", 20201984, 20202012);
        Contact friend3 = new Contact(3, "LORENA", 20201984, 20202014);
        Contact friend4 = new Contact(4, "LEON", 20201984, 20202012);
        Contact friend5 = new Contact(5, "CATHERINE", 20201984, 20202014);
        myContacts.add(friend1);
        myContacts.add(friend2);
        myContacts.add(friend3);
        myContacts.add(friend4);
        myContacts.add(friend5);
        return myContacts;
    }

    @Test
    public void shouldReturnLocationsOfAllContactsF1() throws Exception {

        List<Contact> myContacts = getCurrentContacts();
        when(contactDao.findAllContacts()).thenReturn(myContacts);
        List<Contact> herContacts = contactDao.findAllContacts();

        assertThat(herContacts.size()).isEqualTo(5);
        assertThat(herContacts.get(0).getId()).isEqualTo(1);
        assertThat(herContacts.get(0).getNamex()).isEqualTo("MARVIN");
        assertThat(herContacts.get(1).getNamex()).isEqualTo("NIKOLAS");
        assertThat(herContacts.get(2).getNamex()).isEqualTo("LORENA");
        assertThat(herContacts.get(3).getNamex()).isEqualTo("LEON");
        assertThat(herContacts.get(4).getNamex()).isEqualTo("CATHERINE");
    }

    @Test
    public void shouldInsertNewContactF2() throws Exception {
        String pname = "MAVIN LORENA NIKOLAS";
        int pnumber1 = 1234567891;
        int pnumber2 = 987654321;
        int pstars = 11;

        contactDao.insertNewContact(pname, pnumber1, pnumber2, pstars);
        verify(contactDao, times(1)).insertNewContact(pname, pnumber1, pnumber2, pstars);
    }

    @Test
    public void shouldUpdateContactF3() throws Exception {
        Integer id = 2;
        String pname = "NIKOLAS";
        int pnumber1 = 234;
        int pnumber2 = 232;
        int pstars = 11;

        contactDao.updateOneContact(id, pname, pnumber1, pnumber2, pstars);
        verify(contactDao, times(1)).updateOneContact(id, pname, pnumber1, pnumber2, pstars);
    }

    @Test
    public void shouldReturnSearchedIdentityNumberF4() throws Exception {

        Contact friend6 = new Contact(12, "PRESLY", 19432009, 19472014);

        when(contactDao.findContactById(12)).thenReturn(friend6);
        Contact searchedFriend = contactDao.findContactById(12);

        assertThat(searchedFriend).isEqualTo(friend6);
    }

    @Test
    public void shouldReturnSearchedNameF5() throws Exception {

        Contact friend6 = new Contact(12, "PRESLY", 19432009, 19472014);

        when(contactDao.findContactByName("PRESLY")).thenReturn(friend6);
        Contact searchedFriend = contactDao.findContactByName("PRESLY");

        assertThat(searchedFriend).isEqualTo(friend6);
    }

    @Test
    public void shouldReturnSearchedNameOrIdNumberF6() throws Exception {

        Contact friend6 = new Contact(12, "PRESLY", 19432009, 19472014);

        when(contactDao.findContactByName("PRESLY")).thenReturn(friend6);
        when(contactDao.findContactById(12)).thenReturn(friend6);

        Contact searchedName = contactDao.findContactByName("PRESLY");
        Contact searchedId = contactDao.findContactById(12);

        assertThat(searchedName).isEqualTo(friend6);
        assertThat(searchedId).isEqualTo(friend6);
        assertThat(searchedId).isEqualTo(searchedName);
    }

    @Test
    public void shouldDeleteOneContactF7() throws Exception {
        contactDao.deleteOneContact(23);

        verify(contactDao, times(1)).deleteOneContact(23);
    }

    @Test
    public void shouldDeleteAllContacts8F() throws Exception {
        contactDao.deleteAllContacts();
        verify(contactDao, times(1)).deleteAllContacts();
    }

}
