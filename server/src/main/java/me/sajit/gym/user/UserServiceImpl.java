package me.sajit.gym.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.sajit.gym.user_plan.UserPlanRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserPlanRepository userPlanRepository;

//    @Override
//    public void getUsersNextRoutine() {
//
//    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById((long) userId).orElseThrow();
    }
}
