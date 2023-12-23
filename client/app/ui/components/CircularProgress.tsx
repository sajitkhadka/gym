import React, { useState, useEffect } from 'react';

const CircularProgress = () => {
    const [progress, setProgress] = useState(0);

    useEffect(() => {
        const interval = setInterval(() => {
            setProgress((prevProgress) => (prevProgress + 1) % 101); // Increment progress from 0 to 100
        }, 100); // Update every 100 milliseconds

        return () => clearInterval(interval); // Cleanup interval on component unmount
    }, []);

    return (
        <div className="flex flex-col items-center">
            <div className="relative w-24 h-24">
                <div
                    className="absolute top-0 left-0 w-full h-full bg-green-500 rounded-full"
                    style={{
                        clipPath: `polygon(50% 0%, 100% 50%, 50% 100%, 0% 50%, 50% 0%, ${progress <= 50 ? '100%' : '0%'} ${progress > 50 ? '100%' : '0%'})`
                    }}
                />
                <div
                    className="absolute top-0 left-0 w-full h-full bg-gray-300 rounded-full"
                    style={{
                        clipPath: `polygon(50% 0%, 100% 50%, 50% 100%, 0% 50%, 50% 0%, ${progress <= 50 ? '0%' : '100%'} ${progress > 50 ? '100%' : '0%'})`
                    }}
                />
            </div>
            <div className="mt-2 text-gray-600 text-lg">{`${progress}%`}</div>
        </div>
    );
};

export default CircularProgress;
