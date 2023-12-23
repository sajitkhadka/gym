// WorkoutCard.tsx
import React from 'react';
import { useDrag } from 'react-dnd';

interface WorkoutCardProps {
    id: string;
    workout: string;
}

const WorkoutCard: React.FC<WorkoutCardProps> = ({ id, workout }) => {
    const [, drag] = useDrag({
        type: 'WORKOUT_CARD',
        item: { id },
    });

    return (
        <div ref={drag} className="p-4 border mb-2 cursor-move">
            <h3 className="text-lg font-bold">{workout}</h3>
            <p>{new Date().toLocaleDateString()}</p>
        </div>
    );
};

export default WorkoutCard;
