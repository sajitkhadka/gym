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


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredefinedPlanSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String scheduleName;

    @ManyToOne
    @JoinColumn(name = "predefined_plan_id", nullable = false)
    @JsonIgnoreProperties("predefinedPlanSchedules")
    private PredefinedPlan predefinedPlan;

    @OneToMany(mappedBy = "predefinedPlanSchedule", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("predefinedPlanSchedule")
    @Fetch(FetchMode.JOIN)
    private List<PredefinedPlanCategory> predefinedPlanCategories;

}
