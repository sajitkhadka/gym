import useSWR from "swr";
import { APIResponse, fetcher } from "./fetcher";


export interface Predefinedplan {
    id: number;
    name: string;
    workoutPerCycle: number;
    repeatCycle: number;
    description: string;
    predefinedPlanSchedules: PredefinedPlanSchedule[];
}

export interface PredefinedPlanSchedule {
    id: number;
    scheduleName: string;
    predefinedPlanCategories: PredefinedPlanCategory[];
}

export interface PredefinedPlanCategory {
    id: number;
    workoutCategory: WorkoutCategory;
}

export interface WorkoutCategory {
    id: number;
    category: string;
}


export const usePredefinedPlans = (params: { workoutPerCycle: number }) => {
    const { data, error, isLoading } = useSWR<APIResponse<{ predefined_plans: Predefinedplan[]; }>>("http://localhost:8080/predefined-plan", (url: string) => fetcher(url, params));
    return { data };
}