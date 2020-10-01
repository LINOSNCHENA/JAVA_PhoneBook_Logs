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
    public void setUp(){
        sampleContact = new Contact();
        sampleContact.setId(ID);
        sampleContact.setNamex(NAME);
        sampleContact.setMobilex(NUMBER1);
        sampleContact.setOfficex(NUMBER2);

    }

    @Test
    public void shouldCreateContactMarchingHereDetailsSample() throws Exception {
        assertThat(sampleContact).isNotNull();
        assertThat(sampleContact.getId()).isEqualTo(2020);
        assertThat(sampleContact.getNamex()).isEqualTo("NIKOLASLORENALEON");
        assertThat(sampleContact.getMobilex()).isEqualTo(1234567890);
        assertThat(sampleContact.getOfficex()).isEqualTo(1234567891);

    }
    @Test
    public void createdContactDoesNotMarchHereProvidedContact() throws Exception {
        assertThat(sampleContact).isNotNull();
        assertThat(sampleContact.getId()).isEqualTo(2020);
        assertThat(sampleContact.getNamex()).isNotEqualTo("NIKOLAS");
        assertThat(sampleContact.getMobilex()).isNotEqualTo(123456789);
        assertThat(sampleContact.getOfficex()).isNotEqualTo(123456789);

    }
}
