export enum DaysofWeek {
    'Monday' = 0, 'Tuesday' = 1, 'Wednesday' = 2, 'Thursday' = 3, 'Friday' = 4, 'Saturday' = 5, 'Sunday' = 6
}


export const getDaysOfWeek = (date: Date) => {
    const daysOfWeek: Array<keyof typeof DaysofWeek> = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    return daysOfWeek[date.getDay()];
}

export interface IDay { day: keyof typeof DaysofWeek, date: string, id: number }

export function getUpcomingDates(nDays: number, currentDate = new Date()): Array<IDay> {
    const upcomingDates: Array<IDay> = [];
    for (let i = 0; i < nDays; i++) {
        const nextDate = new Date(currentDate);
        nextDate.setDate(currentDate.getDate() + i);
        upcomingDates.push({
            day: getDaysOfWeek(nextDate),
            id: (i + 1),
            date: nextDate.toDateString()
        });
    }
    return upcomingDates;
}
