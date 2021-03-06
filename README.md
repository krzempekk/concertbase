# ConcertBase

## Uruchomienie projektu

Aby móc poprawnie uruchomić projekt, należy mieć postawioną lokalnie bazę PostgreSQL. Domyślnie baza nazywa się "concertbase", użytkownikiem jest "postgres", a hasłem jest "postgres". Ustawienia te mogą zostać zmodyfikowane w pliku `src/main/resources/application.properties`. 

Projekt po uruchomieniu aplikacji znajduje się pod adresem `http://127.0.0.1:8080/`
## Przewodnik po projekcie

### Używane narzędzia

* **Spring Boot**
* **JPA**
* **PostgreSQL**

### Model
Model bazy danych został utworzony przy wykorzystaniu mechanizmów JPA.
Dla każdej tabeli zostały stworzone odpowiednie klasy.
Wykorzystane zostały następujące klasy:
* **Artist** - wykonawca muzyczny
* **Concert** - klasa abstrakcyjna przedstawiająca dany koncert
* **StreamedConcert** - dziedziczy z klasy Concert, przestawia koncert streamowany
* **LiveConcert** - dziedziczy z klasy Concert, przedstawia koncert odbywający się na żywo
* **Performance** - występ danego artysty na danym koncercie
* **Genre** - gatunek muzyczny
* **Subgenre** - podgatunek muzyczny
* **Venue** - obiekt, w którym odbywają się koncerty

Dziedziczenie z klasy Concert odbywa się przy wykorzystaniu strategii TABLE_PER_CLASS. Rozwiązanie to powoduje, iż w warstwie bazy danych tracimy własność 
klucza obcego w kolumnie Concert_FK w tabeli Performance. Jednak w tym przypadku całą kontrolę nad danymi w bazie sprawuje nasza aplikacja, zatem wady tego
rozwiązania nie mają negatywnego wpływu na jej działanie.

### Persistence
Do każdej z klas stworzono interfejs ClassRepository, który rozszerza interfejs CrudRepository. Gwarantuje on 
pełny dostęp do danych z konkretnej tabeli. Dodatkowo pozwala na definiowanie potrzebnych funkcji
do pobierania określonych danych z bazy - na przykład funkcja `List<Artist> findAllBySubgenres_NameIgnoreCase(String subgenres_name)`
 pozwala na pobranie listy artystów, którzy wykonują muzykę z podgatunku o podanej nazwie.


### Klasy Service (pakiet *service*)
Klasy serwisu są łącznikiem między repozytoriami a  kontrolerem. Służą do pośredniczenia w dodawaniu danych, jak i ich wyszukiwaniu.
Najważniejszą funkcję w aplikacji pełni ConcertService, który umożliwia zaawansowane wyszukiwanie koncertu. Aby zrealizować tę funkcjonalność
została wykorzystana własna implementacja interfejsu Specification. Pozwala on na definiowanie własnego predykatu do filtrowania danych
złożonego z podpredykatów. Dzięki temu koncert można wyszukiwać po dowolnej kombinacji następujących własności:
* artyście
* podgatunku muzycznym
* dacie
* mieście
### Controller i walidacja (pakiet *presentation*)

Controller zdefiniowany w klasie `ApplicationController` zawiera przede wszystkim metody mapujące zapytania GET i POST na poszczególne route'y aplikacji. W każdej z metod odpowiednio jest tworzony kontekst widoku (przez dodawanie atrybutów do obiektu klasy `Model`). Metody korzystają tylko z klas typu Service, nie odwołują się bezpośrednio do klas Repository, co podkreśla warstwową strukturę aplikacji. W klasach `ConcertForm`, `SearchForm` i `VerySimpleSearchForm` zdefiniowane są modele formularzy. W przypadku `ConcertForm` i `VerySimpleSearchForm` zamieszczone są tam także adnotacje służące do walidacji określonych pól. W przypadku gdy wystąpi błąd walidacji, do frontendu przekazywana jest stosowna informacja.

### Frontend

Warstwa frontendu aplikacji składa się z trzech widoków, których szablony zdefiniowane są w folderze `resources/templates` w plikach `intex.html`, `addConcert.html`, `advancedSearch.html`. Są to odpowiednio widoki 
- **główny** - zawierający listę streamowanych koncertów wraz z ich embedami, opcję podstawowego wyszukiwania oraz przyciski prowadzące do widoków zaawansowanego wyszukiwania i dodawania koncertów
- **dodawania koncertu** - zawiera formularz umożliwiający dodawanie koncertu. W przypadku podania błędnych danych z backendu jest zwracany i wyświetlany błąd. W przypadku poprawnych danych koncert jest tworzony, co potwierdzane jest wyświetleniem stosownej informacji.
- **zaawansowanego wyszukiwania koncertów** - zawiera formularz umożliwiający określenie parametrów wyszukiwania, a następnie przeprowadzenie go i wyświetlenie wyników

![Strona główna](https://github.com/krzempekk/concertbase/blob/master/screen.png)