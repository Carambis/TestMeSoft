package by.bsuir.service;

import by.bsuir.entity.UserEntity;
import by.bsuir.repository.UserRepository;
import service_client.security.TokenData;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service_client.data.User;

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

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity add(final UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public String login(final String firstName, final String lastName, final String groupNumber) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setGroupNumber(groupNumber);
        userEntity = add(userEntity);
        if (userEntity != null) {
            return getToken(userEntity);
        }
        throw new RuntimeException("Error");
    }

    public User get(String id) {
        return userRepository.findOne(id).toDto();
    }

    public void deleteUser(String id){
        userRepository.delete(id);
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