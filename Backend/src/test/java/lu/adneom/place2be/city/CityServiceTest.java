package lu.adneom.place2be.city;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    private CityService cityService;

    @Mock
    private CityRepository cityRepository;

    @Before
    public void setUp() {
        this.cityService = new CityService(cityRepository);
    }

    @Test
    public void shouldGetAllCities() {
        // Arrange
        when(cityRepository.findAll()).thenReturn(Arrays.asList(new City(), new City()));
        // Act
        List<City> cities = cityService.getAll();
        // Assert
        assertThat(cities.size(), is(2));
        verify(cityRepository, times(1)).findAll();
    }

}