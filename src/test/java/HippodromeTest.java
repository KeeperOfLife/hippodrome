import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    @Mock
    Hippodrome hippodromeMock;
    @Mock
    List<Horse> horsesListMock;

    @Test
    void HippodromeConstructorNullTest() {
        horsesListMock = null;
        IllegalArgumentException hippodromeThrow = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horsesListMock));
        assertEquals("Horses cannot be null.", hippodromeThrow.getMessage());
    }

    @Test
    void HippodromeConstructorEmptyTest() {
        horsesListMock = new ArrayList<>();
        IllegalArgumentException hippodromeThrow = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horsesListMock));
        assertEquals("Horses cannot be empty.", hippodromeThrow.getMessage());
    }

    @Test
    void getHorsesTest() {
        horsesListMock = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horsesListMock.add(new Horse("Marusia " + i, 0.0, 0.0));
        }
        hippodromeMock = new Hippodrome(horsesListMock);
        assertEquals(horsesListMock, hippodromeMock.getHorses());

    }

    @Test
    void moveTest() {
        horsesListMock = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horsesListMock.add(Mockito.mock(Horse.class));
        }
        new Hippodrome(horsesListMock).move();
        for (Horse horse : horsesListMock) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinnerTest() {
        horsesListMock = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            horsesListMock.add(new Horse("Marusia " + i, i, i));
        }
        hippodromeMock = new Hippodrome(horsesListMock);
        assertSame(horsesListMock.get(3), hippodromeMock.getWinner());
    }
}
