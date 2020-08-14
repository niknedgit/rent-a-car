package rent_a_car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rent_a_car.model.Account;
import rent_a_car.repository.AccountRepository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@SpringBootApplication
public class Main {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    private void postConstruct() {

        Optional<Account> accountOptional = accountRepository.findByUsername("defaultAdmin");
        if (!accountOptional.isPresent())
        {
            Account account =
                    new Account("defaultAdmin", "default", "", "", "ADMIN");

            account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
            accountRepository.save(account);
        }
    }

    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }
}
