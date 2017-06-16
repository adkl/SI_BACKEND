package kjkpvik.services;

import kjkpvik.models.Korisnik;
import kjkpvik.models.Rola;
import kjkpvik.repositories.IKorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Adnan on 5/17/2017.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IKorisnikRepository korisnikRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Korisnik userAccount = korisnikRepository.findKorisnikByUsername(s);
        if(userAccount == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new User(userAccount.getUsername(), userAccount.getPassword(), getGrantedAuthorities(userAccount));
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(Korisnik user) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if(user.getRola() != null) {
            Rola role = user.getRola();
            authorities.add(new SimpleGrantedAuthority(role.getNaziv()));
        }
        return authorities;
    }
}