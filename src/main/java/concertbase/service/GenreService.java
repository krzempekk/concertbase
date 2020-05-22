package concertbase.service;

import concertbase.model.Genre;
import concertbase.model.Subgenre;
import concertbase.persistence.GenreRepository;
import concertbase.persistence.SubgenreRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    @Autowired
    SubgenreRespository subgenreRespository;

    public void addGenre(String name) {
        genreRepository.save(new Genre(name));
    }

    public void addSubgenre(String name, String genreName) {
        Genre genre = genreRepository.findByName(genreName).get(0);
        Subgenre subgenre = new Subgenre(name);
        subgenreRespository.save(subgenre);
        subgenre.setGenre(genre);
    }
}
