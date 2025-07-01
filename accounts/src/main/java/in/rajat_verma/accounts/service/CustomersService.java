package in.rajat_verma.accounts.service;

import in.rajat_verma.accounts.dto.CustomerDetailsDto;

public interface CustomersService {

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
