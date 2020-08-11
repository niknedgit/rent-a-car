package rent_a_car.dto;

import rent_a_car.model.Account;

public class AccountDto {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String role;

    public AccountDto(){}

    public AccountDto(Account account){
        this.id = account.getId();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.email = account.getEmail();
        this.name = account.getName();
        this.role = account.getRole();
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
