import { Predefinedplan } from '@/app/lib/api/predefined-plan';
import React from 'react';
import WorkoutPlanner from './customize-routine/WorkoutPlanner';
import { DndProvider, useDrag, useDrop } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';

const CustomizeRoutine = ({ predefinedPlan }: { predefinedPlan?: Predefinedplan }) => {
    return (
        <div>
            <p className='mt-4'>Customize the selected routine based on your daily schedule. </p>
            <DndProvider backend={HTML5Backend}>
                <WorkoutPlanner />
            </DndProvider>
        </div>
    );
};

export default CustomizeRoutine;