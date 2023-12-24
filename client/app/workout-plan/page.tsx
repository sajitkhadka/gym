"use client"
import { useRouter } from 'next/navigation';
import React from 'react';
import { inter } from '../ui/fonts';
import { useMyActivePlan } from '../lib/api/workout-routine';
import { DaysofWeek, getDaysOfWeek } from '../lib/util';

const page = () => {
    const router = useRouter();
    const { data: myActivePlan } = useMyActivePlan();
    const today = getDaysOfWeek(new Date());
    const todaysWorkouts = myActivePlan?.data.plan.categorySchedules?.filter((plan) => plan.dayOrder === DaysofWeek[today]);
    return (
        <div>
            <p className={`orange_gradient text-2xl ${inter.className}`}> My workout</p>
            {todaysWorkouts?.length ? <div>
                {
                    todaysWorkouts.map((workout) => (<div>
                        {workout.workoutCategory.category}
                    </div>))
                }

            </div> : <div>
                <p className='desc'> You don't have a workout routine set up yet.</p>
                <div>
                    <button className='text-white p-2 px-4 rounded-md min-w-[100px] bg-blue-500 mt-4' onClick={() => {
                        router.push("/workout-plan/create")
                    }}>Create Workout Routine</button>
                </div>
            </div>}
        </div>
    );
};

export default page;