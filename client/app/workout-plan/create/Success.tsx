import React from 'react';

const Success = () => {
    return (
        <div className='mt-4'>
            <h2 className='text-2xl mt-2 text-green-700'>Success!</h2>
            <p className='mt-3 text-lg'>Yay! You have successfully created your plan! You can now go to your homepage and view your upcoming workouts.</p>
            <p className='mt-2 text-lg'>Happy working out!</p>

            <button className='button mt-4'>Go Home</button>
        </div>
    );
};

export default Success;