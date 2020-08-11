package rent_a_car.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rent_a_car.model.Account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomAccountDetails extends Account implements UserDetails {

    public CustomAccountDetails(final Account account) {
        super(account);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> grantedAuthoritiesList=new ArrayList<SimpleGrantedAuthority>();
        grantedAuthoritiesList.add(new SimpleGrantedAuthority("ROLE_" + getRole()));
        return grantedAuthoritiesList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
