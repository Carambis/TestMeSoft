package by.bsuir.service_client.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.authentication.AuthenticationServiceException;

public class TokenService {

    private String key;

    public void setKey(String key) {
        this.key = key;
    }


    public TokenAuthentication parseAndCheckToken(final String token) {
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        } catch (Exception ex) {
            throw new AuthenticationServiceException("Token corrupted");
        }

        String id = claims.get(TokenData.ID.getValue(), String.class);
        String firstName = claims.get(TokenData.FIRSTNAME.getValue(), String.class);
        String lastName = claims.get(TokenData.LASTNAME.getValue(), String.class);
        String groupNumber = claims.get(TokenData.GROUPNUMBER.getValue(), String.class);

        TokenUser user = new TokenUser(id, firstName, lastName, groupNumber);

        return new TokenAuthentication(token, true, user);
    }
}