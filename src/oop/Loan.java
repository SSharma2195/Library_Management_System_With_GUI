package oop;

import server.LoanStatus;

import java.io.Serializable;

public class Loan implements Serializable {
    private int loanID;
    private int bookID;
    private int personID;
    private int loanPeriod;
    private String loanStart;
    private String loanEnd;
    private String returnDate;
    private LoanStatus status;

    public Loan(int loanID, int bookID, int personID, int loanPeriod, String loanStart, String loanEnd, String returnDate, LoanStatus status) {
        this.loanID = loanID;
        this.bookID = bookID;
        this.personID = personID;
        this.loanPeriod = loanPeriod;
        this.loanStart = loanStart;
        this.loanEnd = loanEnd;
        this.returnDate = returnDate;
        this.status = status;
    }

    public int getLoanID() {
        return loanID;
    }

    public int getBookID() {
        return bookID;
    }

    public int getPersonID() {
        return personID;
    }

    public int getLoanPeriod() {
        return loanPeriod;
    }

    public String getLoanStart() {
        return loanStart;
    }

    public String getLoanEnd() {
        return loanEnd;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}
