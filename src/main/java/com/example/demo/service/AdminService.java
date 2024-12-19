package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // TODO: 4. find or save 예제 개선
    @Transactional
    public void reportUsers(List<Long> userIds) {

        // 모든 유저 엔티티를 한 번에 조회
        List<User> users = userRepository.findAllById(userIds);

        // users 크기와 userIds 크기가 다르면 예외 처리
        if (users.size() != userIds.size()) {
            throw new IllegalArgumentException("사용자 ID에 해당하는 값이 일부 존재하지 않습니다.");
        }

        // 각각의 사용자 상태를 업데이트
        for (User user : users) {
            user.updateStatusToBlocked();
        }
    }
}
