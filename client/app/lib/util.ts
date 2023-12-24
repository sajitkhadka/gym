export enum DaysofWeek {
    'Monday' = 0, 'Tuesday' = 1, 'Wednesday' = 2, 'Thursday' = 3, 'Friday' = 4, 'Saturday' = 5, 'Sunday' = 6
}


export const getDaysOfWeek = (date: Date) => {
    const daysOfWeek: Array<keyof typeof DaysofWeek> = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    return daysOfWeek[date.getDay()];
}