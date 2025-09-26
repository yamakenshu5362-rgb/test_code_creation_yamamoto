package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// TODO ここに追加
		goTo("http://localhost:8080/lms/");
		getEvidence(new Object(){});
		
		String url = webDriver.getCurrentUrl();
		assertEquals(url, "http://localhost:8080/lms/");
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加]
		try {

			 WebElement username = webDriver.findElement(By.name("loginId"));
			 WebElement password = webDriver.findElement(By.name("password"));
			 
			 username.clear();
			 password.clear();
			 
			 username.sendKeys("StudentAA01");
	         password.sendKeys("StudentAA01a3E");
			
	         getEvidence(new Object(){});
	         
			 WebElement loginBtn = webDriver.findElement(By.xpath("//input[@class='btn btn-primary']"));
	         loginBtn.click();
	         
	         Thread.sleep(3000);
			 
	         getEvidence(new Object(){});
	         
	         String url = webDriver.getCurrentUrl();
	 		 assertEquals(url, "http://localhost:8080/lms/course/detail");
	         
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// TODO ここに追加
		try {
			
			WebElement dropdownButton = webDriver.findElement(By.linkText("機能"));
			dropdownButton.click();
			
			WebElement helpButton = webDriver.findElement(By.linkText("ヘルプ"));
			helpButton.click();
			
			Thread.sleep(3000);
			
			getEvidence(new Object(){});
			
	        String url = webDriver.getCurrentUrl();
	        assertEquals(url, "http://localhost:8080/lms/help");
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// TODO ここに追加
		try {
			WebElement FAQButton = webDriver.findElement(By.linkText("よくある質問"));
			FAQButton.click();
			
			Thread.sleep(3000);
			
	        Object[] windowHandles=webDriver.getWindowHandles().toArray();
	        webDriver.switchTo().window((String) windowHandles[1]);
	        
			getEvidence(new Object(){});
			
			String url = webDriver.getCurrentUrl();
	        assertEquals(url, "http://localhost:8080/lms/faq");
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
