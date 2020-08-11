package rent_a_car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rent_a_car.model.Account;
import rent_a_car.repository.AccountRepository;
import rent_a_car.security.CustomAccountDetails;

import java.util.Optional;

@Service
public class CustomAccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(username);

        account
                .orElseThrow(()-> new UsernameNotFoundException("Username " + username + " not found"));
        return account
                .map(CustomAccountDetails::new).get();
    }
}
