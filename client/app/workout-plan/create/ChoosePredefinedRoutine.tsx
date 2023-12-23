import { PredefinedPlanCategory, PredefinedPlanSchedule, Predefinedplan, usePredefinedPlans } from '@/app/lib/api/predefined-plan';
import React, { useMemo } from 'react';

const PredefinedPlanCategoryItem = ({ schedule }: { schedule: PredefinedPlanSchedule }) => {
    return (
        <li className="flex items-center mt-2">
            {/* <span className="mr-2">{schedule.scheduleName}</span> */}
            <div className="flex space-x-2">
                {schedule.predefinedPlanCategories.map((category) => (
                    <span key={category.id} className="bg-gray-200 rounded-full px-2 py-1 text-sm">
                        {category.workoutCategory.category}
                    </span>
                ))}
            </div>
        </li>
    );
};


const Card = ({ children, onChoose }: { children: React.ReactNode, onChoose: () => void }) => {
    return <div className="bg-white rounded-md shadow-md p-6 m-4 flex justify-between flex-col border-[1px] border-solid border-gray-500">
        <div>
            {children}
        </div>
        <button className='mt-4 rounded-md bg-blue-400 text-white px-4 py-2 hover:bg-blue-600' onClick={onChoose}>Choose</button>
    </div>
}

const ChoosePredefinedRoutine = ({ daysSplit, onChoose }: { daysSplit: number, onChoose: (plan: Predefinedplan) => void }) => {
    const blurb = useMemo(() => {
        switch (daysSplit) {
            case 1:
            case 2:
            case 3:
            case 4:
                return "Choosing the right workout split is crucial for optimal results. Opt for 4-day split if you seek a balance between intensity and recovery. This plan strategically targets major muscle groups, allowing ample rest for optimal growth and preventing burnout. If time constraints or beginner-level fitness are considerations, 4-day split offers a manageable commitment. However, if you're aiming for more frequent training or have specific performance goals, explore our other split options."
            case 5:
                return "Embark on a dynamic fitness journey with 5-day workout split, ideal for those seeking a more immersive and varied training experience. This plan optimally distributes exercises across the week, ensuring targeted muscle engagement and consistent progression. If you're committed to a slightly higher frequency of workouts and have the time for a more diverse routine, the 5-day split is your go-to choice. However, for those with time constraints or just starting, our 4-day split may be a more manageable option. Select your preferred workout split based on your personal fitness goals and lifestyle for a tailored and effective fitness regimen."
            case 6:
            case 7:
        }
    }, [daysSplit])
    const { data } = usePredefinedPlans({ workoutPerCycle: daysSplit });
    const predefinedPlans = data?.data.predefined_plans;
    return (
        <div>
            <p className='mt-4'>{blurb}</p>
            <p className='desc'>Choose a predefined routine or build a custom {daysSplit} split routine</p>
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-2 xl:grid-cols-2 gap-4">
                {predefinedPlans?.map((plan) => (
                    <Card onChoose={() => {
                        onChoose(plan);
                    }} key={plan.id}>
                        <div>
                            <h2 className="text-xl font-semibold">{plan.name}</h2>
                            <p className="text-gray-600">{plan.description}</p>
                            <div className="mt-4">
                                <ul>
                                    {plan.predefinedPlanSchedules.map((schedule) => (
                                        <PredefinedPlanCategoryItem key={schedule.id} schedule={schedule} />
                                    ))}
                                </ul>
                            </div>
                        </div>
                    </Card>
                ))}
                <Card onChoose={() => {

                }}>
                    <div>
                        <h2 className="text-xl font-semibold">Build custom plan</h2>
                        <p className="text-gray-600">You can build your own split that suits your needs.</p>
                    </div>
                </Card>
            </div>
        </div>
    );
};

export default ChoosePredefinedRoutine;