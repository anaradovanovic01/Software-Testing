package testovi;

import static org.testng.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class PrijavaTestovi {
    public static String baseUrl = "http://localhost/projekat2/projekat_ankete/index.php";
    public static WebDriver driver;

    public PrijavaTestovi() {
    }

    @BeforeTest
    public void pre() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\korisnik\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }
    
    @AfterTest
    public void posle() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void PrijavaUspesna() {
        try {
            String korisnik = "Djordje";
            String lozinka = "sifra123";

            driver.findElement(By.name("username")).sendKeys(korisnik);
            driver.findElement(By.name("sifra")).sendKeys(lozinka);
            driver.findElement(By.name("logovanje")).click();

            Object objekat = driver.findElement(By.xpath("//a[@href='logout.php']"));
            Assert.assertNotNull(objekat);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }

    @Test
    public void PrijavaNeuspesnaKorisnickoImePrazno() {
        try {
            String korisnik = "";
            String lozinka = "sifra123";
            String ocekivano = "Niste uopšte uneli korisničko ime!";

            driver.findElement(By.name("username")).sendKeys(korisnik);
            driver.findElement(By.name("sifra")).sendKeys(lozinka);
            driver.findElement(By.name("logovanje")).click();

            String poruka = driver.findElement(By.xpath("/html/body/div[2]/center/div/center/font/b")).getText();
            Assert.assertEquals(poruka, ocekivano);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }

    @Test
    public void PrijavaNeuspesnaKorisnickoImeNePostoji() {
        try {
            String korisnik = "Djordj";
            String lozinka = "sifra123";
            String ocekivano = "Uneto korisničko ime ne postoji u sistemu!";

            driver.findElement(By.name("username")).sendKeys(korisnik);
            driver.findElement(By.name("sifra")).sendKeys(lozinka);
            driver.findElement(By.name("logovanje")).click();

            String poruka = driver.findElement(By.xpath("/html/body/div[2]/center/div/center/font/b")).getText();
            Assert.assertEquals(poruka, ocekivano);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }

    @Test
    public void PrijavaNeuspesnaLozinkaPrazna() {
        try {
            String korisnik = "Djordje";
            String lozinka = "";
            String ocekivano = "Niste uopšte uneli lozinku!";

            driver.findElement(By.name("username")).sendKeys(korisnik);
            driver.findElement(By.name("sifra")).sendKeys(lozinka);
            driver.findElement(By.name("logovanje")).click();

            String poruka = driver.findElement(By.xpath("/html/body/div[2]/center/div/center/font/b")).getText();
            Assert.assertEquals(poruka, ocekivano);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }

    @Test
    public void PrijavaNeuspesnaPogresnaLozinka() {
        try {
            String korisnik = "Djordje";
            String lozinka = "sifra";
            String ocekivano = "Pogrešno uneta lozinka!";

            driver.findElement(By.name("username")).sendKeys(korisnik);
            driver.findElement(By.name("sifra")).sendKeys(lozinka);
            driver.findElement(By.name("logovanje")).click();

            String poruka = driver.findElement(By.xpath("/html/body/div[2]/center/div/center/font/b")).getText();
            Assert.assertEquals(poruka, ocekivano);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }



}