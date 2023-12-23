// RestCard.tsx
import React from 'react';
import { useDrag } from 'react-dnd';

interface RestCardProps {
    id: string;
}

const RestCard: React.FC<RestCardProps> = ({ id }) => {
    const [, drag] = useDrag({
        type: 'REST_CARD',
        item: { id },
    });

    return (
        <div ref={drag} className="p-4 border mb-2 cursor-move">
            <h3 className="text-lg font-bold">Rest Day</h3>
            <p>{new Date().toLocaleDateString()}</p>
        </div>
    );
};

export default RestCard;
