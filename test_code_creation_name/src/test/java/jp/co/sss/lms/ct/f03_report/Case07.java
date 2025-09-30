package jp.co.sss.lms.ct.f03_report;

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
 * 結合テスト レポート機能
 * ケース07
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース07 受講生 レポート新規登録(日報) 正常系")
public class Case07 {

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
	@DisplayName("テスト03 未提出の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		
		 try {
		     ((JavascriptExecutor) webDriver).executeScript("document.body.style.zoom = '0.25'");
			 
		     Thread.sleep(3000);
		     
			 WebElement detailBtn = webDriver.findElement(By.xpath("/html/body/div/div/div/div/div/div[5]/div[2]/table/tbody/tr[2]/td[5]/form/input[3]"));
			 detailBtn.click();
			 
			 Thread.sleep(3000);
			 
	         getEvidence(new Object(){});
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「提出する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		try {
			((JavascriptExecutor) webDriver).executeScript("document.body.style.zoom = '0.75'");
			 
		    Thread.sleep(3000);
		     
		    WebElement reportBtn = webDriver.findElement(By.xpath("/html/body/div/div/div/div/div/div/table/tbody/tr[2]/td/form/input[5]"));
		    reportBtn.click();
		     
		    Thread.sleep(3000);
		     
		    getEvidence(new Object(){});
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を入力して「提出する」ボタンを押下し確認ボタン名が更新される")
	void test05() {
		try {
			WebElement reportWrite = webDriver.findElement(By.name("contentArray[0]"));
			reportWrite.clear();
			 
			reportWrite.sendKeys("これは本日の報告内容をお書きくださいの報告内容です。");
			
			Thread.sleep(3000);
		     
		    getEvidence(new Object(){});
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}

}
