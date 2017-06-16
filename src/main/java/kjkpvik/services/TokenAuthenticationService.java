package kjkpvik.services;


import io.jsonwebtoken.Jwts;
import kjkpvik.models.Korisnik;
import kjkpvik.repositories.IKorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


import static io.jsonwebtoken.SignatureAlgorithm.HS512;

/**
 * Created by Adnan on 5/15/2017.
 */
@Service
public class TokenAuthenticationService {


    private static IKorisnikRepository korisnikRepository;

    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletRequest req, HttpServletResponse res, String username) {
        ServletContext servletContext = req.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        korisnikRepository = webApplicationContext.getBean(IKorisnikRepository.class);

        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        res.addHeader("Role", korisnikRepository.findKorisnikByUsername(username)
                                                    .getRola()
                                                    .getNaziv());
    }

    public static Authentication getAuthentication(HttpServletRequest request) {

        ServletContext servletContext = request.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        korisnikRepository = webApplicationContext.getBean(IKorisnikRepository.class);

        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            Korisnik userAccount = korisnikRepository.findKorisnikByUsername(user);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            if(userAccount != null) {
                authorities.add(new SimpleGrantedAuthority(userAccount.getRola().getNaziv()));
            }

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, authorities) :
                    null;
        }
        return null;
    }
}
