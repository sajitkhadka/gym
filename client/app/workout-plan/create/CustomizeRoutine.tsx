import { Predefinedplan } from '@/app/lib/api/predefined-plan';
import CustomizeDropDownRoutine from './customize-routine';
import { UserPlanScheduleDto, useCreateRoutine } from '@/app/lib/api/workout-routine';
import { useEffect, useState } from 'react';


const CustomizeRoutine = ({ predefinedPlan, onSuccess }: { predefinedPlan?: Predefinedplan, onSuccess: () => void }) => {

    const { post, loading, error, success } = useCreateRoutine();
    const [userSchedule, setUserSchedule] = useState<UserPlanScheduleDto[]>([]);
    const [planName, setPlanName] = useState("");
    const isDisabled = !planName || !userSchedule.length;
    useEffect(() => {
        if (success) onSuccess();
    }, [success])
    if (!predefinedPlan?.predefinedPlanSchedules) return null;
    return (
        <div className='mb-20'>
            <p className='mt-4'>Customize the selected routine based on your daily schedule. Please drag and drop each exercise to make it more customized to your needs.</p>
            {error ? <p className='my-2 bg-red-400 py-2 px-4'>{error}</p> : null}
            <div className='mt-8'>
                <CustomizeDropDownRoutine
                    predefinedPlanSchedules={predefinedPlan?.predefinedPlanSchedules}
                    onChange={(newUserSchedule) => {
                        setUserSchedule(newUserSchedule)
                    }}
                />
                <legend className='mt-4'>
                    <label className='font-bold'>Name your schedule. You can put any name:</label>
                    <input className='input mt-2' value={planName} onChange={(e) => setPlanName(e.target.value)} placeholder="eg, Sajit's Daily workout" />
                </legend>
                <button disabled={isDisabled} className={`mt-5 button ${isDisabled ? "cursor-not-allowed bg-slate-600" : ""}`} onClick={() => {
                    post({ schedules: userSchedule, planName: planName, predefinedPlanId: predefinedPlan.id })
                }}>{loading ? "Submitting Please wait..." : "Create workout"}</button>
            </div>
        </div>
    );
};

export default CustomizeRoutine;