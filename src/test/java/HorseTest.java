import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    @Mock
    Horse horseMock;

    @ParameterizedTest
    @CsvSource({", 2.0D, 3.0D", "' ', 2.0D, 3.0D", "name, -2.0D, 3.0D", "name, 2.0D, -3.0D"})
    @DisplayName("checking for IllegalArgumentException and that the exception writes a suitable message to the stack trace")
    void horseConstructorTest(String name, double speed, double distance) {
        IllegalArgumentException horseThrow = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        if (isNull(name)) {
            assertEquals("Name cannot be null.", horseThrow.getMessage());
        } else if (name.isBlank()) {
            assertEquals("Name cannot be blank.", horseThrow.getMessage());
        } else if (speed < 0) {
            assertEquals("Speed cannot be negative.", horseThrow.getMessage());
        } else if (distance < 0) {
            assertEquals("Distance cannot be negative.", horseThrow.getMessage());
        }
    }

    @Test
    void getNameTest() {
        Mockito.when(horseMock.getName()).thenReturn("Marusia");
        assertEquals("Marusia", horseMock.getName());
    }

    @Test
    void getSpeedTest() {
        Mockito.when(horseMock.getSpeed()).thenReturn(2.0D);
        assertEquals(2.0D, horseMock.getSpeed());
    }

    @Test
    void getDistanceTest() {
        Mockito.when(horseMock.getDistance()).thenReturn(3.0D);
        assertEquals(3.0D, horseMock.getDistance());
    }

    @ParameterizedTest
    @CsvSource({"Marusia, 33.0D, 550.0D, 0.2D, 0.9D"})
    void moveTest(String name, double speed, double distance, double min, double max) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            new Horse(name, speed, distance).move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(min, max));
        }

    }
}
