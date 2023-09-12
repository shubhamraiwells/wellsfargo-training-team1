//package Repository;
//
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.banking.teamone.model.UserModel;
//import com.banking.teamone.repository.UserRepository;
//
//import static org.junit.Assert.*;
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserRepositoryTest {
////    @Autowired
////    private UserRepository userRepository;
////    @Before
////    public void setUp() throws Exception {
////        UserModel user1= new UserModel("Alice", 23);
////        UserModel user2= new UserModel("Bob", 38);
////        //save user, verify has ID value after save
////        assertNull(user1.getId());
////        assertNull(user2.getId());//null before save
////        this.userRepository.save(user1);
////        this.userRepository.save(user2);
////        assertNotNull(user1.getId());
////        assertNotNull(user2.getId());
////        System.out.println("Test running");
////    }
////
////    @Test
////    public void testFetchData(){
////        /*Test data retrieval*/
//////        UserModel userA = userRepository.findByName("Bob");
//////        assertNotNull(userA);
//////        assertEquals(38, userA.getAge());
//////        /*Get all products, list should only have two*/
//////        Iterable<UserModel> users = userRepository.findAll();
//////        int count = 0;
//////        for(UserModel p : users){
//////            count++;
//////        }
//////        assertEquals(count, 2);
////    }
//}
