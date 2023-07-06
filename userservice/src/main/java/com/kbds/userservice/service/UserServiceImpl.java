package com.kbds.userservice.service;

import com.kbds.userservice.client.EwalletServiceClient;
import com.kbds.userservice.dto.UserDto;
import com.kbds.userservice.jpa.UserEntity;
import com.kbds.userservice.jpa.UserRepository;
import com.kbds.userservice.vo.RequestEwallet;
import com.kbds.userservice.vo.ResponseEwallet;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    private EwalletServiceClient ewalletServiceClient;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, EwalletServiceClient ewalletServiceClient) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.ewalletServiceClient = ewalletServiceClient;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
//        userDto.setUserId(UUID.randomUUID().toString());
        ModelMapper mapper = new ModelMapper();
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));
        userRepository.save(userEntity);

        UserDto returnUserDto = mapper.map(userEntity, UserDto.class);
        RequestEwallet ewallet = new RequestEwallet();
        ewallet.setUserId(userDto.getUserId());
        ewallet.setEwalletId(userDto.getUserId());
        ewallet.setAmt(BigDecimal.valueOf(0));
        ResponseEwallet responseEwallet = ewalletServiceClient.createEwallet(ewallet);

        log.info("responseEwallet ::::::::::::::::::  " + responseEwallet.toString());
        return returnUserDto;
    }

    @Override
    public UserDto userLogin(UserDto userDto) {
        ModelMapper mapper = new ModelMapper();
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));
        userEntity = userRepository.findByUserIdAndPwd(userEntity.getUserId(), userEntity.getPwd());

        UserDto returnUserDto = mapper.map(userEntity, UserDto.class);
        return returnUserDto;
    }

    @Override
    public UserDto getUserDetailsByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null)
            throw new UsernameNotFoundException(userId);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(userEntity, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) {

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null)
            throw new UsernameNotFoundException(userId + ": not found");

        return new User(userEntity.getUserId(), userEntity.getEncryptedPwd(),
                true, true, true, true,
                new ArrayList<>());
    }
}
