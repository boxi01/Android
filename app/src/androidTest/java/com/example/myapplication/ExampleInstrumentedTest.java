package com.example.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.myapplication", appContext.getPackageName());
    }
    /*@RunWith(AndroidJUnit4.class)
    public class SimpleEntityReadWriteTest {
        private UserDao userDao;
        private TestDatabase db;

        @Before
        public void createDb() {
            Context context = ApplicationProvider.getApplicationContext();
            db = Room.inMemoryDatabaseBuilder(context, TestDatabase.class).build();
            userDao = db.getUserDao();
        }

        @After
        public void closeDb() throws IOException {
            db.close();
        }

        @Test
        public void writeUserAndReadInList() throws Exception {
            User user = TestUtil.createUser(3);
            user.setName("george");
            userDao.insert(user);
            List<User> byName = userDao.findUsersByName("george");
            assertThat(byName.get(0), equalTo(user));
        }
    }
*/
}
