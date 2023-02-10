package az.company.kafkatutorialproject.service;

import az.company.kafkatutorialproject.entity.Book;
import az.company.kafkatutorialproject.exception.BookNotFoundException;
import az.company.kafkatutorialproject.mapper.BookMapper;
import az.company.kafkatutorialproject.model.BookDto;
import az.company.kafkatutorialproject.model.request.BookUpdateRequest;
import az.company.kafkatutorialproject.model.request.CreateBookRequest;
import az.company.kafkatutorialproject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final KafkaTemplate<String, List<BookDto>> kafkaTemplate;

    public List<BookDto> getAllBooks() {
        bookRepository.findAll().forEach(bookMapper::mapToDto);

        var books = bookRepository.findAll()
                .stream()
                .map(bookMapper::mapToDto)
                .collect(Collectors.toList());

        kafkaTemplate.send("book_management_topic", books);
        log.info("Books list : {}", books);
        return books;
    }

    public BookDto getBookById(Long id) {
        var book = bookRepository.findById(id)
                .map(bookMapper::mapToDto)
                .orElseThrow(() -> new BookNotFoundException("Book not found by id : " + id));
        log.info("Book founded : {}", book);

        kafkaTemplate.send("book_management_topic", List.of(book));
        return book;
    }

    public BookDto createBook(CreateBookRequest request) {
        var book = bookRepository.save(bookMapper.mapRequestToEntity(request));
        log.info("Book created : {} ", book);

        var mappedBook = bookMapper.mapToDto(book);
        kafkaTemplate.send("book_management_topic", List.of(mappedBook));

        return mappedBook;
    }

    public Book updateBook(Long id, BookUpdateRequest request) {

        var book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found by id : " + id));

        bookMapper.mapUpdateRequestToEntity(book, request);

        log.info("Book updated --> {}", book);
        kafkaTemplate.send("book_management_topic", List.of(bookMapper.mapToDto(book)));

        return bookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
        log.info("Book deleted by id : {}", id);
    }

}
