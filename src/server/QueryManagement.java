package server;

import oop.Books;
import oop.DBConnection;
import oop.Loan;
import oop.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryManagement {

    public ArrayList<Loan> initLoan() {
        ArrayList<Loan> loanList = new ArrayList<>();
        try {
            Connection ex = DBConnection.getConnection();
            Statement st = ex.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM on_loan");
            while (rs.next()) {
                Loan loan = new Loan(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        LoanStatus.get(rs.getString(8))
                );
                loanList.add(loan);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return loanList;
    }

    public ArrayList<Person> initPerson() {
        ArrayList<Person> personList = new ArrayList<>();
        try {
            Connection ex = DBConnection.getConnection();
            Statement st = ex.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM person");
            while (rs.next()) {
                Person person = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                personList.add(person);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return personList;
    }

    public ArrayList<Books> initBooks() {
        ArrayList<Books> bookList = new ArrayList<>();
        try {
            Connection ex = DBConnection.getConnection();
            Statement st = ex.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                Books books = new Books(rs.getInt("book_id"), rs.getString("title"), rs.getString("authors"),
                        rs.getDouble("average_rating"), rs.getString("isbn"), rs.getDouble("isbn13"),
                        rs.getString("language_code"), rs.getInt("#num_pages"), rs.getInt("ratings_count"),
                        rs.getInt("text_reviews_count"), rs.getInt("quantity")
                );
                bookList.add(books);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bookList;
    }

    public void execute(String query) {
        try {
            Connection ex = DBConnection.getConnection();
            Statement st = ex.createStatement();
            st.execute(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
