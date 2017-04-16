import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class RegistrationTest {
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
  }

  @DataProvider (name="sign up")
  public Object[][] dp() {
	  
	  return new Object[][] { { "test@"," ", false }, { "test@yahoo","1234", false }
    ,{"test@yahoo.com","1234",true},{"test@yahoo.com","",false},{"test@yahoo.com","1",false}};
  }
  
  @Test
  public void Signup() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void Login() {
    throw new RuntimeException("Test not implemented");
  }

  
}
