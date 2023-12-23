
import CreateWorkoutPlanForm from '@/app/ui/workout-plans/CreateWorkoutPlanForm';
import React from 'react';

const CreateWorkoutPlan = () => {
    return (
        <div className='container mx-auto p-8 pt-0'>
            <h1 className="text-3xl font-semibold mb-4">Create Predefined Plan</h1>
            <CreateWorkoutPlanForm />
        </div>
    );
};

export default CreateWorkoutPlan;