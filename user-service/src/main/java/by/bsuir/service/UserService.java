package by.bsuir.service;

import by.bsuir.entity.UserEntity;
import by.bsuir.repository.UserRepository;
import service_client.security.TokenData;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service_client.data.User;
import service_client.data.request.UserCreation;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class UserService {

    @Value("${token.alive}")
    private int tokenDaysAlive;

    @Value("${token.key}")
    private String key;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    public User add(final UserCreation userCreation) {
        UserEntity user = new UserEntity();
        user.setFirstName(userCreation.firstName);
        user.setLastName(userCreation.lastName);
        return userRepository.save(user).toDto();
    }

    public String login(final String login, final String password) {
//        UserEntity user = userRepository.findById(id);
//        if (user != null) {
//            return getToken(user);
//        }
        throw new RuntimeException("Error");
    }

    public User get(Long id) {
        return userRepository.findOne(id).toDto();
    }

    public UserEntity getByLogin(final Long id) {
        return userRepository.findById(id);
    }

    private String getToken(final UserEntity user) {
        final Map<String, Object> tokenData = new HashMap<>();
        tokenData.put(TokenData.ID.getValue(), user.getId());
        tokenData.put(TokenData.FIRSTNAME.getValue(), user.getFirstName());
        tokenData.put(TokenData.LASTNAME.getValue(), user.getLastName());
        tokenData.put(TokenData.GROUPNUMBER.getValue(), user.getGroupNumber());
        Calendar calendar = Calendar.getInstance();
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        return jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
    }

}