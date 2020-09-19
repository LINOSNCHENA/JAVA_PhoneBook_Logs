package comTests;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.model.Contact;

public class modelDataTests {

    private static final Integer ID = 2020;
    private static final String NAME = "NIKOLASLORENALEON";
    private static final int NUMBER1 = 1234567890;
    private static final int NUMBER2 = 1234567891;


    private Contact sampleContact;

    @Before
    public void setUp(){
        sampleContact = new Contact();
        sampleContact.setId(ID);
        sampleContact.setName(NAME);
        sampleContact.setPnumber1(NUMBER1);
        sampleContact.setPnumber2(NUMBER2);

    }

    @Test
    public void shouldCreateContactSample() throws Exception {
        assertThat(sampleContact).isNotNull();
        assertThat(sampleContact.getId()).isEqualTo(2020);
        assertThat(sampleContact.getName()).isEqualTo("NIKOLASLORENALEON");
        assertThat(sampleContact.getPnumber1()).isEqualTo(1234567890);
        assertThat(sampleContact.getPnumber2()).isEqualTo(1234567891);

    }
    @Test
    public void shouldCreateFalseContact() throws Exception {
        assertThat(sampleContact).isNotNull();
        assertThat(sampleContact.getId()).isEqualTo(2020);
        assertThat(sampleContact.getName()).isNotEqualTo("NIKOLAS");
        assertThat(sampleContact.getPnumber1()).isNotEqualTo(123456789);
        assertThat(sampleContact.getPnumber2()).isNotEqualTo(123456789);

    }
}
