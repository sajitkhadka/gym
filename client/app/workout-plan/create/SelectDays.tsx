import React from 'react';
const Item = ({ children, onClick }: { children: React.ReactElement | string, onClick: () => void }) => <div className='rounded-md bg-lime-500 text-white w-32 h-20 flex justify-center items-center hover:bg-green-700 cursor-pointer' onClick={onClick}>{children}</div>

const SelectDays = ({ onSelect }: { onSelect: (count: number) => void }) => {
    return (
        <div>
            <p className='desc'> How many days would you like to workout each week?</p>
            <div className='flex gap-6 mt-10 flex-wrap'>
                <Item onClick={() => onSelect(1)}>One</Item>
                <Item onClick={() => onSelect(2)}>Two</Item>
                <Item onClick={() => onSelect(3)}>Three</Item>
                <Item onClick={() => onSelect(4)}>Four</Item>
                <Item onClick={() => onSelect(5)}>Five</Item>
                <Item onClick={() => onSelect(6)}>Six</Item>
                <Item onClick={() => onSelect(7)}>Seven</Item>
                <Item onClick={() => onSelect(0)}>Not sure?</Item>
            </div>
            <p className='mt-4'>Note: Choose "Not Sure" if you would like to have repeating cycle other than 7 days. For eg. If you want to do 5 days workout and 1 day rest and repeat the cycle then choose not sure.</p>
        </div>
    );
};

export default SelectDays;