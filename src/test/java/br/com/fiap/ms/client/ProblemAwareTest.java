package br.com.fiap.ms.client;

import br.com.fiap.ms.client.domain.exception.InvalidProcessException;
import br.com.fiap.ms.client.domain.utils.ProblemAware;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class ProblemAwareTest {

    @Test
    public void testProblemOf() {
        // Given
        String expectedTitle = "Title should match expected title";
        String expectedMessage = "Message should match expected message";
        InvalidProcessException ex = new InvalidProcessException(expectedTitle, expectedMessage);

        // When
        Map<String, String> result = ProblemAware.problemOf(ex);

        // Then
        assertNotNull("Result should not be null", result);
        assertEquals("Title should match expected title", expectedTitle, result.get("tittle")); // Note the typo in "title"
        assertEquals("Message should match expected message", expectedMessage, result.get("message"));
    }
}