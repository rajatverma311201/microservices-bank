package in.rajat_verma.accounts.service.impl;

import in.rajat_verma.accounts.dto.AccountDto;
import in.rajat_verma.accounts.dto.CardDto;
import in.rajat_verma.accounts.dto.CustomerDetailsDto;
import in.rajat_verma.accounts.dto.LoanDto;
import in.rajat_verma.accounts.entity.Account;
import in.rajat_verma.accounts.entity.Customer;
import in.rajat_verma.accounts.exception.ResourceNotFoundException;
import in.rajat_verma.accounts.mapper.AccountMapper;
import in.rajat_verma.accounts.mapper.CustomerMapper;
import in.rajat_verma.accounts.repository.AccountsRepository;
import in.rajat_verma.accounts.repository.CustomersRepository;
import in.rajat_verma.accounts.service.CustomersService;
import in.rajat_verma.accounts.service.feign_client.CardsFeignClient;
import in.rajat_verma.accounts.service.feign_client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements CustomersService {


    private AccountsRepository accountsRepository;
    private CustomersRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Account account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

        ResponseEntity<LoanDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoanDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;

    }

}
