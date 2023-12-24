import useSWR from "swr";
import { usePostRequest } from "./usePostRequest"
import { APIResponse, fetcher } from "./fetcher";
import { WorkoutCategory } from "./predefined-plan";

export interface UserPlanScheduleDto {
    dayorder: number;
    workoutCategoryId: number;
}

export interface CreateUserPlanDto {
    planName: string;
    schedules: UserPlanScheduleDto[]
    predefinedPlanId?: number
}

export const useCreateRoutine = () => {
    return usePostRequest<CreateUserPlanDto>("http://localhost:8080/user-plan")
}

export interface WorkoutCategorySchedule {
    id: number;
    dayOrder: number;
    workoutCategory: WorkoutCategory
}

export interface WorkoutRoutine {
    createdDate: Date;
    active: boolean;
    name: string;
    categorySchedules: WorkoutCategorySchedule[]
}



export const useMyActivePlan = () => {
    const { data, error, isLoading } = useSWR<APIResponse<{ plan: WorkoutRoutine }>>("http://localhost:8080/user-plan", (url: string) => fetcher(url));
    return { data };
}