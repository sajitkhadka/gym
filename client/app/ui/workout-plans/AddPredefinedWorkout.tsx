import { Category, useWorkoutCategory } from '@/app/lib/api/workout-category';
import { count } from 'console';
import React, { useState } from 'react';
import Autocomplete from '../components/AutoComplete';

const AddPredefinedWorkout = ({ index, onRemove, onChange, count }: { index: string, onRemove: (removeIndex: string) => void, onChange: (output: Category[]) => void, count: number }) => {
    const { data } = useWorkoutCategory();
    const categories = data?.data.categories;
    return (
        <div className="bg-gray-100 p-4 m-4 ml-0 rounded-md shadow-md border-solid border-gray-400 border-[1px]">
            <div className='flex justify-between'>
                <h2 className="text-xl font-bold mb-2">Day {count}</h2>
                <button onClick={() => onRemove(index)} className="text-red-500 font-bold">
                    Remove
                </button></div>
            {categories ? <Autocomplete
                items={data?.data.categories}
                getLabel={(category) => category.category}
                getId={(category) => category.id}
                isMultiSelect
                onChange={onChange}
            /> : null}

        </div>
    );
};

export default AddPredefinedWorkout;