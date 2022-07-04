package client.gui;

import client.listner.AddBookListener;
import oop.Books;
import oop.Constants;

import javax.swing.*;
import java.util.ArrayList;

public class AddBookPanel extends JPanel {

    private JTextField title;
    private JTextField author;
    private JTextField avgRating;
    private JTextField isbn;
    private JTextField isbn13;
    private JTextField language;
    private JTextField pages;
    private JTextField ratingCount;
    private JTextField reviewCount;
    private JTextField quantity;
    private JButton add;
    private JButton delete;
    private JButton update;
    private JButton search;
    private JButton reset;

    public AddBookPanel(ArrayList<Books> books, MainFrame frame) {
        setBounds(1000, 0, Constants.ADDING_BOOK_PANEL_WIDTH, Constants.ADDING_BOOK_PANEL_HEIGHT);
        setLayout(null);

        title = new JTextField();
        author = new JTextField();
        avgRating = new JTextField();
        isbn = new JTextField();
        isbn13 = new JTextField();
        language = new JTextField();
        pages = new JTextField();
        ratingCount = new JTextField();
        reviewCount = new JTextField();
        quantity = new JTextField();

        title.setBounds(200, 90, 270, 40);
        author.setBounds(200, 135, 270, 40);
        avgRating.setBounds(200, 180, 270, 40);
        isbn.setBounds(200, 225, 270, 40);
        isbn13.setBounds(200, 270, 270, 40);
        language.setBounds(200, 315, 270, 40);
        pages.setBounds(200, 360, 270, 40);
        ratingCount.setBounds(200, 405, 270, 40);
        reviewCount.setBounds(200, 450, 270, 40);
        quantity.setBounds(200, 495, 270, 40);

        add(title);
        add(author);
        add(avgRating);
        add(isbn);
        add(isbn13);
        add(language);
        add(pages);
        add(ratingCount);
        add(reviewCount);
        add(quantity);

        AddBookListener listener = new AddBookListener(this, books, frame);

        add = new JButton("Add");
        add.setBounds(30, 550, 80, 30);
        add(add);
        add.addActionListener(listener);

        delete = new JButton("Delete");
        delete.setBounds(120, 550, 80, 30);
        add(delete);
        delete.addActionListener(listener);

        update = new JButton("Update");
        update.setBounds(210, 550, 80, 30);
        add(update);
        update.addActionListener(listener);

        search = new JButton("Search");
        search.setBounds(300, 550, 80, 30);
        add(search);
        search.addActionListener(listener);

        reset = new JButton("Reset");
        reset.setBounds(390, 550, 80, 30);
        add(reset);
        reset.addActionListener(listener);

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("./bookFrame.png"));
        background.setBounds(0, 0, Constants.ADDING_BOOK_PANEL_WIDTH, Constants.ADDING_BOOK_PANEL_HEIGHT - 20);
        add(background);
    }

    public boolean notEmpty() {
        return !title.getText().equals("") &&
                !author.getText().equals("") &&
                !avgRating.getText().equals("") &&
                !isbn.getText().equals("") &&
                !isbn13.getText().equals("") &&
                !language.getText().equals("") &&
                !pages.getText().equals("") &&
                !ratingCount.getText().equals("") &&
                !reviewCount.getText().equals("") &&
                !quantity.getText().equals("");
    }

    public boolean valid() {
        return avgRating.getText().matches("[0-9]+(.)?([0-9]+)?") &&
                isbn13.getText().matches("[0-9]+") && isbn13.getText().length() == 13 &&
                pages.getText().matches("[0-9]+") && ratingCount.getText().matches("[0-9]+") &&
                reviewCount.getText().matches("[0-9]+") && quantity.getText().matches("[0-9]+");
    }

    public String[] getText() {
        return new String[]{title.getText(), author.getText(), avgRating.getText(),
                isbn.getText(), isbn13.getText(), language.getText(), pages.getText(),
                ratingCount.getText(), reviewCount.getText(), quantity.getText()};
    }


    public boolean getAdd(Object obj) {
        return add.equals(obj);
    }

    public boolean getDelete(Object obj) {
        return delete.equals(obj);
    }

    public boolean getUpdate(Object obj) {
        return update.equals(obj);
    }

    public boolean getSearch(Object obj) {
        return search.equals(obj);
    }

    public boolean getReset(Object obj) {
        return reset.equals(obj);
    }

    public void setText(Books book) {
        title.setText(book.getTitle());
        author.setText(book.getAuthors());
        avgRating.setText(""+book.getAvgRating());
        isbn.setText(""+book.getIsbn());
        isbn13.setText("%.0f".formatted(book.getIsbn13()));
        language.setText(""+book.getLanguage_code());
        pages.setText(""+book.getPage());
        ratingCount.setText(""+book.getRatingCount());
        reviewCount.setText(""+book.getReviewCount());
        quantity.setText(""+book.getQuantity());
    }

    public void reset() {
        title.setText("");
        author.setText("");
        avgRating.setText("");
        isbn.setText("");
        isbn13.setText("");
        language.setText("");
        pages.setText("");
        ratingCount.setText("");
        reviewCount.setText("");
        quantity.setText("");
    }
}
