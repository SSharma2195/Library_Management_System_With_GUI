package client.listner;

import client.gui.AddBookPanel;
import client.gui.MainFrame;
import oop.Books;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddBookListener implements ActionListener {

    private final AddBookPanel frame;
    private ArrayList<Books> books;
    private int searchID = -1;
    private MainFrame mainFrame;

    public AddBookListener(AddBookPanel frame, ArrayList<Books> books, MainFrame mainFrame) {
        this.books = books;
        this.frame = frame;
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(frame.getAdd(obj)){
            add();
        }else if(frame.getUpdate(obj)){
            update();
        }else if(frame.getDelete(obj)){
            delete();
        }else if(frame.getSearch(obj)){
            search();
        }else if(frame.getReset(obj)){
            searchID = -1;
            frame.reset();
        }
    }

    private void search() {
        String id = JOptionPane.showInputDialog("Enter Book ID");
        if(id != null && id.matches("[0-9]+")){
            int id_ = Integer.parseInt(id);
            for(Books book:books){
                if(book.getBook_id() == id_){
                    searchID = id_;
                    frame.setText(book);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Not book found with given ID");
        }else{
            JOptionPane.showMessageDialog(null, "Enter Valid Book ID");
        }
    }

    private void deleteQuery() {
        String sql = "DELETE FROM books WHERE book_id = "+ searchID +";";
        mainFrame.execute(sql);
        JOptionPane.showMessageDialog(null, "Book deleted");
        mainFrame.setPanel("book");
        books = mainFrame.getClient().getBooks();
    }

    private void delete() {
        if(searchID != -1){
            deleteQuery();
            deleteUser(searchID);
            searchID = -1;
            frame.reset();
            return;
        }
        String id = JOptionPane.showInputDialog("Enter Book ID");
        if(id != null && id.matches("[0-9]+")){
            int id_ = Integer.parseInt(id);
            for(Books book:books){
                if(book.getBook_id() == id_){
                    deleteQuery();
                    deleteUser(id_);
                    searchID = -1;
                    frame.reset();
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Not book found with given ID");
        }else{
            JOptionPane.showMessageDialog(null, "Enter Valid Book ID");
        }
    }

    private void deleteUser(int id){
        for(int i=0;i<books.size(); i++){
            if(books.get(i).getBook_id() == id){
                books.remove(i);
            }
        }
    }

    private void update() {
        if(searchID != -1){
            if(frame.notEmpty() && frame.valid()) {
                String array[] = frame.getText();
                String sql = "UPDATE books SET title=\"" + array[0] + "\", authors=\"" + array[1] + "\", average_rating=" + array[2] +
                        ", isbn=\"" + array[3] + "\", isbn13=" + array[4] + ", language_code=\"" + array[5] + "\", \"#num_pages\"=" + array[6] +
                        ", ratings_count=" + array[7] + ", text_reviews_count=" + array[8] + ", quantity=" + array[9] + " WHERE book_id = "+searchID+";";
                mainFrame.execute(sql);
                JOptionPane.showMessageDialog(null, "Book update");
                mainFrame.setPanel("book");
                books = mainFrame.getClient().getBooks();
            }
        }else {
            JOptionPane.showMessageDialog(null, "No book selected");
        }
    }

    private void add() {
        if(frame.notEmpty() && frame.valid()){
            String array[] = frame.getText();
            String sql = "INSERT INTO books(title, authors, average_rating, isbn, isbn13, language_code, \"#num_pages\", ratings_count, text_reviews_count, quantity)\n" +
                    "VALUES (\""+array[0]+"\", \""+array[1]+"\", "+array[2]+", \""+array[3]+"\", "+array[4]+", \""+array[5]+"\", "+array[6]+", "+array[7]+", "+array[8]+", "+array[9]+");";
            mainFrame.execute(sql);
            JOptionPane.showMessageDialog(null, "Book added");
            mainFrame.setPanel("book");
            books = mainFrame.getClient().getBooks();
        }else{
            JOptionPane.showMessageDialog(null, "Data not valid");
        }
    }
}
