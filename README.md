# ConcertBase

## Uruchomienie projektu

Aby móc poprawnie uruchomić projekt, należy mieć postawioną lokalnie bazę PostgreSQL. Domyślnie baza nazywa się "concertbase", użytkownikiem jest "postgres", a hasłem jest "postgres". Ustawienia te mogą zostać zmodyfikowane w pliku `src/main/resources/application.properties`. 

## Przewodnik po projekcie

### Controller i walidacja (*pakiet Presentation*)

Controller zdefiniowany w klasie `ApplicationController` zawiera przede wszystkim metody mapujące zapytania GET i POST na poszczególne route'y aplikacji. W każdej z metod odpowiednio jest tworzony kontekst widoku (przez dodawanie atrybutów do obiektu klasy `Model`). Metody korzystają tylko z klas typu Service, nie odwołują się bezpośrednio do klas Repository, co podkreśla warstwową strukturę aplikacji. W klasach `ConcertForm`, `SearchForm` i `VerySimpleSearchForm` zdefiniowane są modele formularzy. W przypadku `ConcertForm` i `VerySimpleSearchForm` zamieszczone są tam także adnotacje służące do walidacji określonych pól.

### Frontend

Warstwa frontendu aplikacji składa się z trzech widoków, których szablony zdefiniowane są w folderze `resources/templates` w plikach `intex.html`, `addConcert.html`, `advancedSearch.html`. Są to odpowiednio widoki 
- **główny** - zawierający listę streamowanych koncertów wraz z ich embedami, opcję podstawowego wyszukiwania oraz przyciski prowadzące do widoków zaawansowanego wyszukiwania i dodawania koncertów
- **dodawania koncertu** - zawiera formularz umożliwiający dodawanie koncertu. W przypadku podania błędnych danych z backendu jest zwracany i wyświetlany błąd. W przypadku poprawnych danych koncert jest tworzony, co potwierdzane jest wyświetleniem stosownej informacji.
- **zaawansowanego wyszukiwania koncertów** - zawiera formularz umożliwiający określenie parametrów wyszukiwania, a następnie przeprowadzenie go i wyświetlenie wyników