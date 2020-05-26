# ConcertBase

## Uruchomienie projektu

Aby móc poprawnie uruchomić projekt, należy mieć postawioną lokalnie bazę PostgreSQL. Domyślnie baza nazywa się "concertbase", użytkownikiem jest "postgres", a hasłem jest "postgres". Ustawienia te mogą zostać zmodyfikowane w pliku src/main/resources/application.properties. 

## Przewodnik po projekcie

### Model
Model bazy danych został utworzony przy wykorzystaniu mechanizmów JPA.
Dla każdej tabeli zostały stworzone odpowiednie klasy.
Wykorzystane zostały następujące klasy:
* Artist - wykonawca muzyczny
* Concert - klasa abstrakcyjna przedstawiająca dany koncert
* StreamedConcert - dziedziczy z klasy Concert, przestawia koncert streamowany
* LiveConcert - dziedziczy z klasy Concert, przedstawia koncert odbywający się na żywo
* Performance - występ danego artysty na danym koncercie
* Genre - gatunek muzyczny
* Subgenre - podgatunek muzyczny
* Venue - obiekt, w którym odbywają się koncerty <br>
Dziedziczenie z klasy Concert odbywa się przy wykorzystaniu strategii TABLE_PER_CLASS. Rozwiązanie to powoduje, iż w warstwie bazy danych tracimy własność 
klucza obcego w kolumnie Concert_FK w tabeli Performance. Jednak w tym przypadku całą kontrolę nad danymi w bazie sprawuje nasza aplikacja, zatem wady tego
rozwiązania nie mają negatywnego wpływu na jej działanie.
