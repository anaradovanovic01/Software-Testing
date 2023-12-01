package testovi;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class PrijavaTestovi {
    public static WebDriver driver;

    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\korisnik\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost/projekat2/projekat_ankete/index.php");
        driver.manage().window().maximize();
       
        PrijavaUspesna("Djordje", "sifra123");
        driver.findElement(By.xpath("//a[@href='logout.php']")).click();
        PrijavaNeuspesna("", "sifra123", "Niste uopšte uneli korisničko ime!", "prazno korisnicko ime");
        PrijavaNeuspesna("Djordj", "sifra123", "Uneto korisničko ime ne postoji u sistemu!", "nepostojece korisnicko ime");
        PrijavaNeuspesna("Djordje", "", "Niste uopšte uneli lozinku!", "prazna lozinka");
        PrijavaNeuspesna("Djordje", "sifra", "Pogrešno uneta lozinka!", "pogresna lozinka");
        
        driver.quit();
    }

    public static void PrijavaUspesna(String korisnik, String lozinka){
        
        driver.findElement(By.name("username")).sendKeys(korisnik);
        driver.findElement(By.name("sifra")).sendKeys(lozinka);
        driver.findElement(By.name("logovanje")).click();
        
        Object objekat = driver.findElement(By.xpath("//a[@href='logout.php']"));

        try{
            Assert.assertNotNull(objekat);
            System.out.println("Prijava uspesna - PASS");
        } catch(Throwable e){
            System.out.println("Prijava uspesna - FAIL, Greska:" + e);
        }
        
    }

    public static void PrijavaNeuspesna(String korisnik, String lozinka, String ocekivano, String ime_testa){
        
        driver.findElement(By.name("username")).sendKeys(korisnik);
        driver.findElement(By.name("sifra")).sendKeys(lozinka);
        driver.findElement(By.name("logovanje")).click();
        
        String poruka = driver.findElement(By.xpath("/html/body/div[2]/center/div/center/font/b")).getText();

        try{
            Assert.assertEquals(poruka, ocekivano);
            System.out.println("Prijava neuspesna: " + ime_testa + " - PASS");
        } catch(Throwable e){
            System.out.println("Prijava neuspesna: " + ime_testa + " - FAIL, Greska:" + e);
        }
        
    }
    
}
