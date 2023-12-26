"use client"
import React from 'react';
import { WorkoutCategorySchedule, useMyActivePlan } from '../lib/api/workout-routine';
import { DaysofWeek, getUpcomingDates } from '../lib/util';
import { REST_CATEGORY_ID } from '../workout-plan/create/customize-routine';

const page = () => {
    const { data: myActivePlan } = useMyActivePlan();
    const today = new Date();
    const currentDayOfWeek = today.getDay();


    const groupedSchedules: Record<number, WorkoutCategorySchedule[]> = {};
    myActivePlan?.data.plan.categorySchedules.forEach(schedule => {
        if (!groupedSchedules[schedule.dayOrder]) {
            groupedSchedules[schedule.dayOrder] = [];
        }
        groupedSchedules[schedule.dayOrder].push(schedule);
    });

    return (
        <section className='flex w-full flex-col gap-4 mb-20'>
            <p className='orange_gradient text-3xl font-bold'>My Workout Schedule</p>
            {getUpcomingDates(7).map(dayOffset => {
                const day = dayOffset.day;
                const daySchedules = groupedSchedules[DaysofWeek[day]];
                const formattedDate = new Intl.DateTimeFormat('en-US', { day: 'numeric', month: 'short' }).format(new Date(dayOffset.date));

                return (
                    <div key={day} className="flex-1 p-4 border-solid border-[1px] border-gray-700 rounded-md flex items-center gap-6">
                        <h2 className="text-xl font-bold">
                            {DaysofWeek[day]} - {formattedDate}
                        </h2>
                        <div className='flex'>
                            {daySchedules &&
                                daySchedules.map((schedule: WorkoutCategorySchedule) => (
                                    <div className={`rounded-md px-4 py-2 ${schedule.workoutCategory.id === REST_CATEGORY_ID ? "bg-green-500" : "bg-orange-500"}`}>
                                        <span key={schedule.id} className="font-bold text-white text-lg">{schedule.workoutCategory.category}</span>
                                    </div>
                                ))}
                        </div>
                    </div>
                );
            })}
        </section>
    );
};

export default page;