import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Teacher_DBTest {

	Teacher testTeacher = new Teacher();
	Teacher_DB testDB = new Teacher_DB();

	@DataProvider(name = "Teachers")
	public static Object[][] teachers() {
		return new Object[][] { { true, "salem@e.com", "s99", "Salem" }, { true, "Ahmed@s.com", "scrt", "Ahmed" },
				{ false, "Ahmed@s.com", "dc", "dc" }, { true, "said@s.com", "scrt", "said" },
				{ false, null, "b", "Mick" }, { false, "Bassem@e.com", null, "Bassem" },
				{ false, "saad@e.com", "12", null }, { false, "ahmeds.com", "ss", "ahmad" } };
	}
	

	

	@BeforeTest
	public void addFiles() {
		File file = new File("TPasswords.txt");
		try {
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();

		} catch (IOException e) {
			System.out.println("Passwords file already exists.");
		}
		file = new File("Teachers.txt");
		try {
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Teachers file already exists.");
		}
		file = new File("Accounts.txt");
		try {
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Accounts file already exists.");
		}
	}

	@Test(dataProvider = "Teachers")
	public void AddT(boolean result, String mail, String password, String accountName) {
		testTeacher.mail = mail;
		testTeacher.password = password;
		testTeacher.account_name = accountName;
		try {
			Assert.assertEquals(testDB.AddT(testTeacher), result);
		} catch (IOException e) {
			Assert.assertTrue(false);
		}

	}
	
	@Test(dataProvider = "Teachers")
	public void VerifyT(boolean result, String mail, String password, String accountName) {
		testTeacher.mail = mail;
		testTeacher.password = password;
		testTeacher.account_name = accountName;
		try {
			Assert.assertEquals(testDB.VerifyT(testTeacher), result);
		} catch (IOException e) {
			Assert.assertTrue(false);
		}

	}
	
	
}
