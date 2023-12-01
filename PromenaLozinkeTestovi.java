package testovi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class PromenaLozinkeTestovi {
    public static WebDriver driver;

    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\korisnik\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost/projekat2/projekat_ankete/promena_lozinke.php");
        driver.manage().window().maximize();
       
        PromenaLozinkeNeuspesna("", "sifra123", "sifra", "Ne postoji dato korisničko ime u sistemu!", "prazno korisnicko ime");
        PromenaLozinkeNeuspesna("Djordj", "sifra123", "sifra", "Ne postoji dato korisničko ime u sistemu!", "nepostojece korisnicko ime");
        PromenaLozinkeNeuspesna("Djordje", "", "sifra", "Vaša stara lozinka nije dobro uneta!", "prazna stara lozinka");
        PromenaLozinkeNeuspesna("Djordje", "sifra12", "sifra", "Vaša stara lozinka nije dobro uneta!", "pogresna stara lozinka");
        PromenaLozinkeNeuspesna("Djordje", "sifra123", "", "Vaša nova lozinka mora imati više od 4 karaktera!", "prazna nova lozinka");
        PromenaLozinkeUspesna("Filip", "sifra123", "sifra");        
        
        driver.quit();
    }

    public static void PromenaLozinkeUspesna(String korisnik, String stara, String nova){
        
        driver.findElement(By.name("username")).sendKeys(korisnik);
        driver.findElement(By.name("sifra_stara")).sendKeys(stara);
        driver.findElement(By.name("sifra_nova")).sendKeys(nova);
        driver.findElement(By.name("promeni")).click();

        String ocekivano = "Poštovani korisniče, vaša lozinka je uspešno promenjena!\nVratite se na početnu stranu kako biste se ulogovali.";
        Object objekat = driver.findElement(By.xpath("html/body/div[2]/center/p/b"));
        String poruka = driver.findElement(By.xpath("html/body/div[2]/center/p/b")).getText();

        try{
            Assert.assertNotNull(objekat);
            Assert.assertEquals(poruka, ocekivano);
            System.out.println("Promena lozinke uspesna - PASS");
        } catch(Throwable e){
            System.out.println("Promena lozinke uspesna - FAIL, Greska:" + e);
        }
        
    }
    
    public static void PromenaLozinkeNeuspesna(String korisnik, String stara, String nova, String ocekivano, String ime_testa){
        
        driver.findElement(By.name("username")).sendKeys(korisnik);
        driver.findElement(By.name("sifra_stara")).sendKeys(stara);
        driver.findElement(By.name("sifra_nova")).sendKeys(nova);
        driver.findElement(By.name("promeni")).click();
        
        String poruka = driver.findElement(By.xpath("html/body/div[2]/div/font/b")).getText();

        try{
            Assert.assertEquals(poruka, ocekivano);
            System.out.println("Promena lozinke neuspesna: " + ime_testa + " - PASS");
        } catch(Throwable e){
            System.out.println("Promena lozinke neuspesna: " + ime_testa + " - FAIL, Greska:" + e);
        }
        
    }

}
