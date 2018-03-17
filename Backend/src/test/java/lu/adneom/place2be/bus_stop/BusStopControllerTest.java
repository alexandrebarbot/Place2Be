package lu.adneom.place2be.bus_stop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BusStopController.class)
public class BusStopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusStopService busStopService;

    @Test
    public void shouldGetAllBusStop() throws Exception {
        // Arrange
        when(busStopService.getAll()).thenReturn(Arrays.asList(new BusStop(), new BusStop()));

        // Act
        mockMvc.perform(
                get("/bus_stop/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(2)));


        // Assert
        verify(busStopService, times(1)).getAll();
    }

}