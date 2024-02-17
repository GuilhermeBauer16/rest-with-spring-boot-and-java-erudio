package br.com.erudio.restwithspringbootandjavaerudio.repositories;

import br.com.erudio.restwithspringbootandjavaerudio.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel,Long> {
}
