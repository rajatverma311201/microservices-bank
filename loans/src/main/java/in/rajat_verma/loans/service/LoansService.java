package in.rajat_verma.loans.service;

import in.rajat_verma.loans.dto.LoanDto;

public interface LoansService {

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    void createLoan(String mobileNumber);

    /**
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    LoanDto fetchLoan(String mobileNumber);

    /**
     * @param loansDto - LoanDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateLoan(LoanDto loansDto);

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    boolean deleteLoan(String mobileNumber);


}
