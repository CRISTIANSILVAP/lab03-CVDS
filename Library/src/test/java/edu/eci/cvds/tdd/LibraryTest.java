package edu.eci.cvds.tdd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.eci.cvds.tdd.library.Library;
import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.loan.Loan;

import java.time.LocalDateTime;

public class LibraryTest {

    private Library library;
    private Book book1;
    private User user1;

    @BeforeEach
    public void setUp() {
        library = new Library();
        book1 = new Book("12345", "Java Programming", "Author Name");
        user1 = new User("user1", "John Doe");
        library.addBook(book1); // Agregar un libro al sistema
    }

    @Test
    public void testLoanABookSuccess() {
        // Prueba cuando el préstamo se realiza correctamente
        Loan loan = library.loanABook("user1", "12345");
        assertNotNull(loan, "El préstamo debería haberse realizado");
        assertEquals(LoanStatus.ACTIVE, loan.getStatus(), "El préstamo debe tener el estado ACTIVO");
        assertEquals("user1", loan.getUser().getId(), "El préstamo debe estar asociado al usuario correcto");
        assertEquals("12345", loan.getBook().getIsbn(), "El préstamo debe estar asociado al libro correcto");
    }

    @Test
    public void testLoanABookBookNotAvailable() {
        // Prueba cuando el libro no está disponible
        library.loanABook("user1", "12345");
        Loan loan = library.loanABook("user1", "12345"); // Intentamos realizar otro préstamo con el mismo libro
        assertNull(loan, "El préstamo no debería haberse realizado, el libro ya está prestado");
    }

    @Test
    public void testLoanABookUserNotFound() {
        // Prueba cuando el usuario no existe
        Loan loan = library.loanABook("user2", "12345");
        assertNull(loan, "El préstamo no debería haberse realizado, el usuario no existe");
    }

    @Test
    public void testLoanABookUserHasActiveLoan() {
        // Prueba cuando el usuario ya tiene un préstamo activo
        library.loanABook("user1", "12345"); // Primer préstamo exitoso
        Loan loan = library.loanABook("user1", "12345"); // Intentamos hacer otro préstamo con el mismo usuario
        assertNull(loan, "El préstamo no debería haberse realizado, el usuario ya tiene un préstamo activo");
    }
}
