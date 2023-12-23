import useSWR from 'swr';
import useSWRMutation from 'swr/mutation'
import { APIResponse, fetcher } from './fetcher';
import { usePostRequest } from './usePostRequest';

export interface Category {
    id: number,
    category: string;
}

export const useWorkoutCategory = () => {
    const { data, error, isLoading } = useSWR<APIResponse<{ categories: Category[] }>>("http://localhost:8080/workout-category", fetcher);
    return { data };
}

interface AddWorkoutCategory {
    name: string;
    repeatCycle: number;
    workoutPerCycle: number;
    description: string;
    workoutSchedules: number[][]
}

export const useAddWorkoutCategory = () => {
    return usePostRequest<AddWorkoutCategory>("http://localhost:8080/predefined-plan")
}