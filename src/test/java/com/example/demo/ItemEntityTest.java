package com.example.demo;

import com.example.demo.entity.Item;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ItemEntityTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("status 값이 null이면 예외가 발생하는지 확인(아이템 엔티티에서 다이나믹 인서트 관련 주석 비활성화하여 테스트)")
    @Transactional
    void nullStatusFail() {
        User owner = userRepository.save(new User(Role.USER.getName(), "kkk123@ggg.com", "Owner", "qwer123"));
        User manager = userRepository.save(new User(Role.USER.getName(), "qqq123@aaa.com", "Manger", "asdf123"));

        Item item = new Item("robotA", "figure", owner, manager);
        item.setStatus(null);

        assertThrows(Exception.class, () -> itemRepository.save(item));
    }


}
