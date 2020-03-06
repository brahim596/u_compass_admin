package com.ucompass.ucompassadmin.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ucompass.ucompassadmin.web.rest.TestUtil;

public class CampusServiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampusService.class);
        CampusService campusService1 = new CampusService();
        campusService1.setId(1L);
        CampusService campusService2 = new CampusService();
        campusService2.setId(campusService1.getId());
        assertThat(campusService1).isEqualTo(campusService2);
        campusService2.setId(2L);
        assertThat(campusService1).isNotEqualTo(campusService2);
        campusService1.setId(null);
        assertThat(campusService1).isNotEqualTo(campusService2);
    }
}
