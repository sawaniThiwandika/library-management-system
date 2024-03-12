package lk.ijse.libraryManagementSystem.bo.impl;

import lk.ijse.libraryManagementSystem.bo.BookBo;
import lk.ijse.libraryManagementSystem.config.FactoryConfiguration;
import lk.ijse.libraryManagementSystem.dao.BookDao;
import lk.ijse.libraryManagementSystem.dao.impl.BookDaoImpl;
import lk.ijse.libraryManagementSystem.dto.BookDto;
import lk.ijse.libraryManagementSystem.dto.BranchDto;
import lk.ijse.libraryManagementSystem.entity.Book;
import lk.ijse.libraryManagementSystem.entity.Branch;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class BookBoImpl implements BookBo {
    BookDao bookDao=new BookDaoImpl();
    @Override
    public String generateNewBookId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Book book = bookDao.generateNewId(session);
        session.close();
        if(!(book.getId()==null)) {
            return splitId(book.getId());
        }
        return splitId(null);
    }
    public String splitId(String currentBookId) {
        if(currentBookId!=null){
            String [] split= currentBookId.split("B0");
            int id=Integer.parseInt(split[1]);

            id++;
            if(id<10)
            {return "B00"+id;}
            else{
                return "B0"+id;
            }
        }
        else {
            return "B001";
        }
    }

    @Override
    public boolean saveBook(BookDto bookDto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        boolean saved = bookDao.save(session, new Book(bookDto.getId(), bookDto.getBranch(), bookDto.getTransactions(), bookDto.getTitle(), bookDto.getAuthor(), bookDto.getGenre(), bookDto.getImagePath(), bookDto.isAvailable()));
        session.close();
        return saved;
    }

    @Override
    public List<BookDto> loadAllBook() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        List<Book> bookList = bookDao.getAll(session);
        ArrayList<BookDto> bookDtos = new ArrayList<>();
        for (Book book:bookList){
            bookDtos.add(new BookDto(book.getId(),book.getBranch(),book.getTransactions(),book.getTitle(),book.getAuthor(),book.getGenre(),book.getImagePath(),book.isAvailable()));
        }
        return bookDtos;

    }

    @Override
    public boolean updateBook(BookDto bookDto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        boolean saved = bookDao.update(session, new Book(bookDto.getId(), bookDto.getBranch(), bookDto.getTransactions(), bookDto.getTitle(), bookDto.getAuthor(), bookDto.getGenre(), bookDto.getImagePath(), bookDto.isAvailable()));
        session.close();
        return saved;
    }

    @Override
    public BookDto getBook(String id) {
        Book book = bookDao.getBook(id);
        return new BookDto(book.getId(), book.getBranch(), book.getTransactions(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getImagePath(), book.isAvailable());
    }

    @Override
    public boolean deleteBook(BookDto bookDto) {
        bookDao.delete(new Book(bookDto.getId(), bookDto.getBranch(), bookDto.getTransactions(), bookDto.getTitle(), bookDto.getAuthor(), bookDto.getGenre(), bookDto.getImagePath(), bookDto.isAvailable()));
        return true;
    }
}
