"use client"
import React, { useState } from 'react';
import { inter } from '@/app/ui/fonts';
import SelectDays from './SelectDays';
import ChoosePredefinedRoutine from './ChoosePredefinedRoutine';
import CustomizeRoutine from './CustomizeRoutine';
import { Predefinedplan } from '@/app/lib/api/predefined-plan';
import Success from './Success';

enum Steps {
    ChooseDay = 0,
    ChoosePredefinedRoutine = 1,
    CustomizeDays = 2,
    Success = 3,
}
const CreateWorkoutPlan = () => {
    const [step, setStep] = useState(Steps.ChooseDay);
    const [daysSplit, setDaysSplit] = useState(-1);
    const [selectedPlan, setSelectedPlan] = useState<Predefinedplan>();
    return (
        <div className="container mx-auto">
            {step > 0 && step <= 2 ? <button className='text-2xl mr-3' onClick={() => setStep(step => step - 1)}>❮</button> : null}<span className={`orange_gradient text-center text-2xl ${inter.className}`}> Create workout routine</span>
            {step == Steps.ChooseDay ? <SelectDays onSelect={(daysSplit) => {
                setDaysSplit(daysSplit)
                setStep(Steps.ChoosePredefinedRoutine)
            }} /> : step == Steps.ChoosePredefinedRoutine ?
                <ChoosePredefinedRoutine daysSplit={daysSplit} onChoose={(predefinedPlan) => { setSelectedPlan(predefinedPlan); setStep(Steps.CustomizeDays) }} /> :
                step == Steps.CustomizeDays ? <CustomizeRoutine predefinedPlan={selectedPlan} onSuccess={() => {
                    setStep(3);
                    setSelectedPlan(undefined);
                    setDaysSplit(-1);
                }} /> : step === Steps.Success ? <Success /> : null}
        </div>
    );
};

export default CreateWorkoutPlan;