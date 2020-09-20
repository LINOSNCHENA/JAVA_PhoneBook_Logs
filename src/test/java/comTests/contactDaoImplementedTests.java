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

    private List<Contact> getItemList() {
        List<Contact> contacts = new ArrayList<>();
        Contact friend1 = new Contact(1, "MARVIN", 20201984, 20202009);
        Contact friend2 = new Contact(2, "NIKOLAS", 20201984, 20202012);
        Contact friend3 = new Contact(3, "LORENA", 20201984, 20202014);
        Contact friend4 = new Contact(4, "LEON", 20201984, 20202012);
        Contact friend5 = new Contact(5, "CATHERINE", 20201984, 20202014);
        contacts.add(friend1);
        contacts.add(friend2);
        contacts.add(friend3);
        contacts.add(friend4);
        contacts.add(friend5);
        return contacts;
    }

    @Mock
    private contactDaoImplemented contactDao;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnAllContacts1() throws Exception {

        List<Contact> contacts = getItemList();
        when(contactDao.findAllContacts()).thenReturn(contacts);
        List<Contact> testContacts = contactDao.findAllContacts();

        assertThat(testContacts.size()).isEqualTo(5);
        assertThat(testContacts.get(0).getId()).isEqualTo(1);
        assertThat(testContacts.get(0).getName()).isEqualTo("MARVIN");
        assertThat(testContacts.get(1).getName()).isEqualTo("NIKOLAS");
        assertThat(testContacts.get(2).getName()).isEqualTo("LORENA");
        assertThat(testContacts.get(3).getName()).isEqualTo("LEON");
        assertThat(testContacts.get(4).getName()).isEqualTo("CATHERINE");
    }

    @Test
    public void shouldInsertNewContact2() throws Exception {
        String pname = "MAVIN LORENA NCHENA";
        int pnumber1 = 1234567891;
        int pnumber2 = 987654321;

        contactDao.insertNewContact(pname, pnumber1, pnumber2);
        verify(contactDao, times(1)).insertNewContact(pname, pnumber1, pnumber2);
    }

    @Test
    public void shouldUpdateContact3() throws Exception {
          String pname = "Nchena";
        int pnumber1 = 234;
        int pnumber2 = 232;

        Integer id = 2;
        contactDao.updateOneContact(id, pname, pnumber1, pnumber2);
        verify(contactDao, times(1)).updateOneContact(id, pname, pnumber1, pnumber2);
    }

    @Test
    public void shouldReturnSearchedIdentityNumber4() throws Exception {

        Contact friend6 = new Contact(12, "MARVIN", 2009, 2014);

        when(contactDao.findContactById(12)).thenReturn(friend6);

        Contact searchedFriend = contactDao.findContactById(12);

        assertThat(searchedFriend).isEqualTo(friend6);
    }

    @Test
    public void shouldReturnSearchedName5() throws Exception {

        Contact friend6 = new Contact(12, "PRESLY", 19432009, 19472014);

        when(contactDao.findContactByName("PRESLY")).thenReturn(friend6);

        Contact searchedFriend = contactDao.findContactByName("PRESLY");

        assertThat(searchedFriend).isEqualTo(friend6);
    }

    @Test
    public void shouldReturnSearchedNameVersusID6() throws Exception {

        Contact friend6 = new Contact(12, "PRESLY", 19432009, 19472014);

        when(contactDao.findContactByName("PRESLY")).thenReturn(friend6);

        Contact searchedFriend = contactDao.findContactByName("PRESLY");
        Contact searchedName = contactDao.findContactById(1);

        assertThat(searchedFriend).isNotEqualTo(searchedName);
    }

    @Test
    public void shouldDeleteOneContact7() throws Exception {
        contactDao.deleteOneContact(23);

        verify(contactDao, times(1)).deleteOneContact(23);
    }

    @Test
    public void shouldDeleteAllContacts8() throws Exception {
        contactDao.deleteAllContacts();
        verify(contactDao, times(1)).deleteAllContacts();
    }

}
