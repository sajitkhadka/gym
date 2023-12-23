"use client"
import { useRouter } from 'next/navigation';
import React from 'react';
import { inter } from '../ui/fonts';

const page = () => {
    const router = useRouter();
    return (
        <div>
            <p className={`orange_gradient text-2xl ${inter.className}`}> My workout</p>
            <p className='desc'> You don't have a workout routine set up yet.</p>
            <div>
                <button className='text-white p-2 px-4 rounded-md min-w-[100px] bg-blue-500 mt-4' onClick={() => {
                    router.push("/workout-plan/create")
                }}>Create Workout Routine</button>
            </div>
        </div>
    );
};

export default page;