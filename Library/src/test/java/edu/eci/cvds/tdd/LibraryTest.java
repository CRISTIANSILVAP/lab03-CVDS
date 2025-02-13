package edu.eci.cvds.tdd;

import static org.junit.jupiter.api.Assertions.*;

import edu.eci.cvds.tdd.library.Library;
import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.loan.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class LibraryTest {

    private Library library;
    private Book ConstruyeTuDestino;
    private User user1;
    private Library l;

    @BeforeEach
    public void setUp() {
        library = new Library();
        ConstruyeTuDestino = new Book("Construye tu destino", "Dr. Dayer", "001");
        library.addBook(ConstruyeTuDestino);
        user1 = new User();
        l= new Library();

    }

    @Test
    public void shouldReturnNullWhenLoanIsNull() {
        Loan loan = library.returnLoan(null);
        assertNull(loan, "Si el préstamo es nulo, el método debe devolver null.");
    }

    @Test
    public void shouldReturnNullWhenLoanDoesNotExist() {
        Loan loan = new Loan();
        Loan returnedLoan = library.returnLoan(loan);
        assertNull(returnedLoan, "Si el préstamo no existe en la biblioteca, debe devolver null.");
    }

    @Test
    public void shouldIncreaseBy1TheAmuntOfTheReturnedLibrary() {
        int amountOfBooks = library.getBooks().get(ConstruyeTuDestino);
        assertEquals(1, amountOfBooks);
        library.addBook(ConstruyeTuDestino);
        int amountOfBooks1 = library.getBooks().get(ConstruyeTuDestino);
        assertEquals(2, amountOfBooks1);
    }

    @Test
    public void shouldReturnALoanAndItsStatusIsReturned() {
        user1.setId("1076");
        user1.setName("Juan");
        library.addUser(user1);
        Loan loan = library.loanABook(user1.getId(), ConstruyeTuDestino.getIsbn());
        assertNotNull(loan, "El préstamo no debería ser nulo");
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
        library.returnLoan(loan);
        assertEquals(LoanStatus.RETURNED, loan.getStatus());
    }

    @Test
    public void shouldReturnALoanAndItsReturnDateIsTheCurrentDate() {
        user1.setId("1076");
        user1.setName("Juan");
        library.addUser(user1);
        Loan loan = library.loanABook(user1.getId(), ConstruyeTuDestino.getIsbn());
        assertNotNull(loan);
        library.returnLoan(loan);
        assertEquals(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                loan.getReturnDate().truncatedTo(ChronoUnit.SECONDS),
                "La fecha de devolución debe ser la actual.");
    }
    @Test
    public void testLoanABookSuccess() {
        Book book1 = new Book("12345", "Java Programming", "Author Name");
        User user11 = new User();
        user11.setId("user1");
        l.addBook(book1); // Agregar un libro al sistema
        l.addUser(user11);
        // Prueba cuando el préstamo se realiza correctamente
        Loan loan = l.loanABook("user1", "Author Name");
        assertNotNull(loan, "El préstamo debería haberse realizado");
        assertEquals(LoanStatus.ACTIVE, loan.getStatus(), "El préstamo debe tener el estado ACTIVO");

    }


    @Test
    public void testLoanABookBookNotAvailable() {
        Book book1 = new Book("12345", "Java Programming", "Author Name");
        User user11 = new User();
        user11.setId("user1");
        l.addBook(book1); // Agregar un libro al sistema
        l.addUser(user11);
        // Prueba cuando el libro no está disponible
        l.loanABook("user1", "12345");
        Loan loan = l.loanABook("user1", "12345"); // Intentamos realizar otro préstamo con el mismo libro
        assertNull(loan, "El préstamo no debería haberse realizado, el libro ya está prestado");
    }

    @Test
    public void testLoanABookUserNotFound() {
        // Prueba cuando el usuario no existe
        Loan loan = l.loanABook("user2", "12345");
        assertNull(loan, "El préstamo no debería haberse realizado, el usuario no existe");
    }
    @Test
    public void testLoanABookBookNotFound() {

        User user11 = new User();
        user11.setId("user1");
        l.addUser(user11);
        // Prueba cuando el usuario no existe
        Loan loan = l.loanABook("user1", "123456");
        assertNull(loan, "El préstamo no debería haberse realizado, el Libro no existe");
    }

    @Test
    public void testLoanABookUserHasActiveLoan() {
        // Prueba cuando el usuario ya tiene un préstamo activo
        l.loanABook("user1", "12345"); // Primer préstamo exitoso
        Loan loan = l.loanABook("user1", "12345"); // Intentamos hacer otro préstamo con el mismo usuario
        assertNull(loan, "El préstamo no debería haberse realizado, el usuario ya tiene un préstamo activo");
    }
    @Test
    public void shouldAddNewBookSuccessfullyWhenValidBookIsProvided() {
        // Creamos un libro con los atributos correspondientes.
        Book newBook = new Book("miguel", "cristian", "0000");
        // Verificamos que el libro se añade correctamente.
        assertTrue(library.addBook(newBook));
    }

    @Test
    public void shouldNotAddBookWhenProvidedBookIsNull() {
        // Verificamos que no se puede añadir un libro nulo.
        assertFalse(library.addBook(null));
    }
    @Test
    public void shouldNotModifyBooksWhenNullBookIsAdded() {
        Book book = new Book("miguel", "cristian", "0000");
        library.addBook(book);  // Agregamos un libro
        // Guardamos el estado actual de los libros
        int initialSize = library.getBooks().size();
        // Intentamos agregar un libro nulo
        library.addBook(null);
        // Verificamos que el tamaño de la lista de libros no cambió
        assertEquals(initialSize, library.getBooks().size());
    }
}