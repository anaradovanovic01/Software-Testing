package testovi;

import static org.testng.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class PromenaLozinkeTestovi {
    public static String baseUrl = "http://localhost/projekat2/projekat_ankete/promena_lozinke.php";
    public static WebDriver driver;

    public PromenaLozinkeTestovi() {
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
    public void PromenaLozinkeUspesna() {
        try {
            String korisnik = "Filip";
            String stara = "sifra123";
            String nova = "sifra";

            driver.findElement(By.name("username")).sendKeys(korisnik);
            driver.findElement(By.name("sifra_stara")).sendKeys(stara);
            driver.findElement(By.name("sifra_nova")).sendKeys(nova);
            driver.findElement(By.name("promeni")).click();

            String ocekivano = "Poštovani korisniče, vaša lozinka je uspešno promenjena!\nVratite se na početnu stranu kako biste se ulogovali.";
            Object objekat = driver.findElement(By.xpath("html/body/div[2]/center/p/b"));
            String poruka = driver.findElement(By.xpath("html/body/div[2]/center/p/b")).getText();

            Assert.assertNotNull(objekat);
            Assert.assertEquals(poruka, ocekivano);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }

    @Test
    public void PromenaLozinkeNeuspesnaKorisnickoImePrazno() {
        try {
            String korisnik = "";
            String stara = "sifra123";
            String nova = "sifra";
            String ocekivano = "Ne postoji dato korisničko ime u sistemu!";

            driver.findElement(By.name("username")).sendKeys(korisnik);
            driver.findElement(By.name("sifra_stara")).sendKeys(stara);
            driver.findElement(By.name("sifra_nova")).sendKeys(nova);
            driver.findElement(By.name("promeni")).click();

            String poruka = driver.findElement(By.xpath("html/body/div[2]/div/font/b")).getText();
            Assert.assertEquals(poruka, ocekivano);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }

    @Test
    public void PromenaLozinkeNeuspesnaKorisnickoNePostoji() {
        try {
            String korisnik = "Djordj";
            String stara = "sifra123";
            String nova = "sifra";
            String ocekivano = "Ne postoji dato korisničko ime u sistemu!";

            driver.findElement(By.name("username")).sendKeys(korisnik);
            driver.findElement(By.name("sifra_stara")).sendKeys(stara);
            driver.findElement(By.name("sifra_nova")).sendKeys(nova);
            driver.findElement(By.name("promeni")).click();

            String poruka = driver.findElement(By.xpath("html/body/div[2]/div/font/b")).getText();
            Assert.assertEquals(poruka, ocekivano);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }

    @Test
    public void PromenaLozinkeNeuspesnaStaraLozinkaPrazna() {
        try {
            String korisnik = "Djordje";
            String stara = "";
            String nova = "sifra";
            String ocekivano = "Vaša stara lozinka nije dobro uneta!";

            driver.findElement(By.name("username")).sendKeys(korisnik);
            driver.findElement(By.name("sifra_stara")).sendKeys(stara);
            driver.findElement(By.name("sifra_nova")).sendKeys(nova);
            driver.findElement(By.name("promeni")).click();

            String poruka = driver.findElement(By.xpath("html/body/div[2]/div/font/b")).getText();
            Assert.assertEquals(poruka, ocekivano);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }

    @Test
    public void PromenaLozinkeNeuspesnaStaraLozinkaPogresna() {
        try {
            String korisnik = "Djordje";
            String stara = "sifra12";
            String nova = "sifra";
            String ocekivano = "Vaša stara lozinka nije dobro uneta!";

            driver.findElement(By.name("username")).sendKeys(korisnik);
            driver.findElement(By.name("sifra_stara")).sendKeys(stara);
            driver.findElement(By.name("sifra_nova")).sendKeys(nova);
            driver.findElement(By.name("promeni")).click();

            String poruka = driver.findElement(By.xpath("html/body/div[2]/div/font/b")).getText();
            Assert.assertEquals(poruka, ocekivano);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }

    @Test
    public void PromenaLozinkeNeuspesnaNovaLozinkaPrazna() {
        try {
            String korisnik = "Djordje";
            String stara = "sifra123";
            String nova = "";
            String ocekivano = "Vaša nova lozinka mora imati više od 4 karaktera!";

            driver.findElement(By.name("username")).sendKeys(korisnik);
            driver.findElement(By.name("sifra_stara")).sendKeys(stara);
            driver.findElement(By.name("sifra_nova")).sendKeys(nova);
            driver.findElement(By.name("promeni")).click();

            String poruka = driver.findElement(By.xpath("html/body/div[2]/div/font/b")).getText();
            Assert.assertEquals(poruka, ocekivano);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }
    
}