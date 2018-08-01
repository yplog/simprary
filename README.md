# simprary
Jsf, Jpa, Maven kullanilarak yapilmis basit bir kutuphane uygulamasi.  
## Kullanilan Teknolojiler  
* Jsf 2.2
* EclipseLink JPA
* GlassFish Server 4.1
* MySQL
## Kurulum Gereksinimleri
* JDK 1.8
* Maven
* GlassFish Server 4.1
* MySQL
## Kurulum
Indirilen proje altinda *src/main/webapp/WEB-INF* dizininde bulunan *glassfish-resources.xml* dosyasini 
kendi veri tabaniniza gore yapilandirin:  
```xml
<property name="serverName" value="localhost"/>
<property name="portNumber" value="3306"/>
<property name="databaseName" value="simprarytest"/>
<property name="User" value="root"/>
<property name="Password" value=""/>
<property name="URL" value="jdbc:mysql://localhost:3306/simprarytest?zeroDateTimeBehavior=convertToNull"/>
```
Yapilandirma sonrasi `mvn clean package` komutunu calisitiralim. Bu komut sonrasi proje icinde *target* 
dizininizin olusmus olmasi gerekiyor.  
*target* dizininde bulunan *.war* uzantili paketi GlassFish admin arayuzu uzerinden *Deploy Application* altinda bulunan
*Location* kismindan secerek deploy edebilirsiniz.  
_NOT:_ Sisteme giris yapan ilk kullanici admin olarak atanir. Daha sonra diger kullanicilarin yetkisini duzenleyebilir. 

![simprary](http://i67.tinypic.com/16awkqu.jpg)
