# Opis projekta
Projekat je web aplikacija pod nazivom Device Monitoring System.
Ona predstavlja najosnovniji sistem za praćenje evidencije 
o uređajima i njihovim očitavanjima. U projektu uređaj predstavlja
neko apstraktno merilo koje ima svoj serijski broj, naziv i lokaciju.
Uređaj moze da evidentira svoju vrednost. Projekat je uvod u 
tehnologije razvoja web aplikacija, stoga ne postoje stvarni uređaji
koji periodično evidentiraju zaista izmerene podatke.

### Sistem ima mogućnost da:
- Registruje nove uređaje
- Ispiše pregled svih uređaja
- Ispiše pregled merenja uređaja
- Registruje nova merenja za uređaj

## Korišćene tehnologije
- **Frontend**
  - React 18 (Vite)
  - JavaScript
  - HTML5
  - CSS
  - Axios
- **Backend**
  - Java 21
  - Spring Boot, Data JPA, Web
  - Swagger UI
  - Maven
- **Database**
  - PostgreSQL
- **Ostalo**
  - Docker
  - Docker Compose
  - JUnit
  - Mockito
  - Git

## Struktura projekta
```text
device-monitoring-system/
├── backend/                  # Spring Boot aplikacija
│   ├── src/
│   │   ├── main/java/        # Kontroleri, servisi, DTO-ovi i entiteti
│   │   └── main/resources/   # application.properties i konfiguracija
│   ├── Dockerfile            # Docker instrukcije za backend
│   └── pom.xml               # Maven zavisnosti
├── frontend/                 # React (Vite) aplikacija
│   ├── src/
│   │   ├── components/       # UI komponente (DeviceList, ReadingSection, itd.)
│   │   ├── api.js            # Axios klijent za komunikaciju sa backendom
│   │   └── App.jsx           # Glavna komponenta i upravljanje stanjem
│   └── package.json          # npm zavisnosti i skripte
├── docker-compose.yml        # Orchestracija PostgreSQL baze i backend servisa
└── README.md                 # Dokumentacija projekta
```

## Zavisnosti
### Docker
Da bi pokrenuli aplikaciju pomoću docker kontejnera potrebno je instalirati Docker na korisnikov računar.
Instalaciju pratite po uputstvu na zvaničnom sajtu: https://www.docker.com/products/docker-desktop/
### NodeJS
Za pokretanje frontend-a potrebno je preuzeti NodeJS sa zvaničnog sajta: https://nodejs.org/en/download

## Kada se prvi put pokreće projekat
Treba da se pokrene komanda:<br>
```bash
docker compose up --build -d
```
Ona gradi kontejnere za PostgreSQL i backend. Nakon prvog puta
oni se mogu pokrenuti narednim komandama.

## Kako pokrenuti PostgreSQL
PostgreSQL se može pokrenuti komandom 
```docker compose up postgres -d```.

## Kako pokrenuti Backend
Backend aplikacije može da se pokrene komandom 
```docker compose up backend -d```.

## Kako pokrenuti Frontend
Frontend se može pokrenuti komandom:
``` docker compose up frontend -d ``` 

### Mogu se pokrenuti svi kontejneri komandom
```bash 
docker compose up -d
```

## Url delova projekta
Frontend URL: http://localhost:5173<br>
Backend URL: http://localhost:8080/api (služi samo za upite)<br>
SwaggerUI URL: http://localhost:8080/swagger-ui/index.html
