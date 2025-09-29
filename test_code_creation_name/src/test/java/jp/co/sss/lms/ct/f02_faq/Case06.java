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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト よくある質問機能
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

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

		goTo("http://localhost:8080/lms/");
		getEvidence(new Object(){});
		
		String url = webDriver.getCurrentUrl();
		assertEquals(url, "http://localhost:8080/lms/");
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {

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

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {

		try {
			WebElement kenshuButton = webDriver.findElement(By.linkText("【研修関係】"));
			kenshuButton.click();
			
			Thread.sleep(3000);
			
	        ((JavascriptExecutor) webDriver).executeScript("document.body.style.zoom = '0.5'");
			
	        Thread.sleep(3000);
	        
			getEvidence(new Object(){});
			
			Boolean cancelCheck = webDriver.getPageSource().contains("キャンセル料・途中退校について");
			assertTrue(cancelCheck);
			
			Boolean kenshuCheck = webDriver.getPageSource().contains("研修の申し込みはどのようにすれば良いですか？");
			assertTrue(kenshuCheck);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {

		try {
			WebElement searchQ = webDriver.findElement(By.className("sorting_1"));
			searchQ.click();
			
			Thread.sleep(3000);
			
	        ((JavascriptExecutor) webDriver).executeScript("document.body.style.zoom = '0.5'");
			
	        Thread.sleep(3000);
			
			getEvidence(new Object(){});
			
			WebElement searchA = webDriver.findElement(By.className("fs18"));
			
			assertEquals("A. 受講者の退職や解雇等、やむを得ない事情による途中終了に関してなど、事情をお伺いした上で、協議という形を取らせて頂きます。 弊社営業担当までご相談下さい。", searchA.getText());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
