import { usePostRequest } from "./usePostRequest"

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