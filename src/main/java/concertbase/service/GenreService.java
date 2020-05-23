package concertbase.service;

import concertbase.model.Genre;
import concertbase.model.Subgenre;
import concertbase.persistence.GenreRepository;
import concertbase.persistence.SubgenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    @Autowired
    SubgenreRepository subgenreRepository;

    public Genre addGenre(String name) {
        Genre genre = new Genre(name);
        genreRepository.save(genre);
        return genre;
    }

    private Subgenre addSubgenre(String name, Genre genre) {
        Subgenre subgenre = new Subgenre(name);
        subgenreRepository.save(subgenre);
        subgenre.setGenre(genre);
        return subgenre;
    }

    public Subgenre addSubgenre(String name, long genreId) {
        Genre genre = genreRepository.findById(genreId);
        return addSubgenre(name, genre);

    }

    public Subgenre addSubgenre(String name, String genreName) {
        Genre genre = genreRepository.findByName(genreName).get(0);
        return addSubgenre(name, genre);
    }



}
