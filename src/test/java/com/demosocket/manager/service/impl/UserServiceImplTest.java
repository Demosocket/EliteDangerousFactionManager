//package com.demosocket.manager.service.impl;
//
//import com.demosocket.manager.dto.UserDto;
//import com.demosocket.manager.dto.UserEditDto;
//import com.demosocket.manager.model.User;
//import com.demosocket.manager.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//class UserServiceImplTest {
//
//    private static User testUser;
//
//    @Mock
//    private UserRepository userRepositoryMock;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @BeforeEach
//    public void beforeEach() {
//        testUser = User.builder().id(88L).username("Li3Hi_po3eTky").build();
//    }
//
//    @Test
//    void findByUsername_should_return_user() {
//        Mockito.when(userRepositoryMock.findByUsername("Li3Hi_po3eTky")).thenReturn(testUser);
//        assertEquals(testUser.getUsername(), userService.findByUsername("Li3Hi_po3eTky").getUsername());
//    }
//
//    @Test
//    void findAll_should_return_userList() {
//        List<User> userList = new ArrayList<>();
//        userList.add(testUser);
//        userList.add(User.builder().username("SecondUser").build());
//        Mockito.when(userRepositoryMock.findAll()).thenReturn(userList);
//        assertEquals(2, userRepositoryMock.findAll().size());
//    }
//
//    @Test
//    void findById_should_return_user() {
////        Mockito.when(userRepositoryMock.findById(88L)).thenReturn(Optional.of(testUser));
////        UserEditDto userEditDtoTest = modelMapper.map(testUser, UserEditDto.class);
////        assertEquals(userEditDtoTest, modelMapper.map(userService.findById(88L), UserEditDto.class));
//    }
//
//    @Test
//    void findById_should_return_null() {
////        Mockito.when(userRepositoryMock.findById(Mockito.anyLong())).thenReturn(null);
////        assertNull(userService.findById(Mockito.anyLong()));
//    }
//
//    @Test
//    void saveNewUser_should_return_true() {
////        Mockito.when(userRepositoryMock.save(testUser)).thenAnswer(true);
////        assertEquals(userService.saveNewUser(modelMapper.map(testUser), UserDto.class));
//    }
//
//    @Test
//    void saveUser() {
//    }
//}