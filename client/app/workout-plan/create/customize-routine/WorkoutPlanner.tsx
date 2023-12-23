// WorkoutPlanner.tsx
import React, { useState } from 'react';
import { DndProvider, useDrag, useDrop } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';
import WorkoutCard from './WorkoutCard';
import RestCard from './RestCard';

interface Card {
    id: string;
    type: string;
    workout?: string;
}

const WorkoutPlanner: React.FC = () => {
    const [workouts, setWorkouts] = useState<Card[]>([
        { id: '1', type: 'WORKOUT_CARD', workout: 'Workout 1' },
        { id: '2', type: 'WORKOUT_CARD', workout: 'Workout 2' },
        { id: '3', type: 'WORKOUT_CARD', workout: 'Workout 3' },
        { id: '4', type: 'WORKOUT_CARD', workout: 'Workout 4' },
        { id: '5', type: 'WORKOUT_CARD', workout: 'Workout 5' },
        { id: '6', type: 'REST_CARD' },
        { id: '7', type: 'REST_CARD' },
    ]);

    const moveCard = (dragIndex: number, hoverIndex: number) => {
        const draggedCard = workouts[dragIndex];
        const updatedWorkouts = [...workouts];
        updatedWorkouts.splice(dragIndex, 1);
        updatedWorkouts.splice(hoverIndex, 0, draggedCard);
        setWorkouts(updatedWorkouts);
    };

    const [, drop] = useDrop({
        accept: ['WORKOUT_CARD', 'REST_CARD'],
        hover: (item: Card, monitor) => {
            if (!item || !drop) {
                return;
            }

            const dragIndex = item.id;
            const hoverIndex = monitor.getItem().id;

            if (dragIndex === hoverIndex) {
                return;
            }

            moveCard(Number(dragIndex), Number(hoverIndex));

            monitor.getItem().id = hoverIndex;
        },
    });

    return (
        <div ref={drop} className="flex flex-col">
            {workouts.map((card, index) => (
                <div key={card.id}>
                    {card.type === 'WORKOUT_CARD' ? (
                        <WorkoutCard id={card.id} workout={card.workout || ''} />
                    ) : (
                        <RestCard id={card.id} />
                    )}
                </div>
            ))}
        </div>
    );
};

export default WorkoutPlanner;
