package faker;



import com.github.javafaker.Faker;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class FakerUtilTest {
    @Test
    public void getFakerObjects() {
        List<FakerObject> fakerObjects = FakerUtil.getFakerObjects(new Faker());
        assertNotNull(fakerObjects);
        assertTrue("FakerObjects list is too small",fakerObjects.size() > 10);
    }
}
