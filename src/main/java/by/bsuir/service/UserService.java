package by.bsuir.service;

import by.bsuir.entity.UserEntity;
import by.bsuir.repository.UserRepository;
import reactor.core.publisher.Mono;
import by.bsuir.service_client.security.TokenData;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.bsuir.service_client.data.User;

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

    public Mono<UserEntity> add(final UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public Mono<String> login(UserEntity userEntity) {
        return add(userEntity).map(entity-> {
            if (entity != null) {
                return getToken(entity);
            }
            throw new RuntimeException("Error");
        });
    }

    public Mono<User> get(String id) {
       return userRepository.findById(id).map(UserEntity::toDto);
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
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