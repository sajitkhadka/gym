package me.sajit.gym.workout_plan.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

/**
 * In built workout plan from which users can select a plan, It could be something like
 * 2 days split variation 1, 3 days split variant 1, 3 days split, Arnold Split etc
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredefinedPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    //how many workouts included in each repetition
    @Column
    int workoutPerCycle;

    //how often the workout will be repeated
    @Column
    int repeatCycle;

    @Column(length = 3000)
    String description;

    @OneToMany(mappedBy = "predefinedPlan", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JsonIgnoreProperties("predefinedPlan")
    List<PredefinedPlanSchedule> predefinedPlanSchedules;

//    public void setPredefinedPlanSchedules(List<PredefinedPlanSchedule> predefinedPlanSchedules) {
//        this.predefinedPlanSchedules = predefinedPlanSchedules;
//        predefinedPlanSchedules.forEach(schedule -> schedule.setPredefinedPlan(this));
//    }
}
