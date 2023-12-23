import React from 'react';
import { inter } from '../ui/fonts';

const layout = ({
    children,
}: {
    children: React.ReactNode;
}) => {
    return (
        <div className="container mx-auto">

            {children}
        </div>
    );
};

export default layout;