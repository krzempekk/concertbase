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

    public void addGenre(String name) {
        genreRepository.save(new Genre(name));
    }

    private void addSubgenre(String name, Genre genre) {
        Subgenre subgenre = new Subgenre(name);
        subgenreRepository.save(subgenre);
        subgenre.setGenre(genre);
    }

    public void addSubgenre(String name, long genreId) {
        Genre genre = genreRepository.findById(genreId);
        addSubgenre(name, genre);
    }

    public void addSubgenre(String name, String genreName) {
        Genre genre = genreRepository.findByName(genreName).get(0);
        addSubgenre(name, genre);
    }



}
