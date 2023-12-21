package me.sajit.gym.user_plan;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.sajit.gym.user.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * user's plan more like a plan history. The current plan or the latest plan would be the active one
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    User user;

    @Column
    LocalDateTime createdDate;

    @Column
    boolean active;

    //Unique Name of the plan that user gives
    @Column
    String name;

    @OneToMany(mappedBy = "userPlan", cascade = CascadeType.ALL)
    private List<UserPlanCategorySchedule> categorySchedules;
}
