package br.com.erudio.restwithspringbootandjavaerudio.services;

import br.com.erudio.restwithspringbootandjavaerudio.data.vo.security.AccountCredentialsVO;
import br.com.erudio.restwithspringbootandjavaerudio.data.vo.security.TokenVO;
import br.com.erudio.restwithspringbootandjavaerudio.repositories.UserRepository;
import br.com.erudio.restwithspringbootandjavaerudio.security.Jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentialsVO data) {
        try {
            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            var user = userRepository.findByUsername(username);
            var tokenResponse = new TokenVO();
            if (user != null) {
                tokenResponse = jwtTokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username: " + username + " not found!");
            }
            System.out.println(user.getPassword());
            return ResponseEntity.ok(tokenResponse);

        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {


        var user = userRepository.findByUsername(username);
        var tokenResponse = new TokenVO();
        if (user != null) {
            tokenResponse = jwtTokenProvider.createRefreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username: " + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }
}
