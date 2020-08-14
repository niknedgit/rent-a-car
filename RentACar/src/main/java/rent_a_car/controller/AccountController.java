package rent_a_car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rent_a_car.dto.AccountDto;
import rent_a_car.dto.BookingDto;
import rent_a_car.exception.BadRequestException;
import rent_a_car.exception.ResourceNotFoundException;
import rent_a_car.model.Account;
import rent_a_car.repository.AccountRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/secured/all")
    public List<AccountDto> getAll(){

        return accountRepository.findAll()
                .stream()
                .map(AccountDto::new)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/secured/{id}")
    public AccountDto getAccount(@PathVariable("id") @NotNull int id){

        Optional<Account> account = accountRepository.findById(id);
        account
                .orElseThrow(()-> new ResourceNotFoundException("Account not found with id " + id));
        return account.map(AccountDto::new).get();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping(path = "/secured/getMyAccount")
    public AccountDto getCurrentAccount(Principal principal){

        Optional<Account> account = accountRepository.findByUsername(principal.getName());
        account
                .orElseThrow(()-> new ResourceNotFoundException("Current account not found"));
        return account.map(AccountDto::new).get();
    }

    @PostMapping(path = "/addAccount")
    public AccountDto addAccount(@NotNull @Valid @RequestBody AccountDto accountDto){

        Optional<Account> accountOptional = accountRepository.findByUsername(accountDto.getUsername());

        if(accountOptional.isPresent()) throw new BadRequestException("Username already exist");
        else
        {
            Account account = new Account(accountDto.getUsername(), accountDto.getPassword(), accountDto.getEmail(),
                    accountDto.getName(), "CUSTOMER");

            account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
            return new AccountDto(accountRepository.save(account));
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(path = "/secured/addAccount")
    public AccountDto addSecureAccount(@NotNull @Valid @RequestBody AccountDto accountDto){

        Optional<Account> accountOptional = accountRepository.findByUsername(accountDto.getUsername());

        if(accountOptional.isPresent()) throw new BadRequestException("Username already exist");
        else
        {
            Account account = new Account(accountDto.getUsername(), accountDto.getPassword(), accountDto.getEmail(),
                    accountDto.getName(), "ADMIN");

            account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
            return new AccountDto(accountRepository.save(account));
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(path = "/secured/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable int id) {

        return accountRepository.findById(id)
                .map(account -> {
                    accountRepository.delete(account);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + id));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(path = "/secured/{accountId}")
    public AccountDto updateAccount(@PathVariable int accountId,
                              @Valid @RequestBody AccountDto accountRequest) throws Exception {

        Optional<Account> accountOptional = accountRepository.findByUsername(accountRequest.getUsername());

        if(accountOptional.isPresent()) throw new BadRequestException("Username already exist");

        return accountRepository.findById(accountId)
                .map(account -> {
                    account.setEmail(accountRequest.getEmail());
                    account.setName(accountRequest.getName());
                    account.setUsername(accountRequest.getUsername());
                    account.setPassword(bCryptPasswordEncoder.encode(accountRequest.getPassword()));
                    account.setRole(accountRequest.getRole());
                    return new AccountDto(accountRepository.save(account));
                }).orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + accountId));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/secured/{accountId}/bookings")
    public List<BookingDto> getBookingSet(@PathVariable int accountId){

        Optional<Account> accountOptional = accountRepository.findById(accountId);
        accountOptional
                .orElseThrow(()->new ResourceNotFoundException("Account not found with id " + accountId));
        return accountOptional.get().getBookingSet()
                .stream().map(BookingDto::new).collect(Collectors.toList());

    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping(path = "/secured/myBookings")
    public List<BookingDto> getMyBookingSet(Principal principal){

        Optional<Account> accountOptional = accountRepository.findByUsername(principal.getName());
        accountOptional
                .orElseThrow(()->new ResourceNotFoundException("Account not found with"));
        return accountOptional.get().getBookingSet()
                .stream().map(BookingDto::new).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/secured/getByUsername/{username}")
    public AccountDto getAccountByUsername(@PathVariable("username") @NotNull String username){

        Optional<Account> account = accountRepository.findByUsername(username);
        account
                .orElseThrow(()-> new ResourceNotFoundException("Account not found with username " + username));
        return account.map(AccountDto::new).get();
    }
}
