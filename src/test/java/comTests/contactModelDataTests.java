package comTests;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.model.Contact;

public class contactModelDataTests {

    private static final Integer ID = 2020;
    private static final String NAME = "NIKOLASLORENALEON";
    private static final int NUMBER1 = 1234567890;
    private static final int NUMBER2 = 1234567891;


    private Contact sampleContact;

    @Before
    public void setUpaASampleContact(){
        sampleContact = new Contact();
        sampleContact.setId(ID);
        sampleContact.setName(NAME);
        sampleContact.setMobile(NUMBER1);
        sampleContact.setOffice(NUMBER2);

    }

    @Test
    public void shouldCreateContactMarchingHereDetailsSample() throws Exception {
        assertThat(sampleContact).isNotNull();
        assertThat(sampleContact.getId()).isEqualTo(2020);
        assertThat(sampleContact.getName()).isEqualTo("NIKOLASLORENALEON");
        assertThat(sampleContact.getMobile()).isEqualTo(1234567890);
        assertThat(sampleContact.getOffice()).isEqualTo(1234567891);

    }
    @Test
    public void createdContactDoesNotMarchHereProvidedContact() throws Exception {
        assertThat(sampleContact).isNotNull();
        assertThat(sampleContact.getId()).isEqualTo(2020);
        assertThat(sampleContact.getName()).isNotEqualTo("NIKOLAS");
        assertThat(sampleContact.getMobile()).isNotEqualTo(123456789);
        assertThat(sampleContact.getOffice()).isNotEqualTo(123456789);
    }
 
}
